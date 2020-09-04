package ca.gc.tri_agency.granting_data.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.model.projection.SystemFundingOpportunityProjection;

@Repository
@Transactional(readOnly = true)
public interface SystemFundingOpportunityRepository extends JpaRepository<SystemFundingOpportunity, Long> { // @formatter:off

	List<SystemFundingOpportunity> findByLinkedFundingOpportunityId(Long foId);

	List<SystemFundingOpportunity> findByExtId(String extId);

	List<SystemFundingOpportunity> findByNameEn(String nameEn);

	List<SystemFundingOpportunity> findByLinkedFundingOpportunityBusinessUnitIdIn(List<Long> targetBuIds);

	@Query("SELECT sfo.id AS id, sfo.nameEn AS nameEn, sfo.nameFr AS nameFr, sfo.extId AS extId, fo.id AS fundingOpportunityId,"
			+ " fo.nameEn AS fundingOpportunityEn, fo.nameFr AS fundingOpportunityFr"
			+ " FROM SystemFundingOpportunity sfo"
			+ " LEFT JOIN FundingOpportunity fo ON sfo.linkedFundingOpportunity.id = fo.id"
			+ " WHERE sfo.id = ?1")
	Optional<SystemFundingOpportunityProjection> findSFOAndFOName(Long sfoId);

	@Query("SELECT sfo.id AS id, sfo.nameEn AS nameEn, sfo.nameFr AS nameFr, sfo.extId AS extId, fo.id AS fundingOpportunityId,"
			+ " fo.nameEn AS fundingOpportunityEn, fo.nameFr AS fundingOpportunityFr, gs.id AS grantingSystemId,"
			+ " gs.nameEn AS grantingSystemEn, gs.nameFr AS grantingSystemFr"
			+ " FROM SystemFundingOpportunity sfo"
			+ " LEFT JOIN FundingOpportunity fo ON sfo.linkedFundingOpportunity.id = fo.id"
			+ " LEFT JOIN GrantingSystem gs ON sfo.grantingSystem.id = gs.id")
	List<SystemFundingOpportunityProjection> findAllSFOsAndFONameAndGSysName();

	@Query("SELECT sfo.id AS id, sfo.nameEn AS nameEn, sfo.nameFr AS nameFr, fo.id AS fundingOpportunityId,"
			+ " fo.nameEn AS fundingOpportunityEn, fo.nameFr AS fundingOpportunityFr"
			+ " FROM SystemFundingOpportunity sfo"
			+ " JOIN FundingOpportunity fo ON sfo.linkedFundingOpportunity.id = fo.id"
			+ " WHERE sfo.id = ?1")
	Optional<SystemFundingOpportunityProjection> findSFONameAndFOName(Long sfoId);

} // @formatter:on
