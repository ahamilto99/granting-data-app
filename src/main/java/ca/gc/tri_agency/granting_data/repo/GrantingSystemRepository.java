package ca.gc.tri_agency.granting_data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.gc.tri_agency.granting_data.model.GrantingSystem;

@Repository
public interface GrantingSystemRepository extends JpaRepository<GrantingSystem, Long> {

	GrantingSystem findByAcronym(String string);

}
