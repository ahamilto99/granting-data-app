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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.gc.tri_agency.granting_data.app.exception.UniqueColumnException;
import ca.gc.tri_agency.granting_data.model.FiscalYear;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.FiscalYearService;

@Controller
public class FiscalYearController {

	private FiscalYearService fyService;

	@Autowired
	private MessageSource msgSrc;

	@Autowired
	public FiscalYearController(FiscalYearService fyService) {
		this.fyService = fyService;
	}

	@GetMapping(value = "/browse/viewFYs")
	public String accessViewFYs(Model model) {
		model.addAttribute("fiscalYears", fyService.findAllFiscalYears());
		return "browse/viewFiscalYears";
	}

	@AdminOnly
	@GetMapping(value = "/manage/createFY")
	public String accessCreateFY(Model model) {
		model.addAttribute("fy", new FiscalYear());
		model.addAttribute("fiscalYears", fyService.findAllFiscalYears());
		return "manage/createFiscalYear";
	}

	@AdminOnly
	@PostMapping(value = "/manage/createFY")
	public String processCreateFY(@Valid @ModelAttribute("fy") FiscalYear fy, BindingResult bindingResult, Model model)
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
