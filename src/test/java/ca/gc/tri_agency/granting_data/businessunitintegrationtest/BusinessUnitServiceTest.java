package ca.gc.tri_agency.granting_data.businessunitintegrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.BusinessUnit;
import ca.gc.tri_agency.granting_data.repo.BusinessUnitRepository;
import ca.gc.tri_agency.granting_data.service.AgencyService;
import ca.gc.tri_agency.granting_data.service.BusinessUnitService;

@SpringBootTest(classes = GrantingDataApp.class)
@ActiveProfiles("test")
public class BusinessUnitServiceTest {

	@Autowired
	private BusinessUnitService buService;

	@Autowired
	private AgencyService agencyService;

	@Autowired
	private BusinessUnitRepository buRepo;

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void test_adminCanCreateBU() {
		long initBuCount = buRepo.count();

		Agency agency = agencyService.findAllAgencies().get(0);
		BusinessUnit bu = new BusinessUnit("EN NAME TEST", "FR NAME TEST", "EN ACRONYM TEST", "FR ACRONYM TEST", agency);
		buService.saveBusinessUnit(bu);

		assertEquals(initBuCount + 1, buRepo.count());
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotCreateBU_shouldthrowAccessDeniedException() {
		BusinessUnit bu = new BusinessUnit("EN NAME TEST", "FR NAME TEST", "EN ACRONYM TEST", "FR ACRONYM TEST",
				agencyService.findAgencyById(1L));
		assertThrows(AccessDeniedException.class, () -> buService.saveBusinessUnit(bu));
	}

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void test_adminCanEditBU() {
		long initBuRepoCount = buRepo.count();
		BusinessUnit buBeforeUpate = buRepo.findById(1L).get();
		BusinessUnit buAfterUpdate = buRepo.findById(1L).get();

		assertEquals(buBeforeUpate, buAfterUpdate);

		buAfterUpdate.setNameEn(RandomStringUtils.randomAlphabetic(20));
		buAfterUpdate.setNameFr(RandomStringUtils.randomAlphabetic(20));
		buAfterUpdate.setAcronymEn(RandomStringUtils.randomAlphabetic(5));
		buAfterUpdate.setAcronymFr(RandomStringUtils.randomAlphabetic(5));

		buService.saveBusinessUnit(buAfterUpdate);

		assertEquals(initBuRepoCount, buRepo.count());
		assertNotEquals(buBeforeUpate, buAfterUpdate);
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotEditBU() {
		BusinessUnit bu = buRepo.findById(1L).get();
		bu.setNameEn(RandomStringUtils.randomAlphabetic(20));
		bu.setNameFr(RandomStringUtils.randomAlphabetic(20));
		bu.setAcronymEn(RandomStringUtils.randomAlphabetic(5));
		bu.setAcronymFr(RandomStringUtils.randomAlphabetic(5));

		assertThrows(AccessDeniedException.class, () -> buService.saveBusinessUnit(bu));
	}

	@WithAnonymousUser
	@Test
	public void test_findAllBUs() {
		assertTrue(14 <= buService.findAllBusinessUnits().size());
	}

	@WithMockUser(username = "admin", roles = "MDM ADMIN")
	@Test
	public void test_adminCanFindBusinessUnitRevisionsById() {
		final Long buId = 1L;
		int startNumRevisions = buService.findBusinessUnitRevisionsById(buId).size();

		BusinessUnit bu = buService.findBusinessUnitById(buId);
		String nameEn = RandomStringUtils.randomAlphabetic(5);
		bu.setNameEn(nameEn);
		buService.saveBusinessUnit(bu);

		List<String[]> buRevisions = buService.findBusinessUnitRevisionsById(buId);
		int endNumRevisions = buRevisions.size();

		assertEquals(startNumRevisions + 1, endNumRevisions);
		assertEquals(nameEn, buRevisions.get(endNumRevisions - 1)[3]);

		int numAdds = 0;

		for (String[] strArr : buRevisions) {
			if (strArr[2].equals("ADD")) {
				++numAdds;
			}
		}

		assertEquals(numAdds, 1);
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotFindBusinessUnitRevisionsById() {
		assertThrows(AccessDeniedException.class, () -> buService.findBusinessUnitRevisionsById(1L));
	}

	@WithAnonymousUser
	@Test
	public void test_findBusinessUnitById_shouldThrowException() {
		assertThrows(DataRetrievalFailureException.class, () -> buService.findBusinessUnitById(Long.MAX_VALUE));
	}

	@WithMockUser(username = "admin", roles = "MDM ADMIN")
	@Test
	public void test_adminCanFindAllBusinessUnitRevisions() {
		List<String[]> auditedArrList = buService.findAllBusinessUnitRevisions();

		int numAdds = 0;

		for (String[] strArr : auditedArrList) {
			if (strArr[2].equals("ADD")) {
				++numAdds;
			}
		}

		assertTrue(numAdds >= 14);
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotFindAllBusinessUnitRevisions() {
		assertThrows(AccessDeniedException.class, () -> buService.findAllBusinessUnitRevisions());
	}
	
	@Tag("user_story_19147")
	@WithMockUser(username = "aha")
	@Test
	public void test_findEdiAppPartDataForAuthorizedBUMember() {
		Map<String, Long> ediMap = buService.findEdiAppPartDataForAuthorizedBUMember(13L);

		assertEquals(3L, ediMap.get("numIndigenousApps"));
		assertEquals(7L, ediMap.get("numVisMinorityApps"));
		assertEquals(1L, ediMap.get("numDisabledApps"));
		assertEquals(6L, ediMap.get("numFemaleApps"));
		assertEquals(3L, ediMap.get("numMaleApps"));
		assertEquals(2L, ediMap.get("numNonBinaryApps"));
		assertEquals(11L, ediMap.get("numApps"));

		// user "aha" is not authorized to get EDI data associated with BU 1
		assertThrows(AccessDeniedException.class, () -> buService.findEdiAppPartDataForAuthorizedBUMember(1L));
	}

}
