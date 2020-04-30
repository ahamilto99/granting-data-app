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
	
}
