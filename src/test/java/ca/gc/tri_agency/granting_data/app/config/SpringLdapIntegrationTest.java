package ca.gc.tri_agency.granting_data.app.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.ldap.ADUser;
import ca.gc.tri_agency.granting_data.ldap.ADUserService;

@SpringBootTest(classes = GrantingDataApp.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class SpringLdapIntegrationTest {

	@Autowired
	private ADUserService adUserService;

	@Test
	public void testFindAllADUsers() {
		List<ADUser> users = adUserService.findAllADUsers();
		assertNotNull(users);
		assertEquals(9, users.size());
	}

	@Test
	public void testFindAllADUserFullNames() {
		List<String> persons = adUserService.findAllADUserFullNames();
		assertNotNull(persons);
		assertEquals(9, persons.size());
	}

	@Test
	public void testFindNsercADUser() {
		ADUser user = adUserService.findADUserByDn("uid=admin,ou=NSERC_Users");
		assertNotNull(user);
		assertEquals("Admin User", user.getFullName());
		
		// verify NameNotFoundException handling
		assertNull(adUserService.findADUserByDn("uid=ZZZZZ,ou=ZZZZZ"));
	}

	@Test
	public void testFindSshrcADUser() {
		ADUser user = adUserService.findADUserByDn("uid=sshrc-admin,ou=SSHRC_Users");
		assertNotNull(user);
		assertEquals("SSHRC Admin", user.getFullName());
	}
	
	@Test
	public void testSearchADUsersForMemberRoleCreation() {
		assertEquals(6, adUserService.searchADUsers("user").size());
		assertEquals(0, adUserService.searchADUsers("ZZZZZ").size());
	}

	@Test
	public void testFindDnByUserLogin() {
		// NSERC ADUser
		assertEquals("uid=nserc1-user,ou=NSERC_Users", adUserService.findDnByADUserLogin("nserc1-user"));
		// SSHRC ADUser
		assertEquals("uid=sshrc-user-edi,ou=SSHRC_Users", adUserService.findDnByADUserLogin("sshrc-user-edi"));
		// verify no ADUser found
		assertNull(adUserService.findDnByADUserLogin("ZZZZZ"));
	}
	
}
