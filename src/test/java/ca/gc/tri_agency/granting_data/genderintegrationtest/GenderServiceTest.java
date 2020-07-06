package ca.gc.tri_agency.granting_data.genderintegrationtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.model.Gender;
import ca.gc.tri_agency.granting_data.service.GenderService;

@SpringBootTest(classes = GrantingDataApp.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class GenderServiceTest {

	@Autowired
	private GenderService genderService;

	@WithAnonymousUser
	@Test
	public void test_anonUserCanFindGenderByNameEn() {
		assertEquals(new Long(1L), genderService.findGenderByNameEn("female").getId());

	}
	
	@WithAnonymousUser
	@Test
	public void test_anonUserCanFindGenderByNameFr() {
		assertEquals(new Long(1L), genderService.findGenderByNameFr("femme").getId());
	}

	@WithAnonymousUser
	@Test
	public void test_anonUserCanFindAllGenders() {
		assertTrue(3 <= genderService.findAllGenders().size());
	}
	
	@WithMockUser(username = "mock_admin", roles = "MDM ADMIN")
	@Test
	public void test_adminCanSaveGender() {
		String nameEn = "gender neutral";
		String nameFr = "genre neutre";
		Gender gender = new Gender();
		gender.setNameEn(nameEn);
		gender.setNameFr(nameFr);

		genderService.saveGender(gender);

		assertEquals(nameEn, genderService.findGenderById(4L).getNameEn());
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test(expected = AccessDeniedException.class)
	public void test_nonAdminCannotSaveGender_shouldThrowException() {
		genderService.saveGender(new Gender());
	}
	
}
