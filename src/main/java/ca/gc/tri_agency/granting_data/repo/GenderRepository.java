package ca.gc.tri_agency.granting_data.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.Gender;

@Repository
@Transactional(readOnly = true)
public interface GenderRepository extends JpaRepository<Gender, Long> {
	
	Optional<Gender> findByNameEn(String nameEn);

	Optional<Gender> findByNameFr(String nameFr);

}
