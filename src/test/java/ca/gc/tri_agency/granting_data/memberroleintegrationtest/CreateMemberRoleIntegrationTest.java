package ca.gc.tri_agency.granting_data.memberroleintegrationtest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.repo.MemberRoleRepository;

@SpringBootTest(classes = GrantingDataApp.class)
@ActiveProfiles("test")
public class CreateMemberRoleIntegrationTest {

	@Autowired
	private MemberRoleRepository mrRepo;

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mvc;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(ctx).apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}

	@Tag("user_story_19187")
	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void test_adminCanCreateMemberRole_shouldSucceedWith302() throws Exception {
		// verify "Create Member Role" button is visible to an admin
		mvc.perform(MockMvcRequestBuilders.get("/browse/viewBU").param("id", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString("id=\"createMemberRoleLink\"")));
		
		// verify an admin can access createMR page
		mvc.perform(MockMvcRequestBuilders.get("/admin/createMR").param("buId", "1"))
		.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content()
				.string(Matchers.containsString("id=\"createMemberRolePage\"")));
		
		// verify an admin can create a MemberRole
		long initMRCount = mrRepo.count();

		mvc.perform(MockMvcRequestBuilders.post("/admin/createMR").param("buId", "1").param("businessUnit", "1")
				.param("searchStr", "user").param("role", "2").param("userLogin", "adm").param("ediAuthorized", "1"))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/browse/viewBU?id=1"))
				.andExpect(MockMvcResultMatchers.flash().attribute("actionMsg",
						Matchers.is("Successfully Created Member Role For: adm")));

		mvc.perform(MockMvcRequestBuilders.get("/browse/viewBU").param("id", "1"))
				.andExpect(MockMvcResultMatchers.flash().attributeCount(0));

		assertEquals(initMRCount + 1, mrRepo.count());
	}

	@Tag("user_story_19187")
	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void testController_nonAdminCannotCreateMR_shouldReturn403() throws Exception {
		// verify "Create Member Role" button not visible to a non-admin 
		mvc.perform(MockMvcRequestBuilders.get("/browse/viewBU").param("id", "1"))
		.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content()
				.string(Matchers.not(containsString("id=\"createMemberRoleLink\""))));
		
		// verify createMR page not accessible by a non-admin
		mvc.perform(MockMvcRequestBuilders.get("/admin/createMR").param("buId", "1"))
		.andExpect(MockMvcResultMatchers.status().isForbidden()).andExpect(MockMvcResultMatchers.content()
				.string(Matchers.containsString("id=\"forbiddenByRoleErrorPage\"")));
		
		// verify non-admin cannot create a MemberRole
		long initMRCount = mrRepo.count();

		mvc.perform(MockMvcRequestBuilders.post("/admin/createMR").param("buId", "1").param("businessUnit", "1")
				.param("searchStr", "user").param("userLogin", "adm").param("role", "2").param("ediAuthorized", "1"))
				.andExpect(MockMvcResultMatchers.status().isForbidden()).andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString("id=\"forbiddenByRoleErrorPage\"")));

		assertEquals(initMRCount, mrRepo.count());
	}
}
