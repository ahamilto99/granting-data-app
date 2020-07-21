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
		// user 'aha' is a BU Program Officer for BU id=13
		assertEquals(11, apService.findAppPartsForCurrentUser(13L).size());
		// user 'aha' is a member of BU id=1 but is not a Program Officer for it
		assertEquals(0, apService.findAppPartsForCurrentUser(1L).size());
		// user 'aha' is not even a member of BU id=2 let alone a Program Officer for it
		assertEquals(0, apService.findAppPartsForCurrentUser(2L).size());
	}
	
}

























