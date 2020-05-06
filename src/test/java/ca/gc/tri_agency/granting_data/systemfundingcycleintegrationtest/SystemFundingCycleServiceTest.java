package ca.gc.tri_agency.granting_data.systemfundingcycleintegrationtest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.service.SystemFundingCycleService;

@SpringBootTest(classes = GrantingDataApp.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class SystemFundingCycleServiceTest {

	@Autowired
	private SystemFundingCycleService sfcService;

	@WithAnonymousUser
	@Test
	public void testFindSystemFundingCycleById_succeeds() {
		assertNotNull(sfcService.findSystemFundingCycleById(1L));
	}

	@WithAnonymousUser
	@Test(expected = DataRetrievalFailureException.class)
	public void testFindSystemFundingCycleById_throwsException() {
		sfcService.findSystemFundingCycleById(Long.MAX_VALUE);
	}

	@WithAnonymousUser
	@Test
	public void testFindAllSystemFundingCycles() {
		assertTrue(0 < sfcService.findAllSystemFundingCycles().size());
	}

	@WithAnonymousUser
	@Test
	public void testFindSFCsByFOid() {
		assertTrue(0 < sfcService.findSFCsByFOid(1L).size());
	}

}
