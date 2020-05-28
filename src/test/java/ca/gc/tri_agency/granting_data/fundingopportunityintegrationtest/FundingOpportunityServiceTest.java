package ca.gc.tri_agency.granting_data.fundingopportunityintegrationtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.service.BusinessUnitService;
import ca.gc.tri_agency.granting_data.service.FundingOpportunityService;

@SpringBootTest(classes = GrantingDataApp.class)
@ActiveProfiles("test")
public class FundingOpportunityServiceTest {

	@Autowired
	private FundingOpportunityService foService;

	@Autowired
	private BusinessUnitService buService;

	@WithAnonymousUser
	@Test
	public void test_findFundingOpportunitiesByNameEn() {
		assertTrue(0 == foService.findFundingOpportunitiesByNameEn("ZZZZZZZZZZ").size());

		assertTrue(1 == foService.findFundingOpportunitiesByNameEn("Digging into Data").size());
	}

	@WithAnonymousUser
	@Test
	public void test_findFundingOpportunitiesByBusinessUnit() {
		assertTrue(0 == foService.findFundingOpportunitiesByBusinessUnitId(Long.MAX_VALUE).size());

		assertTrue(8 == foService.findFundingOpportunitiesByBusinessUnitId(1L).size());
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

}
