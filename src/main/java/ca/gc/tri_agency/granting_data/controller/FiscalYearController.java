package ca.gc.tri_agency.granting_data.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.gc.tri_agency.granting_data.model.FiscalYear;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.FiscalYearService;

@Controller
public class FiscalYearController {

	private FiscalYearService fyService;

	@Autowired
	public FiscalYearController(FiscalYearService fyService) {
		this.fyService = fyService;
	}
	
	@GetMapping(value = "/browse/viewFiscalYear")
	public String viewFundingCycles(Model model) {
		model.addAttribute("fiscalYears", fyService.findAllFiscalYears());
		model.addAttribute("fy", new FiscalYear());	// TODO: figure out why this is here
		return "browse/viewFiscalYear";
	}

	@GetMapping(value = "/manage/addFiscalYears", params = "id")
	public String addFiscalYears(Model model) {
		model.addAttribute("fiscalYears", fyService.findAllFiscalYears());
		model.addAttribute("fy", new FiscalYear());
		return "manage/addFiscalYears";
	}

	@AdminOnly
	@PostMapping(value = "/manage/addFiscalYears")
	public String addFiscalYearsPost(@Valid @ModelAttribute("fy") FiscalYear command, BindingResult bindingResult, Model model)
			throws Exception {
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getFieldError().toString());

		}

		try {
			fyService.saveFiscalYear(command);	// TODO: Refactor
		}

		catch (Exception e) {
			model.addAttribute("error", "Your input is not valid!"
					+ " Please make sure to input a year between 1999 and 2050 that was not created before");
			return "manage/addFiscalYears";

		}

		return "redirect:/browse/viewFiscalYear";
	}

}
