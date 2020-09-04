package ca.gc.tri_agency.granting_data.memberroleintegrationtest;

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
public class DeleteMemberRoleIntegrationTest {

	@Autowired
	private MemberRoleRepository mrRepo;

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mvc;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(ctx).apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}

	@Tag("user_story_19193")
	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void test_adminCanDeleteMemberRole_shouldSucceedWith302() throws Exception {
		// verifies "Delete" button is visible to an admin
		mvc.perform(MockMvcRequestBuilders.get("/browse/viewBU").param("id", "1")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("id=\"deleteMemberRole\"")));

		// verifies that an admin can delete a MemberRole
		long initMRCount = mrRepo.count();

		mvc.perform(MockMvcRequestBuilders.get("/browse/viewBU").param("mrId", "3"))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/browse/viewBU?id=1"))
				.andExpect(MockMvcResultMatchers.flash().attribute("actionMsg", "Successfully Deleted Member Role For: rwi"));

		mvc.perform(MockMvcRequestBuilders.get("/browse/ViewBU").param("id", "1"))
				.andExpect(MockMvcResultMatchers.flash().attributeCount(0));

		assertEquals(initMRCount - 1, mrRepo.count());
	}

	@Tag("user_story_19193")
	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotDelete_shouldReturn403() throws Exception {
		// verifies that the "Delete" button is not visible to a non-admin
		mvc.perform(MockMvcRequestBuilders.get("/browse/viewBU").param("id", "1")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.string(Matchers.not(Matchers.containsString("id=\"deleteMemberRole\""))));

		// verifies a non-admin cannot delete a MemberRole
		long initMRCount = mrRepo.count();

		mvc.perform(MockMvcRequestBuilders.get("/browse/viewBU").param("mrId", "1"))
				.andExpect(MockMvcResultMatchers.status().isForbidden())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("id=\"forbiddenByRoleErrorPage\"")));

		assertEquals(initMRCount, mrRepo.count());
	}

}
