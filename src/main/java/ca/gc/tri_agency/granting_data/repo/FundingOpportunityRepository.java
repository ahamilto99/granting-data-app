package ca.gc.tri_agency.granting_data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.model.projection.FundingOpportunityProjection;

@Repository
public interface FundingOpportunityRepository extends JpaRepository<FundingOpportunity, Long> {

	List<FundingOpportunity> findByNameEn(String nameEn);

	List<FundingOpportunity> findByBusinessUnitId(Long buId);
	
	@Query("FROM FundingOpportunity fo INNER JOIN fo.businessUnit bu WHERE bu.agency = :agencyId")
	List<FundingOpportunity> findByAgency(@Param("agencyId") Agency agency);
	
	@Query("SELECT fo.id AS id, fo.nameEn AS nameEn, fo.nameFr AS nameFr, bu.nameEn AS businessUnitNameEn, bu.nameFr AS businessUnitNameFr,"
			+ " gc.grantingStage.id AS grantingStageId, gSys.acronym AS grantingSystemAcronym"
			+ " FROM FundingOpportunity fo"
			+ " LEFT JOIN fo.businessUnit bu"
			+ " LEFT JOIN GrantingCapability gc ON fo.id = gc.fundingOpportunity"
			+ " LEFT JOIN GrantingSystem gSys ON gc.grantingSystem = gSys.id ORDER BY nameEn, grantingSystemAcronym")
	List<FundingOpportunityProjection> findResultsForGoldenListTable();
	
	@Query("SELECT fo.nameEn AS nameEn, fo.nameFr AS nameFr, fo.frequency AS frequency, fo.fundingType AS fundingType, bu.id AS businessUnitId,"
			+ " bu.nameEn AS businessUnitNameEn, bu.nameFr AS businessUnitFr, pa.acronymEn AS agencyAcronymEn, pa.acronymFr AS agencyAcronymFr"
			+ " FROM FundingOpportunity fo"
			+ " LEFT JOIN fo.participatingAgencies pa"
			+ " LEFT JOIN BusinessUnit bu ON fo.businessUnit = bu.id"
			+ " WHERE fo.id = :foId")
	List<FundingOpportunityProjection> findResultsForViewFO(@Param("foId") Long foId);

}
