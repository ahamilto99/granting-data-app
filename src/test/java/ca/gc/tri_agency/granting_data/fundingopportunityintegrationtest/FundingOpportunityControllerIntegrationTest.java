package ca.gc.tri_agency.granting_data.fundingopportunityintegrationtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.repo.FundingOpportunityRepository;
import ca.gc.tri_agency.granting_data.service.FundingOpportunityService;

@SpringBootTest(classes = GrantingDataApp.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class FundingOpportunityControllerIntegrationTest {

	@Autowired
	private FundingOpportunityService foService;

	@Autowired
	private FundingOpportunityRepository foRepo;

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(ctx).apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}

	@WithMockUser(username = "mock_admin", roles = "MDM ADMIN")
	@Test
	public void test_adminCanAccessEditFOPage_shouldSucceedWith200() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/manage/editFo").param("id", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString("id=\"editFundingOpportunityPage\"")));
	}

	@WithMockUser(username = "mock_agency_user", roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotAccessEditFOPage_shouldReturn403() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/manage/editFo").param("id", "1"))
				.andExpect(MockMvcResultMatchers.status().isForbidden()).andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString("id=\"forbiddenByRoleErrorPage\"")));
	}

	@WithMockUser(username = "mock_admin", roles = "MDM ADMIN")
	@Test
	public void test_adminCanEditFundingOpportunity_shouldSucceedWith302() throws Exception {
		long initFOCount = foRepo.count();

		String nameFr = RandomStringUtils.randomAlphabetic(25);
		String frequency = RandomStringUtils.randomAlphabetic(10);

		mvc.perform(MockMvcRequestBuilders.post("/manage/editFo").param("id", "1").param("programLeadName", "Jennifer Mills")
				.param("frequency", frequency).param("nameFr", nameFr)
				.param("nameEn", "Collaborative Health Research Projects (CHRP) (5640)").param("leadAgency", "3")
				.param("division", "MCT").param("_isJointInitiative", "on").param("_isComplex", "on")
				.param("_isNOI", "on").param("_isLOI", "on").param("_participatingAgencies", "1"))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/browse/viewFo?id=1"));

		assertEquals(initFOCount, foRepo.count());
		assertEquals(nameFr, foService.findFundingOpportunityById(1L).getNameFr());
		assertEquals(frequency, foService.findFundingOpportunityById(1L).getFrequency());
	}

	@WithMockUser(username = "mock_agency_user", roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotEditFundingOpportunity_shouldReturn403() throws Exception {
		long initFOCount = foRepo.count();

		String nameFr = RandomStringUtils.randomAlphabetic(25);
		String frequency = RandomStringUtils.randomAlphabetic(10);

		mvc.perform(MockMvcRequestBuilders.post("/manage/editFo").param("id", "1").param("programLeadName", "Jennifer Mills")
				.param("frequency", frequency).param("nameFr", nameFr)
				.param("nameEn", "Collaborative Health Research Projects (CHRP) (5640)").param("leadAgency", "3")
				.param("division", "MCT").param("_isJointInitiative", "on").param("_isComplex", "on")
				.param("_isNOI", "on").param("_isLOI", "on").param("_participatingAgencies", "1"))
				.andExpect(MockMvcResultMatchers.status().isForbidden()).andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString("id=\"forbiddenByRoleErrorPage\"")));

		assertEquals(initFOCount, foRepo.count());
		assertNotEquals(nameFr, foService.findFundingOpportunityById(1L).getNameFr());
		assertNotEquals(frequency, foService.findFundingOpportunityById(1L).getFrequency());

	}

	@WithMockUser(username = "mock_admin", roles = "MDM ADMIN")
	@Test
	public void test_adminCanEditProgramLead_shouldSucceedWith302() throws Exception {
		long initFOCount = foRepo.count();
		String newProgramLead = "uid=sshrc-admin,ou=SSHRC_Users";

		assertNotEquals(newProgramLead, foService.findFundingOpportunityById(3L).getProgramLeadDn());

		mvc.perform(MockMvcRequestBuilders.post("/manage/editProgramLead").param("foId", "3").param("leadUserDn",
				newProgramLead)).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/browse/viewFo?id=3"));

		assertEquals(newProgramLead, foService.findFundingOpportunityById(3L).getProgramLeadDn());
		assertEquals(initFOCount, foRepo.count());
	}

	@WithMockUser(username = "mock_agency_user", roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotEditProgramLead_shouldReturn403() throws Exception {
		String originalProgramLead = foService.findFundingOpportunityById(3L).getProgramLeadDn();

		mvc.perform(MockMvcRequestBuilders.post("/manage/editProgramLead").param("foId", "3").param("leadUserDn",
				"uid=nserc1-user,ou=NSERC_Users")).andExpect(MockMvcResultMatchers.status().isForbidden())
				.andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString("id=\"forbiddenByRoleErrorPage\"")));

		assertEquals(originalProgramLead, foService.findFundingOpportunityById(3L).getProgramLeadDn());
	}

}
