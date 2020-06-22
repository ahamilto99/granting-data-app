package ca.gc.tri_agency.granting_data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EdiController {

	@GetMapping("/browse/ediVisualization")
	public String viewEdiDataVisualization() {
		return "/browse/ediVisualization";
	}
}
