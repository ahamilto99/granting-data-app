package ca.gc.tri_agency.granting_data.service.impl;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.model.FundingCycle;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.repo.FundingCycleRepository;
import ca.gc.tri_agency.granting_data.service.FundingCycleService;
import ca.gc.tri_agency.granting_data.service.MemberRoleService;

@Service
public class FundingCycleServiceImpl implements FundingCycleService {

	private FundingCycleRepository fcRepo;

	private MemberRoleService mrService;

	@Autowired
	public FundingCycleServiceImpl(FundingCycleRepository fcRepo, MemberRoleService mrService) {
		this.fcRepo = fcRepo;
		this.mrService = mrService;
	}

	@Override
	public FundingCycle findFundingCycleById(Long id) {
		return fcRepo.findById(id).orElseThrow(() -> new DataRetrievalFailureException("That Funding Cycle does not exist"));
	}

	@Override
	public List<FundingCycle> findAllFundingCycles() {
		return fcRepo.findAll();
	}

	@Override
	public List<FundingCycle> findFundingCyclesByFundingOpportunityId(Long foId) {
		return fcRepo.findByFundingOpportunityId(foId);
	}

	@Override
	public List<FundingCycle> findFundingCyclesByFiscalYearId(Long fyId) {
		List<FundingCycle> fcList = fcRepo.findByFiscalYearId(fyId);
		fcList.sort(Comparator.comparing((FundingCycle fc) -> {
			FundingOpportunity fo = fc.getFundingOpportunity();
			if (null != fo.getLocalizedAttribute("name")) {
				return fc.getFundingOpportunity().getLocalizedAttribute("name");
			}
			return fo.getNameEn();
		}));
		return fcList;
	}

	// This method is not used
//	@Override
//	public Map<String, List<FundingCycle>> findMonthlyFundingCyclesMapByDate(long plusMinusMonth) {
//		Map<String, List<FundingCycle>> fcsByStartDateMap = new TreeMap<>();
//		LocalDate startDate, endDate;
//		startDate = endDate = LocalDate.now();
//		if (plusMinusMonth == 0) {
//			endDate = endDate.plusMonths(1);
//		} else if (plusMinusMonth < 0) {
//			startDate = startDate.minusMonths(plusMinusMonth * -1);
//			endDate = endDate.minusMonths(plusMinusMonth * -1 + 1);
//		} else {
//			startDate = startDate.plusMonths(plusMinusMonth);
//			endDate = endDate.plusMonths(plusMinusMonth + 1);
//		}
//
//		List<FundingCycle> fcList = fcRepo
//				.findByStartDateGreaterThanEqualAndStartDateLessThanOrEndDateGreaterThanEqualAndEndDateLessThan(
//						startDate, endDate, startDate, endDate);
//		fcListLoop: for (FundingCycle fc : fcList) {
//			String startDateStr = fc.getStartDate().toString();
//			if (fcsByStartDateMap.containsKey(startDateStr)) {
//				fcsByStartDateMap.get(startDateStr).add(fc);
//				continue fcListLoop;
//			}
//			List<FundingCycle> newFcList = new ArrayList<>();
//			newFcList.add(fc);
//			fcsByStartDateMap.put(startDateStr, newFcList);
//		}
//
//		return fcsByStartDateMap;
//	}

	private LocalDate[] getDayRange(int plusMinusMonth) {
		LocalDate[] dates = new LocalDate[2];
		dates[0] = dates[1] = LocalDate.now();
		dates[0] = dates[0].withDayOfMonth(15);
		dates[1] = dates[1].withDayOfMonth(15);

		if (plusMinusMonth == 0) {
			dates[0] = dates[0].minusMonths(1);
			dates[1] = dates[1].plusMonths(1);
		} else if (plusMinusMonth < 0) {
			dates[0] = dates[0].minusMonths(plusMinusMonth * -1 - 1);
			dates[1] = dates[1].minusMonths(plusMinusMonth * -1 + 1);
		} else {
			dates[0] = dates[0].plusMonths(plusMinusMonth - 1);
			dates[1] = dates[1].plusMonths(plusMinusMonth + 1);
		}

		return dates;
	}

	@Override
	public List<FundingCycle> findMonthlyFundingCyclesByStartDate(int plusMinusMonth) {
		LocalDate[] dates = getDayRange(plusMinusMonth);
		return fcRepo.findByStartDateBetween(dates[0], dates[1]);
	}

	@Override
	public List<FundingCycle> findMonthlyFundingCyclesByEndDate(int plusMinusMonth) {
		LocalDate[] dates = getDayRange(plusMinusMonth);
		return fcRepo.findByEndDateBetween(dates[0], dates[1]);
	}

	@Override
	public List<FundingCycle> findMonthlyFundingCyclesByStartDateLOI(int plusMinusMonth) {
		LocalDate[] dates = getDayRange(plusMinusMonth);
		return fcRepo.findByStartDateLOIBetween(dates[0], dates[1]);
	}

	@Override
	public List<FundingCycle> findMonthlyFundingCyclesByEndDateLOI(int plusMinusMonth) {
		LocalDate[] dates = getDayRange(plusMinusMonth);
		return fcRepo.findByEndDateLOIBetween(dates[0], dates[1]);
	}

	@Override
	public List<FundingCycle> findMonthlyFundingCyclesByStartDateNOI(int plusMinusMonth) {
		LocalDate[] dates = getDayRange(plusMinusMonth);
		return fcRepo.findByStartDateNOIBetween(dates[0], dates[1]);
	}

	@Override
	public List<FundingCycle> findMonthlyFundingCyclesByEndDateNOI(int plusMinusMonth) {
		LocalDate[] dates = getDayRange(plusMinusMonth);
		return fcRepo.findByEndDateNOIBetween(dates[0], dates[1]);
	}

	@Override
	public FundingCycle saveFundingCycle(FundingCycle fc) throws AccessDeniedException {
		String currentUserLogin = SecurityContextHolder.getContext().getAuthentication().getName();
		Long foId = fc.getFundingOpportunity().getId();
		if (!mrService.checkIfCurrentUserCanCreateFCs(currentUserLogin, foId)) {
			throw new AccessDeniedException(currentUserLogin
					+ " does not have permission to create a FundingCycle for FundingOpportunty id=" + foId);
		}
		return fcRepo.save(fc);
	}

	@Override
	public Map<Long, FundingCycle> findFundingCyclesByFundingOpportunityMap() {
		Map<Long, FundingCycle> retval = new HashMap<Long, FundingCycle>();
		List<FundingCycle> fundingCycles = findAllFundingCycles();
		for (FundingCycle fc : fundingCycles) {
			retval.put(fc.getFundingOpportunity().getId(), fc);
		}
		return retval;
	}
}
