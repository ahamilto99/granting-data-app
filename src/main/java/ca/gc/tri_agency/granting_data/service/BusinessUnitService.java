package ca.gc.tri_agency.granting_data.service;

import java.util.List;

import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.BusinessUnit;

public interface BusinessUnitService {

	BusinessUnit findBusinessUnitById(Long id);
	
	List<BusinessUnit> findAllBusinessUnits();
	
	List<BusinessUnit> findAllBusinessUnitsByAgency(Agency agency);

	BusinessUnit saveBusinessUnit(BusinessUnit bu);

}
