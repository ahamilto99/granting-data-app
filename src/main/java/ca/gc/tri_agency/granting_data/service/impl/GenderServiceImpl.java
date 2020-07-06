package ca.gc.tri_agency.granting_data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.model.Gender;
import ca.gc.tri_agency.granting_data.repo.GenderRepository;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.GenderService;

@Service
public class GenderServiceImpl implements GenderService {

	private GenderRepository genderRepo;

	@Autowired
	public GenderServiceImpl(GenderRepository genderRepo) {
		this.genderRepo = genderRepo;
	}

	@Override
	public Gender findGenderById(Long id) {
		return genderRepo.findById(id)
				.orElseThrow(() -> new DataRetrievalFailureException("That Gender does not exist"));
	}

	@Override
	public Gender findGenderByNameEn(String nameEn) {
		return genderRepo.findByNameEn(nameEn)
				.orElseThrow(() -> new DataRetrievalFailureException("That Gender does not exist"));
	}

	@Override
	public Gender findGenderByNameFr(String nameFr) {
		return genderRepo.findByNameFr(nameFr)
				.orElseThrow(() -> new DataRetrievalFailureException("That Gender does not exist"));
	}
	
	@Override
	public List<Gender> findAllGenders() {
		return genderRepo.findAll();
	}

	@AdminOnly
	@Override
	public Gender saveGender(Gender gender) {
		return genderRepo.save(gender);
	}

}
