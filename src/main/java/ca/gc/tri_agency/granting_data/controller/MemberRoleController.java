package ca.gc.tri_agency.granting_data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ca.gc.tri_agency.granting_data.repo.RoleRepository;

@Controller
public class MemberRoleController {
	
	@Autowired
	private RoleRepository roleRepo;

	@GetMapping("/admin/createMR")
	public String showCreateMR() {
		
		roleRepo.findAll().forEach(role -> System.out.println(role.getLocalizedAttribute("name")));
		
		return "admin/createMemberRole";
	}
	
}
