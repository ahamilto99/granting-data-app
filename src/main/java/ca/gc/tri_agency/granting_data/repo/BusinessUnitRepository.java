package ca.gc.tri_agency.granting_data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.BusinessUnit;

@Repository
public interface BusinessUnitRepository extends JpaRepository<BusinessUnit, Long>, RevisionRepository<BusinessUnit, Long, Long> {

	List<BusinessUnit> findAllByAgency(Agency agency);
	
}
