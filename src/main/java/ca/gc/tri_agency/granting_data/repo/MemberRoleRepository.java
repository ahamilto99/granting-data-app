package ca.gc.tri_agency.granting_data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.gc.tri_agency.granting_data.model.MemberRole;

@Repository
public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {

	List<MemberRole> findByBusinessUnitIdOrderByUserLogin(Long buId);

	List<MemberRole> findByUserLoginAndEdiAuthorizedTrue(String userLogin);

}
