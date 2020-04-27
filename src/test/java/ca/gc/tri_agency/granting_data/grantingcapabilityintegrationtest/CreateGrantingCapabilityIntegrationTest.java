package ca.gc.tri_agency.granting_data.grantingcapabilityintegrationtest;

import static org.junit.Assert.assertEquals;

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
import ca.gc.tri_agency.granting_data.model.GrantingCapability;
import ca.gc.tri_agency.granting_data.repo.GrantingCapabilityRepository;
import ca.gc.tri_agency.granting_data.service.GrantingCapabilityService;

@SpringBootTest(classes = GrantingDataApp.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("local")
public class CreateGrantingCapabilityIntegrationTest {

	@Autowired
	private GrantingCapabilityService gcService;
	
	@Autowired
	private GrantingCapabilityRepository gcRepo;

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(ctx).apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}

	@WithMockUser(username = "admin", roles = "MDM ADMIN")
	@Test
	public void test_createGCLinkVisibleToAdmin_shouldSucceedWith200() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/manage/manageFo").param("id", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString("id=\"createGrantingCapabilityLink\"")));
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_createGCLinkNotVisibleToNonAdmin_shouldReturn200() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/manage/manageFo").param("id", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content()
						.string(Matchers.not(Matchers.containsString("id=\"createGrantingCapabilityLink\""))));
	}

	@WithMockUser(username = "admin", roles = "MDM ADMIN")
	@Test
	public void test_adminCanAccessCreateGCPage_shouldSucceedWith200() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/manage/addGrantingCapabilities").param("foId", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString("id=\"createGrantingCapabilityPage\"")));
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotAccessCreateGCPage_shouldReturn403() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/manage/addGrantingCapabilities").param("foId", "1"))
				.andExpect(MockMvcResultMatchers.status().isForbidden()).andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString("id=\"forbiddenByRoleErrorPage\"")));
	}

	@WithMockUser(username = "admin", roles = "MDM ADMIN")
	@Test
	public void test_adminCanCreateGC_shouldSucceedWith302() throws Exception {
		long initGCCount = gcRepo.count();

		String description = RandomStringUtils.randomAlphabetic(50);
		String url = RandomStringUtils.randomAlphabetic(25);

		mvc.perform(MockMvcRequestBuilders.post("/manage/addGrantingCapabilities").param("foId", "1")
				.param("description", description).param("url", url).param("fundingOpportunity", "1")
				.param("grantingStage", "1").param("grantingSystem", "1"))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/browse/viewFo?id=1")).andExpect(MockMvcResultMatchers
						.flash().attribute("actionMsg", "Successfully Added New Granting Capability"));

		mvc.perform(MockMvcRequestBuilders.get("/browse/viewFo").param("id", "1"))
				.andExpect(MockMvcResultMatchers.flash().attributeCount(0));

		long newGCCount = gcRepo.count();
		assertEquals(initGCCount + 1, newGCCount);

		GrantingCapability newGc = gcService.findAllGrantingCapabilities().get((int) newGCCount - 1);
		assertEquals(description, newGc.getDescription());
		assertEquals(url, newGc.getUrl());
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotCreateGC_shouldReturn403() throws Exception {
		long initGCCount = gcRepo.count();

		mvc.perform(MockMvcRequestBuilders.post("/manage/addGrantingCapabilities").param("foId", "1")
				.param("description", "test").param("url", "www.test.com").param("fundingOpportunity", "1")
				.param("grantingStage", "1").param("grantinSystem", "1"))
				.andExpect(MockMvcResultMatchers.status().isForbidden());

		assertEquals(initGCCount, gcRepo.count());
	}

}
