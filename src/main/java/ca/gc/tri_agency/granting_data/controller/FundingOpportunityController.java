package ca.gc.tri_agency.granting_data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.gc.tri_agency.granting_data.service.FundingOpportunityService;
import ca.gc.tri_agency.granting_data.service.GrantingCapabilityService;
import ca.gc.tri_agency.granting_data.service.GrantingSystemService;
import ca.gc.tri_agency.granting_data.service.SystemFundingCycleService;

@Controller
public class FundingOpportunityController {
	
	@Autowired
	private FundingOpportunityService foService;
	
	@Autowired
	private GrantingSystemService gSystemService;
	
	@Autowired
	private GrantingCapabilityService gcService;
	
	@Autowired
	private SystemFundingCycleService sfcService;
	
	@GetMapping("/browse/goldenList")
	public String goldListDisplay(Model model) {
		model.addAttribute("goldenList", foService.findAllFundingOpportunities());
		// model.addAttribute("fcByFoMap",
		// fcService.findFundingCyclesByFundingOpportunityMap());
		model.addAttribute("applySystemByFoMap", gSystemService.findApplySystemsByFundingOpportunityMap());
		model.addAttribute("awardSystemsByFoMap", gSystemService.findAwardSystemsByFundingOpportunityMap());
		return "browse/goldenList";
	}
	
	@GetMapping(value = "/browse/viewFo")
	public String viewFundingOpportunity(@RequestParam("id") Long id, Model model) {
		model.addAttribute("fo", foService.findFundingOpportunityById(id));
		// model.addAttribute("systemFoCycles",
		// dataService.getSystemFundingCyclesByFoId(id));
		model.addAttribute("grantingCapabilities", gcService.findGrantingCapabilitiesByFoId(id));
		
		// TODO: REPLACE WITH REFACTORED CODE FROM ManageController
		model.addAttribute("linkedSystemFundingCycles", sfcService.findSystemFundingCyclesByLinkedFundingOpportunity(id));
		return "browse/viewFundingOpportunity";
	}
}
