package ca.gc.tri_agency.granting_data.app.grantingstageintegrationtest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.service.GrantingStageService;

@SpringBootTest(classes = GrantingDataApp.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class GrantingStageServiceTest {
	
	@Autowired
	private GrantingStageService gStageService;
	
	@Test(expected = DataRetrievalFailureException.class)
	public void test_findGrantingStageById() {
		assertNotNull(gStageService.findGrantingStageById(1L));
		
		gStageService.findGrantingStageById(Long.MAX_VALUE);
	}
	
	@Test
	public void test_findAllGrantingStages() {
		assertTrue(0 < gStageService.findAllGrantingStages().size());
	}

}
