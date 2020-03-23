package ca.gc.tri_agency.granting_data.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.gc.tri_agency.granting_data.model.FiscalYear;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.model.GrantingCapability;
import ca.gc.tri_agency.granting_data.model.GrantingStage;
import ca.gc.tri_agency.granting_data.model.GrantingSystem;
import ca.gc.tri_agency.granting_data.model.util.CalendarGrid;
import ca.gc.tri_agency.granting_data.repo.FundingOpportunityRepository;
import ca.gc.tri_agency.granting_data.repo.GrantingCapabilityRepository;
import ca.gc.tri_agency.granting_data.repo.GrantingStageRepository;
import ca.gc.tri_agency.granting_data.repo.GrantingSystemRepository;
import ca.gc.tri_agency.granting_data.service.DataAccessService;

@Controller
@RequestMapping("/browse")
public class BrowseController {

	// TODO: remove these fields when you are done with the
	// populateWithDesiredStructure method
	@Autowired
	private GrantingCapabilityRepository grantingCapabilityRepo;
	@Autowired
	private FundingOpportunityRepository foRepo;
	@Autowired
	private GrantingSystemRepository grantingSystemRepo;
	@Autowired
	private GrantingStageRepository grantingStageRepo;

	// TODO: this method should not be in the release therefore delete it once you
	// are done with it
	private void populateWithDesiredStructure() {
		List<GrantingSystem> grantingSystemList = grantingSystemRepo.findAll();
		Map<String, GrantingSystem> grantingSystemMap = new HashMap<>();
		grantingSystemList
				.forEach(grantingSystem -> grantingSystemMap.put(grantingSystem.getAcronym(), grantingSystem));

		List<GrantingStage> grantingStageList = new ArrayList<>();
		grantingStageList = grantingStageRepo.findAll();

		List<FundingOpportunity> foList = foRepo.findAll();

		// APPLY stage entries
		GrantingStage grantingStageApply = null;
		for (GrantingStage grantingStage : grantingStageList) {
			if (grantingStage.getNameEn().equals("APPLY")) {
				grantingStageApply = grantingStage;
				break;
			}
		}
		for (FundingOpportunity fo : foList) {
			GrantingCapability grantingCapability = new GrantingCapability();
			grantingCapability.setGrantingStage(grantingStageApply);
			grantingCapability.setFundingOpportunity(fo);
			for (Map.Entry<String, GrantingSystem> entry : grantingSystemMap.entrySet()) {
				if (entry != null && entry.getKey() != null && fo.getApplyMethod() != null
						&& fo.getApplyMethod().equals(entry.getKey())) {
					grantingCapability.setGrantingSystem(entry.getValue());
					break;
				}
			}
			// TODO: remove output statement when done debugging
			String stage = "";
			if (grantingCapability.getGrantingStage() != null
					&& grantingCapability.getGrantingStage().getNameEn() != null) {
				stage = grantingCapability.getGrantingStage().getNameEn();
			}
			String system = "";
			if (grantingCapability.getGrantingSystem() != null
					&& String.valueOf(grantingCapability.getGrantingSystem().getId()) != null) {
				system = String.valueOf(grantingCapability.getGrantingSystem().getAcronym());
			}
			System.out.printf("id=%s : foId=%s : stage=%s : granting system=%s%n", grantingCapability.getId(),
					grantingCapability.getFundingOpportunity().getId(), stage, system);

			 grantingCapabilityRepo.save(grantingCapability);
		}

		// AWARD stage entries
		GrantingStage grantingStageAward = null;
		for (GrantingStage grantingStage : grantingStageList) {
			if (grantingStage.getNameEn().equals("AWARD")) {
				grantingStageAward = grantingStage;
				break;
			}
		}
		for (FundingOpportunity fo : foList) {
			GrantingCapability grantingCapability = new GrantingCapability();
			grantingCapability.setGrantingStage(grantingStageAward);
			grantingCapability.setFundingOpportunity(fo);

			for (Map.Entry<String, GrantingSystem> entry : grantingSystemMap.entrySet()) {
				if (entry != null && entry.getKey() != null && fo.getAwardManagementSystem() != null
						&& fo.getAwardManagementSystem().equals(entry.getKey())) {
					grantingCapability.setGrantingSystem(entry.getValue());
					break;
				}
			}
			// TODO: remove output statement when done debugging
			String stage = "";
			if (grantingCapability.getGrantingStage() != null
					&& grantingCapability.getGrantingStage().getNameEn() != null) {
				stage = grantingCapability.getGrantingStage().getNameEn();
			}
			String system = "";
			if (grantingCapability.getGrantingSystem() != null
					&& String.valueOf(grantingCapability.getGrantingSystem().getId()) != null) {
				system = String.valueOf(grantingCapability.getGrantingSystem().getAcronym());
			}
			System.out.printf("id=%s : foId=%s : stage=%s : granting system=%s%n", grantingCapability.getId(),
					grantingCapability.getFundingOpportunity().getId(), stage, system);

                        grantingCapabilityRepo.save(grantingCapability);
		}
		// TODO: remove output statements when done debugging
		System.out.println(foRepo.getOne(92L).getAwardManagementSystem());
		System.out.println(foRepo.getOne(135L).getAwardManagementSystem());
		System.out.println(foRepo.getOne(136L).getAwardManagementSystem());
	}

	// TODO: this method should not be in the release therefore delete it once you
	// are done with it
	private static String assignGrantingSystem(String awardOrApplyMethod) {
		switch (awardOrApplyMethod) {
		case "ApearsinNAMISakaSCYSN":
		case "ApearsinNAMISakaCYN":
		case "NAMIS":
			return "NAMIS";
		case "NOLS":
			return "NSERC Online";
		case "SOLS":
			return "SSHRC Online";
		case "RP 1.0":
		case "RP1.0":
			return "RP1";
		case "CIMS":
			return "CIMS";
		case "ResearchNet":
		case "ResearchNet (CIHR)":
			return "ResearchNet";
		case "CRM":
			return "CRM";
		case "Secure Upload":
			return "SP Secure Upload";
		case "RP2":
		case "RP 2.0 (New system being developed)":
			return "Convergence";
		default:
			return null;
		}
	}
	// private static final Logger LOG = LogManager.getLogger();

	@Autowired
	DataAccessService dataService;

	@GetMapping(value = "/viewAgency")
	public String viewAgency(@RequestParam("id") long id, Model model) {
		model.addAttribute("agency", dataService.getAgency(id));
		model.addAttribute("agencyFos", dataService.getAgencyFundingOpportunities(id));
		return "browse/viewAgency";
	}

	@GetMapping("/goldenList")
	public String goldListDisplay(Model model) {
		model.addAttribute("goldenList", dataService.getAllFundingOpportunities());
		model.addAttribute("fcByFoMap", dataService.getFundingCycleByFundingOpportunityMap());

		// TODO: remove method call when done with it
		populateWithDesiredStructure();

		return "browse/goldenList";
	}

	@GetMapping(value = "/viewFo")
	public String viewFundingOpportunity(@RequestParam("id") long id, Model model) {
		model.addAttribute("fo", dataService.getFundingOpportunity(id));
		// model.addAttribute("systemFoCycles",
		// dataService.getSystemFundingCyclesByFoId(id));
		model.addAttribute("grantingCapabilities", dataService.getGrantingCapabilitiesByFoId(id));
		model.addAttribute("fcDataMap", dataService.getFundingCycleDataMapByYear(id));
		return "browse/viewFundingOpportunity";
	}

	@GetMapping(value = "/viewCalendar")
	public String viewCalendar(@RequestParam(name = "plusMinusMonth", defaultValue = "0") Long plusMinusMonth,
			Model model) {
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
