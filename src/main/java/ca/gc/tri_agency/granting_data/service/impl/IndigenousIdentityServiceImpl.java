package ca.gc.tri_agency.granting_data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.model.IndigenousIdentity;
import ca.gc.tri_agency.granting_data.repo.IndigenousIdentityRepository;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.IndigenousIdentitySerivce;

@Service
public class IndigenousIdentityServiceImpl implements IndigenousIdentitySerivce {

	private IndigenousIdentityRepository indigenousIdentityRepo;
	
	@Autowired
	public IndigenousIdentityServiceImpl(IndigenousIdentityRepository indigenousIdentityRepo) {
		 this.indigenousIdentityRepo = indigenousIdentityRepo;
	}

	@Override
	public IndigenousIdentity findIndigenousIdentityById(Long id) {
		return indigenousIdentityRepo.findById(id).orElseThrow(() -> new DataAccessResourceFailureException("That Indigenous Identity does not exist"));
	}

	@Override
	public IndigenousIdentity findIndigenousIdentityByNameEn(String nameEn) {
		return indigenousIdentityRepo.findByNameEn(nameEn).orElseThrow(() -> new DataAccessResourceFailureException("That Indigenous Identity does not exst"));
	}

	@Override
	public IndigenousIdentity findIndigenousIdentityByNameFr(String nameFr) {
		return indigenousIdentityRepo.findByNameFr(nameFr).orElseThrow(() -> new DataAccessResourceFailureException("That Indigenous Identity does not exist"));
	}

	@Override
	public List<IndigenousIdentity> findAllIndigenousIdentities() {
		return indigenousIdentityRepo.findAll();
	}

	@AdminOnly
	@Override
	public IndigenousIdentity saveIndigenousIdentity(IndigenousIdentity indigenousIdentity) {
		return indigenousIdentityRepo.save(indigenousIdentity);
	}

}
