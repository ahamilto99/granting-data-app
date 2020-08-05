package ca.gc.tri_agency.granting_data.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.app.exception.UniqueColumnException;
import ca.gc.tri_agency.granting_data.model.FiscalYear;
import ca.gc.tri_agency.granting_data.model.projection.FiscalYearProjection;
import ca.gc.tri_agency.granting_data.repo.FiscalYearRepository;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.FiscalYearService;

@Service
public class FiscalYearServiceImpl implements FiscalYearService {

	private FiscalYearRepository fyRepo;

	@Autowired
	public FiscalYearServiceImpl(FiscalYearRepository fyRepo) {
		this.fyRepo = fyRepo;
	}

	@Override
	public FiscalYear findFiscalYearById(Long id) {
		return fyRepo.findById(id).orElseThrow(() -> new DataRetrievalFailureException("That Fiscal Year does not exist"));
	}

	@Override
	public List<FiscalYearProjection> findAllFiscalYearsOrderByYearAsc() {
		return fyRepo.findAllOrderedByYear();
	}

	@Override
	public Optional<FiscalYear> findFiscalYearByYear(Long yr) {
		return fyRepo.findByYear(yr);
	}

	@AdminOnly
	@Override
	public FiscalYear saveFiscalYear(FiscalYear fy) throws UniqueColumnException {
		if (findFiscalYearByYear(fy.getYear()).isPresent()) {
			throw new UniqueColumnException("That Year already exists");
		}
		return fyRepo.save(fy);
	}

	@Override
	public List<Object[]> findNumAppsExpectedForEachFiscalYear() {
		return fyRepo.findNumAppsExpectedForEachYear();
	}
	
}
