package ca.gc.tri_agency.granting_data.fiscalyearintegrationtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import ca.gc.tri_agency.granting_data.model.FiscalYear;
import ca.gc.tri_agency.granting_data.repo.FiscalYearRepository;
import ca.gc.tri_agency.granting_data.service.FiscalYearService;

@SpringBootTest(classes = GrantingDataApp.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("local")
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
	@Test
	public void test_adminCanSaveFiscalYear() {
		long initFYCount = fyRepo.count();

		FiscalYear newFy = new FiscalYear(2040L);
		newFy = fyService.saveFiscalYear(newFy);

		long updatedFYCount = fyRepo.count();

		assertNotNull(newFy.getId());
		assertEquals(initFYCount + 1, updatedFYCount);

		FiscalYear updateFiscalYear = fyService.findFiscalYearById(1L);
		updateFiscalYear.setYear(2031L);
		updateFiscalYear = fyService.saveFiscalYear(updateFiscalYear);

		assertEquals(2031L, (long) fyService.findFiscalYearById(1L).getYear());
		assertEquals(updatedFYCount, fyRepo.count());
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test(expected = AccessDeniedException.class)
	public void test_nonAdminCannotSaveFiscalYear() {
		fyService.saveFiscalYear(new FiscalYear(2041L));
	}
}
