package ca.gc.tri_agency.granting_data.service.impl;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.javafaker.Faker;

import ca.gc.tri_agency.granting_data.model.ApplicationParticipation;
import ca.gc.tri_agency.granting_data.model.GrantingSystem;
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.repo.ApplicationParticipationRepository;
import ca.gc.tri_agency.granting_data.repo.SystemFundingOpportunityRepository;
import ca.gc.tri_agency.granting_data.security.SecurityUtils;
import ca.gc.tri_agency.granting_data.service.ApplicationParticipationService;

@Service
public class ApplicationParticipationServiceImpl implements ApplicationParticipationService {
	Map<GrantingSystem, ReferenceBean[]> roleMap;

	private SecureRandom sRand = new SecureRandom();

	private ApplicationParticipationRepository appParticipationRepo;

	private SystemFundingOpportunityRepository sfoRepo;

	private static int applIdIncrementer = 0;

	@Autowired
	public ApplicationParticipationServiceImpl(ApplicationParticipationRepository appParticipationRepo,
			SystemFundingOpportunityRepository sfoRepo) {
		this.appParticipationRepo = appParticipationRepo;
		this.sfoRepo = sfoRepo;
	}

	@Override
	public List<ApplicationParticipation> generateTestAppParticipations(SystemFundingOpportunity sfo, Instant createDate,
			long maxApplications, long maxParticipants) {
		GrantingSystem gs = sfo.getGrantingSystem();
		List<ApplicationParticipation> appPartList = new ArrayList<>();

		int maxApps = sRand.nextInt((int) maxApplications); // nextLong() does not take in a bounds
		for (int i = 0; i < maxApps; i++) {

			String appId = generateTestAppId(gs);
			String applicationIdentifier = generateTestApplicationIdentifier(appId, gs);

			int maxParts = sRand.nextInt((int) maxParticipants);
			for (int j = 0; j < maxParts; j++) {

				ApplicationParticipation app = new ApplicationParticipation();
				app.setApplId(appId + String.format("%04d", ++applIdIncrementer));
				app.setApplicationIdentifier(applicationIdentifier);

				app.setProgramId(sfo.getExtId());
				app.setProgramEn(sfo.getNameEn());
				app.setProgramFr(sfo.getNameFr());

				app.setPersonIdentifier(sRand.nextInt(900_000_000) + 100_000_000L);

				app.setCreateDate(createDate);

				fillInRandomRole(app, gs);
				fillInRandomOrg(app, gs);
				fillInRandomPerson(app, gs);

				appPartList.add(app);
			}

		}
		return appPartList;
	}

	@Override
	public String generateTestAppId(GrantingSystem system) {
		String acronym = system.getAcronym();
		if (acronym.equals("NAMIS")) {
			return String.format("%d", sRand.nextInt(10) + 40);
		} else if (acronym.equals("AMIS")) {
			return Integer.toString(sRand.nextInt(10) + 40);
		} else if (acronym.equals("CRM")) {
			return String.format("%d-%d-%02d", sRand.nextInt(500) + 100, sRand.nextInt(6) + 2017, sRand.nextInt(100));
		}
		return null;
	}

	@Override
	public String generateTestApplicationIdentifier(String appId, GrantingSystem system) {
		String acronym = system.getAcronym();
		if (acronym.equals("NAMIS")) {
			return String.format("%s-%d", appId, sRand.nextInt(6) + 2017);
		} else if (acronym.equals("AMIS")) {
			return String.format("%d-%d-%04d", sRand.nextInt(900) + 100, sRand.nextInt(6) + 2017, sRand.nextInt(3_000));
		} else if (acronym.equals("CRM")) {
			return String.format("%04X%04X-%04X-4%03X-%04X-%06X%06X", sRand.nextInt(65_536), sRand.nextInt(65_536),
					sRand.nextInt(65_536), sRand.nextInt(4_096), sRand.nextInt(65_536), sRand.nextInt(16_777_216),
					sRand.nextInt(16_777_216));
		}
		return null;
	}

	@Override
	public void fillInRandomRole(ApplicationParticipation app, GrantingSystem sys) {
		String[] roleCodeArr = { "1", "2", "147", "8878CE1D-6020-E211-AF1A-005056AD024F",
				"8F78CE1D-6020-E211-AF1A-005056AD024F", "A878CE1D-6020-E211-AF1A-005056AD024F",
				"A978CE1D-6020-E211-AF1A-005056AD024F", "A778CE1D-6020-E211-AF1A-005056AD024F",
				"8C78CE1D-6020-E211-AF1A-005056AD024F" };
		String[] roleEnArr = { "Scholarship Applicant", "Applicant", "Co-Applicant", "Collaborator", "Reader A", "Reader B",
				"Reader C" };
		String[] roleFrArr = { "Candidat à une bourse", "Candidat", "Applicant", "Co-Applicant", "Collaborator", "Lecteur A",
				"Lecteur B", "Lecteur C" };
		String acronym = sys.getAcronym();
		if (acronym.equals("NAMIS")) {
			int idx = sRand.nextInt(2);
			app.setRoleCode(roleCodeArr[idx]);
			app.setRoleEn(roleEnArr[idx]);
			app.setRoleFr(roleFrArr[idx]);
		} else if (acronym.equals("AMIS")) {
			app.setRoleCode(roleCodeArr[2]);
			app.setRoleEn(roleEnArr[1]);
			app.setRoleFr(roleFrArr[1]);
		} else if (acronym.equals("CRM")) {
			int idx = sRand.nextInt(6);
			app.setRoleCode(roleCodeArr[idx + 3]);
			app.setRoleEn(roleEnArr[idx + 1]);
			app.setRoleFr(roleFrArr[idx + 2]);
		}
	}

	@Override
	public void fillInRandomOrg(ApplicationParticipation app, GrantingSystem sys) {
		String acronym = sys.getAcronym();
		int idx = sRand.nextInt(6);
		if (acronym.equals("NAMIS")) {
			String[] idArr = { "27", "28", "24", "89", "43", "9" };
			String[] nameArr = { "McMaster University", "University of Ottawa", "Guelph University",
					"Memorial Univ. of Nfld", "Ryerson University", "University of Alberta" };
			app.setOrganizationId(idArr[idx]);
			app.setOrganizationNameEn(nameArr[idx]);
			app.setOrganizationNameFr(nameArr[idx]);
		} else if (acronym.equals("AMIS")) {
			String[] idArr = { "1240613", "3248462", "1591011", "1351411", "1350711", "1350511" };
			String[] nameEnArr = { "Université du Québec à Montréal",
					"Regroupement des centres d'amitié autochtones du Québec",
					"University of Northern British Columbia", "York University", "University of Ottawa",
					"Laurentian University" };
			String[] nameFrArr = { "Université du Québec à Montréal",
					"Regroupement des centres d'amitié autochtones du Québec",
					"University of Northern British Columbia", "Université York", "Université d'Ottawa",
					"Université Laurentienne" };
			app.setOrganizationId(idArr[idx]);
			app.setOrganizationNameEn(nameEnArr[idx]);
			app.setOrganizationNameFr(nameFrArr[idx]);
		} else if (acronym.equals("CRM")) {
			String[] idArr = { "FDCA4C51-4421-E211-AEEA-005056AD550D", "9384544B-4421-E211-AEEA-005056AD550D",
					"260F4557-4421-E211-AEEA-005056AD550D", "DAFE633F-4421-E211-AEEA-005056AD550D",
					"02CB4C51-4421-E211-AEEA-005056AD550D", "28CA4C51-4421-E211-AEEA-005056AD550D" };
			String[] nameArr = { "University of Calgary: Psychology", "McMaster University: School of Social Work",
					"Wilfrid Laurier University: School of International Policy and Governance",
					"Sheridan College Institute of Technology and Advanced Learning: International Centre",
					"University of Ottawa: Criminologie", "University of Saskatchewan: Philosophy" };
			app.setOrganizationId(idArr[idx]);
			app.setOrganizationNameEn(nameArr[idx]);
			app.setOrganizationNameFr(nameArr[idx]);
		}
	}

	@Override
	public void fillInRandomPerson(ApplicationParticipation app, GrantingSystem sys) {
		String acronym = sys.getAcronym();
		if (acronym.equals("NAMIS") || acronym.equals("AMIS") || acronym.equals("CRM")) {
			Faker nameFaker = new Faker();
			app.setFamilyName(nameFaker.name().lastName());
			app.setGivenName(nameFaker.name().firstName());
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<ApplicationParticipation> getAllowedRecords() {
		List<ApplicationParticipation> retval = null;
		if (SecurityUtils.hasRole("MDM ADMIN")) {
			retval = appParticipationRepo.findAll();
		} else {
			String username = SecurityUtils.getCurrentUsername();
			retval = appParticipationRepo.findAllowedRecords(SecurityUtils.getCurrentUsername());
		}
		return retval;
	}

	private class ReferenceBean {
		String id;
		String nameEn;
		String nameFr;

	}

	@Transactional
	@Override
	public long generateTestAppParicipationsForAllSystemFundingOpportunities() {
		List<ApplicationParticipation> participations = new ArrayList<ApplicationParticipation>();
		Instant inst = Instant.parse("2020-02-02T00:00:00.00Z");

		for (SystemFundingOpportunity sfo : sfoRepo.findAll()) {
			participations.addAll(generateTestAppParticipations(sfo, inst, 3, 5));
		}

		generateEdiData(participations);

		participations = appParticipationRepo.saveAll(participations);

		return participations.size();
	}

	@Override
	public ApplicationParticipation findAppPartByApplId(String applId) {
		return appParticipationRepo.findByApplId(applId);
	}

	private List<ApplicationParticipation> generateEdiData(List<ApplicationParticipation> appParts) {
		setAppPartGender(appParts);
		setAppPartDisability(appParts);
		setAppPartEthnicity(appParts);
		setAppPartIndiginous(appParts);

		return appParts;
	}

	private List<ApplicationParticipation> setAppPartGender(List<ApplicationParticipation> appParts) {
		Collections.shuffle(appParts);

		int i = 0;
		for (; i < (int) (appParts.size() * 0.5); i++) {
			appParts.get(i).setGenderSelection("female");
		}
		for (; i < (int) (appParts.size() * 0.93); i++) {
			appParts.get(i).setGenderSelection("male");
		}
		for (; i < (int) appParts.size(); i++) {
			appParts.get(i).setGenderSelection("non-binary");
		}

		return appParts;
	}

	private List<ApplicationParticipation> setAppPartDisability(List<ApplicationParticipation> appParts) {
		Collections.shuffle(appParts);

		int i = 0;
		for (; i < (int) (appParts.size() * 0.04); i++) {
			appParts.get(i).setDisabilityResponse("physical");
		}
		for (; i < (int) (appParts.size() * 0.06); i++) {
			appParts.get(i).setDisabilityResponse("deaf");
		}
		for (; i < (int) (appParts.size() * 0.08); i++) {
			appParts.get(i).setDisabilityResponse("other");
		}
		for (; i < (int) (appParts.size() * 0.09); i++) {
			appParts.get(i).setDisabilityResponse("blind");
		}
		return appParts;
	}

	private List<ApplicationParticipation> setAppPartIndiginous(List<ApplicationParticipation> appParts) {
		Collections.shuffle(appParts);

		int i = 0;
		for (; i < (int) (appParts.size() * 0.05); i++) {
			appParts.get(i).setIndIdentityResponse("Métis");
		}
		for (; i < (int) (appParts.size() * 0.08); i++) {
			appParts.get(i).setIndIdentityResponse("Inuit");
		}
		for (; i < (int) (appParts.size() * 0.10); i++) {
			appParts.get(i).setIndIdentityResponse("Mi'kmaq");
		}
		for (; i < (int) (appParts.size() * 0.11); i++) {
			appParts.get(i).setIndIdentityResponse("Iroquois");
		}
		for (; i < (int) (appParts.size() * 0.12); i++) {
			appParts.get(i).setIndIdentityResponse("Haida");
		}
		for (; i < (int) (appParts.size() * 0.13); i++) {
			appParts.get(i).setIndIdentityResponse("Dalelh");
		}

		return appParts;
	}

	private List<ApplicationParticipation> setAppPartEthnicity(List<ApplicationParticipation> appParts) {
		Collections.shuffle(appParts);

		int i = 0;
		for (; i < (int) (appParts.size() * 0.06); i++) {
			appParts.get(i).setVisibleMinorityResponse("Latin American");
		}
		for (; i < (int) (appParts.size() * 0.12); i++) {
			appParts.get(i).setVisibleMinorityResponse("Caribbean");
		}
		for (; i < (int) (appParts.size() * 0.20); i++) {
			appParts.get(i).setVisibleMinorityResponse("Middle Eastern");
		}
		for (; i < (int) (appParts.size() * 0.24); i++) {
			appParts.get(i).setVisibleMinorityResponse("Central Asian");
		}
		for (; i < (int) (appParts.size() * 0.39); i++) {
			appParts.get(i).setVisibleMinorityResponse("East Asian");
		}
		for (; i < (int) (appParts.size() * 0.46); i++) {
			appParts.get(i).setVisibleMinorityResponse("South Asian");
		}
		for (; i < (int) (appParts.size() * 0.49); i++) {
			appParts.get(i).setVisibleMinorityResponse("South East Asian");
		}
		for (; i < (int) (appParts.size() * 0.50); i++) {
			appParts.get(i).setVisibleMinorityResponse("West Asian");
		}
		for (; i < (int) (appParts.size() * 0.60); i++) {
			appParts.get(i).setVisibleMinorityResponse("African");
		}

		return appParts;
	}

}
