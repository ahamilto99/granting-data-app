package ca.gc.tri_agency.granting_data.businessunitintegrationtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.BusinessUnit;
import ca.gc.tri_agency.granting_data.repo.BusinessUnitRepository;
import ca.gc.tri_agency.granting_data.service.AgencyService;
import ca.gc.tri_agency.granting_data.service.BusinessUnitService;

@SpringBootTest(classes = GrantingDataApp.class)
@RunWith(SpringRunner.class)
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
	@Test(expected = AccessDeniedException.class)
	public void test_nonAdminCannotCreateBU_shouldthrowAccessDeniedException() {
		Agency agency = agencyService.findAllAgencies().get(0);
		BusinessUnit bu = new BusinessUnit("EN NAME TEST", "FR NAME TEST", "EN ACRONYM TEST", "FR ACRONYM TEST", agency);
		buService.saveBusinessUnit(bu);
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
	@Test(expected = AccessDeniedException.class)
	public void test_nonAdminCannotEditBU() {
		BusinessUnit bu = buRepo.findById(1L).get();
		bu.setNameEn(RandomStringUtils.randomAlphabetic(20));
		bu.setNameFr(RandomStringUtils.randomAlphabetic(20));
		bu.setAcronymEn(RandomStringUtils.randomAlphabetic(5));
		bu.setAcronymFr(RandomStringUtils.randomAlphabetic(5));

		buService.saveBusinessUnit(bu);
	}

	@WithAnonymousUser
	@Test
	public void test_findAllBUs() {
		assertTrue(0 < buService.findAllBusinessUnits().size());
	}

	@WithMockUser(username = "admin", roles = "MDM ADMIN")
	@Test
	public void test_adminCanFindBusinessUnitRevisionsById() {
		final long buId = 1L;
		int initNumRevisions = buService.findBusinessUnitRevisionsById(buId).size();

		BusinessUnit bu = buService.findBusinessUnitById(buId);
		String nameEn = RandomStringUtils.randomAlphabetic(5);
		bu.setNameEn(nameEn);
		buService.saveBusinessUnit(bu);

		List<String[]> buRevisions = buService.findBusinessUnitRevisionsById(buId);
		int endNumRevisions = buRevisions.size();

		assertEquals(initNumRevisions + 1, endNumRevisions);
		assertEquals(nameEn, buRevisions.get(endNumRevisions - 1)[2]);
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test(expected = AccessDeniedException.class)
	public void test_nonAdminCannotFindBusinessUnitRevisionsById() {
		buService.findBusinessUnitRevisionsById(1L);
	}

	@WithAnonymousUser
	@Test(expected = DataRetrievalFailureException.class)
	public void test_findBusinessUnitById_shouldThrowException() {
		buService.findBusinessUnitById(Long.MAX_VALUE);
	}

	@WithMockUser(username = "admin", roles = "MDM ADMIN")
	@Test
	public void test_adminCanFindAllBusinessUnitRevisions() {
		assertNotNull(buService.findAllBusinessUnitRevisions());
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test(expected = AccessDeniedException.class)
	public void test_nonAdminCannotFindAllBusinessUnitRevisions() {
		buService.findAllBusinessUnitRevisions();
	}
}
