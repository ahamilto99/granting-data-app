package ca.gc.tri_agency.granting_data.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ca.gc.tri_agency.granting_data.model.FiscalYear;

@Repository
public interface FiscalYearRepository extends JpaRepository<FiscalYear, Long> {

	Optional<FiscalYear> findByYear(Long year);
	
	@Query("SELECT fy.id, sfc.fiscalYear, SUM(sfc.numAppsReceived) FROM FiscalYear fy"
			+ " JOIN SystemFundingCycle sfc ON fy.year = sfc.fiscalYear"
			+ " GROUP BY sfc.fiscalYear")
	List<Object[]> findNumAppsExpectedForEachYear();

}
