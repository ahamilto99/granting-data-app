package ca.gc.tri_agency.granting_data.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.VisibleMinority;

@Repository
@Transactional(readOnly = true)
public interface VisibleMinorityRepository extends JpaRepository<VisibleMinority, Long> {

	Optional<VisibleMinority> findByNameEn(String nameEn);
	
	Optional<VisibleMinority> findByNameFr(String nameFr);
	
}
