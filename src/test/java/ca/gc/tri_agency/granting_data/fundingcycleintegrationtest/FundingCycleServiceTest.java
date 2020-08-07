package ca.gc.tri_agency.granting_data.fundingcycleintegrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
import ca.gc.tri_agency.granting_data.model.FundingCycle;
import ca.gc.tri_agency.granting_data.model.projection.FundingCycleProjection;
import ca.gc.tri_agency.granting_data.repo.FundingCycleRepository;
import ca.gc.tri_agency.granting_data.service.FiscalYearService;
import ca.gc.tri_agency.granting_data.service.FundingCycleService;
import ca.gc.tri_agency.granting_data.service.FundingOpportunityService;

@SpringBootTest(classes = GrantingDataApp.class)
@ActiveProfiles("test")
public class FundingCycleServiceTest {

	@Autowired
	private FundingCycleService fcService;

	@Autowired
	private FiscalYearService fyService;

	@Autowired
	private FundingOpportunityService foService;

	@Autowired
	private FundingCycleRepository fcRepo;

	@WithAnonymousUser
	@Test
	public void test_findFundingCycleById() {
		assertNotNull(fcService.findFundingCycleById(1L));

		assertThrows(DataRetrievalFailureException.class, () -> fcService.findFundingCycleById(Long.MIN_VALUE));
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
	 * B/c findMonthlyFundingCyclesByStartDate(int plusMinusMonth) uses LocalDate.now() as the value to
	 * which plusMinusMonth is applied, this test will at times fail since the test database contains
	 * fixed dates for the FundingCycle entries. In order to resolve a failure, the value for
	 * plusMinusMonth must be changed.
	 */
	@WithAnonymousUser
	@Test
	public void test_findMonthlyFundingCyclesByStartDate() {
		assertEquals(0, fcService.findMonthlyFundingCyclesByStartDate(Integer.MAX_VALUE).size());
		assertTrue(0 < fcService.findMonthlyFundingCyclesByStartDate(12).size());
	}

	/*
	 * B/c findMonthlyFundingCyclesByEndDate(int plusMinusMonth) uses LocalDate.now() as the value to
	 * which plusMinusMonth is applied, this test will at times fail since the test database contains
	 * fixed dates for the FundingCycle entries. In order to resolve a failure, the value for
	 * plusMinusMonth must be changed.
	 */
	@WithAnonymousUser
	@Test
	public void test_findMonthlyFundingCyclesByEndDate() {
		assertEquals(0, fcService.findMonthlyFundingCyclesByEndDate(0).size());
		assertTrue(0 < fcService.findMonthlyFundingCyclesByEndDate(8).size());
	}

	/*
	 * B/c findMonthlyFundingCyclesByStartDateLOI(int plusMinusMonth) uses LocalDate.now() as the value
	 * to which plusMinusMonth is applied, this test will at times fail since the test database contains
	 * fixed dates for the FundingCycle entries. In order to resolve a failure, the value for
	 * plusMinusMonth must be changed.
	 */
	@WithAnonymousUser
	@Test
	public void test_findMonthlyFundingCyclesByStartDateLOI() {
		assertEquals(0, fcService.findMonthlyFundingCyclesByStartDateLOI(-1_000).size());
		assertTrue(0 < fcService.findMonthlyFundingCyclesByStartDateLOI(12).size());
	}

	/*
	 * B/c findMonthlyFundingCyclesByEndDateLOI(int plusMinusMonth) uses LocalDate.now() as the value to
	 * which plusMinusMonth is applied, this test will at times fail since the test database contains
	 * fixed dates for the FundingCycle entries. In order to resolve a failure, the value for
	 * plusMinusMonth must be changed.
	 */
	@WithAnonymousUser
	@Test
	public void test_findMonthlyFundingCyclesByEndDateLOI() {
		assertEquals(0, fcService.findMonthlyFundingCyclesByEndDateLOI(Integer.MAX_VALUE).size());
		assertTrue(0 < fcService.findMonthlyFundingCyclesByEndDateLOI(12).size());
	}

	/*
	 * B/c findMonthlyFundingCyclesByStartDateNOI(int plusMinusMonth) uses LocalDate.now() as the value
	 * to which plusMinusMonth is applied, this test will at times fail since the test database contains
	 * fixed dates for the FundingCycle entries. In order to resolve a failure, the value for
	 * plusMinusMonth must be changed.
	 */
	@WithAnonymousUser
	@Test
	public void test_findMonthlyFundingCyclesByStartDateNOI() {
		assertEquals(0, fcService.findMonthlyFundingCyclesByStartDateNOI(Integer.MAX_VALUE).size());
		assertTrue(0 < fcService.findMonthlyFundingCyclesByStartDateNOI(8).size());
	}

	/*
	 * B/c findMonthlyFundingCyclesByEndDateNOI(int plusMinusMonth) uses LocalDate.now() as the value to
	 * which plusMinusMonth is applied, this test will at times fail since the test database contains
	 * fixed dates for the FundingCycle entries. In order to resolve a failure, the value for
	 * plusMinusMonth must be changed.
	 */
	@WithAnonymousUser
	@Test
	public void test_findMonthlyFundingCyclesByEndDateNOI() {
		assertEquals(0, fcService.findMonthlyFundingCyclesByEndDateNOI(Integer.MAX_VALUE).size());
		assertTrue(0 < fcService.findMonthlyFundingCyclesByEndDateNOI(8).size());
	}

	@Tag("user_story_19201")
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
		fc.setFundingOpportunity(foService.findFundingOpportunityById(1L));
		fc.setIsOpen(false);
		fc.setExpectedApplications(100L);

		assertNotNull(fcService.saveFundingCycle(fc).getId());
		assertEquals(initFCCount + 1, fcRepo.count());
	}

	@Tag("user_story_19201")
	@WithMockUser(username = "jfs")
	@Test
	public void test_buProgramLeadCanCreateFundingCycle() {
		long initFCCount = fcRepo.count();

		FundingCycle fc = new FundingCycle();
		fc.setIsOpen(true);
		fc.setStartDate(LocalDate.of(2020, 1, 1));
		fc.setExpectedApplications(1_000L);
		fc.setFiscalYear(fyService.findFiscalYearById(4L));
		fc.setFundingOpportunity(foService.findFundingOpportunityById(77L));

		// jfs can create a FC for FO id=77 since he's a Program Lead for BU id=2
		fcService.saveFundingCycle(fc);
		assertEquals(initFCCount + 1, fcRepo.count());

		// jfs cannot create a FC for FO id=17 since he's a Program Officer for BU id=3
		fc.setFundingOpportunity(foService.findFundingOpportunityById(17L));
		assertThrows(AccessDeniedException.class, () -> fcService.saveFundingCycle(fc));

		// jfs cannot create a FC for FO id=35 since he's not even a member of BU id=4
		fc.setFundingOpportunity(foService.findFundingOpportunityById(35L));
		assertThrows(AccessDeniedException.class, () -> fcService.saveFundingCycle(fc));
	}

	@WithAnonymousUser
	@Test
	public void test_findFundingCyclesByFundingOpportunityMap() {
		Map<Long, FundingCycle> fcsByFosMap = fcService.findFundingCyclesByFundingOpportunityMap();
		assertNotNull(fcsByFosMap);
		assertTrue(0 < fcsByFosMap.size());
	}

	@Tag("user_story_14750")
	@WithAnonymousUser
	@Test
	public void test_anonUserCanFindAllFCsForOneFO() {
		List<FundingCycleProjection> fcProjections = fcService.findFCsForBrowseViewFO(100L);

		assertEquals(1, fcProjections.size());
		assertEquals(9_162, fcProjections.get(0).getNumAppsExpected());
	}

	@Tag("user_story_19213")
	@WithMockUser(roles = "MDM ADMIN")
	@Test
	public void test_adminCanDeleteFC() {
		long initFCCount = fcRepo.count();

		fcService.deleteFundingCycle(141L);

		assertEquals(initFCCount - 1, fcRepo.count());
	}

	@Tag("user_story_19213")
	@WithMockUser(username = "dev")
	@Test
	public void test_buProgramLeadCanDeleteFC() {
		long initFCCount = fcRepo.count();

		fcService.deleteFundingCycle(127L);

		assertEquals(initFCCount - 1, fcRepo.count());
	}

	@Tag("user_story_19213")
	@WithMockUser(username = "dev")
	@Test
	public void test_buProgramOfficerCannotDeleteFC() {
		assertThrows(AccessDeniedException.class, () -> fcService.deleteFundingCycle(11L));
	}

	@Tag("user_story_19213")
	@WithMockUser(username = "dev")
	@Test
	public void test_buNonMemberCannotDeleteFC() {
		assertThrows(AccessDeniedException.class, () -> fcService.deleteFundingCycle(1L));
	}

}
