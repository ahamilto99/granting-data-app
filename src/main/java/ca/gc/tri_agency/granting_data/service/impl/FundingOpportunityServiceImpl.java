package ca.gc.tri_agency.granting_data.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.form.FundingOpportunityFilterForm;
import ca.gc.tri_agency.granting_data.ldap.ADUser;
import ca.gc.tri_agency.granting_data.ldap.ADUserService;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.model.GrantingSystem;
import ca.gc.tri_agency.granting_data.repo.FundingOpportunityRepository;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.FundingOpportunityService;

@Service
public class FundingOpportunityServiceImpl implements FundingOpportunityService {

	private FundingOpportunityRepository foRepo;
	
	private ADUserService aduService;

	@Autowired
	public FundingOpportunityServiceImpl(FundingOpportunityRepository foRepo, ADUserService aduService) {
		this.foRepo = foRepo;
		this.aduService = aduService;
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
	public List<FundingOpportunity> findFundingOpportunitiesByLeadAgencyId(Long leadAgencyId) {
		return foRepo.findByLeadAgencyId(leadAgencyId);
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

	@AdminOnly
	@Transactional
	@Override
	public void setFundingOpportunityLeadContributor(Long foId, String dn) {
		ADUser adUser = aduService.findADUserByDn(dn);
		
		FundingOpportunity fo = findFundingOpportunityById(foId);
		fo.setProgramLeadDn(dn);
		fo.setProgramLeadName(adUser.getFullName());
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
			if (filter.getLeadAgency() != null) {
				if (fo.getBusinessUnit() == null || fo.getBusinessUnit() != null
						&& fo.getBusinessUnit().getAgency().getId() != filter.getLeadAgency().getId()) {
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

}
