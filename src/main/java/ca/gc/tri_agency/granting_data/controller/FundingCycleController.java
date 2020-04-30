package ca.gc.tri_agency.granting_data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		System.out.println("--------------------------------------------------------------");
		fcService.findMonthlyFundingCyclesMapByDate(24).forEach((k, v) -> {
			System.out.print("Start Date=" + k + ": ");
			v.forEach(fc -> System.out.print("Funding Cycle Id=" + fc.getId()+"  "));
			System.out.println();
		});
		System.out.println("--------------------------------------------------------------");
		
		
		return "browse/viewFcFromFy";
	}

}
