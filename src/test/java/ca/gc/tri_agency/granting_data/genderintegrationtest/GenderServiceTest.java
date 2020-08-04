package ca.gc.tri_agency.granting_data.genderintegrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.model.Gender;
import ca.gc.tri_agency.granting_data.service.GenderService;

@SpringBootTest(classes = GrantingDataApp.class)
@ActiveProfiles("test")
public class GenderServiceTest {

	@Autowired
	private GenderService genderService;

	@WithAnonymousUser
	@Test
	public void test_anonUserCanFindGenderByNameEn() {
		assertEquals(new Long(1L), genderService.findGenderByNameEn("Female").getId());

	}
	
	@WithAnonymousUser
	@Test
	public void test_anonUserCanFindGenderByNameFr() {
		assertEquals(new Long(2L), genderService.findGenderByNameFr("Homme").getId());
	}

	@WithAnonymousUser
	@Test
	public void test_anonUserCanFindAllGenders() {
		assertTrue(3 <= genderService.findAllGenders().size());
	}
	
	@WithMockUser(username = "mock_admin", roles = "MDM ADMIN")
	@Test
	public void test_adminCanSaveGender() {
		String nameEn = RandomStringUtils.randomAlphabetic(10);
		String nameFr = RandomStringUtils.randomAlphabetic(10);
		Gender gender = new Gender();
		gender.setNameEn(nameEn);
		gender.setNameFr(nameFr);

		genderService.saveGender(gender);

		assertEquals(nameEn, genderService.findGenderById(4L).getNameEn());
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotSaveGender_shouldThrowException() {
		assertThrows(AccessDeniedException.class, () -> genderService.saveGender(new Gender()));
	}
	
}
