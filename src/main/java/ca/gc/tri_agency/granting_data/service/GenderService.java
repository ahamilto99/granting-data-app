package ca.gc.tri_agency.granting_data.service;

import java.util.List;

import ca.gc.tri_agency.granting_data.model.Gender;

public interface GenderService {
	
	Gender findGenderById(Long id);
	
	Gender findGenderByNameEn(String nameEn);
	
	Gender findGenderByNameFr(String nameFr);
	
	Gender saveGender(Gender gender);

	List<Gender> findAllGenders();
	
}
