package ca.gc.tri_agency.granting_data.service;

import java.util.List;

import ca.gc.tri_agency.granting_data.model.VisibleMinority;

public interface VisibleMinorityService {
	
	VisibleMinority findVisibleMinorityById(Long id);
	
	VisibleMinority findVisibleMinorityByNameEn(String nameEn);
	
	VisibleMinority findVisibleMinorityByNameFr(String nameFr);
	
	List<VisibleMinority> findAllVisibleMinorities();
	
	VisibleMinority saveVisibleMinority(VisibleMinority minority);

}
