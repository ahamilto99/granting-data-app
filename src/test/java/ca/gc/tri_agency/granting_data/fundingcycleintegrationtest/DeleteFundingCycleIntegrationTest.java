package ca.gc.tri_agency.granting_data.fundingcycleintegrationtest;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.repo.FundingCycleRepository;
import ca.gc.tri_agency.granting_data.service.FundingCycleService;

@SpringBootTest(classes = GrantingDataApp.class)
@ActiveProfiles("test")
public class DeleteFundingCycleIntegrationTest {

	@Autowired
	private FundingCycleService fcService;

	@Autowired
	private FundingCycleRepository fcRepo;

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mvc;

	private final String MANAGE_FO_URL = "/manage/manageFo";

	private final String DELETE_FC_URL = "/manage/deleteFC";

	private final String DEL_BTN_HALF_1 = "<a class=\"btn btn-primary\" href=\"" + DELETE_FC_URL + "?id=";

	private final String DEL_BTN_HALF_2 = "\">Delete</a>";

	private final String DELETE_PAGE_ID = "id=\"deleteFundingCyclePage\"";

	private final String FLASH_ATTRIBUTE = "Successfully Deleted Funding Cycle";

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(ctx).apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}

	@Tag("user_story_19213")
	@WithMockUser(username = "dev")
	@Test
	public void test_buProgramOfficeCanDeleteFC_shouldSucceedWith302() throws Exception {
		long initFCCount = fcRepo.count();

		String fcId = "67";
		String foId = "67";

		// verify "Delete" button is visible for Program Officer
		mvc.perform(MockMvcRequestBuilders.get(MANAGE_FO_URL).param("id", foId)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString(DEL_BTN_HALF_1 + fcId + DEL_BTN_HALF_2)));

		// verify Program Officer can access the deleteFC page
		mvc.perform(MockMvcRequestBuilders.get(DELETE_FC_URL).param("id", fcId)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(DELETE_PAGE_ID)));

		// verify Program Officer can delete a FundingCycle
		Assertions.assertTrue(
				mvc.perform(MockMvcRequestBuilders.post(DELETE_FC_URL).param("id", fcId))
						.andExpect(MockMvcResultMatchers.status().isFound())
						.andExpect(MockMvcResultMatchers.redirectedUrl(MANAGE_FO_URL + "?id=" + foId)).andReturn()
						.getFlashMap().containsValue(FLASH_ATTRIBUTE),
				"Flash attribute for deletion message is missing");

		Assertions.assertEquals(initFCCount - 1, fcRepo.count());
		Assertions.assertThrows(DataRetrievalFailureException.class, () -> fcService.findFundingCycleById(new Long(fcId)),
				"FundingCycle (id=" + fcId + ") was not deleted");
	}

	@Tag("user_story_19213")
	@WithMockUser(username = "dev")
	@Test
	public void test_buProgramOfficerCannotDeleteFC_shouldReturn403() throws Exception {
		long initFCCount = fcRepo.count();

		String fcId = "68";
		String foId = "68";

		// verify "Delete" button is not visible to a Program Officer
		mvc.perform(MockMvcRequestBuilders.get(MANAGE_FO_URL).param("id", foId)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.string(Matchers.not(Matchers.containsString(DEL_BTN_HALF_1 + fcId + DEL_BTN_HALF_2))));

		// verify Program Officer cannot access the deleteFC page
		mvc.perform(MockMvcRequestBuilders.get(DELETE_FC_URL).param("id", fcId)).andExpect(MockMvcResultMatchers.status().isForbidden());

		// verify Program Officer cannot delete a FundingCycle
		mvc.perform(MockMvcRequestBuilders.post(DELETE_FC_URL).param("id", fcId))
				.andExpect(MockMvcResultMatchers.status().isForbidden());

		Assertions.assertEquals(initFCCount, fcRepo.count());
	}

	@Tag("user_story_19213")
	@WithMockUser(username = "dev")
	@Test
	public void test_buNonMemberCannotDeleteFC_shouldReturn403() throws Exception {
		long initFCCount = fcRepo.count();

		String fcId = "65";
		String foId = "65";

		// verify "Delete" button is not visible to a user who is not a member of the linked BU
		mvc.perform(MockMvcRequestBuilders.get(MANAGE_FO_URL).param("id", foId)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.string(Matchers.not(Matchers.containsString(DEL_BTN_HALF_1 + fcId + DEL_BTN_HALF_2))));

		// verify user who is not a member of the linked BU cannot access the deleteFC page
		mvc.perform(MockMvcRequestBuilders.get(DELETE_FC_URL).param("id", fcId)).andExpect(MockMvcResultMatchers.status().isForbidden());

		// verify user who is not a member of the linked BU cannot delete a FundingCycle
		mvc.perform(MockMvcRequestBuilders.post(DELETE_FC_URL).param("id", fcId))
				.andExpect(MockMvcResultMatchers.status().isForbidden());

		Assertions.assertEquals(initFCCount, fcRepo.count());
	}

	@Tag("user_story_19213")
	@WithMockUser(roles = "MDM ADMIN")
	@Test
	public void test_adminCanDeleteFC_shouldSucceedWith302() throws Exception {
		long initFCCount = fcRepo.count();

		String fcId = "30";
		String foId = "30";

		// verify "Delete" button is visible to admin users
		mvc.perform(MockMvcRequestBuilders.get(MANAGE_FO_URL).param("id", foId)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString(DEL_BTN_HALF_1 + fcId + DEL_BTN_HALF_2)));

		// verify admin users can access the deleteFC page
		mvc.perform(MockMvcRequestBuilders.get(DELETE_FC_URL).param("id", fcId)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(DELETE_PAGE_ID)));

		// verify admin users can delete FundingCycles
		Assertions.assertTrue(mvc.perform(MockMvcRequestBuilders.post(DELETE_FC_URL).param("id", fcId))
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.redirectedUrl(MANAGE_FO_URL + "?id=" + foId)).andReturn().getFlashMap()
				.containsValue(FLASH_ATTRIBUTE), "Deletion message flash attribute is missing");

		Assertions.assertEquals(initFCCount - 1, fcRepo.count());
		Assertions.assertThrows(DataRetrievalFailureException.class, () -> fcService.findFundingCycleById(new Long(fcId)),
				"FundingCycle (id=" + fcId + ") was not deleted");
	}

}
