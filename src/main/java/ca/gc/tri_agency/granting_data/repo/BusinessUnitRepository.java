package ca.gc.tri_agency.granting_data.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.BusinessUnit;
import ca.gc.tri_agency.granting_data.model.projection.BusinessUnitProjection;

@Repository
@Transactional(readOnly = true)
public interface BusinessUnitRepository extends JpaRepository<BusinessUnit, Long> { // @formatter:off

	List<BusinessUnit> findAllByAgency(Agency agency);
	
	@Query("SELECT nameEn AS nameEn, nameFr AS nameFr FROM BusinessUnit WHERE id = :buId")
	Optional<BusinessUnitProjection> findName(@Param("buId") Long buId);

	@Query("SELECT bu.id AS id, bu.nameEn AS nameEn, bu.nameFr AS nameFr, bu.acronymEn AS acronymEn, bu.acronymFr AS acronymFr,"
			+ " a.id AS agencyId, a.nameEn AS agencyEn, a.nameFr AS agencyFr, mr.id AS memRoleId, mr.userLogin AS memRoleLogin,"
			+ " mr.ediAuthorized AS memRoleEdiAuthorized, r.nameEn AS memRoleEn, r.nameFr AS memRoleFr"
			+ " FROM BusinessUnit bu"
			+ " LEFT JOIN Agency a ON bu.agency.id = a.id"
			+ " LEFT JOIN MemberRole mr ON bu.id = mr.businessUnit.id"
			+ " LEFT JOIN Role r on mr.role.id = r.id"
			+ " WHERE bu.id = ?1")
	List<BusinessUnitProjection> findForViewBU(Long buId);
	
	@Query("SELECT bu, a"
			+ " FROM BusinessUnit bu "
			+ " JOIN FETCH Agency a ON bu.agency.id = a.id"
			+ " WHERE bu.id = ?1")
	BusinessUnit findWithAgency(Long buId);
	
}  // @formatter:on
