package ca.gc.tri_agency.granting_data.applicationparticipationintegrationtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.model.ApplicationParticipation;
import ca.gc.tri_agency.granting_data.repo.ApplicationParticipationRepository;
import ca.gc.tri_agency.granting_data.service.ApplicationParticipationService;

@SpringBootTest(classes = GrantingDataApp.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ApplicationParticipationServiceTest {

	@Autowired
	private ApplicationParticipationService apService;

	@Autowired
	ApplicationParticipationRepository apRepo;

	@WithMockUser(username = "admin", roles = "MDM ADMIN")
	@Test
	public void test_adminCanFindApplicationParticipationRevisionsById() {
		ApplicationParticipation ap = new ApplicationParticipation();
		String initAppIdentifier = RandomStringUtils.randomAlphabetic(10);
		ap.setApplicationIdentifier(initAppIdentifier);
		ap.setCompetitionYear(2022L);
		ap.setCountry(RandomStringUtils.randomAlphabetic(10));
		ap.setCreateDate(Instant.now());
		ap.setCreateUserId(RandomStringUtils.randomAlphabetic(3));
		ap.setDateOfBirth(LocalDate.now());
		ap.setFamilyName(RandomStringUtils.randomAlphabetic(10));
		ap.setFreeformAddress1(RandomStringUtils.randomAlphabetic(10));
		ap.setFreeformAddress2(RandomStringUtils.randomAlphabetic(10));
		ap.setFreeformAddress3(RandomStringUtils.randomAlphabetic(10));
		ap.setFreeformAddress4(RandomStringUtils.randomAlphabetic(10));
		ap.setIndIdentityResponse(RandomStringUtils.randomAlphabetic(10));
		ap.setIndIdentitySelection1(1L);
		ap.setIndIdentitySelection2(2L);
		ap.setIndIdentitySelection3(3L);
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
		ap.setVisibleMinSelection1(1L);
		ap.setVisibleMinSelection2(2L);
		ap.setVisibleMinSelection3(3L);
		ap.setVisibleMinSelection4(4L);
		ap.setVisibleMinSelection5(5L);
		ap.setVisibleMinSelection6(6L);
		ap.setVisibleMinSelection7(7L);
		ap.setVisibleMinSelection8(8L);
		ap.setVisibleMinSelection9(9L);
		ap.setVisibleMinSelection10(10L);
		ap.setVisibleMinSelection11(11L);

		final Long apId = apRepo.save(ap).getId();
		int startNumRevisions = apService.findApplicationParticipationRevisionsById(apId).size();

		String newApplicationIdentifier = RandomStringUtils.randomAlphabetic(15);
		ApplicationParticipation apRevised = apRepo.findById(apId).get();
		apRevised.setApplicationIdentifier(newApplicationIdentifier);
		apRepo.save(apRevised);

		List<String[]> apRevisions = apService.findApplicationParticipationRevisionsById(apId);
		int endNumRevisions = apRevisions.size();

		assertEquals(startNumRevisions + 1, endNumRevisions);
		assertEquals(newApplicationIdentifier, apRevisions.get(endNumRevisions - 1)[2]);
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test(expected = AccessDeniedException.class)
	public void test_nonAdminCannotFindApplicationParticipationRevisionsById_shouldThrowException() {
		apService.findApplicationParticipationRevisionsById(1L);
	}

	@WithMockUser(username = "admin", roles = "MDM ADMIN")
	@Test
	public void test_adminCanFindAllApplicationParticipationRevisions() {
		assertNotNull(apService.findAllApplicationParticipationRevisions());
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test(expected = AccessDeniedException.class)
	public void test_nonAdminCannotFindAllApplicationParticipationRevisions_shouldThrowException() {
		apService.findAllApplicationParticipationRevisions();
	}
}
