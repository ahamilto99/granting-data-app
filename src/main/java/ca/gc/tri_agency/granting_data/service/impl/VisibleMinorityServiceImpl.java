package ca.gc.tri_agency.granting_data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.model.VisibleMinority;
import ca.gc.tri_agency.granting_data.repo.VisibleMinorityRepository;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.VisibleMinorityService;

@Service
public class VisibleMinorityServiceImpl implements VisibleMinorityService {

	private VisibleMinorityRepository vMinorityRepo;

	@Autowired
	public VisibleMinorityServiceImpl(VisibleMinorityRepository vMinorityRepo) {
		this.vMinorityRepo = vMinorityRepo;
	}

	@Override
	public VisibleMinority findVisibleMinorityById(Long id) {
		return vMinorityRepo.findById(id)
				.orElseThrow(() -> new DataRetrievalFailureException("That Visible Minority does not exist"));
	}

	@Override
	public VisibleMinority findVisibleMinorityByNameEn(String nameEn) {
		return vMinorityRepo.findByNameEn(nameEn)
				.orElseThrow(() -> new DataRetrievalFailureException("That Visible Minority does not exist"));
	}

	@Override
	public VisibleMinority findVisibleMinorityByNameFr(String nameFr) {
		return vMinorityRepo.findByNameFr(nameFr)
				.orElseThrow(() -> new DataRetrievalFailureException("That Visible Minority does not exist"));
	}

	@Override
	public List<VisibleMinority> findAllVisibleMinorities() {
		return vMinorityRepo.findAll();
	}

	@AdminOnly
	@Override
	public VisibleMinority saveVisibleMinority(VisibleMinority minority) {
		return vMinorityRepo.save(minority);
	}

}
