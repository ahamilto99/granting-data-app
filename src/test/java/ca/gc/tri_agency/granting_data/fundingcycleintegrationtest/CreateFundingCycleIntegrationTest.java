package ca.gc.tri_agency.granting_data.fundingcycleintegrationtest;

import static org.junit.Assert.assertEquals;

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
import ca.gc.tri_agency.granting_data.repo.FundingCycleRepository;

@SpringBootTest(classes = GrantingDataApp.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class CreateFundingCycleIntegrationTest {

	@Autowired
	private FundingCycleRepository fcRepo;

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(ctx).apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}

	@WithMockUser(username = "mock_admin", roles = { "MDM ADMIN" })
	@Test
	public void test_adminCanAccessCreateFCPage_shouldSucceedWith200() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/manage/createFundingCycle").param("foId", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString("id=\"createFundingCyclePage\"")));
	}

	@WithMockUser(username = "mock_agency_user", roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotAccessCreateFCPage_shouldReturn403() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/manage/createFundingCycle").param("foId", "1"))
				.andExpect(MockMvcResultMatchers.status().isForbidden()).andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString("id=\"forbiddenByRoleErrorPage\"")));
	}

	@WithMockUser(username = "mock_admin", roles = { "MDM ADMIN" })
	@Test
	public void test_adminCanCreateFC_shouldSucceedWith302() throws Exception {
		long initFCCount = fcRepo.count();

		mvc.perform(MockMvcRequestBuilders.post("/manage/createFundingCycle").param("foId", "1").param("fiscalYear", "1")
				.param("open", "true").param("expectedApplications", "123").param("fundingOpportunity", "1"))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/browse/viewFo?id=1"));

		assertEquals(initFCCount + 1, fcRepo.count());
	}

	@WithMockUser(username = "mock_agency_user", roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotCreateFC_shouldReturn403() throws Exception {
		long initFCCount = fcRepo.count();

		mvc.perform(MockMvcRequestBuilders.post("/manage/createFundingCycle").param("foId", "1")
				.param("fundingOpportunity", "1").param("expectedApplications", "123").param("open", "true"))
				.andExpect(MockMvcResultMatchers.status().isForbidden()).andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString("id=\"forbiddenByRoleErrorPage\"")));

		assertEquals(initFCCount, fcRepo.count());
	}

}
