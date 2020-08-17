package ca.gc.tri_agency.granting_data.repo;

import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.ApplicationParticipation;
import ca.gc.tri_agency.granting_data.model.projection.ApplicationParticipationProjection;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;

@Repository
@Transactional(readOnly = true)
public interface ApplicationParticipationRepository extends JpaRepository<ApplicationParticipation, Long> { // @formatter:off

	String ONE_APP_PART_QUERY = "SELECT ap.id AS id, ap.applicationIdentifier AS applicationIdentifier, ap.applId AS applId,"
			+ " ap.competitionYear AS competitionYear, ap.programId AS programId, ap.programEn AS programNameEn,"
			+ " ap.programFr AS programNameFr, ap.createDate AS createdDate, ap.roleEn AS roleEn, ap.roleFr AS roleFR,"
			+ " ap.familyName AS familyName, ap.givenName AS firstName, ap.personIdentifier AS personIdentifier,"
			+ " ap.organizationNameEn AS organizationNameEn, ap.organizationNameFr AS organizationNameFr, ap.municipality AS city,"
			+ " ap.provinceStateCode AS provinceState, ap.country AS country"
			+ " FROM ApplicationParticipation ap"
			+ " WHERE ap.id = :apId";
	
	String BU_MEMBER_CLAUSE = " ap.programId IN (SELECT sfo.extId FROM SystemFundingOpportunity sfo"
			+ " JOIN FundingOpportunity fo ON sfo.linkedFundingOpportunity.id = fo.id"
			+ " JOIN BusinessUnit bu ON fo.businessUnit.id = bu.id"
			+ " JOIN MemberRole mr ON bu.id = mr.businessUnit.id"
			+ " WHERE mr.userLogin = :username) ";
	
	String EDI_SUBQUERY_JPQL = " ap.programId IN (SELECT sfo.extId FROM SystemFundingOpportunity sfo"
			+ " JOIN FundingOpportunity fo ON sfo.linkedFundingOpportunity.id = fo.id"
			+ " JOIN BusinessUnit bu ON fo.businessUnit.id = bu.id WHERE bu.id = :buId)";

	@Query(value = "SELECT * FROM APPLICATION_PARTICIPATION where program_id in (select sfo.ext_id from system_funding_opportunity as sfo join funding_opportunity as fo on sfo.linked_funding_opportunity_id=fo.id join business_unit as bu on fo.business_unit_id=bu.id join member_role as mr on bu.id=mr.business_unit_id where user_login = :username)", nativeQuery = true)
	List<ApplicationParticipation> findAllowedRecords(@Param("username") String username);

	ApplicationParticipation findByApplId(String applId);

	@AdminOnly
	@Query("SELECT id AS id, applId AS applId, programId AS programId, familyName AS familyName, givenName AS firstName, roleEn AS roleEn,"
			+ " roleFr AS roleFr, organizationNameEn AS organizationNameEn, organizationNameFr AS organizationNameFr"
			+ " FROM ApplicationParticipation")
	List<ApplicationParticipationProjection> findAllForAdmin();

	@Query("SELECT ap.id AS id, ap.applId AS applId, ap.programId AS programId, ap.familyName AS familyName, ap.givenName AS firstName, ap.roleEn AS roleEn,"
			+ " ap.roleFr AS roleFr, ap.organizationNameEn AS organizationNameEn, ap.organizationNameFr AS organizationNameFr"
			+ " FROM ApplicationParticipation ap WHERE ap.programId IN"
			+ " (SELECT sfo.extId FROM SystemFundingOpportunity sfo"
			+ " JOIN FundingOpportunity fo ON sfo.linkedFundingOpportunity.id = fo.id"
			+ " JOIN BusinessUnit bu ON fo.businessUnit.id = bu.id"
			+ " JOIN MemberRole mr ON mr.businessUnit.id = bu.id"
			+ " WHERE mr.userLogin = :username)"
			+ " ORDER BY ap.id")
	List<ApplicationParticipationProjection> findForCurrentUser(@Param("username") String userLogin);

	@Query("SELECT ap.id AS id FROM ApplicationParticipation ap WHERE ap.programId IN"
			+ " (SELECT sfo.extId FROM SystemFundingOpportunity sfo"
			+ " JOIN FundingOpportunity fo ON sfo.linkedFundingOpportunity.id = fo.id"
			+ " JOIN BusinessUnit bu ON fo.businessUnit.id = bu.id"
			+ " JOIN MemberRole mr ON mr.businessUnit.id = bu.id"
			+ " WHERE mr.userLogin = :username AND mr.ediAuthorized = TRUE)"
			+ " ORDER BY ap.id")
	List<ApplicationParticipationProjection> findIdsForCurrentUserEdiAuthorized(@Param("username") String userLogin);
	
	@AdminOnly
	@Query(ONE_APP_PART_QUERY)
	Optional<ApplicationParticipationProjection> findOneAppPartByIdForAdminOnly(@Param("apId") Long apId);
	
	@Query(ONE_APP_PART_QUERY + " AND" + BU_MEMBER_CLAUSE)
	Optional<ApplicationParticipationProjection> findOneAppPartById(@Param("apId") Long apId, @Param("username") String userLogin) throws AccessDeniedException;
	
	@Query("SELECT COUNT(CASE WHEN ap.gender.id = 1 THEN 'female' END) AS female,"
			+ " COUNT(CASE WHEN ap.gender.id = 2 THEN 'male' END) AS male,"
			+ " COUNT(CASE WHEN ap.gender.id > 2 THEN 'nonbinary' END) AS nonbinary"
			+ " FROM ApplicationParticipation ap WHERE" 
			+ EDI_SUBQUERY_JPQL)
	Tuple findGenderCounts(@Param("buId") Long buId);

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

	/*
	 * Returns a List even though we are querying for one AP because an applicant can have multiple indigenous identities
	 * and/or can have multiple ethnicities  
	 */
	@Query("SELECT ap.id AS id, ap.applicationIdentifier AS applicationIdentifier, ap.applId AS applId, ap.dateOfBirth AS dateOfBirth,"
			+ " ap.givenName AS firstName, ap.familyName AS familyName, ap.disabilityResponse AS disability,"
			+ " g.nameEn AS genderEn, g.nameFr AS genderFr, ind.id AS indIdentityId, ind.nameEn AS indIdentityEn,"
			+ " ind.nameFr AS indIdentityFr, vm.id AS visMinorityId, vm.nameEn AS visMinorityEn, vm.nameFr AS visMinorityFr"
			+ " FROM ApplicationParticipation ap"
			+ " LEFT JOIN Gender g ON ap.gender.id = g.id"
			+ " LEFT JOIN ap.indigenousIdentities ind"
			+ " LEFT JOIN ap.visibleMinorities vm"
			+ " WHERE ap.id = ?1")
	List<ApplicationParticipationProjection> findOneWithEdiData(Long apId);
	
	@Query("SELECT COUNT(CASE WHEN ap.disabilityResponse IS NOT NULL THEN 'numDisableds' END) AS numDisableds,"
			+ " COUNT(CASE WHEN ap.gender.id = 1 THEN 'numFemales' END) AS numFemales,"
			+ " COUNT(CASE WHEN ap.gender.id = 2 THEN 'numMales' END) AS numMales,"
			+ " COUNT(CASE WHEN ap.gender.id > 2 THEN 'numNonBinaries' END) AS numNonBinaries,"
			+ " COUNT(ap.id) AS numApps"
			+ " FROM ApplicationParticipation ap"
			+ " WHERE ap.programId IN "
			+ "	(SELECT sfo.extId FROM SystemFundingOpportunity sfo"
			+ " 		JOIN FundingOpportunity fo ON sfo.linkedFundingOpportunity.id = fo.id"
			+ " 		JOIN BusinessUnit bu ON fo.businessUnit.id = bu.id "
			+ "		WHERE bu.id = ?1)")
	Tuple findEdiDataForBU(Long buId);
	
} // @formatter:on












































