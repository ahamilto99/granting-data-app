package ca.gc.tri_agency.granting_data.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.form.FundingOpportunityFilterForm;
import ca.gc.tri_agency.granting_data.model.FiscalYear;
import ca.gc.tri_agency.granting_data.model.FundingCycle;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.model.GrantingSystem;
import ca.gc.tri_agency.granting_data.model.SystemFundingCycle;
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.repo.FiscalYearRepository;
import ca.gc.tri_agency.granting_data.repo.FundingCycleRepository;
import ca.gc.tri_agency.granting_data.repo.FundingOpportunityRepository;
import ca.gc.tri_agency.granting_data.repo.SystemFundingCycleRepository;
import ca.gc.tri_agency.granting_data.repo.SystemFundingOpportunityRepository;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.DataAccessService;

@Service
public class DataAccessServiceImpl implements DataAccessService {

	@Autowired
	private SystemFundingOpportunityRepository systemFoRepo;
	@Autowired
	private SystemFundingCycleRepository systemFundingCycleRepo;
	@Autowired
	private FundingOpportunityRepository foRepo;
	@Autowired
	private FundingCycleRepository fundingCycleRepo;
	@Autowired
	private FundingCycleRepository fcRepo;
	@Autowired
	private FiscalYearRepository fyRepo;

	@Override
	public List<SystemFundingOpportunity> getAllSystemFOs() {
		return systemFoRepo.findAll();
	}

	@Override
	public SystemFundingOpportunity getSystemFO(long id) {
		return systemFoRepo.findById(id).orElseThrow(
				() -> new DataRetrievalFailureException("That System Funding Opportunity does not exist"));
	}

	@Override
	public List<FundingOpportunity> getAllFundingOpportunities() {
		return foRepo.findAll();
	}

	@Override
	public FundingOpportunity getFundingOpportunity(long id) {
		return foRepo.findById(id)
				.orElseThrow(() -> new DataRetrievalFailureException("That Funding Opportunity does not exist"));
	}

	@Override
	public List<FundingCycle> getFundingCyclesByFoId(Long id) {
		return fundingCycleRepo.findByFundingOpportunityId(id);
	}

	/*
	 * FIXME: CONCEPT OF `1 TO `MANY `ON `IS` DUE` TO `CGSM. `CHANGED `THIS FUNCTION `TO RETURN `LIST,
	 * `BUT `THIS` NEEDS` MORE` REFACTORING AS IT DOES 3 DB CALLS or more. THIS NEEDS ANALYSIS
	 */
	@Override
	public List<SystemFundingCycle> getSystemFundingCyclesByFoId(Long id) {
		ArrayList<SystemFundingCycle> retval = new ArrayList<SystemFundingCycle>();
		List<SystemFundingOpportunity> sysFos = systemFoRepo.findByLinkedFundingOpportunityId(id);
		for (SystemFundingOpportunity sfo : sysFos) {
			List<SystemFundingCycle> sfoFcs = systemFundingCycleRepo.findBySystemFundingOpportunityId(sfo.getId());
			retval.addAll(sfoFcs);
		}
		return retval;
	}

	@Override
	public List<FundingOpportunity> getFoByNameEn(String nameEn) {
		return foRepo.findAllByNameEn(nameEn);
	}

	@Override
	public Map<Long, FundingCycle> getFundingCycleByFundingOpportunityMap() {
		Map<Long, FundingCycle> retval = new HashMap<Long, FundingCycle>();
		List<FundingCycle> fundingCycles = fcRepo.findAll();
		for (FundingCycle fc : fundingCycles) {
			retval.put(fc.getFundingOpportunity().getId(), fc);
		}
		return retval;
	}

	@Override
	public List<FundingOpportunity> getAgencyFundingOpportunities(long id) {
		return foRepo.findAllByLeadAgencyId(id);
	}

	@Override
	public Map<String, List<FundingCycle>> getMonthlyFundingCyclesMapByDate(long plusMinusMonth) {
		Map<String, List<FundingCycle>> retval = new HashMap<String, List<FundingCycle>>();
		LocalDate now = LocalDate.now();
		Date startDate, endDate;
		if (plusMinusMonth == 0) {
			startDate = java.sql.Date.valueOf(now);
			endDate = java.sql.Date.valueOf(now.plusMonths(1));
		} else if (plusMinusMonth < 0) {
			startDate = java.sql.Date.valueOf(now.minusMonths(plusMinusMonth * -1));
			endDate = java.sql.Date.valueOf(now.minusMonths((plusMinusMonth * -1) + 1));
		} else {
			startDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth));
			endDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth + 1));
		}

		// GET FCs THAT HAVE A START OR END DATE WITHIN THE RANGE (TARGET MONTH)
		List<FundingCycle> fcList = fcRepo
				.findAllByStartDateGreaterThanEqualAndStartDateLessThanOrEndDateGreaterThanEqualAndEndDateLessThan(
						startDate, endDate, startDate, endDate);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for (FundingCycle fc : fcList) {
			String startDateKey = formatter.format(fc.getStartDate());
			String endDateKey = formatter.format(fc.getEndDate());
			if (retval.get(startDateKey) == null) {
				retval.put(startDateKey, new ArrayList<FundingCycle>());
			}
			if (retval.get(endDateKey) == null) {
				retval.put(endDateKey, new ArrayList<FundingCycle>());
			}
			retval.get(startDateKey).add(fc);
			retval.get(endDateKey).add(fc);
		}
		return retval;
	}

	@Override
	public FundingCycle getFundingCycle(long id) {
		return fcRepo.findById(id).orElseThrow(() -> new DataRetrievalFailureException("That Funding Cycle does not exist"));
	}

	public List<FundingCycle> getAllFundingCycles() {
		return fcRepo.findAll();
	}

	@Override
	public List<FiscalYear> findAllFiscalYears() {
		// TODO Auto-generated method stub
		return fyRepo.findAll();
	}

	@SuppressWarnings("null")
	@Override
	public List<FiscalYear> fiscalYears() {
		List<FiscalYear> fy = fyRepo.findAll();
		return fy;
	}

	@SuppressWarnings("null")
	@Override
	public List<FundingCycle> fundingCyclesByFiscalYearId(Long Id) {
		List<FundingCycle> fc = fcRepo.findAll();
		List<FundingCycle> fcNew = new ArrayList<FundingCycle>();

		for (FundingCycle e : fc) {
			if (e.getFiscalYear().getId() == Id) {
				fcNew.add(e);
			}

		}

		return fcNew;
	}

	@Override
	public void createFy(Long year) {
		FiscalYear fy = new FiscalYear();
		fy.setYear(year);
		fyRepo.save(fy);

	}

	public String getEmail(String dn) {
		// String x = userRepo.g
		return null;

	}

	@AdminOnly
	@Override
	public void createFo(FundingOpportunity fo) {
		// TODO Auto-generated method stub
		foRepo.save(fo);

	}

	@Override
	public Map<String, List<FundingCycle>> getAllStartingDates(Long plusMinusMonth) {

		Map<String, List<FundingCycle>> retval = new HashMap<String, List<FundingCycle>>();
		LocalDate now = LocalDate.now();
		Date startDate, endDate;
		if (plusMinusMonth == 0) {
			startDate = java.sql.Date.valueOf(now);
			endDate = java.sql.Date.valueOf(now.plusMonths(1));
		} else if (plusMinusMonth < 0) {
			startDate = java.sql.Date.valueOf(now.minusMonths(plusMinusMonth * -1));
			endDate = java.sql.Date.valueOf(now.minusMonths((plusMinusMonth * -1) + 1));
		} else {
			startDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth));
			endDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth + 1));
		}

		// GET FCs THAT HAVE A START OR END DATE WITHIN THE RANGE (TARGET MONTH)
		List<FundingCycle> fcList = fcRepo
				.findAllByStartDateGreaterThanEqualAndStartDateLessThanOrEndDateGreaterThanEqualAndEndDateLessThan(
						startDate, endDate, startDate, endDate);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for (FundingCycle fc : fcList) {
			String startDateKey = formatter.format(fc.getStartDate());
			if (retval.get(startDateKey) == null) {
				retval.put(startDateKey, new ArrayList<FundingCycle>());
			}
			retval.get(startDateKey).add(fc);

		}
		return retval;
	}

	@Override
	public Map<String, List<FundingCycle>> getAllEndingDates(Long plusMinusMonth) {

		Map<String, List<FundingCycle>> retval = new HashMap<String, List<FundingCycle>>();
		LocalDate now = LocalDate.now();
		Date startDate, endDate;
		if (plusMinusMonth == 0) {
			startDate = java.sql.Date.valueOf(now);
			endDate = java.sql.Date.valueOf(now.plusMonths(1));
		} else if (plusMinusMonth < 0) {
			startDate = java.sql.Date.valueOf(now.minusMonths(plusMinusMonth * -1));
			endDate = java.sql.Date.valueOf(now.minusMonths((plusMinusMonth * -1) + 1));
		} else {
			startDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth));
			endDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth + 1));
		}

		List<FundingCycle> fcList = fcRepo
				.findAllByStartDateGreaterThanEqualAndStartDateLessThanOrEndDateGreaterThanEqualAndEndDateLessThan(
						startDate, endDate, startDate, endDate);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for (FundingCycle fc : fcList) {
			String endDateKey = formatter.format(fc.getEndDate());

			if (retval.get(endDateKey) == null) {
				retval.put(endDateKey, new ArrayList<FundingCycle>());
			}

			retval.get(endDateKey).add(fc);
		}
		return retval;
	}

	@Override
	public Map<String, List<FundingCycle>> getAllDatesNOIStart(long plusMinusMonth) {
		Map<String, List<FundingCycle>> retval = new HashMap<String, List<FundingCycle>>();
		LocalDate now = LocalDate.now();
		Date startDate, endDate;
		if (plusMinusMonth == 0) {
			startDate = java.sql.Date.valueOf(now);
			endDate = java.sql.Date.valueOf(now.plusMonths(1));
		} else if (plusMinusMonth < 0) {
			startDate = java.sql.Date.valueOf(now.minusMonths(plusMinusMonth * -1));
			endDate = java.sql.Date.valueOf(now.minusMonths((plusMinusMonth * -1) + 1));
		} else {
			startDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth));
			endDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth + 1));
		}

		// GET FCs THAT HAVE A START OR END DATE WITHIN THE RANGE (TARGET MONTH)
		List<FundingCycle> fcList = fcRepo
				.findAllByStartDateGreaterThanEqualAndStartDateLessThanOrEndDateGreaterThanEqualAndEndDateLessThan(
						startDate, endDate, startDate, endDate);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for (FundingCycle fc : fcList) {
			String startDateKey = formatter.format(fc.getStartDateNOI());
			String endDateKey = formatter.format(fc.getEndDateNOI());
			if (retval.get(startDateKey) == null) {
				retval.put(startDateKey, new ArrayList<FundingCycle>());
			}
			if (retval.get(endDateKey) == null) {
				retval.put(endDateKey, new ArrayList<FundingCycle>());
			}
			retval.get(startDateKey).add(fc);
		}
		return retval;
	}

	@Override
	public Map<String, List<FundingCycle>> getAllDatesNOIEnd(long plusMinusMonth) {
		Map<String, List<FundingCycle>> retval = new HashMap<String, List<FundingCycle>>();
		LocalDate now = LocalDate.now();
		Date startDate, endDate;
		if (plusMinusMonth == 0) {
			startDate = java.sql.Date.valueOf(now);
			endDate = java.sql.Date.valueOf(now.plusMonths(1));
		} else if (plusMinusMonth < 0) {
			startDate = java.sql.Date.valueOf(now.minusMonths(plusMinusMonth * -1));
			endDate = java.sql.Date.valueOf(now.minusMonths((plusMinusMonth * -1) + 1));
		} else {
			startDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth));
			endDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth + 1));
		}

		// GET FCs THAT HAVE A START OR END DATE WITHIN THE RANGE (TARGET MONTH)
		List<FundingCycle> fcList = fcRepo
				.findAllByStartDateGreaterThanEqualAndStartDateLessThanOrEndDateGreaterThanEqualAndEndDateLessThan(
						startDate, endDate, startDate, endDate);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for (FundingCycle fc : fcList) {
			String startDateKey = formatter.format(fc.getStartDateNOI());
			String endDateKey = formatter.format(fc.getEndDateNOI());
			if (retval.get(startDateKey) == null) {
				retval.put(startDateKey, new ArrayList<FundingCycle>());
			}
			if (retval.get(endDateKey) == null) {
				retval.put(endDateKey, new ArrayList<FundingCycle>());
			}
			retval.get(startDateKey).add(fc);
		}
		return retval;
	}

	@Override
	public Map<String, List<FundingCycle>> getAllDatesLOIStart(long plusMinusMonth) {
		Map<String, List<FundingCycle>> retval = new HashMap<String, List<FundingCycle>>();
		LocalDate now = LocalDate.now();
		Date startDate, endDate;
		if (plusMinusMonth == 0) {
			startDate = java.sql.Date.valueOf(now);
			endDate = java.sql.Date.valueOf(now.plusMonths(1));
		} else if (plusMinusMonth < 0) {
			startDate = java.sql.Date.valueOf(now.minusMonths(plusMinusMonth * -1));
			endDate = java.sql.Date.valueOf(now.minusMonths((plusMinusMonth * -1) + 1));
		} else {
			startDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth));
			endDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth + 1));
		}

		// GET FCs THAT HAVE A START OR END DATE WITHIN THE RANGE (TARGET MONTH)
		List<FundingCycle> fcList = fcRepo
				.findAllByStartDateGreaterThanEqualAndStartDateLessThanOrEndDateGreaterThanEqualAndEndDateLessThan(
						startDate, endDate, startDate, endDate);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for (FundingCycle fc : fcList) {
			String startDateKey = formatter.format(fc.getStartDateLOI());
			String endDateKey = formatter.format(fc.getEndDateLOI());
			if (retval.get(startDateKey) == null) {
				retval.put(startDateKey, new ArrayList<FundingCycle>());
			}
			if (retval.get(endDateKey) == null) {
				retval.put(endDateKey, new ArrayList<FundingCycle>());
			}
			retval.get(startDateKey).add(fc);
		}
		return retval;
	}

	@Override
	public Map<String, List<FundingCycle>> getAllDatesLOIEnd(long plusMinusMonth) {
		Map<String, List<FundingCycle>> retval = new HashMap<String, List<FundingCycle>>();
		LocalDate now = LocalDate.now();
		Date startDate, endDate;
		if (plusMinusMonth == 0) {
			startDate = java.sql.Date.valueOf(now);
			endDate = java.sql.Date.valueOf(now.plusMonths(1));
		} else if (plusMinusMonth < 0) {
			startDate = java.sql.Date.valueOf(now.minusMonths(plusMinusMonth * -1));
			endDate = java.sql.Date.valueOf(now.minusMonths((plusMinusMonth * -1) + 1));
		} else {
			startDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth));
			endDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth + 1));
		}

		// GET FCs THAT HAVE A START OR END DATE WITHIN THE RANGE (TARGET MONTH)
		List<FundingCycle> fcList = fcRepo
				.findAllByStartDateGreaterThanEqualAndStartDateLessThanOrEndDateGreaterThanEqualAndEndDateLessThan(
						startDate, endDate, startDate, endDate);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for (FundingCycle fc : fcList) {
			String startDateKey = formatter.format(fc.getStartDateLOI());
			String endDateKey = formatter.format(fc.getEndDateLOI());
			if (retval.get(startDateKey) == null) {
				retval.put(startDateKey, new ArrayList<FundingCycle>());
			}
			if (retval.get(endDateKey) == null) {
				retval.put(endDateKey, new ArrayList<FundingCycle>());
			}
			retval.get(endDateKey).add(fc);
		}
		return retval;
	}

	@Override
	public List<FundingOpportunity> getFilteredFundingOpportunities(FundingOpportunityFilterForm filter,
			Map<Long, GrantingSystem> applyMap, Map<Long, List<GrantingSystem>> awardMap) {
		// RELATIVELY SMALL DATASET (140), SO NO USE PERFORMING A QUERY FOR EACH FILTER. WILL SIMPLY WEED
		// THEM OUT IN A LOOP.
		List<FundingOpportunity> fullList = getAllFundingOpportunities();
		List<FundingOpportunity> retval = new ArrayList<FundingOpportunity>();
		GrantingSystem targetSystem = null;
		for (FundingOpportunity fo : fullList) {
			if (filter.getApplySystem() != null) {
				targetSystem = applyMap.get(fo.getId());
				if (targetSystem == null
						|| targetSystem != null && targetSystem.getId() != filter.getApplySystem().getId()) {
					continue;
				}
			}
			if (filter.getAwardSystem() != null) {
				boolean hasSystem = false;
				if (awardMap.get(fo.getId()) != null) {
					for (GrantingSystem sys : awardMap.get(fo.getId())) {
						if (sys == null || sys != null && sys.getId() == filter.getAwardSystem().getId()) {
							hasSystem = true;
						}
					}

				}
				if (hasSystem == false) {
					continue;
				}
			}
			if (filter.getDivision() != null) {
				if (fo.getBusinessUnit() == null || fo.getBusinessUnit().getId() != filter.getDivision().getId()) {
					continue;
				}
			}
			if (filter.getLeadAgency() != null) {
				if (fo.getBusinessUnit() == null || fo.getBusinessUnit() != null
						&& fo.getBusinessUnit().getAgency().getId() != filter.getLeadAgency().getId()) {
					continue;
				}
			}
			if (filter.getType() != null && filter.getType().length() != 0) {
				if (fo.getFundingType() == null || filter.getType().compareTo(fo.getFundingType()) != 0) {
					continue;
				}
			}
			retval.add(fo);
		}
		return retval;
	}

}
