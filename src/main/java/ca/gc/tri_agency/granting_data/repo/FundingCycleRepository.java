package ca.gc.tri_agency.granting_data.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import ca.gc.tri_agency.granting_data.model.FundingCycle;
import ca.gc.tri_agency.granting_data.model.projection.FundingCycleProjection;

@Repository
@Transactional(readOnly = true)
public interface FundingCycleRepository extends JpaRepository<FundingCycle, Long> { // @formatter:off

	List<FundingCycle> findByFundingOpportunityId(Long foId);

	@Query("SELECT fc.id AS id, fc.expectedApplications AS numAppsExpected, fc.isOpen AS isOpenEnded, fc.startDate AS startDate,"
			+ " fc.endDate AS endDate, fc.startDateNOI AS startDateNOI, fc.endDateNOI AS endDateNOI,"
			+ " fc.startDateLOI AS startDateLOI, fc.endDateLOI AS endDateLOI, fy.id AS fiscalYearId, fy.year AS fiscalYear,"
			+ " fo.id AS fundingOpportunityId, fo.nameEn AS fundingOpportunityNameEn, fo.nameFr AS fundingOpportunityNameFr"
			+ " FROM FundingCycle fc"
			+ " JOIN FiscalYear fy ON fc.fiscalYear.id = fy.id"
			+ " JOIN FundingOpportunity fo ON fc.fundingOpportunity.id = fo.id"
			+ " WHERE fy.id = :fyId"
			+ " ORDER BY fo.nameEn")
	List<FundingCycleProjection> findByFiscalYearId(@Param("fyId") Long fyId);
	
	@Query("SELECT fc.id AS id, fc.expectedApplications AS numAppsExpected, fc.isOpen AS isOpenEnded, fc.startDate AS startDate,"
			+ " fc.endDate AS endDate, fc.startDateNOI AS startDateNOI, fc.endDateNOI AS endDateNOI,"
			+ " fc.startDateLOI AS startDateLOI, fc.endDateLOI AS endDateLOI, fy.id AS fiscalYearId, fy.year AS fiscalYear"
			+ " FROM FundingCycle fc"
			+ " JOIN FiscalYear fy ON fc.fiscalYear.id = fy.id"
			+ " WHERE fc.fundingOpportunity.id = :foId"
			+ " ORDER BY fc.startDate")
	List<FundingCycleProjection> findForBrowseViewFO(@Param("foId") Long foId);
	
	@Query("SELECT id AS id, startDate AS startDate, expectedApplications AS numAppsExpected, fundingOpportunity.id AS fundingOpportunityId"
			+ " FROM FundingCycle"
			+ " WHERE id = :fcId")
	Optional<FundingCycleProjection> findForDeleteFC(@RequestParam("fcId") Long fcId);
	
	@Query("SELECT fc.id AS id, fc.startDate AS startDate, fc.endDate AS endDate, fc.startDateNOI AS startDateNOI,"
			+ " fc.endDateNOI as endDateNOI, fc.startDateLOI AS startDateLOI, fc.endDateLOI AS endDateLOI,"
			+ " fc.expectedApplications AS numAppsExpected, fo.id AS fundingOpportunityId,"
			+ " fo.nameEn AS fundingOpportunityNameEn, fo.nameFr AS fundingOpportunityNameFr, bu.agency.id AS agencyId"
			+ " FROM FundingCycle fc"
			+ " JOIN FundingOpportunity fo ON fc.fundingOpportunity.id = fo.id"
			+ " JOIN BusinessUnit bu ON fo.businessUnit.id = bu.id"
			+ " WHERE fc.startDate BETWEEN :start AND :end"
			+ " OR fc.startDateNOI BETWEEN :start AND :end"
			+ " OR fc.startDateLOI BETWEEN :start AND :end"
			+ " OR fc.endDate BETWEEN :start AND :end"
			+ " OR fc.endDateNOI BETWEEN :start AND :end"
			+ " OR fc.endDateLOI BETWEEN :start AND :end")
	List<FundingCycleProjection> findForCalendar(@Param("start") LocalDate dayRangeStart, @Param("end") LocalDate dayRangeEnd);
	
	@Transactional
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query("DELETE FROM FundingCycle"
			+ " WHERE id = ?1")
	void deleteByIdentifier(Long fcId);
	
} // @formatter:on
