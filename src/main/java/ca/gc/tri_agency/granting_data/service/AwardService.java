package ca.gc.tri_agency.granting_data.service;

import java.util.List;

import ca.gc.tri_agency.granting_data.model.ApplicationParticipation;
import ca.gc.tri_agency.granting_data.model.Award;
import ca.gc.tri_agency.granting_data.model.projection.AwardProjection;

public interface AwardService {

	List<Award> generateTestAwards(List<ApplicationParticipation> appParts, double percentageOfMainApplicants);

	List<AwardProjection> findAwardsForCurrentUser();

}
