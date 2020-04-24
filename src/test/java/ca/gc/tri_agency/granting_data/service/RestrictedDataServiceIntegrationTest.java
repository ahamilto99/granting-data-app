package ca.gc.tri_agency.granting_data.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.model.GrantingCapability;
import ca.gc.tri_agency.granting_data.repo.FundingOpportunityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GrantingDataApp.class)
@ActiveProfiles("test")
public class RestrictedDataServiceIntegrationTest {

	@Autowired
	private GrantingSystemService gSystemService;
	@Autowired
	private GrantingStageService gStageService;
	@Autowired
	private FundingOpportunityRepository foRepo;
	@Autowired
	private GrantingCapabilityService gcService;

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void test_createGrantingCapabaility() {
		GrantingCapability newGc = new GrantingCapability();
		newGc.setDescription("TEST GRANTING CAPABILITY");
		newGc.setUrl("www.testGrantingCapability.com");
		newGc.setGrantingStage(gStageService.findAllGrantingStages().get(0));
		newGc.setGrantingSystem(gSystemService.findAllGrantingSystems().get(0));
		newGc.setFundingOpportunity(foRepo.findAll().get(0));

		GrantingCapability addedGc = gcService.saveGrantingCapability(newGc);

		assertNotNull(addedGc);

		Long addedGcId = addedGc.getId();
		assertEquals(gcService.findGrantingCapabilityById(addedGcId).getFundingOpportunity().getNameEn(),
				newGc.getFundingOpportunity().getNameEn());
		assertEquals(gcService.findGrantingCapabilityById(addedGcId).getDescription(), newGc.getDescription());
	}

}
