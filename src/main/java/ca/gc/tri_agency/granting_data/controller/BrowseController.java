package ca.gc.tri_agency.granting_data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.gc.tri_agency.granting_data.service.DataAccessService;
import ca.gc.tri_agency.granting_data.service.GrantingCapabilityService;
import ca.gc.tri_agency.granting_data.service.GrantingSystemService;
import ca.gc.tri_agency.granting_data.service.SystemFundingCycleService;

@Controller
@RequestMapping("/browse")
public class BrowseController {

	// private static final Logger LOG = LogManager.getLogger();

	private DataAccessService dataService;

	private GrantingSystemService gsService;

	private GrantingCapabilityService gcService;

	@Autowired
	private SystemFundingCycleService sfcService;

	@Autowired
	public BrowseController(DataAccessService dataService, GrantingSystemService gsService, GrantingCapabilityService gcService) {
		this.dataService = dataService;
		this.gsService = gsService;
		this.gcService = gcService;
	}

	@GetMapping("/goldenList")
	public String goldListDisplay(Model model) {
		model.addAttribute("goldenList", dataService.getAllFundingOpportunities());
		// model.addAttribute("fcByFoMap",
		// fcService.findFundingCyclesByFundingOpportunityMap());
		model.addAttribute("applySystemByFoMap", gsService.findApplySystemsByFundingOpportunityMap());
		model.addAttribute("awardSystemsByFoMap", gsService.findAwardSystemsByFundingOpportunityMap());
		return "browse/goldenList";
	}

	@GetMapping(value = "/viewFo")
	public String viewFundingOpportunity(@RequestParam("id") long id, Model model) {
		model.addAttribute("fo", dataService.getFundingOpportunity(id));
		// model.addAttribute("systemFoCycles",
		// dataService.getSystemFundingCyclesByFoId(id));
		model.addAttribute("grantingCapabilities", gcService.findGrantingCapabilitiesByFoId(id));
		
		// TODO: REPLACE WITH REFACTORED CODE FROM ManageController
		model.addAttribute("linkedSystemFundingCycles", sfcService.findSFCsBySFOid(id));
		return "browse/viewFundingOpportunity";
	}

}
