package ca.gc.tri_agency.granting_data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;

@Repository
@Transactional(readOnly = true)
public interface SystemFundingOpportunityRepository extends JpaRepository<SystemFundingOpportunity, Long> {

	List<SystemFundingOpportunity> findByLinkedFundingOpportunityId(Long foId);

	List<SystemFundingOpportunity> findByExtId(String extId);

	List<SystemFundingOpportunity> findByNameEn(String nameEn);

	List<SystemFundingOpportunity> findByLinkedFundingOpportunityBusinessUnitIdIn(List<Long> targetBuIds);

}
