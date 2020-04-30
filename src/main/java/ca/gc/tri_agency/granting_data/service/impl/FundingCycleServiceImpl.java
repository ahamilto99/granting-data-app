package ca.gc.tri_agency.granting_data.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.model.FundingCycle;
import ca.gc.tri_agency.granting_data.repo.FundingCycleRepository;
import ca.gc.tri_agency.granting_data.service.FundingCycleService;

@Service
public class FundingCycleServiceImpl implements FundingCycleService {

	private FundingCycleRepository fcRepo;

	@Autowired
	public FundingCycleServiceImpl(FundingCycleRepository fcRepo) {
		this.fcRepo = fcRepo;
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
		List<FundingCycle> fc = findAllFundingCycles();
		List<FundingCycle> fcNew = new ArrayList<FundingCycle>();
		for (FundingCycle e : fc) {
			if (e.getFiscalYear().getId() == fyId) {
				fcNew.add(e);
			}
		}
		return fcNew;
	}

	@Override
	public Map<String, List<FundingCycle>> findMonthlyFundingCyclesMapByDate(long plusMinusMonth) {
		Map<String, List<FundingCycle>> fcsByStartDateMap = new TreeMap<>();
		LocalDate startDate, endDate;
		startDate = endDate = LocalDate.now();
		if (plusMinusMonth == 0) {
			endDate = endDate.plusMonths(1);
		} else if (plusMinusMonth < 0) {
			startDate = startDate.minusMonths(plusMinusMonth * -1);
			endDate = endDate.minusMonths(plusMinusMonth * -1 + 1);
		} else {
			startDate = startDate.plusMonths(plusMinusMonth);
			endDate = endDate.plusMonths(plusMinusMonth + 1);
		}

		List<FundingCycle> fcList = fcRepo
				.findByStartDateGreaterThanEqualAndStartDateLessThanOrEndDateGreaterThanEqualAndEndDateLessThan(
						startDate, endDate, startDate, endDate);
		fcListLoop: for (FundingCycle fc : fcList) {
			String startDateStr = fc.getStartDate().toString();
			if (fcsByStartDateMap.containsKey(startDateStr)) {
				fcsByStartDateMap.get(startDateStr).add(fc);
				continue fcListLoop;
			}
			List<FundingCycle> newFcList = new ArrayList<>();
			newFcList.add(fc);
			fcsByStartDateMap.put(startDateStr, newFcList);
		}

		return fcsByStartDateMap;
	}

}
