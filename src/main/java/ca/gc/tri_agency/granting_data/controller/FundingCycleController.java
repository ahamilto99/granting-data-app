package ca.gc.tri_agency.granting_data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.gc.tri_agency.granting_data.model.util.CalendarGrid;
import ca.gc.tri_agency.granting_data.service.FundingCycleService;

@Controller
public class FundingCycleController {
	
	private FundingCycleService fcService;
	
	@Autowired
	public FundingCycleController(FundingCycleService fcService) {
		this.fcService = fcService;
	}
	
	@GetMapping(value = "/browse/viewFcFromFy")
	public String viewFundingCyclesFromFiscalYear(@RequestParam("id") long id, Model model) {
		model.addAttribute("fc", fcService.findFundingCyclesByFiscalYearId(id));
		return "browse/viewFcFromFy";
	}
	
	@GetMapping(value = "/browse/viewCalendar")
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

}

















