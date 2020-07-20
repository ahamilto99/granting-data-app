package ca.gc.tri_agency.granting_data.memberroleintegrationtest;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.model.BusinessUnit;
import ca.gc.tri_agency.granting_data.model.MemberRole;
import ca.gc.tri_agency.granting_data.model.Role;
import ca.gc.tri_agency.granting_data.repo.MemberRoleRepository;
import ca.gc.tri_agency.granting_data.service.BusinessUnitService;
import ca.gc.tri_agency.granting_data.service.MemberRoleService;
import ca.gc.tri_agency.granting_data.service.RoleService;

@SpringBootTest(classes = GrantingDataApp.class)
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
		memberRole.setEdiAuthorized(true);

		MemberRole mr = mrService.saveMemberRole(memberRole);

		assertEquals(initMRCount + 1, mrRepo.count());
		assertNotNull(mr.getId());
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotCreateMR_shouldThrowAccessDeniedException() {
		MemberRole mr = new MemberRole();
		mr.setUserLogin(RandomStringUtils.randomAlphabetic(3));
		mr.setRole(rService.findRoleById(1L));
		mr.setBusinessUnit(buService.findBusinessUnitById(1L));
		mr.setEdiAuthorized(true);

		assertThrows(AccessDeniedException.class, () -> mrService.saveMemberRole(mr));
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
	@Test
	public void test_nonAdminCannotDeleteMR_shouldThrowAccessDeniedException() {
		assertThrows(AccessDeniedException.class, () -> mrService.deleteMemberRole(2L));
	}

	@WithMockUser(username = "admin", roles = "MDM ADMIN")
	@Test
	public void test_adminCanFindMemberRoleRevisionsById() {
		final Long mrId = 1L;
		int startNumRevisions = mrService.findMemberRoleRevisionsById(mrId).size();

		MemberRole mr = mrService.findMemberRoleById(mrId);
		String revisedUserLogin = RandomStringUtils.randomAlphabetic(3);
		mr.setUserLogin(revisedUserLogin);
		mrService.saveMemberRole(mr);

		List<String[]> mrRevisions = mrService.findMemberRoleRevisionsById(mrId);
		int endNumRevisions = mrRevisions.size();

		assertEquals(startNumRevisions + 1, endNumRevisions);
		assertEquals(revisedUserLogin, mrRevisions.get(endNumRevisions - 1)[3]);
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotFindMemberRoleRevisionsById_shouldThrowAccessDeniedException() {
		assertThrows(AccessDeniedException.class, () -> mrService.findMemberRoleRevisionsById(1L));
	}

	@WithMockUser(username = "admin", roles = "MDM ADMIN")
	@Test
	public void test_adminCanFindAllMemberRoleRevisions() {
		List<String[]> auditedArrList = mrService.findAllMemberRoleRevisions();
		assertNotNull(auditedArrList);

		int numAdds = 0;
		for (String[] strArr : auditedArrList) {
			if (strArr[2].equals("ADD")) {
				++numAdds;
			}
		}

		assertTrue(numAdds >= 3);
	}

	@WithMockUser(roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test
	public void test_nonAdminCannotFindAllMemberRoleRevisions_shouldThrowAccessDeniedException() {
		assertThrows(AccessDeniedException.class, () -> mrService.findAllMemberRoleRevisions());
	}

	@Tag("user_story_19147")
	@WithMockUser(username = "aha")
	@Test
	public void test_checkIfCurrentUserEdiAuthorized() {
		// user "aha" is a member of BU 1 but does not have EDI permission
		assertThrows(AccessDeniedException.class, () -> mrService.checkIfCurrentUserEdiAuthorized(1L));
		// user "aha" is not a member of BU 2
		assertThrows(AccessDeniedException.class, () -> mrService.checkIfCurrentUserEdiAuthorized(2L));
		// user "aha" is a member of BU 13 and does have EDI permission
		assertThatCode(() -> mrService.checkIfCurrentUserEdiAuthorized(13L)).doesNotThrowAnyException();
	}
	
	@Tag("user_story_19147")
	@WithMockUser(roles = "MDM ADMIN")
	@Test
	public void test_adminUserIsAlwaysEdiAuthorized() {
		assertThatCode(() -> mrService.checkIfCurrentUserEdiAuthorized(1L)).doesNotThrowAnyException();
	}

}






















