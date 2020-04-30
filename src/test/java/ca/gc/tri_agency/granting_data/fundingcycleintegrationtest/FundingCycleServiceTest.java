package ca.gc.tri_agency.granting_data.fundingcycleintegrationtest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.repo.FundingCycleRepository;
import ca.gc.tri_agency.granting_data.service.FundingCycleService;

@SpringBootTest(classes = GrantingDataApp.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class FundingCycleServiceTest {

	@Autowired
	private FundingCycleService fcService;
	
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
		assertTrue(0 < fcService.findFundingCyclesByFundingOpportunityId(1L).size());
	}
	
//	@WithAnonymousUser
//	@Test
//	public void test_findFundingCyclesByStartDateGreaterThanEqualAndStartDateLessThanOrEndDateGreaterThanEqualAndEndDateLessThan() {
//		assertTrue(0 < fcService.findFundingCyclesByStartDateGreaterThanEqualAndStartDateLessThanOrEndDateGreaterThanEqualAndEndDateLessThan(LocalDate.of(2021, 4, 1), LocalDate.of(2022, 4, 1), LocalDate.of(2021, 5, 1), LocalDate.of(2022, 5, 1)).size());
//	}
	
	@WithAnonymousUser
	@Test
	public void test_findFundingCyclesByFiscalYearId() {
		assertTrue(0 < fcService.findFundingCyclesByFiscalYearId(1L).size());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}




























