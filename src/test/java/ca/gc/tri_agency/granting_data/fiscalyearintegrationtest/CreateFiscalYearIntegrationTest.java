package ca.gc.tri_agency.granting_data.fiscalyearintegrationtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
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
import ca.gc.tri_agency.granting_data.repo.FiscalYearRepository;
import ca.gc.tri_agency.granting_data.service.FiscalYearService;

@SpringBootTest(classes = GrantingDataApp.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Ignore(value = "The FiscalYear functionality is not required for Version 1")
public class CreateFiscalYearIntegrationTest {

	@Autowired
	private FiscalYearService fyService;

	@Autowired
	private FiscalYearRepository fyRepo;

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(ctx).apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void test_createFYLinkVisibleToAdmin_shouldSucceedWith200() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/browse/viewFYs")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
				MockMvcResultMatchers.content().string(Matchers.containsString("id=\"createFiscalYearLink\"")));
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_createFYLinkNotVisibleToNonAdmin_shouldReturn200() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/browse/viewFYs")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.string(Matchers.not(Matchers.containsString("id=\"createFiscalYearLink\""))));
	}

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void test_adminCanAccessCreateFYPage_shouldSucceedWith200() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/manage/createFY")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(
				MockMvcResultMatchers.content().string(Matchers.containsString("id=\"createFiscalYearPage\"")));
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotAccessCreateFYPage_shouldReturn403() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/manage/createFY")).andExpect(MockMvcResultMatchers.status().isForbidden())
				.andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString("id=\"forbiddenByRoleErrorPage\"")));
	}

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Rollback
	@Test
	public void test_adminCanCreateFY_shouldSucceedWith302() throws Exception {
		long initFYCount = fyRepo.count();

		String year = "2025";

		mvc.perform(MockMvcRequestBuilders.post("/manage/createFY").param("year", year))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/browse/viewFYs"));

		assertTrue(fyService.findFiscalYearByYear(new Long(year)).isPresent());
		assertEquals(initFYCount + 1, fyRepo.count());
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotCreateFY_shouldReturn403() throws Exception {
		long initFYCount = fyRepo.count();

		mvc.perform(MockMvcRequestBuilders.post("/manage/createFY").param("year", "2025"))
				.andExpect(MockMvcResultMatchers.status().isForbidden()).andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString("id=\"forbiddenByRoleErrorPage\"")));

		assertEquals(initFYCount, fyRepo.count());
	}

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void test_createFormValidation_shouldReturn200() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/manage/createFY").param("year", String.valueOf(Integer.MAX_VALUE)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString("Year must be between 1999 and 2050")));
	
		mvc.perform(MockMvcRequestBuilders.post("/manage/createFY").param("year", "2020"))
		.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content()
				.string(Matchers.containsString("That year already exists")));
	}
}
