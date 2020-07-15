package ca.gc.tri_agency.granting_data.fundingopportunityintegrationtest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.repo.FundingOpportunityRepository;
import ca.gc.tri_agency.granting_data.service.AgencyService;
import ca.gc.tri_agency.granting_data.service.FundingOpportunityService;

@SpringBootTest(classes = GrantingDataApp.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class FundingOpportunityServiceTest {

	@Autowired
	private FundingOpportunityService foService;

	@Autowired
	private AgencyService agencyService;

	@Autowired
	private FundingOpportunityRepository foRepo;

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
			if (strArr[2].equals("ADD")) {
				++numAdds;
			}
		}

		assertTrue(numAdds >= 141);
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test(expected = AccessDeniedException.class)
	public void test_nonAdminCannotFindAllFundingOpportunitiesRevisions() {
		foService.findAllFundingOpportunitiesRevisions();
	}

	@WithAnonymousUser
	@Test
	public void test_anonUserCanFindFundingOpportunitiesByAgency() {
		assertEquals(43, foService.findFundingOpportunitiesByAgency(agencyService.findAgencyById(1L)).size());
	}

	@WithAnonymousUser
	@org.junit.jupiter.api.Test
	@Tag("User_Story_14627")
	public void test_anonUserCanFindResultsForGoldenListTable() {
		long foCount = foRepo.count();

		LocaleContextHolder.setDefaultLocale(Locale.CANADA_FRENCH);
		List<String[]> frResultSet = foService.findGoldenListTableResults();
		String[] frRow = frResultSet.stream().filter(arr -> arr[0].equals("9")).findFirst().get();

		assertEquals(foCount, frResultSet.size());
		assertArrayEquals(new String[] { "9", "Bourse pour ambassadeurs autochtones des sciences naturelles et du génie", "FR ICSP",
				"SP Secure Upload", "NAMIS" }, frRow);

		LocaleContextHolder.setDefaultLocale(Locale.ENGLISH);
		List<String[]> enResultSet = foService.findGoldenListTableResults();
		String[] enRow = enResultSet.stream().filter(arr -> arr[0].equals("131")).findFirst().get();

		assertEquals(foCount, enResultSet.size());
		assertArrayEquals(new String[] { "131", "Business-Led Networks of Centres of Excellence program", "NCE", "SP Secure Upload",
				"AMIS / NAMIS / ResearchNet" }, enRow);
	}

}
