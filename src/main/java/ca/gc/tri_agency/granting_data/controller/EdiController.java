package ca.gc.tri_agency.granting_data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.gc.tri_agency.granting_data.service.BusinessUnitService;

@Controller
public class EdiController {
	
	private BusinessUnitService buService;
	
	@Autowired
	public EdiController(BusinessUnitService buService) {
		this.buService = buService;
	}

	@GetMapping("/browse/ediVisualization")
	public String viewEdiDataVisualization() {
		return "/browse/ediVisualization";
	}
	
	@GetMapping("/manage/viewBuEdiData")
	public String viewBuEdiData(@RequestParam("buId") Long buId, Model model)  throws AccessDeniedException {
		model.addAttribute("buName", buService.fetchBusinessUnitName(buId));
		model.addAttribute("ediMap", buService.findEdiAppPartDataForAuthorizedBUMember(buId));
		return "/manage/viewBuEdiData";
	}
}
