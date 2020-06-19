package ca.gc.tri_agency.granting_data.fundingcycleintegrationtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Map;

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
import ca.gc.tri_agency.granting_data.model.FundingCycle;
import ca.gc.tri_agency.granting_data.repo.FundingCycleRepository;
import ca.gc.tri_agency.granting_data.repo.FundingOpportunityRepository;
import ca.gc.tri_agency.granting_data.service.FiscalYearService;
import ca.gc.tri_agency.granting_data.service.FundingCycleService;

@SpringBootTest(classes = GrantingDataApp.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class FundingCycleServiceTest {

	@Autowired
	private FundingCycleService fcService;

	@Autowired
	private FiscalYearService fyService;

	@Autowired
	private FundingOpportunityRepository foRepo;

	@Autowired
	private FundingCycleRepository fcRepo;

	@WithAnonymousUser
	@Test(expected = DataRetrievalFailureException.class)
	public void test_findFundingCycleById() {
		assertNotNull(fcService.findFundingCycleById(1L));

		fcService.findFundingCycleById(Long.MIN_VALUE);
	}

	@WithAnonymousUser
	@Test
	public void test_findAllFundingCycles() {
		assertTrue(0 < fcService.findAllFundingCycles().size());
	}

	@WithAnonymousUser
	@Test
	public void test_findFundingCyclesByFoId() {
		assertEquals(0, fcService.findFundingCyclesByFundingOpportunityId(Long.MAX_VALUE).size());
		assertTrue(0 < fcService.findFundingCyclesByFundingOpportunityId(1L).size());
	}

	@WithAnonymousUser
	@Test
	public void test_findFundingCyclesByFiscalYearId() {
		assertEquals(0, fcService.findFundingCyclesByFiscalYearId(Long.MAX_VALUE).size());
		assertTrue(0 < fcService.findFundingCyclesByFiscalYearId(1L).size());
	}

	/*
	 * B/c findMonthlyFundingCyclesByStartDate(int plusMinusMonth) uses LocalDate.now() 
	 * as the value to which plusMinusMonth is applied, this test will at times fail
	 * since the test database contains fixed dates for the FundingCycle entries.
	 * In order to resolve a failure, the value for plusMinusMonth must be changed.
	 */
	@WithAnonymousUser
	@Test
	public void test_findMonthlyFundingCyclesByStartDate() {
		assertEquals(0, fcService.findMonthlyFundingCyclesByStartDate(Integer.MAX_VALUE).size());
		assertTrue(0 < fcService.findMonthlyFundingCyclesByStartDate(12).size());
	}

	/*
	 * B/c findMonthlyFundingCyclesByEndDate(int plusMinusMonth) uses LocalDate.now() 
	 * as the value to which plusMinusMonth is applied, this test will at times fail
	 * since the test database contains fixed dates for the FundingCycle entries.
	 * In order to resolve a failure, the value for plusMinusMonth must be changed.
	 */
	@WithAnonymousUser
	@Test
	public void test_findMonthlyFundingCyclesByEndDate() {
		assertEquals(0, fcService.findMonthlyFundingCyclesByEndDate(0).size());
		assertTrue(0 < fcService.findMonthlyFundingCyclesByEndDate(8).size());
	}

	/*
	 * B/c findMonthlyFundingCyclesByStartDateLOI(int plusMinusMonth) uses LocalDate.now() 
	 * as the value to which plusMinusMonth is applied, this test will at times fail
	 * since the test database contains fixed dates for the FundingCycle entries.
	 * In order to resolve a failure, the value for plusMinusMonth must be changed.
	 */
	@WithAnonymousUser
	@Test
	public void test_findMonthlyFundingCyclesByStartDateLOI() {
		assertEquals(0, fcService.findMonthlyFundingCyclesByStartDateLOI(-1_000).size());
		assertTrue(0 < fcService.findMonthlyFundingCyclesByStartDateLOI(12).size());
	}

	/*
	 * B/c findMonthlyFundingCyclesByEndDateLOI(int plusMinusMonth) uses LocalDate.now() 
	 * as the value to which plusMinusMonth is applied, this test will at times fail
	 * since the test database contains fixed dates for the FundingCycle entries.
	 * In order to resolve a failure, the value for plusMinusMonth must be changed.
	 */
	@WithAnonymousUser
	@Test
	public void test_findMonthlyFundingCyclesByEndDateLOI() {
		assertEquals(0, fcService.findMonthlyFundingCyclesByEndDateLOI(Integer.MAX_VALUE).size());
		assertTrue(0 < fcService.findMonthlyFundingCyclesByEndDateLOI(12).size());
	}

	/*
	 * B/c findMonthlyFundingCyclesByStartDateNOI(int plusMinusMonth) uses LocalDate.now() 
	 * as the value to which plusMinusMonth is applied, this test will at times fail
	 * since the test database contains fixed dates for the FundingCycle entries.
	 * In order to resolve a failure, the value for plusMinusMonth must be changed.
	 */
	@WithAnonymousUser
	@Test
	public void test_findMonthlyFundingCyclesByStartDateNOI() {
		assertEquals(0, fcService.findMonthlyFundingCyclesByStartDateNOI(Integer.MAX_VALUE).size());
		assertTrue(0 < fcService.findMonthlyFundingCyclesByStartDateNOI(8).size());
	}

	/*
	 * B/c findMonthlyFundingCyclesByEndDateNOI(int plusMinusMonth) uses LocalDate.now() 
	 * as the value to which plusMinusMonth is applied, this test will at times fail
	 * since the test database contains fixed dates for the FundingCycle entries.
	 * In order to resolve a failure, the value for plusMinusMonth must be changed.
	 */
	@WithAnonymousUser
	@Test
	public void test_findMonthlyFundingCyclesByEndDateNOI() {
		assertEquals(0, fcService.findMonthlyFundingCyclesByEndDateNOI(Integer.MAX_VALUE).size());
		assertTrue(0 < fcService.findMonthlyFundingCyclesByEndDateNOI(8).size());
	}

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void test_adminCanCreateFundingCycle() {
		long initFCCount = fcRepo.count();

		FundingCycle fc = new FundingCycle();
		fc.setStartDate(LocalDate.of(2030, 1, 1));
		fc.setEndDate(LocalDate.of(2030, 1, 1));
		fc.setStartDateLOI(LocalDate.of(2030, 1, 1));
		fc.setEndDateLOI(LocalDate.of(2030, 1, 1));
		fc.setStartDateNOI(LocalDate.of(2030, 1, 1));
		fc.setEndDateNOI(LocalDate.of(2030, 1, 1));
		fc.setFiscalYear(fyService.findFiscalYearById(1L));
		fc.setFundingOpportunity(foRepo.findById(1L).get());
		fc.setIsOpen(false);
		fc.setExpectedApplications(100L);

		assertNotNull(fcService.saveFundingCycle(fc).getId());
		assertEquals(initFCCount + 1, fcRepo.count());
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test(expected = AccessDeniedException.class)
	public void test_nonAdminCannotCreateFundingCycle() {
		fcService.saveFundingCycle(new FundingCycle());
	}

	@WithAnonymousUser
	@Test
	public void testFindFundingCyclesByFundingOpportunityMap() {
		Map<Long, FundingCycle> fcsByFosMap = fcService.findFundingCyclesByFundingOpportunityMap();
		assertNotNull(fcsByFosMap);
		assertTrue(0 < fcsByFosMap.size());
	}

}
