package ca.gc.tri_agency.granting_data.service;

import java.util.List;
import java.util.Optional;

import ca.gc.tri_agency.granting_data.app.exception.UniqueColumnException;
import ca.gc.tri_agency.granting_data.model.FiscalYear;

public interface FiscalYearService {

	FiscalYear findFiscalYearById(Long id);
	
	List<FiscalYear> findAllFiscalYears();
	
	Optional<FiscalYear> findFiscalYearByYear(Long yr);
	
	FiscalYear saveFiscalYear(FiscalYear fy) throws UniqueColumnException;
	
}
