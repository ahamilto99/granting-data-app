package ca.gc.tri_agency.granting_data.visibleminorityintegrationtest;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.model.VisibleMinority;
import ca.gc.tri_agency.granting_data.repo.VisibleMinorityRepository;
import ca.gc.tri_agency.granting_data.service.VisibleMinorityService;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = GrantingDataApp.class)
@ActiveProfiles("test")
public class VisibleMinorityServiceTest {

	@Autowired
	private VisibleMinorityService vMinorityService;

	@Autowired
	private VisibleMinorityRepository vMinorityRepo;

	@WithAnonymousUser
	@Test
	public void test_anonUserCanFindVisibleMinorityById() {
		assertEquals("Latin American", vMinorityService.findVisibleMinorityById(1L).getNameEn());
	}

	@WithAnonymousUser
	@Test
	public void test_anonUserCanFindVisibleMinorityByNameEn() {
		assertEquals(new Long(2L), vMinorityService.findVisibleMinorityByNameEn("Caribbean").getId());
	}

	@WithAnonymousUser
	@Test
	public void test_anonUserCanFindVisibleMinorityByNameFr() {
		assertEquals(new Long(3L), vMinorityService.findVisibleMinorityByNameFr("Moyen Orient").getId());
	}
	
	@WithAnonymousUser
	@Test
	public void test_anonUserCanFindAllVisilbeMinorities() {
		assertTrue(9 <= vMinorityService.findAllVisibleMinorities().size());
	}

	@WithMockUser(username = "mock_admin", roles = "MDM ADMIN")
	@Test
	public void test_adminUserCanSaveVisibleMinority() {
		long initNumVMins = vMinorityRepo.count();

		String nameEn = RandomStringUtils.randomAlphabetic(10);
		String nameFr = RandomStringUtils.randomAlphabetic(10);
		VisibleMinority vMin = new VisibleMinority();
		vMin.setNameEn(nameEn);
		vMin.setNameFr(nameFr);

		vMinorityService.saveVisibleMinority(vMin);

		assertEquals(initNumVMins + 1, vMinorityRepo.count());
	}

	@WithMockUser(username = "mock_agency_user", roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotSaveVisibleMinority_shouldThrowException() {
		assertThrows(AccessDeniedException.class, () -> vMinorityService.saveVisibleMinority(new VisibleMinority()));
	}
}
