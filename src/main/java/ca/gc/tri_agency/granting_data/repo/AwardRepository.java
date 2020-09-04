package ca.gc.tri_agency.granting_data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.Award;
import ca.gc.tri_agency.granting_data.model.projection.AwardProjection;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;

@Repository
@Transactional(readOnly = true)
public interface AwardRepository extends JpaRepository<Award, Long> { // @formatter:off
	
	String BROWSE_AWARDS_COLS = "a.id AS id, a.applId AS applId, a.programNameEn AS programEn, a.programNameFr AS programFr,"
			+ " a.familyName AS familyName, a.givenName AS firstName, a.awardedAmount AS awardedAmt,"
			+ " a.requestedAmount AS requestedAmt, a.fundingYear AS fundingYr";
			
	@Query("SELECT " + BROWSE_AWARDS_COLS
			+ " FROM Award a"
			+ " JOIN ApplicationParticipation ap ON a.applId = ap.applId WHERE ap.programId IN"
			+ " (SELECT sfo.extId FROM SystemFundingOpportunity sfo"
				+ " JOIN FundingOpportunity fo"
				+ " ON sfo.linkedFundingOpportunity = fo"
				+ " JOIN BusinessUnit bu"
				+ " ON bu = fo.businessUnit"
				+ " JOIN MemberRole mr"
				+ " ON bu = mr.businessUnit"
				+ " WHERE mr.userLogin = :userLogin"
			+ ")")
	List<AwardProjection> findForCurrentUser(String userLogin);
	
	@AdminOnly
	@Query("SELECT " + BROWSE_AWARDS_COLS + " FROM Award a")
	List<AwardProjection> findAllAdminOnly();
	
} // @formatter:on
