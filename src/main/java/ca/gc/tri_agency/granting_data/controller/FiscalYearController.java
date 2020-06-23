package ca.gc.tri_agency.granting_data.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.gc.tri_agency.granting_data.app.exception.UniqueColumnException;
import ca.gc.tri_agency.granting_data.model.FiscalYear;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.FiscalYearService;

@Controller
public class FiscalYearController {

	private FiscalYearService fyService;

	private MessageSource msgSrc;

	@Autowired
	public FiscalYearController(FiscalYearService fyService, MessageSource msgSrc) {
		this.fyService = fyService;
		this.msgSrc = msgSrc;
	}

	@GetMapping("/browse/viewFYs")
	public String viewFiscalYears(Model model) {
		model.addAttribute("fiscalYearStats", fyService.findNumAppsExpectedForEachFiscalYear());
		return "browse/viewFiscalYears";
	}

	@AdminOnly
	@GetMapping("/manage/createFY")
	public String createFiscalYearGet(Model model) {
		model.addAttribute("fy", new FiscalYear());
		model.addAttribute("fiscalYears", fyService.findAllFiscalYearsOrderByYearAsc());
		return "manage/createFiscalYear";
	}

	@AdminOnly
	@PostMapping("/manage/createFY")
	public String createFiscalYearPost(@Valid @ModelAttribute("fy") FiscalYear fy, BindingResult bindingResult, Model model)
			throws Exception {
		if (bindingResult.hasErrors()) {
			return "manage/createFiscalYear";
		}
		try {
			fyService.saveFiscalYear(fy);
		} catch (UniqueColumnException uce) {
			bindingResult.addError(new FieldError("fy", "year",
					msgSrc.getMessage("err.yrExists", null, LocaleContextHolder.getLocale())));
			model.addAttribute("duplicateFiscalYr", fy);
			return "manage/createFiscalYear";
		}
		return "redirect:/browse/viewFYs";
	}

}
