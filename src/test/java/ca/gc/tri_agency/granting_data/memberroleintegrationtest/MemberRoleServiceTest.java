package ca.gc.tri_agency.granting_data.memberroleintegrationtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.model.BusinessUnit;
import ca.gc.tri_agency.granting_data.model.MemberRole;
import ca.gc.tri_agency.granting_data.model.Role;
import ca.gc.tri_agency.granting_data.repo.MemberRoleRepository;
import ca.gc.tri_agency.granting_data.service.BusinessUnitService;
import ca.gc.tri_agency.granting_data.service.MemberRoleService;
import ca.gc.tri_agency.granting_data.service.RoleService;

@SpringBootTest(classes = GrantingDataApp.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class MemberRoleServiceTest {

	@Autowired
	private MemberRoleService mrService;
	@Autowired
	private RoleService rService;
	@Autowired
	private BusinessUnitService buService;

	@Autowired
	private MemberRoleRepository mrRepo;

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Test
	public void test_adminCanCreateMR() {
		long initMRCount = mrRepo.count();

		String userLogin = RandomStringUtils.randomAlphabetic(3);
		Role role = rService.findRoleById(1L);
		BusinessUnit bu = buService.findBusinessUnitById(1L);

		MemberRole memberRole = new MemberRole();
		memberRole.setUserLogin(userLogin);
		memberRole.setRole(role);
		memberRole.setBusinessUnit(bu);

		MemberRole mr = mrService.saveMemberRole(memberRole);

		assertEquals(initMRCount + 1, mrRepo.count());
		assertNotNull(mr.getId());
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test(expected = AccessDeniedException.class)
	public void test_nonAdminCannotCreateMR_shouldThrowAccessDeniedException() {
		MemberRole mr = new MemberRole();
		mr.setUserLogin(RandomStringUtils.randomAlphabetic(3));
		mr.setRole(rService.findRoleById(1L));
		mr.setBusinessUnit(buService.findBusinessUnitById(1L));

		mrService.saveMemberRole(mr);
	}

	@WithMockUser(username = "admin", roles = { "MDM ADMIN" })
	@Rollback
	@Test
	public void test_adminCanDeleteMR() {
		long initMRCount = mrRepo.count();

		mrService.deleteMemberRole(1L);

		assertEquals(initMRCount - 1, mrRepo.count());
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test(expected = AccessDeniedException.class)
	public void test_nonAdminCannotDeleteMR_shouldThrowAccessDeniedException() {
		mrService.deleteMemberRole(mrService.findAllMemberRoles().get(0).getId());
	}

}
