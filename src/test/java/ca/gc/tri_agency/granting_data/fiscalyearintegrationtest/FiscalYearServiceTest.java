package ca.gc.tri_agency.granting_data.fiscalyearintegrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.app.exception.UniqueColumnException;
import ca.gc.tri_agency.granting_data.model.FiscalYear;
import ca.gc.tri_agency.granting_data.repo.FiscalYearRepository;
import ca.gc.tri_agency.granting_data.service.FiscalYearService;

@SpringBootTest(classes = GrantingDataApp.class)
@ActiveProfiles("test")
public class FiscalYearServiceTest {

	@Autowired
	private FiscalYearService fyService;

	@Autowired
	private FiscalYearRepository fyRepo;
	
	@WithAnonymousUser
	@Test
	public void test_findFiscalYearById() {
		assertNotNull(fyService.findFiscalYearById(1L));
		
		assertThrows(DataRetrievalFailureException.class, () -> fyService.findFiscalYearById(Long.MAX_VALUE));
	}

	@Tag("user_story_19201")
	@WithAnonymousUser
	@Test
	public void test_findAllFiscalYearsOrderedByYear() {
		assertTrue(4 <= fyService.findAllFiscalYearProjectionsOrderByYear().size());
	}

	@WithAnonymousUser
	@Test
	public void test_findFiscalYearByYear() {
		assertTrue(fyService.findFiscalYearByYear(2020L).isPresent());
		assertFalse(fyService.findFiscalYearByYear(Long.MAX_VALUE).isPresent());
	}

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Rollback
	@Test
	public void test_adminCanCreateFiscalYear() {
		long initFYCount = fyRepo.count();

		FiscalYear newFy = new FiscalYear(2040L);
		newFy = fyService.saveFiscalYear(newFy);

		assertNotNull(newFy.getId());
		assertEquals(initFYCount + 1, fyRepo.count());
		
		assertThrows(UniqueColumnException.class, () -> fyService.saveFiscalYear(new FiscalYear(2040L)));
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotCreateFiscalYear() {
		assertThrows(AccessDeniedException.class, () -> fyService.saveFiscalYear(new FiscalYear(2041L)));
	}
	
	@WithAnonymousUser
	@Test
	public void test_findNumAppsExpectedForEachFiscalYear() {
		List<Object[]> results = fyService.findNumAppsExpectedForEachFiscalYear();
		
		assertEquals(4, results.size());
		assertEquals(12_467L, results.get(0)[2]);
	}
}
