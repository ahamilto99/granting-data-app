package ca.gc.tri_agency.granting_data.service.impl;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import com.github.javafaker.Faker;

import ca.gc.tri_agency.granting_data.model.ApplicationParticipation;
import ca.gc.tri_agency.granting_data.model.GrantingSystem;
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.service.ApplicationParticipationService;

public class ApplicationParticipationServiceImpl implements ApplicationParticipationService {
	Map<GrantingSystem, ReferenceBean[]> roleMap;

	private SecureRandom sRand = new SecureRandom();

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
	public String fillInRandomRole(ApplicationParticipation app, GrantingSystem sys) {
//		String[] roleArr = { "Scholarship Applicant", "Applicant", "Co-Applicant", "Collaborator", "Reader A", "Reader B",
//				"Reader C" };
		String[] roleArr = { "1", "2", "147", "8878CE1D-6020-E211-AF1A-005056AD024F", "8F78CE1D-6020-E211-AF1A-005056AD024F",
				"A878CE1D-6020-E211-AF1A-005056AD024F", "A978CE1D-6020-E211-AF1A-005056AD024F",
				"A778CE1D-6020-E211-AF1A-005056AD024F", "8C78CE1D-6020-E211-AF1A-005056AD024F" };
		String acronym = sys.getAcronym();
		if (acronym.equals("NAMIS")) {
			return roleArr[sRand.nextInt(2)];
		} else if (acronym.equals("AMIS")) {
			return roleArr[2];
		} else if (acronym.equals("CRM")) {
			return roleArr[sRand.nextInt(6) + 3];
		}
		return null;
	}

	@Override
	public String fillInRandomOrg(ApplicationParticipation app, GrantingSystem sys) {
		String[] orgArr = { "Algonquin College", "McGill University", "Carleton University", "Univeristy of Ottawa",
				"University of Toronto", "Dalhousie University", "University of British Columbia",
				"University of Ontario Institute of Technology", "University of Guelph", "Concordia University",
				"Collège de Maisonneuve", "Université Laval", "The University of Winnipeg", "York University" };
		String acronym = sys.getAcronym();
		if (acronym.equals("NAMIS") || acronym.equals("AMIS") || acronym.equals("CRM")) {
			return orgArr[sRand.nextInt(orgArr.length)];
		}
		return null;
	}

	@Override
	public String fillInRandomPerson(ApplicationParticipation app, GrantingSystem sys) {
		String acronym = sys.getAcronym();
		if (acronym.equals("NAMIS") || acronym.equals("AMIS") || acronym.equals("CRM")) {
			Faker nameFaker = new Faker();
			return nameFaker.name().firstName() + ' ' + nameFaker.name().lastName();
		}
		return null;
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
