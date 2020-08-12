package ca.gc.tri_agency.granting_data.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.IndigenousIdentity;

@Repository
@Transactional(readOnly = true)
public interface IndigenousIdentityRepository extends JpaRepository<IndigenousIdentity, Long>{

	Optional<IndigenousIdentity> findByNameEn(String nameEn);

	Optional<IndigenousIdentity> findByNameFr(String nameFr);
	
}
