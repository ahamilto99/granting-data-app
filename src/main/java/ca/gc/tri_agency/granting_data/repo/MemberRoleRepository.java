package ca.gc.tri_agency.granting_data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import ca.gc.tri_agency.granting_data.model.MemberRole;

@Repository
public interface MemberRoleRepository extends JpaRepository<MemberRole, Long>, RevisionRepository<MemberRole, Long, Long> {

	List<MemberRole> findByBusinessUnitIdOrderByUserLogin(Long buId);

}
