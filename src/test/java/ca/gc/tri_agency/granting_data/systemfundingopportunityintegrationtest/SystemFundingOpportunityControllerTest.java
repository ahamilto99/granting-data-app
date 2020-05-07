package ca.gc.tri_agency.granting_data.systemfundingopportunityintegrationtest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.transaction.Transactional;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.service.SystemFundingOpportunityService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GrantingDataApp.class)
@ActiveProfiles("test")
public class SystemFundingOpportunityControllerTest {

	@Autowired
	private SystemFundingOpportunityService sfoService;

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(ctx).apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}

	@Transactional
	@Rollback
	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void test_adminCanUnlinkFoFromSfo_shouldRedirectToViewSystemFo() throws Exception {
		assertNotNull(sfoService.findSystemFundingOpportunityById(1L).getLinkedFundingOpportunity());

		mvc.perform(MockMvcRequestBuilders.post("/admin/confirmUnlink").param("sfoId", "1"))
				.andExpect(MockMvcResultMatchers.flash().attributeCount(1))
				.andExpect(MockMvcResultMatchers.flash().attributeExists("actionMessage"))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/admin/viewSFO?id=1"));

		assertNull(sfoService.findSystemFundingOpportunityById(1L).getLinkedFundingOpportunity());

		// when the page is refreshed, the flash attribute should disappear
		mvc.perform(MockMvcRequestBuilders.get("/admin/confirmUnlink").param("sfoId", "1"))
				.andExpect(MockMvcResultMatchers.flash().attributeCount(0));
	}

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void test_unlinkFoConfirmationPageAccessableByAdmin_shouldSucceedWith200() throws Exception {
		String sfoName = sfoService.findSystemFundingOpportunityById(1L).getNameEn();
		String foName = sfoService.findSystemFundingOpportunityById(1L).getLinkedFundingOpportunity().getNameEn();
		assertTrue(mvc.perform(MockMvcRequestBuilders.get("/admin/confirmUnlink").param("sfoId", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString()
				.contains("Are you sure you want to unlink the System Funding Opportunity named " + sfoName
						+ " from the Funding Opportunity named " + foName + '?'));
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_unlinkFoConfirmationPageNotAccessableByNonAdminUsers_shouldReturn403() throws Exception {
		assertNotNull(sfoService.findSystemFundingOpportunityById(1L).getLinkedFundingOpportunity());
		assertTrue(mvc.perform(MockMvcRequestBuilders.get("/admin/confirmUnlink").param("sfoId", "1"))
				/*
				 * If status().isForbidden() is changed to status().isOk() then this test will pass but is that what
				 * you want?
				 */
				.andExpect(MockMvcResultMatchers.status().isForbidden()).andReturn().getResponse().getContentAsString()
				.contains("id=\"forbiddenByRoleErrorPage\""));
	}

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void test_confirmUnlinkPageNotAccessibleWhenSfoHasNoLinkedFo_shouldReturn404() throws Exception {
		assertNull(sfoService.findSystemFundingOpportunityById(2L).getLinkedFundingOpportunity());
		assertTrue(mvc.perform(MockMvcRequestBuilders.get("/admin/confirmUnlink").param("sfoId", "2"))
				.andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn().getResponse().getContentAsString()
				.contains("id=\"generalErrorPage\""));
	}

	@WithMockUser(roles = { "MDM ADMIN" })
	@Test
	public void test_unlinkFoBtnNotVisibleToAdminWhenSfoHasNoLinkedFo() throws Exception {
		assertNull(sfoService.findSystemFundingOpportunityById(2L).getLinkedFundingOpportunity());
		assertFalse(mvc.perform(MockMvcRequestBuilders.get("/admin/viewSFO").param("id", "2"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString()
				.contains("id=\"unlinkSfoBtn\""));
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_unlinkFoBtnNotVisibleToNonAdminUsers() throws Exception {
		assertNotNull(sfoService.findSystemFundingOpportunityById(1L).getLinkedFundingOpportunity());
		assertFalse(mvc.perform(MockMvcRequestBuilders.get("/admin/viewSFO").param("id", "1"))
				.andExpect(MockMvcResultMatchers.status().isForbidden()).andReturn().getResponse().getContentAsString()
				.contains("id=\"unlinkSfoBtn\""));
	}

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void test_unlinkFoBtnVisibleToAdminWhenFoLinkedToSfo() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/admin/viewSFO").param("id", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("id=\"unlinkSfoBtn\"")));
	}

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void test_adminCanAccessAnalyzeSystemFOsPage_shouldSucceedWith200() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/admin/analyzeSFOs")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString("id=\"analyzeSystemFundingOpportunitiesPage\"")));
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotAccessAnalyzeSystemFundingOpportunitiesPage_shouldReturn403() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/admin/analyzeSFOs")).andExpect(MockMvcResultMatchers.status().isForbidden())
				.andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString("id=\"forbiddenByRoleErrorPage\"")));
	}

}
