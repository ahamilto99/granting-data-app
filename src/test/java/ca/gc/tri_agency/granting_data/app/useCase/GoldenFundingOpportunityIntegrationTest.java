package ca.gc.tri_agency.granting_data.app.useCase;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.controller.FundingOpportunityController;
import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.BusinessUnit;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.repo.FundingOpportunityRepository;
import ca.gc.tri_agency.granting_data.repo.SystemFundingOpportunityRepository;
import ca.gc.tri_agency.granting_data.service.AgencyService;
import ca.gc.tri_agency.granting_data.service.BusinessUnitService;
import ca.gc.tri_agency.granting_data.service.FundingOpportunityService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GrantingDataApp.class)
@ActiveProfiles("test")
public class GoldenFundingOpportunityIntegrationTest {

	@Autowired
	private FundingOpportunityController foController;

	@Autowired
	private AgencyService agencyService;

	@Autowired
	private FundingOpportunityRepository foRepo;

	@Autowired
	SystemFundingOpportunityRepository sfoRepo;

	@Autowired
	private BusinessUnitService businessUnitService;
	@Autowired
	private FundingOpportunityService foService;

	@Autowired
	private WebApplicationContext ctx;

	@Mock
	private BindingResult bindingResult;
	@Mock
	private Model model;
	@Mock
	private RedirectAttributes redirectAttributes;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(ctx).apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}

	@WithMockUser(username = "mock_admin", roles = { "MDM ADMIN" })
	@Test
	public void testNameFieldsEmptyOnAddFoPageWhenNewSfoNotLinkedWithSfo() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/admin/createFo")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
				MockMvcResultMatchers.content().string(Matchers.containsString("name=\"nameEn\" value=\"\"")));
	}

	@WithMockUser(username = "mock_admin", roles = { "MDM ADMIN" })
	@Test
	public void testNameFieldsAutoFilledOnAddFoPageWhenLinkingNewFoWithSfo() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/admin/createFo").param("sfoId", "4"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString("value=\"Insight Grants\"")));
	}

	@WithMockUser(username = "mock_agency_user", roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_NonAdminCannotCreateGoldenFo_shouldFailWith403() throws Exception {
		boolean cpx = true;
		String div = RandomStringUtils.randomAlphabetic(10);
		boolean edi = true;
		String frequency = RandomStringUtils.randomAlphabetic(10);
		String ft = RandomStringUtils.randomAlphabetic(10);
		boolean loi = true;
		boolean noi = true;
		boolean ji = true;
		String nameEn = RandomStringUtils.randomAlphabetic(25);
		String nameFr = RandomStringUtils.randomAlphabetic(25);
		String po = RandomStringUtils.randomAlphabetic(25);
		String pld = RandomStringUtils.randomAlphabetic(25);
		String pln = RandomStringUtils.randomAlphabetic(25);
		List<Agency> agencyList = agencyService.findAllAgencies();
		Agency la = agencyList.size() > 0 ? agencyList.remove(0) : null;
//		Set<Agency> pas = agencyList.size() > 0 ? new HashSet<>(agencyList) : null;

		List<FundingOpportunity> fos = foRepo.findAll();
		String idParam = String.valueOf(fos.get(fos.size() - 1).getId() + 1L);

		mvc.perform(MockMvcRequestBuilders.post("/admin/createFo").param("id", idParam).param("nameEn", nameEn)
				.param("nameFr", nameFr).param("leadAgency", Long.toString(la.getId())).param("division", div)
				.param("isJointInitiative", Boolean.toString(ji)).param("fundingType", ft).param("partnerOrg", po)
				.param("frequency", frequency).param("isComplex", Boolean.toString(cpx))
				.param("isEdiRequired", Boolean.toString(edi)).param("isNOI", Boolean.toString(noi))
				.param("isLOI", Boolean.toString(loi)).param("programLeadName", pln).param("programLeadDn", pld))
				.andExpect(MockMvcResultMatchers.status().isForbidden()).andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString("id=\"forbiddenByRoleErrorPage\"")));
	}

	@WithMockUser(username = "mock_admin", roles = "MDM ADMIN")
	@Test
	public void test_AdminCanCreateGoldenFo_usingMvcPerform_shouldSucceed() throws Exception {
		boolean cpx = true;
		boolean edi = true;
		String frequency = RandomStringUtils.randomAlphabetic(10);
		String ft = RandomStringUtils.randomAlphabetic(10);
		boolean loi = true;
		boolean noi = true;
		boolean ji = true;
		String nameEn = RandomStringUtils.randomAlphabetic(25);
		String nameFr = RandomStringUtils.randomAlphabetic(25);
		String po = RandomStringUtils.randomAlphabetic(25);
		String pld = RandomStringUtils.randomAlphabetic(25);
		String pln = RandomStringUtils.randomAlphabetic(25);
		List<Agency> agencyList = agencyService.findAllAgencies();
		Agency la = agencyList.size() > 0 ? agencyList.remove(0) : null;
		List<BusinessUnit> businessUnitList = businessUnitService.findAllBusinessUnits();
		BusinessUnit leadBu = businessUnitList.size() > 0 ? businessUnitList.remove(0) : null;

		List<FundingOpportunity> fos = foRepo.findAll();
		String idParam = String.valueOf(fos.get(fos.size() - 1).getId() + 1L);

		mvc.perform(MockMvcRequestBuilders.post("/admin/createFo").param("id", idParam).param("nameEn", nameEn)
				.param("nameFr", nameFr).param("leadAgency", Long.toString(la.getId()))
				.param("businessUnit", Long.toString(leadBu.getId())).param("isJointInitiative", Boolean.toString(ji))
				.param("fundingType", ft).param("partnerOrg", po).param("frequency", frequency)
				.param("isComplex", Boolean.toString(cpx)).param("isEdiRequired", Boolean.toString(edi))
				.param("isNOI", Boolean.toString(noi)).param("isLOI", Boolean.toString(loi))
				.param("programLeadName", pln).param("programLeadDn", pld))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/admin/home")).andExpect(MockMvcResultMatchers.flash()
						.attribute("actionMessage", "Created Funding Opportunity named: " + nameEn));

		// when the page is refreshed, the flash attribute should disappear
		mvc.perform(MockMvcRequestBuilders.get("/admin/home")).andExpect(MockMvcResultMatchers.flash().attributeCount(0));

		fos = foRepo.findAll();
		FundingOpportunity newGfo = fos.get(fos.size() - 1);

		assertEquals(idParam, String.valueOf(newGfo.getId()));
		assertEquals(cpx, newGfo.getIsComplex());
		assertEquals(leadBu, newGfo.getBusinessUnit());
		assertEquals(edi, newGfo.getIsEdiRequired());
		assertEquals(frequency, newGfo.getFrequency());
		assertEquals(ft, newGfo.getFundingType());
		assertEquals(loi, newGfo.getIsLOI());
		assertEquals(noi, newGfo.getIsNOI());
		assertEquals(ji, newGfo.getIsJointInitiative());
		assertEquals(la.getId(), newGfo.getLeadAgency().getId());
		assertEquals(nameEn, newGfo.getNameEn());
		assertEquals(nameFr, newGfo.getNameFr());
		assertEquals(po, newGfo.getPartnerOrg());
		assertEquals(pld, newGfo.getProgramLeadDn());
		assertEquals(pln, newGfo.getProgramLeadName());
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test(expected = AccessDeniedException.class)
	public void test_NonAdminCannotCreateGoldenFo_shouldThrowDataAccessException() throws Exception {
		foController.createFundingOpportunityPost(new FundingOpportunity(), bindingResult, model, redirectAttributes);
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test(expected = AccessDeniedException.class)
	public void test_NonAdminCannotCreateGoldenFo_shouldThrowAccessDeniedException() {
		foService.saveFundingOpportunity(new FundingOpportunity());
	}

	@WithMockUser(username = "mock_admin", roles = { "MDM ADMIN" })
	@Test
	@Transactional
	public void test_AdminCanCreateGoldenFo_shouldSucceed() throws Exception {
		long initFoRepoSize = foRepo.count();

		FundingOpportunity gfo = new FundingOpportunity();
		gfo.setIsComplex(false);
		gfo.setIsEdiRequired(false);
		gfo.setFrequency(RandomStringUtils.randomAlphabetic(10));
		gfo.setFundingType(RandomStringUtils.randomAlphabetic(10));
		gfo.setIsLOI(false);
		gfo.setIsNOI(false);
		gfo.setIsJointInitiative(true);
		gfo.setNameEn(RandomStringUtils.randomAlphabetic(25));
		gfo.setNameFr(RandomStringUtils.randomAlphabetic(25));
		gfo.setPartnerOrg(RandomStringUtils.randomAlphabetic(25));
		gfo.setProgramLeadDn(RandomStringUtils.randomAlphabetic(25));
		gfo.setProgramLeadName(RandomStringUtils.randomAlphabetic(25));
		gfo.setLeadAgency(agencyService.findAgencyById(1L));

		String successUrl = foController.createFundingOpportunityPost(gfo, bindingResult, model, redirectAttributes);

		assertEquals("redirect:/admin/home", successUrl);
		assertEquals(initFoRepoSize + 1, foRepo.count());
	}

}