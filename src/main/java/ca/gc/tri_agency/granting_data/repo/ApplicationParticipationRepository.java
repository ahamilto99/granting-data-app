package ca.gc.tri_agency.granting_data.repo;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.gc.tri_agency.granting_data.model.ApplicationParticipation;

@Repository
public interface ApplicationParticipationRepository extends JpaRepository<ApplicationParticipation, Long> {

	String EDI_SUBQUERY_JPQL = " ap.programId IN (SELECT sfo.extId FROM SystemFundingOpportunity sfo"
			+ " JOIN FundingOpportunity fo ON sfo.linkedFundingOpportunity.id = fo.id"
			+ " JOIN BusinessUnit bu ON fo.businessUnit.id = bu.id WHERE bu.id = :buId)";

//	@Query(value = "SELECT a FROM ApplicationParticipation a where program_id in (select sfo.extId from SystemFundingOpportunity sfo join FundingOpportunity fo on sfo.linkedFundingOpportunity.id=fo.id join BusinessUnit bu on fo.businessUnit.id=bu.id join MemberRole mr on bu.id=mr.businessUnit.id where mr.userLogin like '?1')", nativeQuery = true)
	@Query(value = "SELECT * FROM APPLICATION_PARTICIPATION where program_id in (select sfo.ext_id from system_funding_opportunity as sfo join funding_opportunity as fo on sfo.linked_funding_opportunity_id=fo.id join business_unit as bu on fo.business_unit_id=bu.id join member_role as mr on bu.id=mr.business_unit_id where user_login = :username)", nativeQuery = true)
	List<ApplicationParticipation> findAllowedRecords(@Param("username") String username);

	ApplicationParticipation findByApplId(String applId);

	@Query("SELECT COUNT(ap.id) AS total, CASE WHEN ap.gender.id = 1 THEN 'female' WHEN ap.gender.id = 2 THEN 'male'"
			+ " ELSE 'non-binary' END AS gender"
			+ " FROM ApplicationParticipation ap WHERE" 
			+ EDI_SUBQUERY_JPQL
			+ " GROUP BY ap.gender.id ORDER BY gender")
	List<Tuple> findGenderCounts(@Param("buId") Long buId);

	@Query("SELECT COUNT(ap.id) AS total FROM ApplicationParticipation ap WHERE ap.disabilityResponse IS NOT NULL AND" + EDI_SUBQUERY_JPQL)
	Tuple findDisabledCountForBU(@Param("buId") Long buId);

	@Query("SELECT COUNT(DISTINCT ap.id) AS total FROM ApplicationParticipation ap JOIN ap.indigenousIdentities ii WHERE"
			+ EDI_SUBQUERY_JPQL)
	Tuple findIndigenousCountForBU(@Param("buId") Long buId);

	@Query("SELECT COUNT(DISTINCT ap.id) AS total FROM ApplicationParticipation ap JOIN ap.visibleMinorities vm WHERE" + EDI_SUBQUERY_JPQL)
	Tuple findMinorityCountForBU(@Param("buId") Long buId);
	
	@Query("SELECT COUNT(ap.id) AS total FROM ApplicationParticipation ap WHERE"
			+ EDI_SUBQUERY_JPQL)
	Tuple findNumAPsForBU(@Param("buId") Long buId);

}
