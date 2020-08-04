package ca.gc.tri_agency.granting_data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.model.GrantingCapability;

@SpringBootTest(classes = GrantingDataApp.class)
@ActiveProfiles("test")
public class RestrictedDataServiceIntegrationTest {

	@Autowired
	private GrantingSystemService gSystemService;
	@Autowired
	private GrantingStageService gStageService;
	@Autowired
	private FundingOpportunityService foService;
	@Autowired
	private GrantingCapabilityService gcService;

	@Tag("user_story_14572")
	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void test_createGrantingCapabaility() {
		GrantingCapability newGc = new GrantingCapability();
		newGc.setDescription("TEST GRANTING CAPABILITY");
		newGc.setUrl("www.testGrantingCapability.com");
		newGc.setGrantingStage(gStageService.findGrantingStageById(1L));
		newGc.setGrantingSystem(gSystemService.findGrantingSystemById(1L));
		newGc.setFundingOpportunity(foService.findFundingOpportunityById(1L));

		GrantingCapability addedGc = gcService.saveGrantingCapability(newGc);

		assertNotNull(addedGc);

		Long addedGcId = addedGc.getId();
		assertEquals(foService.findFundingOpportunityById(gcService.findGrantingCapabilityById(addedGcId).getFundingOpportunity().getId()).getNameEn(),
				newGc.getFundingOpportunity().getNameEn());
		assertEquals(gcService.findGrantingCapabilityById(addedGcId).getDescription(), newGc.getDescription());
	}

}
