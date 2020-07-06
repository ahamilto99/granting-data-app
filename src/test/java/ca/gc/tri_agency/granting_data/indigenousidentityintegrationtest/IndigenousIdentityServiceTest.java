package ca.gc.tri_agency.granting_data.indigenousidentityintegrationtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.model.IndigenousIdentity;
import ca.gc.tri_agency.granting_data.service.IndigenousIdentitySerivce;

@SpringBootTest(classes = GrantingDataApp.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class IndigenousIdentityServiceTest {

	@Autowired
	private IndigenousIdentitySerivce indigenousIdentitySerivce;

	@WithAnonymousUser
	@Test
	public void test_anonUserCanFindIndigenousIdentityByNameEn() {
		assertEquals(new Long(2), indigenousIdentitySerivce.findIndigenousIdentityByNameEn("Inuit").getId());
	}

	@WithAnonymousUser
	@Test
	public void test_anonUserCanFindIndigenousIdentityByNameFr() {
		assertEquals(new Long(2), indigenousIdentitySerivce.findIndigenousIdentityByNameFr("Inuit").getId());
	}

	@WithAnonymousUser
	@Test
	public void test_anonUserCanFindAllIndigenousIdentitiest() {
		assertTrue(6 <= indigenousIdentitySerivce.findAllIndigenousIdentities().size());
	}

	@WithMockUser(username = "mock_admin", roles = "MDM ADMIN")
	@Test
	public void test_adminCanSaveIndigenousIdentity() {
		String nameEn = RandomStringUtils.randomAlphabetic(10);
		String nameFr = RandomStringUtils.randomAlphabetic(10);
		IndigenousIdentity identity = new IndigenousIdentity();
		identity.setNameEn(nameEn);
		identity.setNameFr(nameFr);

		indigenousIdentitySerivce.saveIndigenousIdentity(identity);

		assertEquals(nameEn, indigenousIdentitySerivce.findIndigenousIdentityById(7L).getNameEn());
	}

	@WithMockUser(username = "mock_agency_user", roles = { "NSERC_USER", "SSHRC_USER", "AGENCY_USER" })
	@Test(expected = AccessDeniedException.class)
	public void test_nonAdminCannotSaveIndigenousIdentity_shouldThrowException() {
		indigenousIdentitySerivce.saveIndigenousIdentity(new IndigenousIdentity());
	}

}
