package ca.gc.tri_agency.granting_data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.gc.tri_agency.granting_data.repoLdap.ADUserRepository;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.BusinessUnitService;
import ca.gc.tri_agency.granting_data.service.MemberRoleService;
import ca.gc.tri_agency.granting_data.service.RoleService;

@Controller
public class MemberRoleController {

	private MemberRoleService memberRoleService;

	private RoleService roleService;

	private BusinessUnitService buService;

	@Autowired
	private ADUserRepository aduRepo;

	public MemberRoleController(MemberRoleService memberRoleService, RoleService roleService, BusinessUnitService buService) {
		this.memberRoleService = memberRoleService;
		this.roleService = roleService;
		this.buService = buService;
	}

	@AdminOnly
	@GetMapping("/admin/createMR")
	public String showCreateMR(@RequestParam("buId") Long buId,
			@RequestParam(value = "searchStr", required = false) String searchStr, Model model) {
		model.addAttribute("bu", buService.findBusinessUnitById(buId));
		if (null != searchStr && !searchStr.trim().isEmpty()) {
			model.addAttribute("adUserList", aduRepo.searchADUsersForMemberRoleCreation(searchStr.trim()));
		}
		return "admin/createMemberRole";
	}

	@AdminOnly
	@PostMapping("/admin/createMR")
	public String processCreateMRUserSearch(@RequestParam("buId") Long buId, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("bu", buService.findBusinessUnitById(buId));
		return "admin/createMemberRole";
	}

}
