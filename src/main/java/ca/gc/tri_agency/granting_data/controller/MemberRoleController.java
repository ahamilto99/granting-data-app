package ca.gc.tri_agency.granting_data.controller;

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

import ca.gc.tri_agency.granting_data.ldap.ADUserService;
import ca.gc.tri_agency.granting_data.model.MemberRole;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.BusinessUnitService;
import ca.gc.tri_agency.granting_data.service.MemberRoleService;

@AdminOnly
@Controller
public class MemberRoleController {

	private MemberRoleService memberRoleService;

	private BusinessUnitService buService;

	private ADUserService adUserService;

	private MessageSource msgSrc;

	@Autowired
	public MemberRoleController(MemberRoleService memberRoleService, BusinessUnitService buService, ADUserService adUserService, MessageSource msgSrc) {
		this.memberRoleService = memberRoleService;
		this.buService = buService;
		this.adUserService = adUserService;
		this.msgSrc = msgSrc;
	}

	@GetMapping("/admin/createMR")
	public String createMemberRoleGet(@RequestParam("buId") Long buId,
			@RequestParam(value = "searchStr", defaultValue = "") String searchStr, Model model) {
		MemberRole memberRole = new MemberRole();
		memberRole.setBusinessUnit(buService.findBusinessUnitById(buId));
		model.addAttribute("memberRole", memberRole);
		if (!searchStr.trim().isEmpty()) {
			model.addAttribute("adUserList", adUserService.searchADUsers(searchStr.trim()));
		}
		return "admin/createMemberRole";
	}

	@PostMapping("/admin/createMR")
	public String createMemeberRolePost(@RequestParam("buId") Long buId, @RequestParam("searchStr") String searchStr,
			@Valid @ModelAttribute("memberRole") MemberRole mr, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("adUserList", adUserService.searchADUsers(searchStr));
			return "admin/createMemberRole";
		}
		mr = memberRoleService.saveMemberRole(mr);
		
		String actionMsg = msgSrc.getMessage("h.createdMR", null, LocaleContextHolder.getLocale());
		redirectAttributes.addFlashAttribute("actionMsg", actionMsg + mr.getUserLogin());
		
		return "redirect:/browse/viewBU?id=" + buId;
	}

	@GetMapping(value = "/browse/viewBU", params = "mrId")
	public String deleteMemberRolePost(@RequestParam("mrId") Long mrId, RedirectAttributes redirectAttributes) {
		MemberRole mr = memberRoleService.findMemberRoleById(mrId);
		Long buId = mr.getBusinessUnit().getId();
		String mrLogin = mr.getUserLogin();
		
		memberRoleService.deleteMemberRole(mrId);
		
		String actionMsg = msgSrc.getMessage("h.deletedMR", null, LocaleContextHolder.getLocale());
		redirectAttributes.addFlashAttribute("actionMsg", actionMsg + mrLogin);
		
		return "redirect:/browse/viewBU?id=" + buId;
	}

}
