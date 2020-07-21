package ca.gc.tri_agency.granting_data.applicationparticipationintegrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.model.ApplicationParticipation;
import ca.gc.tri_agency.granting_data.repo.ApplicationParticipationRepository;
import ca.gc.tri_agency.granting_data.service.ApplicationParticipationService;
import ca.gc.tri_agency.granting_data.service.GenderService;

@SpringBootTest(classes = GrantingDataApp.class)
@ActiveProfiles("test")
public class ApplicationParticipationServiceTest {

	@Autowired
	private ApplicationParticipationRepository apRepo;

	@Autowired
	private GenderService genderService;

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

	@Test
	public void test_repo() {
		long initApRepoCount = apRepo.count();

		ApplicationParticipation ap = new ApplicationParticipation();
		String appIdentifier = RandomStringUtils.randomAlphabetic(10);
		ap.setApplicationIdentifier(appIdentifier);
		ap.setCompetitionYear(2022L);
		ap.setCountry(RandomStringUtils.randomAlphabetic(10));
		Instant currentTimestamp = Instant.now();
		ap.setCreateDate(currentTimestamp);
		ap.setCreateUserId(RandomStringUtils.randomAlphabetic(3));
		ap.setFamilyName(RandomStringUtils.randomAlphabetic(10));
		ap.setFreeformAddress1(RandomStringUtils.randomAlphabetic(10));
		ap.setFreeformAddress2(RandomStringUtils.randomAlphabetic(10));
		ap.setFreeformAddress3(RandomStringUtils.randomAlphabetic(10));
		ap.setFreeformAddress4(RandomStringUtils.randomAlphabetic(10));
		ap.setGivenName(RandomStringUtils.randomAlphabetic(10));
		ap.setMunicipality(RandomStringUtils.randomAlphabetic(10));
		ap.setOrganizationNameEn(RandomStringUtils.randomAlphabetic(10));
		ap.setOrganizationNameFr(RandomStringUtils.randomAlphabetic(10));
		ap.setOrganizationId("1");
		ap.setPersonIdentifier(99L);
		ap.setPostalZipCode(RandomStringUtils.randomAlphabetic(7));
		ap.setProgramEn(RandomStringUtils.randomAlphabetic(10));
		ap.setProgramFr(RandomStringUtils.randomAlphabetic(10));
		ap.setProgramId(RandomStringUtils.randomAlphabetic(10));
		ap.setProvinceStateCode(RandomStringUtils.randomAlphabetic(2));
		ap.setRoleCode("1234");
		ap.setRoleEn(RandomStringUtils.randomAlphabetic(10));
		ap.setRoleFr(RandomStringUtils.randomAlphabetic(10));
		ap.setGender(genderService.findGenderByNameEn("Female"));

		ap = apRepo.save(ap);

		ApplicationParticipation savedAp = apRepo.findById(ap.getId()).get();

		assertEquals(initApRepoCount + 1L, apRepo.count());
		assertEquals(appIdentifier, savedAp.getApplicationIdentifier());
		assertEquals(currentTimestamp, savedAp.getCreateDate());
		assertEquals("Female", savedAp.getGender().getNameEn());

	}
}
