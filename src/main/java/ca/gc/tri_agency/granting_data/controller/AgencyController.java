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
import ca.gc.tri_agency.granting_data.service.DataAccessService;

@Controller
public class AgencyController {

	@Autowired
	private DataAccessService dataService;	// TODO: refactor FundingOpportunity
	
	private BusinessUnitService buService;

	private AgencyService agencyService;
	
	public AgencyController(BusinessUnitService buService, AgencyService agencyService) {
		this.buService = buService;
		this.agencyService = agencyService;
	}

	@GetMapping(value = "/browse/viewAgency")
	public String viewAgency(@RequestParam("id") long id, Model model) {
		Agency agency = agencyService.findAgencyById(id);
		model.addAttribute("agency", agency);
		model.addAttribute("agencyFos", dataService.getAgencyFundingOpportunities(id));
		model.addAttribute("agencyBUs", buService.findAllBusinessUnitsByAgency(agency).stream()
				.sorted(Comparator.comparing(BusinessUnit::getName)).collect(Collectors.toList()));
		return "browse/viewAgency";
	}

}
