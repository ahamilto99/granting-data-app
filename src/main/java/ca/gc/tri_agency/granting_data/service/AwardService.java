package ca.gc.tri_agency.granting_data.service;

import java.util.List;

import ca.gc.tri_agency.granting_data.model.ApplicationParticipation;
import ca.gc.tri_agency.granting_data.model.Award;

public interface AwardService {

	Award findAwardById(Long awardId);
	
	List<Award> findAllAwards();
	
	List<Award> generateTestAwards(List<ApplicationParticipation> appParts, double percentageOfMainApplicants);
	
}
