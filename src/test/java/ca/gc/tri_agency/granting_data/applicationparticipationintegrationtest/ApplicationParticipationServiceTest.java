package ca.gc.tri_agency.granting_data.applicationparticipationintegrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.service.ApplicationParticipationService;

@SpringBootTest(classes = GrantingDataApp.class)
@ActiveProfiles("test")
public class ApplicationParticipationServiceTest {

	@Autowired
	private ApplicationParticipationService apService;

	@Tag("user_story_19154")
	@WithMockUser(username = "aha")
	@Test
	public void test_findAppPartsForCurrentUser() {
		// user 'aha' is a BU Program Officer for BU id=13 and there 11 AppParts linked to that BU
		assertEquals(11, apService.findAppPartsForCurrentUser().size());
	}

	@Tag("user_story_19154")
	@WithMockUser(roles = "MDM ADMIN")
	@Test
	public void test_adminCanFindAllAppParts() {
		// an admin user doesn't have a member or even a Program Officer
		assertEquals(17, apService.findAppPartsForCurrentUser().size());
	}
}
