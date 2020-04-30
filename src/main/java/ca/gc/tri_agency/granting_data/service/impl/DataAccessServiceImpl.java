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

import ca.gc.tri_agency.granting_data.model.FundingCycle;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.model.SystemFundingCycle;
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.repo.FundingCycleRepository;
import ca.gc.tri_agency.granting_data.repo.FundingOpportunityRepository;
import ca.gc.tri_agency.granting_data.repo.SystemFundingCycleRepository;
import ca.gc.tri_agency.granting_data.repo.SystemFundingOpportunityRepository;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.DataAccessService;
import ca.gc.tri_agency.granting_data.service.FundingCycleService;

@Service
public class DataAccessServiceImpl implements DataAccessService {

	@Autowired
	private SystemFundingOpportunityRepository systemFoRepo;
	@Autowired
	private SystemFundingCycleRepository systemFundingCycleRepo;
	@Autowired
	private FundingOpportunityRepository foRepo;
	@Autowired
	private FundingCycleService fcService;

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
		List<FundingCycle> fundingCycles = fcService.findAllFundingCycles();
		for (FundingCycle fc : fundingCycles) {
			retval.put(fc.getFundingOpportunity().getId(), fc);
		}
		return retval;
	}

	@Override
	public List<FundingOpportunity> getAgencyFundingOpportunities(long id) {
		return foRepo.findAllByLeadAgencyId(id);
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

//		Map<String, List<FundingCycle>> retval = new HashMap<String, List<FundingCycle>>();
//		LocalDate now = LocalDate.now();
//		Date startDate, endDate;
//		if (plusMinusMonth == 0) {
//			startDate = java.sql.Date.valueOf(now);
//			endDate = java.sql.Date.valueOf(now.plusMonths(1));
//		} else if (plusMinusMonth < 0) {
//			startDate = java.sql.Date.valueOf(now.minusMonths(plusMinusMonth * -1));
//			endDate = java.sql.Date.valueOf(now.minusMonths((plusMinusMonth * -1) + 1));
//		} else {
//			startDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth));
//			endDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth + 1));
//		}
//
//		// GET FCs THAT HAVE A START OR END DATE WITHIN THE RANGE (TARGET MONTH)
//		List<FundingCycle> fcList = fcService
//				.findFundingCyclesByStartDateGreaterThanEqualAndStartDateLessThanOrEndDateGreaterThanEqualAndEndDateLessThan(
//						startDate, endDate, startDate, endDate);
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		for (FundingCycle fc : fcList) {
//			String startDateKey = formatter.format(fc.getStartDate());
//			if (retval.get(startDateKey) == null) {
//				retval.put(startDateKey, new ArrayList<FundingCycle>());
//			}
//			retval.get(startDateKey).add(fc);
//
//		}
		return null;
	}

	@Override
	public Map<String, List<FundingCycle>> getAllEndingDates(Long plusMinusMonth) {

//		Map<String, List<FundingCycle>> retval = new HashMap<String, List<FundingCycle>>();
//		LocalDate now = LocalDate.now();
//		Date startDate, endDate;
//		if (plusMinusMonth == 0) {
//			startDate = java.sql.Date.valueOf(now);
//			endDate = java.sql.Date.valueOf(now.plusMonths(1));
//		} else if (plusMinusMonth < 0) {
//			startDate = java.sql.Date.valueOf(now.minusMonths(plusMinusMonth * -1));
//			endDate = java.sql.Date.valueOf(now.minusMonths((plusMinusMonth * -1) + 1));
//		} else {
//			startDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth));
//			endDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth + 1));
//		}
//
//		List<FundingCycle> fcList = fcService
//				.findFundingCyclesByStartDateGreaterThanEqualAndStartDateLessThanOrEndDateGreaterThanEqualAndEndDateLessThan(
//						startDate, endDate, startDate, endDate);
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		for (FundingCycle fc : fcList) {
//			String endDateKey = formatter.format(fc.getEndDate());
//
//			if (retval.get(endDateKey) == null) {
//				retval.put(endDateKey, new ArrayList<FundingCycle>());
//			}
//
//			retval.get(endDateKey).add(fc);
//		}
		return null;
	}

	@Override
	public Map<String, List<FundingCycle>> getAllDatesNOIStart(long plusMinusMonth) {
//		Map<String, List<FundingCycle>> retval = new HashMap<String, List<FundingCycle>>();
//		LocalDate now = LocalDate.now();
//		Date startDate, endDate;
//		if (plusMinusMonth == 0) {
//			startDate = java.sql.Date.valueOf(now);
//			endDate = java.sql.Date.valueOf(now.plusMonths(1));
//		} else if (plusMinusMonth < 0) {
//			startDate = java.sql.Date.valueOf(now.minusMonths(plusMinusMonth * -1));
//			endDate = java.sql.Date.valueOf(now.minusMonths((plusMinusMonth * -1) + 1));
//		} else {
//			startDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth));
//			endDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth + 1));
//		}
//
//		// GET FCs THAT HAVE A START OR END DATE WITHIN THE RANGE (TARGET MONTH)
//		List<FundingCycle> fcList = fcService
//				.findFundingCyclesByStartDateGreaterThanEqualAndStartDateLessThanOrEndDateGreaterThanEqualAndEndDateLessThan(
//						startDate, endDate, startDate, endDate);
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		for (FundingCycle fc : fcList) {
//			String startDateKey = formatter.format(fc.getStartDateNOI());
//			String endDateKey = formatter.format(fc.getEndDateNOI());
//			if (retval.get(startDateKey) == null) {
//				retval.put(startDateKey, new ArrayList<FundingCycle>());
//			}
//			if (retval.get(endDateKey) == null) {
//				retval.put(endDateKey, new ArrayList<FundingCycle>());
//			}
//			retval.get(startDateKey).add(fc);
//		}
		return null;
	}

	@Override
	public Map<String, List<FundingCycle>> getAllDatesNOIEnd(long plusMinusMonth) {
//		Map<String, List<FundingCycle>> retval = new HashMap<String, List<FundingCycle>>();
//		LocalDate now = LocalDate.now();
//		Date startDate, endDate;
//		if (plusMinusMonth == 0) {
//			startDate = java.sql.Date.valueOf(now);
//			endDate = java.sql.Date.valueOf(now.plusMonths(1));
//		} else if (plusMinusMonth < 0) {
//			startDate = java.sql.Date.valueOf(now.minusMonths(plusMinusMonth * -1));
//			endDate = java.sql.Date.valueOf(now.minusMonths((plusMinusMonth * -1) + 1));
//		} else {
//			startDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth));
//			endDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth + 1));
//		}
//
//		// GET FCs THAT HAVE A START OR END DATE WITHIN THE RANGE (TARGET MONTH)
//		List<FundingCycle> fcList = fcService
//				.findFundingCyclesByStartDateGreaterThanEqualAndStartDateLessThanOrEndDateGreaterThanEqualAndEndDateLessThan(
//						startDate, endDate, startDate, endDate);
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		for (FundingCycle fc : fcList) {
//			String startDateKey = formatter.format(fc.getStartDateNOI());
//			String endDateKey = formatter.format(fc.getEndDateNOI());
//			if (retval.get(startDateKey) == null) {
//				retval.put(startDateKey, new ArrayList<FundingCycle>());
//			}
//			if (retval.get(endDateKey) == null) {
//				retval.put(endDateKey, new ArrayList<FundingCycle>());
//			}
//			retval.get(startDateKey).add(fc);
//		}
		return null;
	}

	@Override
	public Map<String, List<FundingCycle>> getAllDatesLOIStart(long plusMinusMonth) {
//		Map<String, List<FundingCycle>> retval = new HashMap<String, List<FundingCycle>>();
//		LocalDate now = LocalDate.now();
//		Date startDate, endDate;
//		if (plusMinusMonth == 0) {
//			startDate = java.sql.Date.valueOf(now);
//			endDate = java.sql.Date.valueOf(now.plusMonths(1));
//		} else if (plusMinusMonth < 0) {
//			startDate = java.sql.Date.valueOf(now.minusMonths(plusMinusMonth * -1));
//			endDate = java.sql.Date.valueOf(now.minusMonths((plusMinusMonth * -1) + 1));
//		} else {
//			startDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth));
//			endDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth + 1));
//		}
//
//		// GET FCs THAT HAVE A START OR END DATE WITHIN THE RANGE (TARGET MONTH)
//		List<FundingCycle> fcList = fcService
//				.findFundingCyclesByStartDateGreaterThanEqualAndStartDateLessThanOrEndDateGreaterThanEqualAndEndDateLessThan(
//						startDate, endDate, startDate, endDate);
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		for (FundingCycle fc : fcList) {
//			String startDateKey = formatter.format(fc.getStartDateLOI());
//			String endDateKey = formatter.format(fc.getEndDateLOI());
//			if (retval.get(startDateKey) == null) {
//				retval.put(startDateKey, new ArrayList<FundingCycle>());
//			}
//			if (retval.get(endDateKey) == null) {
//				retval.put(endDateKey, new ArrayList<FundingCycle>());
//			}
//			retval.get(startDateKey).add(fc);
//		}
		return null;
	}

	@Override
	public Map<String, List<FundingCycle>> getAllDatesLOIEnd(long plusMinusMonth) {
//		Map<String, List<FundingCycle>> retval = new HashMap<String, List<FundingCycle>>();
//		LocalDate now = LocalDate.now();
//		Date startDate, endDate;
//		if (plusMinusMonth == 0) {
//			startDate = java.sql.Date.valueOf(now);
//			endDate = java.sql.Date.valueOf(now.plusMonths(1));
//		} else if (plusMinusMonth < 0) {
//			startDate = java.sql.Date.valueOf(now.minusMonths(plusMinusMonth * -1));
//			endDate = java.sql.Date.valueOf(now.minusMonths((plusMinusMonth * -1) + 1));
//		} else {
//			startDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth));
//			endDate = java.sql.Date.valueOf(now.plusMonths(plusMinusMonth + 1));
//		}
//
//		// GET FCs THAT HAVE A START OR END DATE WITHIN THE RANGE (TARGET MONTH)
//		List<FundingCycle> fcList = fcService
//				.findFundingCyclesByStartDateGreaterThanEqualAndStartDateLessThanOrEndDateGreaterThanEqualAndEndDateLessThan(
//						startDate, endDate, startDate, endDate);
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		for (FundingCycle fc : fcList) {
//			String startDateKey = formatter.format(fc.getStartDateLOI());
//			String endDateKey = formatter.format(fc.getEndDateLOI());
//			if (retval.get(startDateKey) == null) {
//				retval.put(startDateKey, new ArrayList<FundingCycle>());
//			}
//			if (retval.get(endDateKey) == null) {
//				retval.put(endDateKey, new ArrayList<FundingCycle>());
//			}
//			retval.get(endDateKey).add(fc);
//		}
		return null;
	}

}
