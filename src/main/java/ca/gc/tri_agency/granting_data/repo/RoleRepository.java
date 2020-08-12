package ca.gc.tri_agency.granting_data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.Role;

@Repository
@Transactional(readOnly = true)
public interface RoleRepository extends JpaRepository<Role, Long> {

}
