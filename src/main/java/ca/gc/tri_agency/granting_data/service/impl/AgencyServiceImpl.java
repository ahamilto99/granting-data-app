package ca.gc.tri_agency.granting_data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.projection.AgencyProjection;
import ca.gc.tri_agency.granting_data.repo.AgencyRepository;
import ca.gc.tri_agency.granting_data.service.AgencyService;

@Service
public class AgencyServiceImpl implements AgencyService {

	private AgencyRepository agencyRepo;

	@Autowired
	public AgencyServiceImpl(AgencyRepository agencyRepo) {
		this.agencyRepo = agencyRepo;
	}

	@Override
	public Agency findAgencyById(Long id) {
		return agencyRepo.findById(id).orElseThrow(() -> new DataRetrievalFailureException("Agency id=" + id + " does not exist"));
	}

	@Override
	public List<Agency> findAllAgencies() {
		return agencyRepo.findAll();
	}

	@Override
	public List<AgencyProjection> findResultsForBrowseViewAgency(Long agencyId) {
		List<AgencyProjection> agencyProjections = agencyRepo.findForBrowseViewAgency(agencyId);

		if (agencyProjections.isEmpty()) {
			throw new DataRetrievalFailureException("Agency id=" + agencyId + " does not exist");
		}

		return agencyProjections;
	}

	@Override
	public AgencyProjection findAgencyName(Long agencyId) {
		return agencyRepo.findName(agencyId)
				.orElseThrow(() -> new DataRetrievalFailureException("Agency id=" + agencyId + " does not exist"));
	}

}
