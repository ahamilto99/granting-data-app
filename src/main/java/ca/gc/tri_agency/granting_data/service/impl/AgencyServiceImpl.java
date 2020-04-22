package ca.gc.tri_agency.granting_data.service.impl;

import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.repo.AgencyRepository;
import ca.gc.tri_agency.granting_data.service.AgencyService;

@Service
public class AgencyServiceImpl implements AgencyService {

	private AgencyRepository agencyRepo;

	public AgencyServiceImpl(AgencyRepository agencyRepo) {
		this.agencyRepo = agencyRepo;
	}

	@Override
	public Agency findAgencyById(Long id) {
		return agencyRepo.findById(id).orElseThrow(() -> new DataRetrievalFailureException("That Agency does not exist"));
	}

	@Override
	public List<Agency> findAllAgencies() {
		return agencyRepo.findAll();
	}

}
