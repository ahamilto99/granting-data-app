package ca.gc.tri_agency.granting_data.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.gc.tri_agency.granting_data.model.FundingCycle;
import ca.gc.tri_agency.granting_data.model.projection.FundingCycleProjection;

@Repository
public interface FundingCycleRepository extends JpaRepository<FundingCycle, Long> {

	List<FundingCycle> findByFundingOpportunityId(Long foId);

	List<FundingCycle> findByStartDateGreaterThanEqualAndStartDateLessThanOrEndDateGreaterThanEqualAndEndDateLessThan(
			LocalDate startDate, LocalDate endDate, LocalDate startDate2, LocalDate endDate2);
	
	List<FundingCycle> findByStartDateBetween(LocalDate startDateStart, LocalDate startDateEnd);
	
	List<FundingCycle> findByEndDateBetween(LocalDate endDateStart, LocalDate endDateEnd);
	
	List<FundingCycle> findByStartDateLOIBetween(LocalDate startDateLOIStart, LocalDate startDateLOIEnd);
	
	List<FundingCycle> findByEndDateLOIBetween(LocalDate endDateLOIStart, LocalDate endDateLOIEnd);
	
	List<FundingCycle> findByStartDateNOIBetween(LocalDate startDateNOIStart, LocalDate startDateNOIEnd);
	
	List<FundingCycle> findByEndDateNOIBetween(LocalDate endDateNOIStart, LocalDate endDateNOIEnd);
	
	List<FundingCycle> findByFiscalYearId(Long fyId);
	
	@Query("SELECT fc.expectedApplications AS numAppsExpected, fc.isOpen AS isOpenEnded, fc.startDate AS startDate, fc.endDate AS endDate,"
			+ " fc.startDateNOI AS startDateNOI, fc.endDateNOI AS endDateNOI, fc.startDateLOI AS startDateLOI,"
			+ " fc.endDateLOI AS endDateLOI, fy.id AS fiscalYearId, fy.year AS fiscalYear"
			+ " FROM FundingCycle fc"
			+ " JOIN FiscalYear fy ON fc.fiscalYear.id = fy.id"
			+ " WHERE fc.fundingOpportunity.id = :foId"
			+ " ORDER BY fc.startDate")
	List<FundingCycleProjection> findForBrowseViewFO(@Param("foId") Long foId);
	
}
