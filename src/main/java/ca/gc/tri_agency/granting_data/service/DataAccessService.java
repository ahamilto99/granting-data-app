package ca.gc.tri_agency.granting_data.service;

import java.util.List;
import java.util.Map;

import ca.gc.tri_agency.granting_data.form.FundingOpportunityFilterForm;
import ca.gc.tri_agency.granting_data.model.FiscalYear;
import ca.gc.tri_agency.granting_data.model.FundingCycle;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.model.GrantingSystem;
import ca.gc.tri_agency.granting_data.model.SystemFundingCycle;
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;

public interface DataAccessService {
	List<SystemFundingOpportunity> getAllSystemFOs();

	SystemFundingOpportunity getSystemFO(long id);

	List<FundingOpportunity> getAllFundingOpportunities();

	FundingOpportunity getFundingOpportunity(long id);

	List<FundingCycle> getFundingCyclesByFoId(Long id);

	List<SystemFundingCycle> getSystemFundingCyclesByFoId(Long id);

	List<FundingOpportunity> getFoByNameEn(String nameEn);

	Map<Long, FundingCycle> getFundingCycleByFundingOpportunityMap();

	List<FundingOpportunity> getAgencyFundingOpportunities(long id);

	Map<String, List<FundingCycle>> getMonthlyFundingCyclesMapByDate(long plusMinusMonth);

	FundingCycle getFundingCycle(long id);

	List<FundingCycle> getAllFundingCycles();

	List<FiscalYear> findAllFiscalYears();

	List<FiscalYear> fiscalYears();

	List<FundingCycle> fundingCyclesByFiscalYearId(Long Id);

	void createFy(Long year);

	void createFo(FundingOpportunity fo);

	Map<String, List<FundingCycle>> getAllStartingDates(Long plusMinusMonth);

	Map<String, List<FundingCycle>> getAllEndingDates(Long plusMinusMonth);

	Map<String, List<FundingCycle>> getAllDatesNOIStart(long plusMinusMonth);

	Map<String, List<FundingCycle>> getAllDatesNOIEnd(long plusMinusMonth);

	Map<String, List<FundingCycle>> getAllDatesLOIStart(long plusMinusMonth);

	Map<String, List<FundingCycle>> getAllDatesLOIEnd(long plusMinusMonth);

	List<FundingOpportunity> getFilteredFundingOpportunities(FundingOpportunityFilterForm filter,
			Map<Long, GrantingSystem> applyMap, Map<Long, List<GrantingSystem>> awardMap);

}
