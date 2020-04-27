package ca.gc.tri_agency.granting_data.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.gc.tri_agency.granting_data.ldap.ADUser;
import ca.gc.tri_agency.granting_data.ldap.ADUserService;
import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.FundingCycle;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.AgencyService;
import ca.gc.tri_agency.granting_data.service.DataAccessService;
import ca.gc.tri_agency.granting_data.service.FiscalYearService;
import ca.gc.tri_agency.granting_data.service.GrantingCapabilityService;
import ca.gc.tri_agency.granting_data.service.GrantingSystemService;
import ca.gc.tri_agency.granting_data.service.RestrictedDataService;

@Controller
@RequestMapping(value = "/manage", method = RequestMethod.GET)
public class ManageFundingOpportunityController {

	@Autowired
	ADUserService adUserService;

	@Autowired
	RestrictedDataService restrictedDataService;

	@Autowired
	DataAccessService dataService;

	@Autowired
	private AgencyService agencyService;

	@Autowired
	GrantingSystemService gSystemService;

	@Autowired
	private GrantingCapabilityService gcService;
	
	@Autowired
	private FiscalYearService fyService;

	@GetMapping(value = "/searchUser")
	public String searchUserForm() {
		return "manage/searchUser";
	}

	@GetMapping(value = "/manageFo")
	public String viewFundingOpportunity(@RequestParam("id") long id, Model model) {
		model.addAttribute("fo", dataService.getFundingOpportunity(id));
		// model.addAttribute("fundingCycles", dataService.getFundingCyclesByFoId(id));
		model.addAttribute("linkedSystemFundingCycles", dataService.getSystemFundingCyclesByFoId(id));
		model.addAttribute("grantingCapabilities", gcService.findGrantingCapabilitiesByFoId(id));
		return "manage/manageFundingOpportunity";
	}

	@GetMapping(value = "/searchUser", params = "username")
	public String searchUserAction(@RequestParam("username") String username, Model model) {
		String matchingUsers = adUserService.findDnByADUserLogin(username);
		model.addAttribute("matchingUsers", matchingUsers);
		return "manage/searchUser";
	}

	@AdminOnly
	@GetMapping(value = "/editFo", params = "id")
	public String editFo(@RequestParam("id") long id, Model model) {
		FundingOpportunity fo = dataService.getFundingOpportunity(id);
		model.addAttribute("programForm", fo);

		List<Agency> allAgencies = agencyService.findAllAgencies();
		List<Agency> otherAgencies = new ArrayList<Agency>();
		for (Agency a : allAgencies) {
			if (fo.getParticipatingAgencies().contains(a) == false) {
				otherAgencies.add(a);
			}
		}
		model.addAttribute("otherAgencies", otherAgencies);
		model.addAttribute("allAgencies", allAgencies);
		return "manage/editFundingOpportunity";
	}

	@AdminOnly
	@PostMapping(value = "/editFo")
	public String editFoPost(@Valid @ModelAttribute("programForm") FundingOpportunity command, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// model.addAttribute("allAgencies", dataService.getAllAgencies());
			for (ObjectError br : bindingResult.getAllErrors()) {
				System.out.println(br.toString());
			}
			return "redirect:/browse/goldenList";
		}
		restrictedDataService.saveFundingOpportunity(command);
		return "redirect:/browse/viewFo?id=" + command.getId();
	}

	@AdminOnly
	@GetMapping(value = "/editProgramLead", params = "id")
	public String editProgramLead(@RequestParam("id") long id, Model model) {
		model.addAttribute("originalId", id);
		List<ADUser> matchingUsers = adUserService.findAllADUsers();
		model.addAttribute("matchingUsers", matchingUsers);
		return "manage/editProgramLead";
	}

	@AdminOnly
	@GetMapping(value = "/editProgramLead", params = { "id", "username" })
	public String editProgramLeadSearchUser(@RequestParam("id") long id, @RequestParam("username") String username, Model model) {
		List<ADUser> matchingUsers = adUserService.searchADUsers(username);
		model.addAttribute("matchingUsers", matchingUsers);
		model.addAttribute("originalId", id);
		return "manage/editProgramLead";
	}

	@AdminOnly
	@PostMapping(value = "/editProgramLead")
	public String editProgramLeadPost(@RequestParam long foId, @RequestParam String leadUserDn) {
		// get the FO based on the ID
		// get the AD person based on the leadUserDn
		// in the FO, lead name and lead DN, save the FO
		// service.setFoLeadContributor(long foId, leadUserDn)
		restrictedDataService.setFoLeadContributor(foId, leadUserDn);
		return "redirect:/browse/viewFo?id=" + foId;
	}

	@GetMapping(value = "/createFundingCycle", params = "id")
	public String createFundingCycle(@RequestParam("id") long id, Model model) {
		model.addAttribute("foId", id);
		model.addAttribute("fundingCycle", new FundingCycle());
		model.addAttribute("fy", fyService.findAllFiscalYears());
		model.addAttribute("fo", dataService.getFundingOpportunity(id));
		return "manage/createFundingCycle";
	}

	@PostMapping(value = "/createFundingCycle")
	public String createFundingCyclePost(@Valid @ModelAttribute("fundingCycle") FundingCycle command,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			for (ObjectError br : bindingResult.getAllErrors()) {
				System.out.println(br.toString());
			}
			return "manage/createFundingCycle";
		}
		restrictedDataService.createOrUpdateFundingCycle(command);

		return "redirect:/browse/viewFo?id=" + command.getFundingOpportunity().getId();
	}

}
