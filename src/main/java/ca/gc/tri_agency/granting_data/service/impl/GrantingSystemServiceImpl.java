package ca.gc.tri_agency.granting_data.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.model.GrantingCapability;
import ca.gc.tri_agency.granting_data.model.GrantingSystem;
import ca.gc.tri_agency.granting_data.repo.GrantingCapabilityRepository;
import ca.gc.tri_agency.granting_data.repo.GrantingSystemRepository;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.GrantingSystemService;

@Service
public class GrantingSystemServiceImpl implements GrantingSystemService {

	private GrantingSystemRepository gsRepo;
	
	private GrantingCapabilityRepository grantingCapabilityRepo; // TODO: refactor GrantingCapability

	public GrantingSystemServiceImpl(GrantingSystemRepository gsRepo, GrantingCapabilityRepository grantingCapabilityRepo) {
		this.gsRepo = gsRepo;
		this.grantingCapabilityRepo = grantingCapabilityRepo;
	}

	@Override
	public GrantingSystem findGrantingSystemById(Long id) {
		return gsRepo.findById(id).orElseThrow(() -> new DataRetrievalFailureException("That Granting System does not exist"));
	}

	@Override
	public List<GrantingSystem> findAllGrantingSystems() {
		return gsRepo.findAll();
	}

	@Override
	public GrantingSystem findGrantingSystemByAcronym(String acronym) {
		return gsRepo.findByAcronym(acronym);
	}

	@AdminOnly
	@Override
	public GrantingSystem findGrantingSystemFromFile(String fileName) {
		List<GrantingSystem> grantingSystems = findAllGrantingSystems();
		GrantingSystem gs = null;
		for (GrantingSystem system : grantingSystems) {
			if (fileName.contains(system.getAcronym())) {
				gs = system;
			}
		}
		return gs;
	}

	@Override
	public Map<Long, GrantingSystem> findApplySystemsByFundingOpportunityMap() {
		Map<Long, GrantingSystem> retval = new HashMap<>();
		List<GrantingCapability> applyCapabilities = grantingCapabilityRepo.findByGrantingStageNameEn("APPLY");
		for (GrantingCapability c : applyCapabilities) {
			retval.put(c.getFundingOpportunity().getId(), c.getGrantingSystem());
		}
		return retval;
	}

	@Override
	public Map<Long, List<GrantingSystem>> findAwardSystemsByFundingOpportunityMap() {
		Map<Long, List<GrantingSystem>> retval = new HashMap<Long, List<GrantingSystem>>();
		List<GrantingCapability> applyCapabilities = grantingCapabilityRepo.findByGrantingStageNameEn("AWARD");
		for (GrantingCapability c : applyCapabilities) {
			if (retval.containsKey(c.getFundingOpportunity().getId()) == false) {
				retval.put(c.getFundingOpportunity().getId(), new ArrayList<GrantingSystem>());
			}
			retval.get(c.getFundingOpportunity().getId()).add(c.getGrantingSystem());
		}
		return retval;
	}

}
