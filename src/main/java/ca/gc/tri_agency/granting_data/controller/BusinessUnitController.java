package ca.gc.tri_agency.granting_data.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.gc.tri_agency.granting_data.model.BusinessUnit;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.AgencyService;
import ca.gc.tri_agency.granting_data.service.BusinessUnitService;
import ca.gc.tri_agency.granting_data.service.MemberRoleService;

@Controller
public class BusinessUnitController {

	private BusinessUnitService buService;

	private AgencyService agencyService;

	private MemberRoleService mrService;

	private MessageSource msgSource;

	@Autowired
	public BusinessUnitController(BusinessUnitService buService, AgencyService agencyService, MemberRoleService mrService,
			MessageSource msgSource) {
		this.buService = buService;
		this.agencyService = agencyService;
		this.mrService = mrService;
		this.msgSource = msgSource;

	}

	@GetMapping("/browse/viewBU")
	public String viewBusinessUnit(@RequestParam("id") Long id, Model model) {
		model.addAttribute("bu", buService.findBusinessUnitById(id));
		model.addAttribute("mrList", mrService.findMemberRolesByBusinessUnitId(id));

//		Can't use SecurityUtils' hasRole(...) b/c tests don't mock an LDAP user, i.e. tests fail when
//		using that method b/c we can't cast a User object to a LdapUserDetails object.
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities()
				.contains(new SimpleGrantedAuthority("ROLE_MDM ADMIN"))) {
			model.addAttribute("revisionList", buService.findBusinessUnitRevisionsById(id));
		}
		
		return "browse/viewBU";
	}

	@AdminOnly
	@GetMapping("/admin/createBU")
	public String createBusinessUnitGet(@RequestParam("agencyId") Long agencyId, Model model) {
		BusinessUnit bu = new BusinessUnit();
		bu.setAgency(agencyService.findAgencyById(agencyId));
		model.addAttribute("bu", bu);
		return "admin/createBU";
	}

	@AdminOnly
	@PostMapping("/admin/createBU")
	public String createBusinessUnitPost(@Valid @ModelAttribute("bu") BusinessUnit bu, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "admin/createBU";
		}
		buService.saveBusinessUnit(bu);
		String actionMsg = msgSource.getMessage("h.createdBu", null, LocaleContextHolder.getLocale());
		redirectAttributes.addFlashAttribute("actionMsg", actionMsg + bu.getLocalizedAttribute("name"));
		return "redirect:/browse/viewAgency?id=" + bu.getAgency().getId();
	}

	@AdminOnly
	@GetMapping("/admin/editBU")
	public String editBusinessUnitGet(@RequestParam("id") Long id, Model model) {
		model.addAttribute("bu", buService.findBusinessUnitById(id));
		return "admin/editBU";
	}

	@AdminOnly
	@PostMapping("/admin/editBU")
	public String editBusinessUnitPost(@Valid @ModelAttribute("bu") BusinessUnit bu, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "admin/editBU";
		}
		buService.saveBusinessUnit(bu);

		return "redirect:/browse/viewBU?id=" + bu.getId();
	}

}
