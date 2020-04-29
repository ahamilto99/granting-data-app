package ca.gc.tri_agency.granting_data.fundingcycleintegrationtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
	@Test
	public void test_findFundingCycleById() {
		fcService.findAllFundingCycles().forEach(System.out::println);
	}
	
}




























