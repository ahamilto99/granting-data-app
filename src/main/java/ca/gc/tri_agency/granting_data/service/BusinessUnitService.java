package ca.gc.tri_agency.granting_data.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.AccessDeniedException;

import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.BusinessUnit;
import ca.gc.tri_agency.granting_data.model.projection.BusinessUnitProjection;

public interface BusinessUnitService {

	BusinessUnit findBusinessUnitById(Long id);

	List<BusinessUnit> findAllBusinessUnits();

	List<BusinessUnit> findAllBusinessUnitsByAgency(Agency agency);

	BusinessUnit saveBusinessUnit(BusinessUnit bu);

	List<String[]> findBusinessUnitRevisionsById(Long buId);

	List<String[]> findAllBusinessUnitRevisions();

	Map<String, Long> findEdiAppPartDataForAuthorizedBUMember(Long buId) throws AccessDeniedException;
	
	BusinessUnitProjection fetchBusinessUnitName(Long buId);
}
