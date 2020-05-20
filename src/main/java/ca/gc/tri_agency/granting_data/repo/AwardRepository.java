package ca.gc.tri_agency.granting_data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ca.gc.tri_agency.granting_data.model.Award;

@Repository
public interface AwardRepository extends JpaRepository<Award, Long> {

	@Query("FROM Award a JOIN ApplicationParticipation ap ON a.applId = ap.applId WHERE ap.programId IN"
			+ " (SELECT sfo.extId FROM SystemFundingOpportunity sfo"
				+ " JOIN FundingOpportunity fo"
				+ " ON sfo.linkedFundingOpportunity = fo"
				+ " JOIN BusinessUnit bu"
				+ " ON bu = fo.businessUnit"
				+ " JOIN MemberRole mr"
				+ " ON bu = mr.businessUnit"
				+ " WHERE mr.userLogin = :userLogin"
			+ ")")
	List<Award> findForCurrentUser(String userLogin);
	
}
