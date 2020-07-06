package ca.gc.tri_agency.granting_data.applicationparticipationintegrationtest;

import static org.junit.Assert.assertEquals;

import java.time.Instant;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ca.gc.tri_agency.granting_data.app.GrantingDataApp;
import ca.gc.tri_agency.granting_data.model.ApplicationParticipation;
import ca.gc.tri_agency.granting_data.repo.ApplicationParticipationRepository;
import ca.gc.tri_agency.granting_data.service.GenderService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GrantingDataApp.class)
@ActiveProfiles("test")
public class BrowseApplicationParticipationIntegrationTest {

    @Autowired
    private ApplicationParticipationRepository apRepo;
    
    @Autowired 
    private GenderService genderService;

    @Test
    public void test_repo() {
        long initApRepoCount = apRepo.count();
        
        ApplicationParticipation ap = new ApplicationParticipation();
        String appIdentifier = RandomStringUtils.randomAlphabetic(10);
        ap.setApplicationIdentifier(appIdentifier);
        ap.setCompetitionYear(2022L);
        ap.setCountry(RandomStringUtils.randomAlphabetic(10));
        Instant currentTimestamp = Instant.now();
        ap.setCreateDate(currentTimestamp);
	ap.setCreateUserId(RandomStringUtils.randomAlphabetic(3));
	ap.setFamilyName(RandomStringUtils.randomAlphabetic(10));
	ap.setFreeformAddress1(RandomStringUtils.randomAlphabetic(10));
	ap.setFreeformAddress2(RandomStringUtils.randomAlphabetic(10));
	ap.setFreeformAddress3(RandomStringUtils.randomAlphabetic(10));
	ap.setFreeformAddress4(RandomStringUtils.randomAlphabetic(10));
	ap.setGivenName(RandomStringUtils.randomAlphabetic(10));
	ap.setMunicipality(RandomStringUtils.randomAlphabetic(10));
	ap.setOrganizationNameEn(RandomStringUtils.randomAlphabetic(10));
	ap.setOrganizationNameFr(RandomStringUtils.randomAlphabetic(10));
	ap.setOrganizationId("1");
	ap.setPersonIdentifier(99L);
	ap.setPostalZipCode(RandomStringUtils.randomAlphabetic(7));
	ap.setProgramEn(RandomStringUtils.randomAlphabetic(10));
	ap.setProgramFr(RandomStringUtils.randomAlphabetic(10));
	ap.setProgramId(RandomStringUtils.randomAlphabetic(10));
	ap.setProvinceStateCode(RandomStringUtils.randomAlphabetic(2));
	ap.setRoleCode("1234");
	ap.setRoleEn(RandomStringUtils.randomAlphabetic(10));
	ap.setRoleFr(RandomStringUtils.randomAlphabetic(10));
	ap.setGender(genderService.findGenderByNameEn("female"));

        apRepo.save(ap);
        
        ApplicationParticipation savedAp = apRepo.findById(1L).get();
        
        assertEquals(initApRepoCount + 1, apRepo.count());
        assertEquals(appIdentifier, savedAp.getApplicationIdentifier());
        assertEquals(currentTimestamp, savedAp.getCreateDate());
        assertEquals("female", savedAp.getGender().getNameEn());
        
    }
    
}
