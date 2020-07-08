package ca.gc.tri_agency.granting_data.service;

import java.util.List;

import ca.gc.tri_agency.granting_data.model.IndigenousIdentity;

public interface IndigenousIdentitySerivce {

	IndigenousIdentity findIndigenousIdentityById(Long id);
	
	IndigenousIdentity findIndigenousIdentityByNameEn(String nameEn);
	
	IndigenousIdentity findIndigenousIdentityByNameFr(String nameFr);
	
	List<IndigenousIdentity> findAllIndigenousIdentities() ;
	
	IndigenousIdentity saveIndigenousIdentity(IndigenousIdentity indigenousIdentity);
	
}
