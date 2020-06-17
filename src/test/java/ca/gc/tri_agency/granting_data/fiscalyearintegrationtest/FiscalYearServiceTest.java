package ca.gc.tri_agency.granting_data.fiscalyearintegrationtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.app.exception.UniqueColumnException;
import ca.gc.tri_agency.granting_data.model.FiscalYear;
import ca.gc.tri_agency.granting_data.repo.FiscalYearRepository;
import ca.gc.tri_agency.granting_data.service.FiscalYearService;

@SpringBootTest(classes = GrantingDataApp.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
//@Ignore(value = "The FiscalYear functionality is not required for Version 1")
public class FiscalYearServiceTest {

	@Autowired
	private FiscalYearService fyService;

	@Autowired
	private FiscalYearRepository fyRepo;
	
	@WithAnonymousUser
	@Test(expected = DataRetrievalFailureException.class)
	public void test_findFiscalYearById() {
		assertNotNull(fyService.findFiscalYearById(1L));
		
		fyService.findFiscalYearById(Long.MAX_VALUE);
	}

	@WithAnonymousUser
	@Test
	public void test_findAllFiscalYears() {
		assertTrue(0 < fyService.findAllFiscalYears().size());
	}

	@WithAnonymousUser
	@Test
	public void test_findFiscalYearByYear() {
		assertTrue(fyService.findFiscalYearByYear(2020L).isPresent());
		assertFalse(fyService.findFiscalYearByYear(Long.MAX_VALUE).isPresent());
	}

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Rollback
	@Test(expected = UniqueColumnException.class)
	public void test_adminCanCreateFiscalYear() {
		long initFYCount = fyRepo.count();

		FiscalYear newFy = new FiscalYear(2040L);
		newFy = fyService.saveFiscalYear(newFy);

		assertNotNull(newFy.getId());
		assertEquals(initFYCount + 1, fyRepo.count());
		
		fyService.saveFiscalYear(new FiscalYear(2040L));
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test(expected = AccessDeniedException.class)
	public void test_nonAdminCannotCreateFiscalYear() {
		fyService.saveFiscalYear(new FiscalYear(2041L));
	}
	
	@WithAnonymousUser
	@Test
	public void test_findNumAppsExpectedForEachFiscalYear() {
		List<Object[]> results = fyService.findNumAppsExpectedForEachFiscalYear();
		
		assertEquals(4, results.size());
		assertEquals(702_527L, results.get(3)[2]);
	}
}
