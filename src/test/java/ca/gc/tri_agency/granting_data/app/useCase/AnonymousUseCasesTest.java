package ca.gc.tri_agency.granting_data.app.useCase;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.repo.FundingOpportunityRepository;

@SpringBootTest(classes = GrantingDataApp.class)
@ActiveProfiles("test")
public class AnonymousUseCasesTest {

	@Autowired
	private WebApplicationContext ctx;

	@Autowired
	private FundingOpportunityRepository foRepo;

	private MockMvc mvc;

	@BeforeEach
	public void setupJUnit5() {
		mvc = MockMvcBuilders.webAppContextSetup(ctx).apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}

	@Tag("user_story_14594")
	@WithAnonymousUser
	@Test
	public void test_anonUserCanViewFieldsOnViewFoPage_shouldSucceedWith200() throws Exception {
		mvc.perform(get("/browse/viewFo").param("id", "1")).andExpect(status().isOk())
				.andExpect(content().string(containsString("id=\"nameRow\"")))
				.andExpect(content().string(containsString("id=\"fundingTypeRow\"")));
	}

	@Tag("user_story_14594")
	@WithAnonymousUser
	@Test
	public void test_anonUserCanViewFoPageInEnglish_shouldSucceed200() throws Exception {
		mvc.perform(get("/browse/viewFo").param("id", "1").param("lang", "en")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Collaborative Health Research Projects (CHRP) (5640)")));
	}

	@Tag("user_story_14594")
	@WithAnonymousUser
	@Test
	public void test_anonUserCanViewFoPageInFrench_shouldSucceed200() throws Exception {
		mvc.perform(get("/browse/viewFo").param("id", "3").param("lang", "fr")).andExpect(status().isOk())
				.andExpect(content().string(containsString(
						"Chaires en génie de la conception")));
	}

	@Tag("user_story_14594")
	@Tag("user_story_14750")
	@WithAnonymousUser
	@Test
	public void test_anonUserCanAccessViewFoPage_shouldSucceedWith200() throws Exception {
		String response = mvc.perform(get("/browse/viewFo").param("id", "100")).andExpect(status().isOk())
				.andExpect(content().string(containsString("id=\"viewFundingOpportunityPage\"")))
				.andReturn().getResponse().getContentAsString();
		
		// verify the linked FCs table appears
		int numFCs = 0;
		
		Pattern regex = Pattern.compile("<a href=\"\\/browse\\/viewFcFromFy\\?fyId=");
		Matcher regexMatcher = regex.matcher(response);
		while (regexMatcher.find()) {
			++numFCs;
		}
		
		assertEquals(1, numFCs, "The FCs table should contain 1 linked FC for FO id=100");
	}

	@Tag("user_story_14627")
	@WithAnonymousUser
	@Test
	public void test_anonUserCanAccessViewGoldenListPage_shouldSucceedWith200() throws Exception {
		mvc.perform(get("/browse/fundingOpportunities")).andExpect(status().isOk())
				.andExpect(content().string(containsString("id=\"fundingOpportunitiesPage\"")));
	}

	@Tag("user_story_14627")
	@WithAnonymousUser
	@Test
	public void test_anonUserCanViewAllFosOnGoldenListPage_shouldSucceedWith200() throws Exception {
		long numFos = foRepo.count();
		String response = mvc.perform(get("/browse/fundingOpportunities")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString("id=\"fundingOpportunitiesPage\"")))
				.andReturn().getResponse().getContentAsString();
		Pattern responseRegex = Pattern.compile("<tr>");
		long numRows = responseRegex.splitAsStream(response).filter(str -> str.contains("</tr>")).count();
		// b/c the web page contains 1 table where 1 row contains the table cell headers
		// and every other row contains a FO,
		// the number of FOs should equal the number of rows in the table minus 1
		assertEquals(numFos, numRows - 1L);
	}

}
