package ca.gc.tri_agency.granting_data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.gc.tri_agency.granting_data.service.ApplicationParticipationService;

@Controller
public class ApplicationParticipationController {

	private ApplicationParticipationService appParticipationService;

	@Autowired
	public ApplicationParticipationController(ApplicationParticipationService appParticipationService) {
		this.appParticipationService = appParticipationService;
	}

	@GetMapping("/browse/appParticipations")
	public String appParticipations(Model model) {
		model.addAttribute("appParticipations", appParticipationService.getAllowedRecords());
		return "browse/appParticipations";
	}

	@GetMapping("/admin/generateTestParticipations")
	public String generateTestParticipations() {
		return "admin/generateTestParticipations";
	}

	@PostMapping(value = "/admin/generateTestParticipations")
	public String post_generateTestParticipations(RedirectAttributes redirectAttrs) {
		long numCreated = appParticipationService.generateTestAppParicipationsForAllSystemFundingOpportunities();
		redirectAttrs.addFlashAttribute("actionMessage", "Successfully created " + numCreated + " Test App Participations");
		return "redirect:/admin/home";
	}

}
