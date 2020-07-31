package ca.gc.tri_agency.granting_data.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.gc.tri_agency.granting_data.model.FundingCycle;
import ca.gc.tri_agency.granting_data.model.util.CalendarGrid;
import ca.gc.tri_agency.granting_data.security.SecurityUtils;
import ca.gc.tri_agency.granting_data.service.FiscalYearService;
import ca.gc.tri_agency.granting_data.service.FundingCycleService;
import ca.gc.tri_agency.granting_data.service.MemberRoleService;

@Controller
public class FundingCycleController {

	private FundingCycleService fcService;

	private FiscalYearService fyService;

	private MemberRoleService mrService;

	private MessageSource msgSource;

	@Autowired
	public FundingCycleController(FundingCycleService fcService, FiscalYearService fyService, MemberRoleService mrService,
			MessageSource msgSource) {
		this.fcService = fcService;
		this.fyService = fyService;
		this.mrService = mrService;
		this.msgSource = msgSource;
	}

	@GetMapping("/browse/viewFcFromFy")
	public String viewFundingCyclesFromFiscalYear(@RequestParam("fyId") long fyId, Model model) {
		model.addAttribute("fc", fcService.findFundingCyclesByFiscalYearId(fyId));
		return "browse/viewFcFromFy";
	}

	@GetMapping("/browse/viewCalendar")
	public String viewCalendar(@RequestParam(name = "plusMinusMonth", defaultValue = "0") Integer plusMinusMonth, Model model) {
		model.addAttribute("plusMonth", plusMinusMonth + 1);
		model.addAttribute("minusMonth", plusMinusMonth - 1);
		model.addAttribute("calGrid", new CalendarGrid(plusMinusMonth));

		model.addAttribute("startDates", fcService.findMonthlyFundingCyclesByStartDate(plusMinusMonth));
		model.addAttribute("endDates", fcService.findMonthlyFundingCyclesByEndDate(plusMinusMonth));
		model.addAttribute("startLOIDates", fcService.findMonthlyFundingCyclesByStartDateLOI(plusMinusMonth));
		model.addAttribute("endLOIDates", fcService.findMonthlyFundingCyclesByEndDateLOI(plusMinusMonth));
		model.addAttribute("startNOIDates", fcService.findMonthlyFundingCyclesByStartDateNOI(plusMinusMonth));
		model.addAttribute("endNOIDates", fcService.findMonthlyFundingCyclesByEndDateNOI(plusMinusMonth));

		return "browse/viewCalendar";
	}

	@GetMapping("/manage/createFC")
	public String createFundingCycleGet(@RequestParam("foId") Long foId, Model model) throws AccessDeniedException {
		if (!mrService.checkIfCurrentUserCanCreateFC(foId)) {
			throw new AccessDeniedException(
					SecurityUtils.getCurrentUsername() + " cannot create a FundingCycle for FundingOpportunity id=" + foId);
		}

		model.addAttribute("foId", foId);
		model.addAttribute("fundingCycle", new FundingCycle());
		model.addAttribute("fy", fyService.findAllFiscalYearsOrderByYearAsc());

		return "manage/createFundingCycle";
	}

	@PostMapping("/manage/createFC")
	public String createFundingCyclePost(@RequestParam("foId") Long foId, @Valid @ModelAttribute("fundingCycle") FundingCycle fc,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) throws AccessDeniedException {
		if (!mrService.checkIfCurrentUserCanCreateFC(foId)) {
			throw new AccessDeniedException(
					SecurityUtils.getCurrentUsername() + " cannot create a FundingCycle for FundingOpportunity id=" + foId);
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("fy", fyService.findAllFiscalYearsOrderByYearAsc());
			return "manage/createFundingCycle";
		}

		fcService.saveFundingCycle(fc);

		String actionMsg = msgSource.getMessage("h.createdFC", null, LocaleContextHolder.getLocale());
		redirectAttributes.addFlashAttribute("actionMsg", actionMsg);
		
		return "redirect:/browse/viewFo?id=" + foId;
	}

}
