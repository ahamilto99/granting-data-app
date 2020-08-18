package ca.gc.tri_agency.granting_data.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.FundingCycle;
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
		return fcRepo.findById(id).orElseThrow(() -> new DataRetrievalFailureException("FundingCycle id=" + id + " does not exist"));
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
	public List<FundingCycleProjection> findFundingCyclesByFiscalYearId(Long fyId) {
		List<FundingCycleProjection> fcProjections = fcRepo.findByFiscalYearId(fyId);

		if (fcProjections.isEmpty()) {
			throw new DataRetrievalFailureException("FiscalYear id=" + fyId + " does not exist");
		}

		return fcProjections;
	}

	@Transactional
	@Override
	public FundingCycle saveFundingCycle(FundingCycle fc) throws AccessDeniedException {
		Long foId = fc.getFundingOpportunity().getId();
		Long fcId = fc.getId();

		System.out.println("\n\n\n" + foId + "\n\n\n");
		System.out.println("\n\n\n" + fcId + "\n\n\n");

		if (fcId == null && !mrService.checkIfCurrentUserCanCreateFC(foId)) {
			throw new AccessDeniedException(SecurityUtils.getCurrentUsername()
					+ " does not have permission to create a FundingCycle for FundingOpportunty id=" + foId);
		} else if (fcId != null && !mrService.checkIfCurrentUserCanUpdateDeleteFC(fcId)) {
			throw new AccessDeniedException(
					SecurityUtils.getCurrentUsername() + " does not have permission to update the FundingCycle id=" + fcId);
		}

		return fcRepo.save(fc);
	}

	@Override
	public List<FundingCycleProjection> findFCsForBrowseViewFO(Long foId) {
		return fcRepo.findForBrowseViewFO(foId);
	}

	@Transactional
	@Override
	public void deleteFundingCycleId(Long fcId) throws AccessDeniedException {
		if (!mrService.checkIfCurrentUserCanUpdateDeleteFC(fcId)) {
			throw new AccessDeniedException(
					SecurityUtils.getCurrentUsername() + " does not have permission to delete the FundingCycle id=" + fcId);
		}

		fcRepo.deleteByIdentifier(fcId);
	}

	@Transactional(readOnly = true)
	@Override
	public FundingCycleProjection findFundingCycleForConfirmDeleteFC(Long fcId) throws AccessDeniedException {
		if (!mrService.checkIfCurrentUserCanUpdateDeleteFC(fcId)) {
			throw new AccessDeniedException(SecurityUtils.getCurrentUsername()
					+ " is trying to access the deleteFC page for the FundingCycle id=" + fcId);
		}

		FundingCycleProjection fcProjection = fcRepo.findForDeleteFC(fcId).orElseThrow(() -> {
			return new DataRetrievalFailureException("FundingCycle id=" + fcId + " does not exist");
		});

		return fcProjection;
	}

	@Override
	public List<FundingCycleProjection> findFundingCyclesForCalendar(long plusMinusMonth) {
		LocalDate calendarDay = LocalDate.now().plusMonths(plusMinusMonth);

		return fcRepo.findForCalendar(calendarDay.withDayOfMonth(1).minusDays(8), calendarDay.withDayOfMonth(28).plusDays(13));
	}

}
