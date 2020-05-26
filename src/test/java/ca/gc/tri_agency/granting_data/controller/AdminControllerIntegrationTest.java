package ca.gc.tri_agency.granting_data.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.repo.FundingOpportunityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GrantingDataApp.class)
@ActiveProfiles("test")
public class AdminControllerIntegrationTest {

	@Autowired
	private WebApplicationContext context;
	@Autowired
	private FundingOpportunityRepository foRepo;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminUserCannotAddFundingOpportunities_shouldFailWith403() throws Exception {
		long numFos = foRepo.count();

		mvc.perform(post("/admin/createFo").param("id", "26").param("nameEn", "ACE").param("nameFr", "BDF")
				.param("leadAgency", "3").param("division", "Q").param("isJointIntiative", "false")
				.param("_isJointIntiative", "on").param("partnerOrg", "Z").param("isComplex", "false")
				.param("_isComplex", "on").param("isEdiRequired", "false").param("_isEdiRequired", "on")
				.param("fundingType", "E").param("frequency", "Once").param("isNOI", "false").param("_isNOI", "on")
				.param("isLOI", "false").param("_isLOI", "on")).andExpect(status().isForbidden())
				.andExpect(content().string(containsString("id=\"forbiddenByRoleErrorPage\"")));

		// verify that a FO was not added
		assertEquals(numFos, foRepo.count());
	}

	@WithAnonymousUser
	@Test
	public void test_anonUserCannotAddFundingOpportunities_shouldRedirectToLoginWith302() throws Exception {
		long numFos = foRepo.count();

		mvc.perform(post("/admin/createFo").param("id", "26").param("nameEn", "A").param("nameFr", "B")
				.param("leadAgency", "3").param("division", "Q").param("isJointIntiative", "false")
				.param("_isJointIntiative", "on").param("partnerOrg", "Z").param("isComplex", "false")
				.param("_isComplex", "on").param("isEdiRequired", "false").param("_isEdiRequired", "on")
				.param("fundingType", "E").param("frequency", "Once").param("isNOI", "false").param("_isNOI", "on")
				.param("isLOI", "false").param("_isLOI", "on")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("http://localhost/login"));

		// verify that a FO was not added
		assertEquals(numFos, foRepo.count());
	}

	@WithMockUser(roles = { "MDM ADMIN" })
	@Test
	public void test_onlyAdminCanAddFundingOpportunities_shouldSucceedWith302() throws Exception {
		long numFos = foRepo.count();

		mvc.perform(post("/admin/createFo").param("nameEn", "ABC").param("nameFr", "BCD").param("leadAgency", "3")
				.param("division", "Q").param("isJointIntiative", "false").param("_isJointIntiative", "on")
				.param("partnerOrg", "Z").param("isComplex", "false").param("_isComplex", "on")
				.param("isEdiRequired", "false").param("_isEdiRequired", "on").param("fundingType", "E")
				.param("frequency", "Once").param("isNOI", "false").param("_isNOI", "on").param("isLOI", "false")
				.param("_isLOI", "on")).andDo(MockMvcResultHandlers.print()).andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/admin/home"));

		// verify that a FO was added
		assertEquals(numFos + 1, foRepo.count());
	}

	@WithAnonymousUser
	@Test
	public void givenAnonymousRequestOnAdminUrl_shouldFailWith301() throws Exception {
		mvc.perform(get("/admin/home").contentType(MediaType.APPLICATION_XHTML_XML)).andExpect(status().is3xxRedirection());
	}

	@WithMockUser(username = "sshrc-user", roles = { "SSHRC" })
	@Test
	public void givenSshrcRequestOnAdminUrl_shouldFailWithForbiddenByRoleError() throws Exception {
		mvc.perform(get("/admin/home").contentType(MediaType.APPLICATION_XHTML_XML))
				.andExpect(content().string(containsString("id=\"forbiddenByRoleErrorPage\"")));
	}

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void givenAdminAuthRequestOnAdminUrl_shouldSucceedWith200() throws Exception {
		mvc.perform(get("/admin/home").contentType(MediaType.APPLICATION_XHTML_XML)).andExpect(status().isOk());
	}

}
