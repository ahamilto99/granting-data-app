package ca.gc.tri_agency.granting_data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.gc.tri_agency.granting_data.model.Award;

@Repository
public interface AwardRepository extends JpaRepository<Award, Long> {

}
