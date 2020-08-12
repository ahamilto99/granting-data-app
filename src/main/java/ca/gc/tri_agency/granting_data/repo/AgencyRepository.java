package ca.gc.tri_agency.granting_data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.projection.AgencyProjection;

@Repository
@Transactional(readOnly = true)
public interface AgencyRepository extends JpaRepository<Agency, Long> { // @formatter:off
	
	@Query("SELECT a.id AS id, a.nameEn AS nameEn, a.nameFr AS nameFr, a.acronymEn AS acronymEn, a.acronymFr AS acronymFr,"
			+ " fo.id AS foId, fo.nameEn AS foNameEn, fo.nameFr AS foNameFr, fo.isJointInitiative AS foIsJointInitiative,"
			+ " fo.fundingType AS foFundingType, fo.frequency AS foFrequency, bu.id AS buId, bu.nameEn AS buNameEn,"
			+ " bu.nameFr AS buNameFr, bu.acronymEn AS buAcronymEn, bu.acronymFr AS buAcronymFr"
			+ " FROM Agency a"
			+ " LEFT JOIN BusinessUnit bu ON a.id = bu.agency.id"
			+ " LEFT JOIN FundingOpportunity fo ON bu.id = fo.businessUnit.id"
			+ " WHERE a.id = :agencyId"
			+ " ORDER BY fo.nameEn")
	List<AgencyProjection> findForBrowseViewAgency(@Param("agencyId") Long agencyId);

}	// @formatter:on
