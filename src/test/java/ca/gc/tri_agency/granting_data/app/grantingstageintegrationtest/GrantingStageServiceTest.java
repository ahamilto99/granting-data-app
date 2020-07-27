package ca.gc.tri_agency.granting_data.app.grantingstageintegrationtest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.context.ActiveProfiles;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.service.GrantingStageService;

@SpringBootTest(classes = GrantingDataApp.class)
@ActiveProfiles("test")
public class GrantingStageServiceTest {
	
	@Autowired
	private GrantingStageService gStageService;
	
	@Test
	public void test_findGrantingStageById() {
		assertNotNull(gStageService.findGrantingStageById(1L));
		
		assertThrows(DataRetrievalFailureException.class, () -> gStageService.findGrantingStageById(Long.MAX_VALUE));
	}
	
	@Test
	public void test_findAllGrantingStages() {
		assertTrue(0 < gStageService.findAllGrantingStages().size());
	}

}
