package ca.gc.tri_agency.granting_data.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.gc.tri_agency.granting_data.form.FundingOpportunityFilterForm;
import ca.gc.tri_agency.granting_data.ldap.ADUser;
import ca.gc.tri_agency.granting_data.ldap.ADUserService;
import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.model.GrantingSystem;
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.AgencyService;
import ca.gc.tri_agency.granting_data.service.BusinessUnitService;
import ca.gc.tri_agency.granting_data.service.FundingOpportunityService;
import ca.gc.tri_agency.granting_data.service.GrantingCapabilityService;
import ca.gc.tri_agency.granting_data.service.GrantingSystemService;
import ca.gc.tri_agency.granting_data.service.SystemFundingCycleService;
import ca.gc.tri_agency.granting_data.service.SystemFundingOpportunityService;

@Controller
public class FundingOpportunityController {

	private FundingOpportunityService foService;

	private GrantingSystemService gSystemService;

	private GrantingCapabilityService gcService;

	private SystemFundingCycleService sfcService;

	private AgencyService agencyService;

	private SystemFundingOpportunityService sfoService;
	
	private BusinessUnitService buService;

	private MessageSource msgSource;

	private ADUserService adUserService;

	@Autowired
	public FundingOpportunityController(FundingOpportunityService foService, GrantingSystemService gSystemService,
			GrantingCapabilityService gcService, SystemFundingCycleService sfcService, AgencyService agencyService,
			SystemFundingOpportunityService sfoService, BusinessUnitService buService, MessageSource msgSource,
			ADUserService adUserService) {
		this.foService = foService;
		this.gSystemService = gSystemService;
		this.gcService = gcService;
		this.sfcService = sfcService;
		this.adUserService = adUserService;
		this.agencyService = agencyService;
		this.sfoService = sfoService;
		this.buService = buService;
		this.msgSource = msgSource;
	}

	@GetMapping("/browse/fundingOpportunities")
	public String viewGoldenList(@ModelAttribute("filter") FundingOpportunityFilterForm filter, Model model) {
		Map<Long, GrantingSystem> applyMap = gSystemService.findApplySystemsByFundingOpportunityMap();
		Map<Long, List<GrantingSystem>> awardMap = gSystemService.findAwardSystemsByFundingOpportunityMap();

		model.addAttribute("fundingOpportunities", foService.getFilteredFundingOpportunities(filter, applyMap, awardMap));
		model.addAttribute("allAgencies", agencyService.findAllAgencies());
		model.addAttribute("allDivisions", buService.findAllBusinessUnits());
		model.addAttribute("allGrantingSystems", gSystemService.findAllGrantingSystems());
		model.addAttribute("applySystemByFoMap", applyMap);
		model.addAttribute("awardSystemsByFoMap", awardMap);

		return "browse/fundingOpportunities";
	}

	@GetMapping("/browse/viewFo")
	public String viewFundingOpportunity(@RequestParam("id") Long id, Model model) {
		model.addAttribute("fo", foService.findFundingOpportunityById(id));
		model.addAttribute("grantingCapabilities", gcService.findGrantingCapabilitiesByFoId(id));
		model.addAttribute("linkedSystemFundingCycles", sfcService.findSystemFundingCyclesByLinkedFundingOpportunity(id));
		return "browse/viewFundingOpportunity";
	}

	@GetMapping("/manage/searchUser")
	public String searchUser(@RequestParam(value = "username", defaultValue = "") String username, Model model) {
		if (!username.trim().isEmpty()) {
			List<ADUser> matchingUsers = adUserService.searchADUsers(username.trim());
			model.addAttribute("matchingUsers", matchingUsers);
		}
		return "manage/searchUser";
	}

	@GetMapping("/manage/manageFo")
	public String manageFundingOpportunity(@RequestParam("id") Long id, Model model) {
		FundingOpportunity fo = foService.findFundingOpportunityById(id);
		model.addAttribute("fo", fo);
		model.addAttribute("linkedSystemFundingCycles", sfcService.findSystemFundingCyclesByLinkedFundingOpportunity(id));
		model.addAttribute("grantingCapabilities", gcService.findGrantingCapabilitiesByFoId(id));
		return "manage/manageFundingOpportunity";
	}

	@AdminOnly
	@GetMapping("/manage/editFo")
	public String editFundingOpportunityGet(@RequestParam("id") Long id, Model model) {
		FundingOpportunity fo = foService.findFundingOpportunityById(id);
		model = populateAgencyOptions(model, fo);
		model.addAttribute("programForm", fo);
		return "manage/editFundingOpportunity";
	}

	@AdminOnly
	@PostMapping("/manage/editFo")
	public String editFundingOpportunityPost(@Valid @ModelAttribute("programForm") FundingOpportunity fo,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model = populateAgencyOptions(model, fo);
			return "/manage/editFundingOpportunity";
		}
		foService.saveFundingOpportunity(fo);
		return "redirect:/browse/viewFo?id=" + fo.getId();
	}

	@AdminOnly
	@GetMapping("/manage/editProgramLead")
	public String editProgramLeadGet(@RequestParam("id") Long id,
			@RequestParam(value = "username", required = false) String username, Model model) {
		List<ADUser> matchingUsers = username == null ? adUserService.findAllADUsers() : adUserService.searchADUsers(username);
		model.addAttribute("matchingUsers", matchingUsers);
		model.addAttribute("originalId", id);
		return "manage/editProgramLead";
	}

	@AdminOnly
	@PostMapping(value = "/manage/editProgramLead", params = { "foId", "leadUserDn" })
	public String editProgramLeadPost(@RequestParam("foId") Long foId, @RequestParam("leadUserDn") String leadUserDn) {
		foService.setFundingOpportunityLeadContributor(foId, leadUserDn);
		return "redirect:/browse/viewFo?id=" + foId;
	}

	@GetMapping("/admin/createFo")
	public String createFundingOpportunityGet(Model model, @RequestParam("sfoId") Optional<Long> sfoId) {
		FundingOpportunity fo = new FundingOpportunity();
		if (sfoId.isPresent()) {
			SystemFundingOpportunity sfo = sfoService.findSystemFundingOpportunityById(sfoId.get());
			fo.setNameEn(sfo.getNameEn());
			fo.setNameFr(sfo.getNameFr());
		}
		List<Agency> allAgencies = agencyService.findAllAgencies();
		model.addAttribute("fo", fo);
		model.addAttribute("allAgencies", allAgencies);
		return "admin/createFo";
	}

	@PostMapping("/admin/createFo")
	public String createFundingOpportunityPost(@Valid @ModelAttribute("fo") FundingOpportunity command,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws Exception {
		if (bindingResult.hasErrors()) {
			model.addAttribute("allAgencies", agencyService.findAllAgencies());
			return "admin/createFo";
		}
		foService.saveFundingOpportunity(command);
		String createdFo = msgSource.getMessage("h.createdFo", null, LocaleContextHolder.getLocale());
		redirectAttributes.addFlashAttribute("actionMessage", createdFo + command.getLocalizedAttribute("name"));
		return "redirect:/admin/home";
	}

	private Model populateAgencyOptions(Model model, FundingOpportunity fo) {
		List<Agency> allAgencies = agencyService.findAllAgencies();
		List<Agency> otherAgencies = new ArrayList<Agency>();
		for (Agency a : allAgencies) {
			if (!fo.getParticipatingAgencies().contains(a)) {
				otherAgencies.add(a);
			}
		}
		model.addAttribute("otherAgencies", otherAgencies);
		model.addAttribute("allAgencies", allAgencies);
		return model;
	}
}
