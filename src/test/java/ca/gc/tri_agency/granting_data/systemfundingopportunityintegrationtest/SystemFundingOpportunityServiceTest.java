package ca.gc.tri_agency.granting_data.systemfundingopportunityintegrationtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
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
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.service.GrantingSystemService;
import ca.gc.tri_agency.granting_data.service.SystemFundingOpportunityService;

@SpringBootTest(classes = GrantingDataApp.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class SystemFundingOpportunityServiceTest {

	@Autowired
	private SystemFundingOpportunityService sfoService;

	@Autowired
	private GrantingSystemService gSystemService;

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
		assertTrue(0 < sfoService.findSystemFundingOpportunitiesByExtId("CTAC").size());
	}

	@WithAnonymousUser
	@Test
	public void testFindSystemFundingOpportunitiesByNameEn() {
		assertTrue(0 < sfoService.findSystemFundingOpportunitiesByNameEn("Technology Access Centre").size());
	}

	@WithMockUser(username = "mock_admin", roles = { "MDM ADMIN" })
	@Test
	public void testLinkSystemFundingOpportunity() {
		SystemFundingOpportunity sfo = new SystemFundingOpportunity();
		sfo.setExtId(RandomStringUtils.randomAlphabetic(10));
		sfo.setNameEn(RandomStringUtils.randomAlphabetic(25));
		sfo.setNameFr(RandomStringUtils.randomAlphabetic(25));
		sfo.setGrantingSystem(gSystemService.findGrantingSystemById(1L));

		long sfoId = sfoService.saveSystemFundingOpportunity(sfo).getId();

		assertNull(sfoService.findSystemFundingOpportunityById(sfoId).getLinkedFundingOpportunity());

		sfoService.linkSystemFundingOpportunity(sfoId, 1L);

		assertEquals(1L, (long) sfoService.findSystemFundingOpportunityById(sfoId).getLinkedFundingOpportunity().getId());
	}

	@WithMockUser(username = "mock_admin", roles = { "MDM ADMIN" })
	@Test(expected = DataRetrievalFailureException.class)
	public void testUnlinkSystemFundingOpportunity_shouldThrowDataRetrievalFailureException() {
		sfoService.unlinkSystemFundingOpportunity(1L, 100L);
	}

	@WithMockUser(username = "mock_admin", roles = "MDM ADMIN")
	@Test
	public void testAdminCanFindAllSystemFundingOpportunityRevisions() {
		List<String[]> revisionList = sfoService.findAllSystemFundingOpportunityRevisions();

		int numAdds = 0;

		for (String[] strArr : revisionList) {
			if (strArr[2].equals("ADD")) {
				++numAdds;
			}
		}

		assertTrue(numAdds >= 123);
	}

	@WithMockUser(username = "mock_agency_user", roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test(expected = AccessDeniedException.class)
	public void testNonAdminCannotFindAllSystemFundingOpportunityRevisionsShouldThrowsException() {
		sfoService.findAllSystemFundingOpportunityRevisions();
	}

	@WithMockUser(username = "mock_admin", roles = "MDM ADMIN")
	@Test
	public void testAdminCanFindSystemFundingOpportunityRevisionById() {
		List<String[]> revisionList = sfoService.findSystemFundingOpportunityRevisionById(1L);

		int numAdds = 0;

		for (String[] strArr : revisionList) {
			if (strArr[2].equals("ADD")) {
				++numAdds;
			}
		}

		assertEquals(1, numAdds);
	}

	@WithMockUser(username = "mock_agency_user", roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test(expected = AccessDeniedException.class)
	public void testNonAdminCannotFindSystemFundingOpportunityRevisionByIdShouldThrowException() {
		sfoService.findSystemFundingOpportunityRevisionById(1L);
	}
}
