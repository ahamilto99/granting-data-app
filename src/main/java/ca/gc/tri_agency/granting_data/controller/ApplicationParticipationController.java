package ca.gc.tri_agency.granting_data.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.gc.tri_agency.granting_data.model.projection.ApplicationParticipationProjection;
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
//		model.addAttribute("appParticipations", appParticipationService.getAllowedRecords());
//		model.addAttribute("ediExtIds", appParticipationService.getExtIdsQualifiedForEdi());

		model.addAttribute("appParticipations", appParticipationService.findAppPartsForCurrentUserWithEdiAuth());

		return "browse/appParticipations";
	}

	@GetMapping("/browse/viewAppParticipationEdi")
	public String appParticipationEdi(@RequestParam("id") Long id, Model model) {
		model.addAttribute("appParticipation", appParticipationService.getAllowdRecord(id));
		return "browse/viewAppParticipationEdi";
	}

	@GetMapping("/admin/generateTestParticipations")
	public String generateTestParticipations() {
		return "admin/generateTestParticipations";
	}

	@PostMapping("/admin/generateTestParticipations")
	public String post_generateTestParticipations(RedirectAttributes redirectAttrs) {
		long numCreated = appParticipationService.generateTestAppParicipationsForAllSystemFundingOpportunities();
		redirectAttrs.addFlashAttribute("actionMsg", "Successfully created " + numCreated + " Test App Participations");
		return "redirect:/admin/home";
	}

	@GetMapping("/browse/viewAP")
	public String viewOneApplicationParticipation(@RequestParam("id") Long apId, Model model) throws AccessDeniedException {
		ApplicationParticipationProjection ap = appParticipationService.findAppPartById(apId);

		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy h:mm:ss a");
		String formattedCreatedDate = ap.getCreatedDate() != null
				? dtFormatter.format(LocalDateTime.ofInstant(ap.getCreatedDate(), ZoneId.of("Canada/Eastern")))
				: "";

		model.addAttribute("ap", ap);
		model.addAttribute("formattedCreatedDate", formattedCreatedDate);

		return "/browse/viewAppParticipation";
	}

}
