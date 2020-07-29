package ca.gc.tri_agency.granting_data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.BusinessUnit;
import ca.gc.tri_agency.granting_data.model.projection.BusinessUnitProjection;

@Repository
public interface BusinessUnitRepository extends JpaRepository<BusinessUnit, Long> {

	List<BusinessUnit> findAllByAgency(Agency agency);
	
	@Query("SELECT nameEn AS nameEn, nameFr AS nameFr FROM BusinessUnit WHERE id = :buId")
	BusinessUnitProjection fetchName(@Param("buId") Long buId);
	
}
