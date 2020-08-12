package ca.gc.tri_agency.granting_data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.GrantingStage;

@Repository
@Transactional(readOnly = true)
public interface GrantingStageRepository extends JpaRepository<GrantingStage, Long> {

}
