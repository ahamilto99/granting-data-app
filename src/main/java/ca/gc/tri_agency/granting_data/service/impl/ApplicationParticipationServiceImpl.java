package ca.gc.tri_agency.granting_data.service.impl;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.javafaker.Faker;

import ca.gc.tri_agency.granting_data.model.ApplicationParticipation;
import ca.gc.tri_agency.granting_data.model.GrantingSystem;
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.repo.ApplicationParticipationRepository;
import ca.gc.tri_agency.granting_data.service.ApplicationParticipationService;

public class ApplicationParticipationServiceImpl implements ApplicationParticipationService {
	Map<GrantingSystem, ReferenceBean[]> roleMap;

	private SecureRandom sRand = new SecureRandom();

	@Autowired
	private ApplicationParticipationRepository apRepo;

	@Override
	public List<ApplicationParticipation> generateTestAppParticipations(SystemFundingOpportunity sfo, Instant createDate,
			long maxApplications, long maxParticipants) {
		return null;
	}

	@Override
	public String generateTestAppId(GrantingSystem system) {
		String acronym = system.getAcronym();
		if (acronym.equals("NAMIS")) {
			return Integer.toString(sRand.nextInt(103_000) + 434_000);
		} else if (acronym.equals("AMIS")) {
			return Integer.toString(sRand.nextInt(34_000) + 444_000);
		} else if (acronym.equals("CRM")) {
			return String.format("%d-%d-%05d", sRand.nextInt(500) + 100, sRand.nextInt(6) + 2017, sRand.nextInt(1_000));
		}
		return null;
	}

	@Override
	public void generateTestApplicationIdentifier(String appId, GrantingSystem system) {
		ApplicationParticipation appPart = apRepo.findByApplicationId(appId);
		String acronym = system.getAcronym();
		if (acronym.equals("NAMIS")) {
			appPart.setApplicationIdentifier(String.format("%s-%d", appId, sRand.nextInt(6) + 2017));
		} else if (acronym.equals("AMIS")) {
			appPart.setApplicationIdentifier(String.format("%d-%d-%04d", sRand.nextInt(900) + 100, sRand.nextInt(6) + 2017,
					sRand.nextInt(3_000)));
		} else if (acronym.equals("CRM")) {
			appPart.setApplicationIdentifier(String.format("%04X%04X-%04X-4%03X-%04X-%06X%06X", sRand.nextInt(65_536),
					sRand.nextInt(65_536), sRand.nextInt(65_536), sRand.nextInt(4_096), sRand.nextInt(65_536),
					sRand.nextInt(16_777_216), sRand.nextInt(16_777_216)));
		}
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
			String[] nameArr = { "McMaster", "Ottawa", "Guelph", "Memorial Univ. of Nfld", "Ryerson", "Alberta" };
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
					"University of Ottawa: criminologie", "University of Saskatchewan: Philosophy" };
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

	@Override
	public List<ApplicationParticipation> getAllowedRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	private class ReferenceBean {
		String id;
		String nameEn;
		String nameFr;

	}

}
