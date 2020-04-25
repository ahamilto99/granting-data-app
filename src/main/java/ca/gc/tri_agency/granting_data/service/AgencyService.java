package ca.gc.tri_agency.granting_data.service;

import java.util.List;

import ca.gc.tri_agency.granting_data.model.Agency;

public interface AgencyService {

	Agency findAgencyById(Long id);
	
	List<Agency> findAllAgencies();
}
