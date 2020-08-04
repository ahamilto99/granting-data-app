package ca.gc.tri_agency.granting_data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.gc.tri_agency.granting_data.model.SystemFundingCycle;
import ca.gc.tri_agency.granting_data.model.projection.SystemFundingCycleProjection;

@Repository
public interface SystemFundingCycleRepository extends JpaRepository<SystemFundingCycle, Long> {
	
	List<SystemFundingCycle> findBySystemFundingOpportunityId(Long id);

	@Query("SELECT sfc.extId AS extId, sfc.fiscalYear AS fiscalYear, sfc.numAppsReceived AS numAppsReceived,"
			+ " sfo.nameEn AS systemFundingOpportunityNameEn, sfo.nameFr AS systemFundingOpportunityNameFr"
			+ " FROM SystemFundingCycle sfc"
			+ " JOIN SystemFundingOpportunity sfo ON sfc.systemFundingOpportunity.id = sfo.id"
			+ " JOIN FundingOpportunity fo ON sfo.linkedFundingOpportunity.id = fo.id"
			+ " WHERE fo.id = :foId")
	List<SystemFundingCycleProjection> findForBrowseViewFO(@Param("foId") Long foId);
}