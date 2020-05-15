package ca.gc.tri_agency.granting_data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.gc.tri_agency.granting_data.ldap.ADUser;
import ca.gc.tri_agency.granting_data.ldap.ADUserService;
import ca.gc.tri_agency.granting_data.service.FundingOpportunityService;
import ca.gc.tri_agency.granting_data.service.GrantingCapabilityService;
import ca.gc.tri_agency.granting_data.service.GrantingSystemService;
import ca.gc.tri_agency.granting_data.service.SystemFundingCycleService;

@Controller
public class FundingOpportunityController {

	private FundingOpportunityService foService;

	private GrantingSystemService gSystemService;

	private GrantingCapabilityService gcService;

	private SystemFundingCycleService sfcService;

	private ADUserService adUserService;

	@Autowired
	public FundingOpportunityController(FundingOpportunityService foService, GrantingSystemService gSystemService,
			GrantingCapabilityService gcService, SystemFundingCycleService sfcService, ADUserService adUserService) {
		this.foService = foService;
		this.gSystemService = gSystemService;
		this.gcService = gcService;
		this.sfcService = sfcService;
		this.adUserService = adUserService;
	}

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

		model.addAttribute("linkedSystemFundingCycles", sfcService.findSystemFundingCyclesByLinkedFundingOpportunity(id));
		return "browse/viewFundingOpportunity";
	}

	@GetMapping(value = "/manage/searchUser")
	public String searchUserAction(@RequestParam(value = "username", required = false) String username, Model model) {
		if (username != null && !username.trim().isEmpty()) {
			List<ADUser> matchingUsers = adUserService.searchADUsers(username.trim());
			model.addAttribute("matchingUsers", matchingUsers);
		}
		return "manage/searchUser";
	}

}
