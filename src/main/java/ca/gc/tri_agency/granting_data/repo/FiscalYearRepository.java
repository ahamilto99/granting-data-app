package ca.gc.tri_agency.granting_data.repo;

import java.util.List;
import java.util.Optional;

import javax.persistence.OrderBy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.FiscalYear;
import ca.gc.tri_agency.granting_data.model.projection.FiscalYearProjection;

@Repository
@Transactional(readOnly = true)
public interface FiscalYearRepository extends JpaRepository<FiscalYear, Long> {

	Optional<FiscalYear> findByYear(Long year);
	
	@Query("SELECT fy.id, fy.year, SUM(sfc.numAppsReceived) FROM FiscalYear fy"
			+ " LEFT JOIN SystemFundingCycle sfc ON fy.year = sfc.fiscalYear"
			+ " GROUP BY sfc.fiscalYear, fy.id"
			+ " ORDER BY fy.year")
	List<Object[]> findNumAppsExpectedForEachYear();
	
	@Query("SELECT id AS id, year AS year FROM FiscalYear ORDER BY year")
	List<FiscalYearProjection> findAllProjectionsOrderByYear();
	
	@OrderBy("year")
	List<FiscalYear> findAll();

}
