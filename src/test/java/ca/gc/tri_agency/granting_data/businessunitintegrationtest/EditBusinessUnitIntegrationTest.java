package ca.gc.tri_agency.granting_data.businessunitintegrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
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
import ca.gc.tri_agency.granting_data.model.BusinessUnit;
import ca.gc.tri_agency.granting_data.repo.BusinessUnitRepository;

@SpringBootTest(classes = GrantingDataApp.class)
@ActiveProfiles("test")
public class EditBusinessUnitIntegrationTest {

	@Autowired
	private BusinessUnitRepository buRepo;

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mvc;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(ctx).apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void test_editBULinkVisibileToAdmin_shouldSucceedWith200() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/browse/viewBU").param("id", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("id=\"editBULink\"")));
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_editBULinkNotVisibileToNonAdmin_shouldReturn200() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/browse/viewBU").param("id", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content()
						.string(Matchers.not(Matchers.containsString("id=\"editBULink\""))));
	}

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void test_adminCanAccessEditBUPage_shouldSucceedWith200() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/admin/editBU").param("id", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("id=\"editBusinessUnitPage\"")));
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotAccessEditBUPage_shouldReturn403() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/admin/editBU").param("id", "1"))
				.andExpect(MockMvcResultMatchers.status().isForbidden()).andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString("id=\"forbiddenByRoleErrorPage\"")));
	}

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void testController_adminCanEditBU_shouldSucceedWith302() throws Exception {
		long initBuRepoCount = buRepo.count();
		BusinessUnit buBeforeUpate = buRepo.findById(1L).get();

		String nameEn = RandomStringUtils.randomAlphabetic(20);
		String nameFr = RandomStringUtils.randomAlphabetic(20);
		String acronymEn = RandomStringUtils.randomAlphabetic(5);
		String acronymFr = RandomStringUtils.randomAlphabetic(5);
		String agencyId = "2";

		mvc.perform(MockMvcRequestBuilders.post("/admin/editBU").param("id", "1").param("nameEn", nameEn)
				.param("nameFr", nameFr).param("acronymEn", acronymEn).param("acronymFr", acronymFr)
				.param("agency", agencyId)).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/browse/viewBU?id=1"))
				.andExpect(MockMvcResultMatchers.flash().attribute("actionMsg",
						"Successfully Edited Business Unit"));

		// when viewBUs page is refreshed, flash attribute should disappear
		mvc.perform(MockMvcRequestBuilders.get("/browse/viewBU?id=1"))
				.andExpect(MockMvcResultMatchers.flash().attributeCount(0));

		assertEquals(initBuRepoCount, buRepo.count());

		// BusinessUnit after update
		BusinessUnit buAfterUpdate = buRepo.findById(1L).get();
		assertNotEquals(buBeforeUpate.getNameEn(), buAfterUpdate.getNameEn());
		assertEquals(buBeforeUpate.getId(), buAfterUpdate.getId());
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void testController_nonAdminCannotEditBU_shouldReturn403() throws Exception {
		long initBuRepoCount = buRepo.count();
		BusinessUnit buBeforeFailedUpate = buRepo.findById(1L).get();

		String nameEn = RandomStringUtils.randomAlphabetic(20);
		String nameFr = RandomStringUtils.randomAlphabetic(20);
		String acronymEn = RandomStringUtils.randomAlphabetic(5);
		String acronymFr = RandomStringUtils.randomAlphabetic(5);
		String agencyId = "2";

		mvc.perform(MockMvcRequestBuilders.post("/admin/editBU").param("id", "1").param("nameEn", nameEn)
				.param("nameFr", nameFr).param("acronymEn", acronymEn).param("acronymFr", acronymFr)
				.param("agencyId", agencyId)).andExpect(MockMvcResultMatchers.status().isForbidden());

		assertEquals(initBuRepoCount, buRepo.count());

		BusinessUnit buAfterFailedUpdate = buRepo.findById(1L).get();
		assertEquals(buBeforeFailedUpate, buAfterFailedUpdate);
		assertEquals(buBeforeFailedUpate.getId(), buAfterFailedUpdate.getId());
	}

}
