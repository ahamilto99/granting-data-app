package ca.gc.tri_agency.granting_data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.gc.tri_agency.granting_data.model.projection.AgencyProjection;
import ca.gc.tri_agency.granting_data.model.util.Utility;
import ca.gc.tri_agency.granting_data.service.AgencyService;

@Controller
public class AgencyController {

	private AgencyService agencyService;

	@Autowired
	public AgencyController(AgencyService agencyService) {
		this.agencyService = agencyService;
	}

	@GetMapping("/browse/viewAgency")
	public String viewAgency(@RequestParam("id") Long id, Model model) {
		List<AgencyProjection> agencyProjections = agencyService.findResultsForBrowseViewAgency(id);

		model.addAttribute("agency", agencyProjections.get(0));
		model.addAttribute("agencyFos", agencyProjections);
		model.addAttribute("agencyBUs",
				agencyProjections.stream().filter(Utility.getDistinctProjectionsByField(AgencyProjection::getBuId)).iterator());

		return "browse/viewAgency";
	}

}
