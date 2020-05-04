package ca.gc.tri_agency.granting_data.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.gc.tri_agency.granting_data.form.FundingOpportunityFilterForm;
import ca.gc.tri_agency.granting_data.model.FiscalYear;
import ca.gc.tri_agency.granting_data.model.GrantingSystem;
import ca.gc.tri_agency.granting_data.model.util.CalendarGrid;
import ca.gc.tri_agency.granting_data.service.AgencyService;
import ca.gc.tri_agency.granting_data.service.ApplicationParticipationService;
import ca.gc.tri_agency.granting_data.service.BusinessUnitService;
import ca.gc.tri_agency.granting_data.service.DataAccessService;
import ca.gc.tri_agency.granting_data.service.GrantingCapabilityService;
import ca.gc.tri_agency.granting_data.service.GrantingSystemService;

@Controller
@RequestMapping("/browse")
public class BrowseController {

	// private static final Logger LOG = LogManager.getLogger();

	private DataAccessService dataService;

	private GrantingSystemService gsService;

	private GrantingCapabilityService gcService;

	private BusinessUnitService buService;

	private AgencyService agencyService;

	private ApplicationParticipationService appParticipationService;

	public BrowseController(DataAccessService dataService, GrantingSystemService gsService, GrantingCapabilityService gcService,
			AgencyService agencyService, BusinessUnitService buService,
			ApplicationParticipationService appParticipationService) {
		this.dataService = dataService;
		this.gsService = gsService;
		this.gcService = gcService;
		this.agencyService = agencyService;
		this.buService = buService;
		this.appParticipationService = appParticipationService;
	}

	@GetMapping("/fundingOpportunities")
	public String goldListDisplay(@ModelAttribute("filter") FundingOpportunityFilterForm filter, Model model) {
		Map<Long, GrantingSystem> applyMap = gsService.findApplySystemsByFundingOpportunityMap();
		Map<Long, List<GrantingSystem>> awardMap = gsService.findAwardSystemsByFundingOpportunityMap();

		model.addAttribute("fundingOpportunities", dataService.getFilteredFundingOpportunities(filter, applyMap, awardMap));
		model.addAttribute("allAgencies", agencyService.findAllAgencies());
		model.addAttribute("allDivisions", buService.findAllBusinessUnits());
		model.addAttribute("allGrantingSystems", gsService.findAllGrantingSystems());
		model.addAttribute("applySystemByFoMap", applyMap);
		model.addAttribute("awardSystemsByFoMap", awardMap);
		return "browse/fundingOpportunities";
	}

	@GetMapping("/appParticipations")
	public String appParticipations(Model model) {
		model.addAttribute("appParticipations", appParticipationService.getAllowedRecords());
		return "browse/appParticipations";
	}

	@GetMapping(value = "/viewFo")
	public String viewFundingOpportunity(@RequestParam("id") long id, Model model) {
		model.addAttribute("fo", dataService.getFundingOpportunity(id));
		// model.addAttribute("systemFoCycles",
		// dataService.getSystemFundingCyclesByFoId(id));
		model.addAttribute("grantingCapabilities", gcService.findGrantingCapabilitiesByFoId(id));
		model.addAttribute("linkedSystemFundingCycles", dataService.getSystemFundingCyclesByFoId(id));
		return "browse/viewFundingOpportunity";
	}

	@GetMapping(value = "/viewCalendar")
	public String viewCalendar(@RequestParam(name = "plusMinusMonth", defaultValue = "0") Long plusMinusMonth, Model model) {
		model.addAttribute("plusMonth", plusMinusMonth + 1);
		model.addAttribute("minusMonth", plusMinusMonth - 1);
		model.addAttribute("calGrid", new CalendarGrid(plusMinusMonth));
		model.addAttribute("fcCalEvents", dataService.getMonthlyFundingCyclesMapByDate(plusMinusMonth));
		model.addAttribute("startingDates", dataService.getAllStartingDates(plusMinusMonth));
		model.addAttribute("endDates", dataService.getAllEndingDates(plusMinusMonth));
		model.addAttribute("datesNoiStart", dataService.getAllDatesNOIStart(plusMinusMonth));
		model.addAttribute("datesLoiEnd", dataService.getAllDatesLOIEnd(plusMinusMonth));
		model.addAttribute("datesNoiEnd", dataService.getAllDatesNOIEnd(plusMinusMonth));
		model.addAttribute("datesLoiStart", dataService.getAllDatesLOIStart(plusMinusMonth));
		return "browse/viewCalendar";
	}

	@GetMapping(value = "/viewFiscalYear")
	public String viewFundingCycles(Model model) {
		model.addAttribute("fiscalYears", dataService.findAllFiscalYears());
		model.addAttribute("fy", new FiscalYear());
		return "browse/viewFiscalYear";
	}

	@GetMapping(value = "/viewFcFromFy")
	public String viewFundingCyclesFromFiscalYear(@RequestParam("id") long id, Model model) {
		model.addAttribute("fc", dataService.fundingCyclesByFiscalYearId(id));
		return "browse/viewFcFromFy";
	}

}
