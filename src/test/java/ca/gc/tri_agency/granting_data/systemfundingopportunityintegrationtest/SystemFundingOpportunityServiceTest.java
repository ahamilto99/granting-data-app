package ca.gc.tri_agency.granting_data.systemfundingopportunityintegrationtest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.service.SystemFundingOpportunityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = GrantingDataApp.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class SystemFundingOpportunityServiceTest {
	
	@Autowired
	private SystemFundingOpportunityService sfoService;
	
	@WithAnonymousUser
	@Test
	public void testFindSystemFundingOpportunityById_shouldSucceed() {
		assertNotNull(sfoService.findSystemFundingOpportunityById(1L));
	}
	
	@WithAnonymousUser
	@Test(expected = DataRetrievalFailureException.class)
	public void testFindSystemFundingOpportunityById_shouldThrowDataRetrievalFailureException() {
		sfoService.findSystemFundingOpportunityById(Long.MAX_VALUE);
	}
	
	@WithAnonymousUser
	@Test
	public void testFindAllSystemFundingOpportunities() {
		assertTrue(0 < sfoService.findAllSystemFundingOpportunities().size());
	}
	
	@WithAnonymousUser
	@Test
	public void testFindSystemFundingOpportunitiesByLinkedFOid() {
		assertTrue(0 < sfoService.findSystemFundingOpportunitiesByLinkedFOid(1L).size());
	}

	@WithAnonymousUser
	@Test
	public void testFindSystemFundingOpportunitiesByExtId() {
		assertTrue(0 < sfoService.findSystemFundingOpportunitiesByExtId("SAMPLE EXT ID").size());
	}
	
	@WithAnonymousUser
	@Test
	public void testFindSystemFundingOpportunitiesByNameEn() {
		assertTrue(0 < sfoService.findSystemFundingOpportunitiesByNameEn("NAME EN").size());
	}
	
}

























