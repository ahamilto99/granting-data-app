package ca.gc.tri_agency.granting_data.systemfundingcycleintegrationtest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ActiveProfiles;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.service.SystemFundingCycleService;

@SpringBootTest(classes = GrantingDataApp.class)
@ActiveProfiles("test")
public class SystemFundingCycleServiceTest {

	@Autowired
	private SystemFundingCycleService sfcService;

	@WithAnonymousUser
	@Test
	public void test_findSystemFundingCycleById_shouldSucceed() {
		assertNotNull(sfcService.findSystemFundingCycleById(1L));
	}

	@WithAnonymousUser
	@Test
	public void test_findSystemFundingCycleById_shouldThrowDataRetrievalFailureException() {
		assertThrows(DataRetrievalFailureException.class, () -> sfcService.findSystemFundingCycleById(Long.MAX_VALUE));
	}

	@WithAnonymousUser
	@Test
	public void test_findAllSystemFundingCycles() {
		assertTrue(0 < sfcService.findAllSystemFundingCycles().size());
	}

	@WithAnonymousUser
	@Test
	public void test_findSFCsByFOid() {
		assertTrue(0 < sfcService.findSFCsBySFOid(1L).size());
	}

}
