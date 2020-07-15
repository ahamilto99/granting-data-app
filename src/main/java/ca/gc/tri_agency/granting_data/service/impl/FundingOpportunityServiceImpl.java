package ca.gc.tri_agency.granting_data.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.form.FundingOpportunityFilterForm;
import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.model.GrantingSystem;
import ca.gc.tri_agency.granting_data.model.auditing.UsernameRevisionEntity;
import ca.gc.tri_agency.granting_data.model.projection.FundingOpportunityProjection;
import ca.gc.tri_agency.granting_data.repo.FundingOpportunityRepository;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.FundingOpportunityService;

@Service
public class FundingOpportunityServiceImpl implements FundingOpportunityService {

	private FundingOpportunityRepository foRepo;

	private final String COL_SEPARATOR = "\n~@~\n";
	
	@PersistenceUnit
	private EntityManagerFactory emf;

	@Autowired
	public FundingOpportunityServiceImpl(FundingOpportunityRepository foRepo) {
		this.foRepo = foRepo;
	}

	@Override
	public FundingOpportunity findFundingOpportunityById(Long foId) {
		return foRepo.findById(foId)
				.orElseThrow(() -> new DataRetrievalFailureException("That Funding Opportunity does not exist"));
	}

	@Override
	public List<FundingOpportunity> findAllFundingOpportunities() {
		return foRepo.findAll();
	}

	@Override
	public List<FundingOpportunity> findFundingOpportunitiesByNameEn(String nameEn) {
		return foRepo.findByNameEn(nameEn);
	}

	@Override
	public List<FundingOpportunity> findFundingOpportunitiesByBusinessUnitId(Long buId) {
		return foRepo.findByBusinessUnitId(buId);
	}

	@AdminOnly
	@Override
	public FundingOpportunity saveFundingOpportunity(FundingOpportunity fo) {
		return foRepo.save(fo);
	}

	@Override
	public List<FundingOpportunity> getFilteredFundingOpportunities(FundingOpportunityFilterForm filter,
			Map<Long, GrantingSystem> applyMap, Map<Long, List<GrantingSystem>> awardMap) {
		// RELATIVELY SMALL DATASET (140), SO NO USE PERFORMING A QUERY FOR EACH FILTER. WILL SIMPLY WEED
		// THEM OUT IN A LOOP.
		List<FundingOpportunity> fullList = findAllFundingOpportunities();
		List<FundingOpportunity> retval = new ArrayList<FundingOpportunity>();
		GrantingSystem targetSystem = null;
		for (FundingOpportunity fo : fullList) {
			if (filter.getApplySystem() != null) {
				targetSystem = applyMap.get(fo.getId());
				if (targetSystem == null
						|| targetSystem != null && targetSystem.getId() != filter.getApplySystem().getId()) {
					continue;
				}
			}
			if (filter.getAwardSystem() != null) {
				boolean hasSystem = false;
				if (awardMap.get(fo.getId()) != null) {
					for (GrantingSystem sys : awardMap.get(fo.getId())) {
						if (sys == null || sys != null && sys.getId() == filter.getAwardSystem().getId()) {
							hasSystem = true;
						}
					}

				}
				if (hasSystem == false) {
					continue;
				}
			}
			if (filter.getDivision() != null) {
				if (fo.getBusinessUnit() == null || fo.getBusinessUnit().getId() != filter.getDivision().getId()) {
					continue;
				}
			}
			if (filter.getType() != null && filter.getType().length() != 0) {
				if (fo.getFundingType() == null || filter.getType().compareTo(fo.getFundingType()) != 0) {
					continue;
				}
			}
			retval.add(fo);
		}
		return retval;
	}

	@Override
	public List<FundingOpportunity> findFundingOpportunitiesByAgency(Agency agency) {
		return foRepo.findByAgency(agency);
	}

	@Override
	public List<String[]> findGoldenListTableResults() {
		List<FundingOpportunityProjection> resultSet;
		Map<String, String[]> resultSetMapWithoutValues = new HashMap<>();

		if (LocaleContextHolder.getLocale().getLanguage().equals("fr")) {
			resultSet = findGoldenListTableResultsHelperFr();
			resultSet.forEach(projection -> {
				String key = projection.getId().toString() + COL_SEPARATOR + projection.getNameFr() + COL_SEPARATOR
						+ projection.getBusinessUnitNameFr();
				resultSetMapWithoutValues.put(key, new String[] { "", "" });
			});
		} else {
			resultSet = findGoldenListTableResultsHelperEn();
			resultSet.forEach(projection -> {
				String key = projection.getId().toString() + COL_SEPARATOR + projection.getNameEn() + COL_SEPARATOR
						+ projection.getBusinessUnitNameEn();
				resultSetMapWithoutValues.put(key, new String[] { "", "" });
			});
		}

		Map<String, String[]> resultSetMapWithValues = extractApplyAndAwardSystemsForGoldenList(resultSet,
				resultSetMapWithoutValues);

		return convertResultSetMapForGoldenList(resultSetMapWithValues);
	}

	@Transactional(readOnly = true)
	private List<FundingOpportunityProjection> findGoldenListTableResultsHelperEn() {
		return foRepo.findResultsForGoldenListTableEn();
	}

	@Transactional(readOnly = true)
	private List<FundingOpportunityProjection> findGoldenListTableResultsHelperFr() {
		return foRepo.findResultsForGoldenListTableFr();
	}

	private Map<String, String[]> extractApplyAndAwardSystemsForGoldenList(List<FundingOpportunityProjection> foProjections,
			Map<String, String[]> resultSetMap) {
		foProjections.forEach(projection -> {

			String grantingSys = projection.getGrantingSystemAcronym();

			resultSetMap.forEach((k, v) -> {
				String[] id = k.split(COL_SEPARATOR);
				if (projection.getGrantingStageId() != null && id[0].equals(projection.getId().toString())) {

					if (projection.getGrantingStageId() == 2L) {
						v[0] = grantingSys;
					} else if (projection.getGrantingStageId() == 4L) {
						v[1] += " / " + grantingSys;
					}

				}
			});

		});

		return resultSetMap;
	}

	private List<String[]> convertResultSetMapForGoldenList(Map<String, String[]> resultSetMap) {
		List<String[]> retVal = new ArrayList<>();

		resultSetMap.forEach((k, v) -> {
			String[] idNameBu = k.split(COL_SEPARATOR);
			retVal.add(new String[] { idNameBu[0], idNameBu[1], idNameBu[2], v[0], v[1].length() > 3 ? v[1].substring(3) : "" });
		});

		return retVal;
	}

	private List<String[]> convertAuditResults(List<Object[]> revisionList) {
		List<String[]> auditArrList = new ArrayList<>();

		revisionList.forEach(objArr -> {
			FundingOpportunity fo = (FundingOpportunity) objArr[0];
			UsernameRevisionEntity revEntity = (UsernameRevisionEntity) objArr[1];
			RevisionType revType = (RevisionType) objArr[2];

			auditArrList.add(new String[] { fo.getId().toString(), revEntity.getUsername(), revType.toString(), fo.getNameEn(), fo.getNameFr(),
					fo.getFrequency(), fo.getFundingType(),
					(fo.getIsComplex() != null) ? fo.getIsComplex().toString() : null,
					(fo.getIsEdiRequired() != null) ? fo.getIsEdiRequired().toString() : null,
					(fo.getIsJointInitiative() != null) ? fo.getIsJointInitiative().toString() : null,
					(fo.getIsLOI() != null) ? fo.getIsLOI().toString() : null,
					(fo.getIsNOI() != null) ? fo.getIsNOI().toString() : null, fo.getPartnerOrg(),
					(fo.getBusinessUnit() != null) ? fo.getBusinessUnit().getId().toString() : null,
					revEntity.getRevTimestamp().toString()

			});
		});

		return auditArrList;
	}

	@AdminOnly
	@SuppressWarnings("unchecked")
	@Override
	public List<String[]> findFundingOpportunityRevisionsById(Long foId) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		AuditReader auditReader = AuditReaderFactory.get(em);

		AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(FundingOpportunity.class, false, true);
		auditQuery.add(AuditEntity.id().eq(foId));
		auditQuery.addOrder(AuditEntity.revisionProperty("id").asc());
		List<Object[]> revisionList = auditQuery.getResultList();

		em.getTransaction().commit();
		em.close();

		return convertAuditResults(revisionList);
	}

	@AdminOnly
	@SuppressWarnings("unchecked")
	@Override
	public List<String[]> findAllFundingOpportunitiesRevisions() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		AuditReader auditReader = AuditReaderFactory.get(em);

		AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(FundingOpportunity.class, false, true);
		auditQuery.addOrder(AuditEntity.revisionProperty("id").asc());
		List<Object[]> revisionList = auditQuery.getResultList();

		em.getTransaction().commit();
		em.close();

		return convertAuditResults(revisionList);
	}

}
