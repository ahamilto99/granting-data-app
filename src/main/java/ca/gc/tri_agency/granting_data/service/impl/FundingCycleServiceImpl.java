package ca.gc.tri_agency.granting_data.service.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.FundingCycle;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.model.projection.FundingCycleProjection;
import ca.gc.tri_agency.granting_data.repo.FundingCycleRepository;
import ca.gc.tri_agency.granting_data.security.SecurityUtils;
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

	@Transactional(readOnly = true)
	@Override
	public List<FundingCycleProjection> findFundingCyclesByFiscalYearId(Long fyId) {
		return fcRepo.findByFiscalYearId(fyId);
	}

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

	@Transactional
	@Override
	public FundingCycle saveFundingCycle(FundingCycle fc) throws AccessDeniedException {
		Long foId = fc.getFundingOpportunity().getId();
		if (!mrService.checkIfCurrentUserCanCreateUpdateDeleteFC(foId)) {
			throw new AccessDeniedException(SecurityUtils.getCurrentUsername()
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

	@Transactional(readOnly = true)
	@Override
	public List<FundingCycleProjection> findFCsForBrowseViewFO(Long foId) {
		return fcRepo.findForBrowseViewFO(foId);
	}

	@Transactional
	@Override
	public void deleteFundingCycle(Long fcId) throws AccessDeniedException {
		FundingCycle fc;

		try {
			fc = findFundingCycleById(fcId);
		} catch (DataRetrievalFailureException drfe) {
			throw new AccessDeniedException(SecurityUtils.getCurrentUsername() + " is trying to delete a FundingCycle that"
					+ " does not exist (id=" + fcId + ")");
		}

		FundingOpportunity fo = fc.getFundingOpportunity();
		if (fo == null || !mrService.checkIfCurrentUserCanCreateUpdateDeleteFC(fo.getId())) {
			throw new AccessDeniedException(SecurityUtils.getCurrentUsername()
					+ " does not have permission to delete the FundingCycle id=" + fc.getId());
		}

		fcRepo.delete(fc);
	}

	@Transactional(readOnly = true)
	@Override
	public FundingCycleProjection findFundingCycleForConfirmDeleteFC(Long fcId) throws AccessDeniedException {
		final String FORBIDDEN_MSG = " is trying to access the deleteFC page for the FundingCycle id=";

		FundingCycleProjection fcProjection = fcRepo.findForDeleteFC(fcId).orElseThrow(() -> {
			if (SecurityUtils.isCurrentUserAdmin()) {
				return new DataRetrievalFailureException("FundingCycle id=" + fcId + " does not exist");
			}
			return new AccessDeniedException(SecurityUtils.getCurrentUsername() + FORBIDDEN_MSG + fcId + " which does not exist");
		});

		if (!mrService.checkIfCurrentUserCanCreateUpdateDeleteFC(fcProjection.getFundingOpportunityId())) {
			throw new AccessDeniedException(SecurityUtils.getCurrentUsername() + FORBIDDEN_MSG + fcId);
		}

		return fcProjection;
	}

}
