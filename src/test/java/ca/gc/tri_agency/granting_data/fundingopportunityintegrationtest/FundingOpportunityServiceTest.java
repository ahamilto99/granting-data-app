package ca.gc.tri_agency.granting_data.fundingopportunityintegrationtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.service.FundingOpportunityService;

@SpringBootTest(classes = GrantingDataApp.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class FundingOpportunityServiceTest {

	@Autowired
	private FundingOpportunityService foService;

	@WithAnonymousUser
	@Test
	public void test_findFundingOpportunitiesByNameEn() {
		assertEquals(0, foService.findFundingOpportunitiesByNameEn("ZZZZZZZZZZ").size());
		assertEquals(1, foService.findFundingOpportunitiesByNameEn("Digging into Data").size());
	}

	@WithAnonymousUser
	@Test
	public void test_findFundingOpportunitiesByBusinessUnit() {
		assertEquals(0, foService.findFundingOpportunitiesByBusinessUnitId(Long.MAX_VALUE).size());
		assertTrue(0 < foService.findFundingOpportunitiesByBusinessUnitId(1L).size());
	}

	@WithMockUser(username = "admin", roles = "MDM ADMIN")
	@Test
	public void test_setFundingOpportunityLeadContributor() {
		Long foId = 3L;

		FundingOpportunity fo = foService.findFundingOpportunityById(foId);
		String initProgamLeadDn = fo.getProgramLeadDn();
		String initProgramLeadName = fo.getProgramLeadName();

		String newLeadDn = "uid=nserc1-user,ou=NSERC_Users";

		foService.setFundingOpportunityLeadContributor(foId, newLeadDn);

		FundingOpportunity updatedFo = foService.findFundingOpportunityById(foId);

		assertNotEquals(initProgamLeadDn, updatedFo.getProgramLeadDn());
		assertNotEquals(initProgramLeadName, updatedFo.getProgramLeadName());

		assertEquals(newLeadDn, updatedFo.getProgramLeadDn());
		assertEquals("NSERC1 User", updatedFo.getProgramLeadName());
	}

	@WithMockUser(username = "admin", roles = "MDM ADMIN")
	@Test
	public void test_adminCanFindFundingOpportunityRevisionsById() {
		final Long foId = 1L;
		int startNumRevs = foService.findFundingOpportunityRevisionsById(foId).size();

		FundingOpportunity fo = foService.findFundingOpportunityById(foId);
		String newNameEn = RandomStringUtils.randomAlphabetic(15);
		String newNameFr = RandomStringUtils.randomAlphabetic(15);
		fo.setNameEn(newNameEn);
		fo.setNameFr(newNameFr);
		foService.saveFundingOpportunity(fo);

		List<String[]> foRevs = foService.findFundingOpportunityRevisionsById(foId);
		int endNumRevs = foRevs.size();

		assertEquals(startNumRevs + 1, endNumRevs);
		assertEquals("MOD", foRevs.get(endNumRevs - 1)[2]);
		assertEquals(newNameEn, foRevs.get(endNumRevs - 1)[3]);
		assertEquals(newNameFr, foRevs.get(endNumRevs - 1)[4]);

		int numAdds = 0;
		for (String[] arr : foRevs) {
			if (arr[2].equals("ADD")) {
				++numAdds;
			}
		}
		assertEquals(1, numAdds);
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test(expected = AccessDeniedException.class)
	public void test_nonAdminCannotFindFundingOpportunityRevisionsById_shouldThrowException() {
		foService.findFundingOpportunityRevisionsById(1L);
	}

	@WithMockUser(username = "admin", roles = "MDM ADMIN")
	@Test
	public void test_adminCanFindAllFundingOpportunitiesRevisions() {
		int numAdds = 0;

		List<String[]> foRevs = foService.findAllFundingOpportunitiesRevisions();
		for (String[] strArr : foRevs) {
			if (strArr[1].equals("ADD"))
				;
			++numAdds;
		}

		assertTrue(numAdds >= 141);
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test(expected = AccessDeniedException.class)
	public void test_nonAdminCannotFindAllFundingOpportunitiesRevisions() {
		foService.findAllFundingOpportunitiesRevisions();
	}

}
