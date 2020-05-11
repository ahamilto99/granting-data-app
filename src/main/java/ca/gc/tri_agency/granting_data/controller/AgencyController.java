package ca.gc.tri_agency.granting_data.controller;

import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.BusinessUnit;
import ca.gc.tri_agency.granting_data.service.AgencyService;
import ca.gc.tri_agency.granting_data.service.BusinessUnitService;
import ca.gc.tri_agency.granting_data.service.FundingOpportunityService;

@Controller
public class AgencyController {

	private BusinessUnitService buService;

	private AgencyService agencyService;
	
	private FundingOpportunityService foService;
	
	@Autowired
	public AgencyController(BusinessUnitService buService, AgencyService agencyService, FundingOpportunityService foService) {
		this.buService = buService;
		this.agencyService = agencyService;
		this.foService = foService;
	}

	@GetMapping(value = "/browse/viewAgency")
	public String viewAgency(@RequestParam("id") Long id, Model model) {
		Agency agency = agencyService.findAgencyById(id);
		model.addAttribute("agency", agency);
		model.addAttribute("agencyFos", foService.findFundingOpportunitiesByLeadAgencyId(id));
		model.addAttribute("agencyBUs", buService.findAllBusinessUnitsByAgency(agency).stream()
				.sorted(Comparator.comparing((BusinessUnit bu) -> bu.getLocalizedAttribute("name"))).collect(Collectors.toList()));
		return "browse/viewAgency";
	}

}
