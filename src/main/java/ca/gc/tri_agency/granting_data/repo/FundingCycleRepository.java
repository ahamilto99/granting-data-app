package ca.gc.tri_agency.granting_data.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.gc.tri_agency.granting_data.model.FundingCycle;

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
	
}
