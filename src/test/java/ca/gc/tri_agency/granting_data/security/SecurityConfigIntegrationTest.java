package ca.gc.tri_agency.granting_data.security;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;

@SpringBootTest(classes = GrantingDataApp.class)
@ActiveProfiles("test")
public class SecurityConfigIntegrationTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	@Test
	public void givenAnonymousRequestOnHomeUrl_shouldBeOk200() throws Exception {
		mvc.perform(get("/home").contentType(MediaType.APPLICATION_XHTML_XML)).andExpect(status().isOk());
	}

	@Test
	public void givenAnonymousRequestOnAdminUrl_shouldRedirectToLogin302() throws Exception {
		mvc.perform(get("/admin/home").contentType(MediaType.APPLICATION_XHTML_XML))
				.andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
	}

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void givenAdminAuthRequestOnAdminUrl_shouldSucceedWith200() throws Exception {
		mvc.perform(get("/admin/home").contentType(MediaType.APPLICATION_XHTML_XML)).andExpect(status().isOk());
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER", "nserc-user-edi" })
	@Test
	public void nonAdminUserCannotAccessEditFOPage_shouldBeForbidden() throws Exception {
		String mockResponse = mvc.perform(get("/manage/editFo").param("id", "26")).andExpect(status().isForbidden())
				.andReturn().getResponse().getContentAsString();
		assertTrue(mockResponse.contains("id=\"forbiddenByRoleErrorPage\""),
				"Non-admin users should not be able to access the \"Edit Funding Opportunity\" page");
	}
	
	@WithMockUser(roles = { "MDM ADMIN", "NSERC_USER", "SSHRC_USER", "AGENCY_USER", "nserc-user-edi" })
	@Test
        public void signOutButtonVisibleOnlyForAuthenticatedUsers() throws Exception {
                ResultActions resAction = mvc.perform(get("/home")).andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().string(containsString(">Sign Out</a>")));
                assertFalse(resAction.andReturn().getResponse().getContentAsString().contains("Sign In"));
        }
    
        @WithAnonymousUser
        @Test
        public void signInButtonVisibleOnlyForUnauthenticatedUsers() throws Exception {
                ResultActions resAction = mvc.perform(get("/home")).andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().string(containsString(">Sign In</a>")));
                assertFalse(resAction.andReturn().getResponse().getContentAsString().contains("Sign Out"));
        }

}
