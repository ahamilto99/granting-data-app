package ca.gc.tri_agency.granting_data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.MemberRole;
import ca.gc.tri_agency.granting_data.model.projection.MemberRoleProjection;

@Repository
@Transactional(readOnly = true)
public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> { // @formatter:off

	List<MemberRole> findByBusinessUnitIdOrderByUserLogin(Long buId);

	List<MemberRole> findByUserLoginAndEdiAuthorizedTrue(String userLogin);
	
	@Query("SELECT mr.ediAuthorized AS ediAuthorized FROM MemberRole mr WHERE mr.userLogin = :login AND mr.businessUnit.id = :buId"
			+ " AND mr.ediAuthorized = TRUE")
	MemberRoleProjection findEdiAuthorizedByUserLoginBuId(@Param("login") String userLogin, @Param("buId") Long buId);
	
	@Query("SELECT mr.id AS id FROM MemberRole mr"
			+ " JOIN BusinessUnit bu ON mr.businessUnit.id = bu.id"
			+ " JOIN FundingOpportunity fo ON bu.id = fo.businessUnit.id"
			+ " WHERE mr.role.id = 1 AND mr.userLogin = :login AND fo.id = :foId")
	MemberRoleProjection findIfCanCreateFC(@Param("login") String userLogin, @Param("foId") Long foId);
	
	@Query("SELECT mr.id AS id FROM MemberRole mr"
			+ " JOIN BusinessUnit bu ON mr.businessUnit.id = bu.id"
			+ " JOIN FundingOpportunity fo ON bu.id = fo.businessUnit.id"
			+ " JOIN FundingCycle fc ON fo.id = fc.fundingOpportunity.id"
			+ " WHERE mr.role.id = 1 AND mr.userLogin = :login AND fc.id = :fcId")
	MemberRoleProjection findIfCanUpdateDeleteFC(@Param("login") String userLogin, @Param("fcId") Long fcId);

} // @formatter:on
