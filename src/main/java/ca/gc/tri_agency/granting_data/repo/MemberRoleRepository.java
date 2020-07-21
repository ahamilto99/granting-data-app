package ca.gc.tri_agency.granting_data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.gc.tri_agency.granting_data.model.MemberRole;
import ca.gc.tri_agency.granting_data.model.projection.MemberRoleProjection;

@Repository
public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {

	List<MemberRole> findByBusinessUnitIdOrderByUserLogin(Long buId);

	List<MemberRole> findByUserLoginAndEdiAuthorizedTrue(String userLogin);
	
	@Query("SELECT mr.ediAuthorized AS ediAuthorized FROM MemberRole mr WHERE mr.userLogin = :login AND mr.businessUnit.id = :buId"
			+ " AND mr.ediAuthorized = TRUE")
	MemberRoleProjection findEdiAuthorizedByUserLoginBuId(@Param("login") String userLogin, @Param("buId") Long buId);

}
