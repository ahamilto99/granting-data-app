package ca.gc.tri_agency.granting_data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.AwardService;

@Controller
public class AwardController {

	private AwardService awardService;

	@Autowired
	public AwardController(AwardService awardService) {
		this.awardService = awardService;
	}

	@AdminOnly
	@GetMapping("/admin/generateTestAwards")
	public String generateTestAwardsGet() {
		return "/admin/generateTestAwards";
	}

	@AdminOnly
	@PostMapping("/admin/generateTestAwards")
	public String generateTestAwardsPost(RedirectAttributes redirectAttributes) {
		long numAwards = 2_000_000_000_000L; // TODO: GENERATE TEST AWARDS
		redirectAttributes.addFlashAttribute("actionMessage",
				"Successfully created Awards for " + numAwards + " of the Test App Participations");
		return "redirect:/admin/home";
	}

	@GetMapping("/browse/awards")
	public String browseAwardsGet(Model model) {
		// TODO: ADD GENERATED TEST AWARDS
		return "/browse/awards";
	}
	
}

































