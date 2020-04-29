package ca.gc.tri_agency.granting_data.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import ca.gc.tri_agency.granting_data.model.ApplicationParticipation;
import ca.gc.tri_agency.granting_data.model.GrantingSystem;
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.service.ApplicationParticipationService;

public class ApplicationParticipationServiceImpl implements ApplicationParticipationService {
	Map<GrantingSystem, ReferenceBean[]> roleMap;

	@Override
	public List<ApplicationParticipation> generateTestAppParticipations(SystemFundingOpportunity sfo, Instant createDate,
			long maxApplications, long maxParticipants) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateTestAppId(GrantingSystem system) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateTestApplicationIdentifier(String appId, GrantingSystem system) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String fillInRandomRole(ApplicationParticipation app, GrantingSystem sys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String fillInRandomOrg(ApplicationParticipation app, GrantingSystem sys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String fillInRandomPerson(ApplicationParticipation app, GrantingSystem sys) {
		// TODO Auto-generated method stub
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
