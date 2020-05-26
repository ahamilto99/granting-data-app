package ca.gc.tri_agency.granting_data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.gc.tri_agency.granting_data.model.SystemFundingCycle;

@Repository
public interface SystemFundingCycleRepository extends JpaRepository<SystemFundingCycle, Long> {
	
	List<SystemFundingCycle> findBySystemFundingOpportunityId(Long id);

}