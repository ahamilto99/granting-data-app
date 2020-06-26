package ca.gc.tri_agency.granting_data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;

@Repository
public interface FundingOpportunityRepository extends JpaRepository<FundingOpportunity, Long> {

	List<FundingOpportunity> findByNameEn(String nameEn);

	List<FundingOpportunity> findByBusinessUnitId(Long buId);
	
	@Query("FROM FundingOpportunity fo INNER JOIN fo.businessUnit bu WHERE bu.agency = :agencyId")
	List<FundingOpportunity> findByAgency(@Param("agencyId") Agency agency);
	
}
