package ca.gc.tri_agency.granting_data.grantingcapabilityintegrationtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.model.GrantingCapability;
import ca.gc.tri_agency.granting_data.service.GrantingCapabilityService;

@SpringBootTest(classes = GrantingDataApp.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("local")
public class GrantingCapabilityServiceTest {

	@Autowired
	GrantingCapabilityService gcService;

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test(expected = DataRetrievalFailureException.class)
	public void testService_adminCanDeleteGC() {
		long numGCs = gcService.grantingCapabilityCount();

		gcService.deleteGrantingCapabilityById(100L);

		assertEquals(numGCs - 1, gcService.grantingCapabilityCount());

		gcService.findGrantingCapabilityById(100L);
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test(expected = AccessDeniedException.class)
	public void testService_nonAdminCannotDeleteGC_shouldThrowAccessDeniedExcepction() {
		gcService.deleteGrantingCapabilityById(101L);
	}

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void testService_adminCanEditGC() {
		long initGcRepoCount = gcService.grantingCapabilityCount();

		GrantingCapability gc = gcService.findGrantingCapabilityById(1L);
		String editDescription = "TEST DESCRIPTION EDIT";
		gc.setDescription(editDescription);
		gcService.saveGrantingCapability(gc);

		assertTrue(gcService.findGrantingCapabilityById(1L).getDescription().equals(editDescription));
		assertEquals(initGcRepoCount, gcService.grantingCapabilityCount());
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test(expected = AccessDeniedException.class)
	public void testService_nonAdminCannotEditGC_shouldThrowAccessDeniedException() {
		GrantingCapability gc = gcService.findGrantingCapabilityById(1L);
		String editDescription = "TEST DESCRIPTION EDIT";
		gc.setDescription(editDescription);
		gcService.saveGrantingCapability(gc);
	}

}
