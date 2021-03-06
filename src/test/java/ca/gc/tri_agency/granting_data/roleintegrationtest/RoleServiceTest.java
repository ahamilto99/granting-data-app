package ca.gc.tri_agency.granting_data.roleintegrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.model.Role;
import ca.gc.tri_agency.granting_data.repo.RoleRepository;
import ca.gc.tri_agency.granting_data.service.RoleService;

@SpringBootTest(classes = GrantingDataApp.class)
@ActiveProfiles("test")
public class RoleServiceTest {

	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleRepository roleRepo;

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void test_adminCanCreateRole() {
		long initRoleCount = roleRepo.count();

		String nameEn = RandomStringUtils.randomAlphabetic(10);
		String nameFr = RandomStringUtils.randomAlphabetic(10);

		Role newRole = roleService.saveRole(new Role(nameEn, nameFr));

		assertEquals(initRoleCount + 1, roleRepo.count());

		assertNotNull(newRole.getId());
		assertEquals(nameEn, newRole.getNameEn());
		assertEquals(nameFr, newRole.getNameFr());
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotCreateRole() {
		String nameEn = RandomStringUtils.randomAlphabetic(10);
		String nameFr = RandomStringUtils.randomAlphabetic(10);

		assertThrows(AccessDeniedException.class, () -> roleService.saveRole(new Role(nameEn, nameFr)));
	}

}
