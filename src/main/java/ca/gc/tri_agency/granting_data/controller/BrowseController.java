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
        grantingSystemList.forEach(grantingSystem -> grantingSystemMap.put(grantingSystem.getAcronym(), grantingSystem));

        List<GrantingStage> grantingStageList = new ArrayList<>();
        grantingStageList = grantingStageRepo.findAll();

        List<FundingOpportunity> foList = foRepo.findAll();

        // APPLY stage entries
        GrantingStage grantingStageApply = null;
        for (GrantingStage grantingStage : grantingStageList) {
            if (grantingStage.getNameEn()
                .equals("APPLY")) {
                grantingStageApply = grantingStage;
                break;
            }
        }
        for (FundingOpportunity fo : foList) {
            GrantingCapability grantingCapability = new GrantingCapability();
            grantingCapability.setGrantingStage(grantingStageApply);
            grantingCapability.setFundingOpportunity(fo);
            String applyMethod = fo.getApplyMethod();
            if (null != fo.getDivision() && fo.getDivision()
                .equals("MCT")) {
                grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym("ResearchNet"));
                grantingCapabilityRepo.save(grantingCapability);
                continue;
            } else if (null != fo.getProgramLeadName() && fo.getProgramLeadName()
                .equals("Open")) {
                grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym("NSERC Online"));
                grantingCapabilityRepo.save(grantingCapability);
            } else if ((applyMethod == null || applyMethod.equals("Offline") || applyMethod.equals("NotApplicable") || applyMethod.equals("Email")) && null != fo.getAwardManagementSystem() && !fo.getAwardManagementSystem()
                .equals("null") && !fo.getAwardManagementSystem()
                    .equals("ApearsinNAMISakaCSYN")
                && !fo.getAwardManagementSystem()
                    .equals("ApearsinNAMISakaSYN")) {
                grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym(fo.getAwardManagementSystem()));
                grantingCapabilityRepo.save(grantingCapability);
                continue;
            } else if (null != fo.getProgramLeadName() && fo.getProgramLeadName()
                .equals("AllanahBrown")) {
                grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym("ResearchNet"));
                grantingCapabilityRepo.save(grantingCapability);
                continue;
            } else if (null != fo.getNameEn() && fo.getNameEn()
                .equals("SubatomicPhysics-Individual(SAPIN)(5840)")) {
                grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym("RP1"));
                grantingCapabilityRepo.save(grantingCapability);
                continue;
            } else if (null != fo.getNameEn() && fo.getNameEn()
                .equals("ThematicResourcesSupportinMathematicsandStatistics(CTRMS)")) {
                grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym("SP Secure Upload"));
                grantingCapabilityRepo.save(grantingCapability);
                continue;
            } else if (null != fo.getNameEn() && fo.getNameEn()
                .equals("ResearchSupportFund(RSF)")) {
                grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym("CIMS"));
                grantingCapabilityRepo.save(grantingCapability);
                continue;
            } else if (null != fo.getNameEn() && fo.getNameEn()
                .equals("DiggingintoData")) {
                grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym("SP Secure Upload"));
                grantingCapabilityRepo.save(grantingCapability);
                continue;
            } else if (null != fo.getNameEn() && fo.getNameEn()
                .equals("SpecialInitiativesFundForResearchSupportAndCollaboration")) {
                grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym("SP Secure Upload"));
                grantingCapabilityRepo.save(grantingCapability);
                continue;
            } else if (null != fo.getNameEn() && fo.getNameEn()
                .contains("Synergy")) {
                grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym("NAMIS"));
                grantingCapabilityRepo.save(grantingCapability);
                continue;
            }

            for (Map.Entry<String, GrantingSystem> entry : grantingSystemMap.entrySet()) {
                if (entry != null && entry.getKey() != null && fo.getApplyMethod() != null && fo.getApplyMethod()
                    .equals(entry.getKey())) {
                    grantingCapability.setGrantingSystem(entry.getValue());
                    grantingCapabilityRepo.save(grantingCapability);
                    break;
                } else if (null != fo.getApplyMethod() && null != assignGrantingSystem(fo.getApplyMethod())) {
                    grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym(assignGrantingSystem(fo.getApplyMethod())));
                    grantingCapabilityRepo.save(grantingCapability);
                    break;
                }
            }
        }

        // AWARD stage entries
        GrantingStage grantingStageAward = null;
        for (GrantingStage grantingStage : grantingStageList) {
            if (grantingStage.getNameEn()
                .equals("AWARD")) {
                grantingStageAward = grantingStage;
                break;
            }
        }
        for (FundingOpportunity fo : foList) {
            GrantingCapability grantingCapability = new GrantingCapability();
            grantingCapability.setGrantingStage(grantingStageAward);
            grantingCapability.setFundingOpportunity(fo);

            if (null != fo.getDivision() && fo.getDivision()
                .equals("MCT")) {
                grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym("NAMIS"));
                grantingCapabilityRepo.save(grantingCapability);
                continue;
            } else if (null != fo.getProgramLeadName() && fo.getProgramLeadName()
                .equals("Open")) {
                grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym("NAMIS"));
                grantingCapabilityRepo.save(grantingCapability);
            } else if (null != fo.getAwardManagementSystem() && fo.getAwardManagementSystem()
                .equals("NAMIS/AMIS/CIHRSystem")) {
                grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym("NAMIS"));
                grantingCapabilityRepo.save(grantingCapability);
                grantingCapability = new GrantingCapability();
                grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym("AMIS"));
                grantingCapability.setGrantingStage(grantingStageAward);
                grantingCapability.setFundingOpportunity(fo);
                grantingCapabilityRepo.save(grantingCapability);
                continue;
            } else if ((fo.getAwardManagementSystem() == null || fo.getAwardManagementSystem()
                .equals("ViaResearchOffices") || fo.getAwardManagementSystem()
                    .equals("Partner"))
                && fo.getApplyMethod() != null && !fo.getApplyMethod()
                    .equals("Offline")
                && !fo.getApplyMethod()
                    .equals("NotApplicable")
                && !fo.getApplyMethod()
                    .equals("offline")
                && !fo.getApplyMethod()
                    .equals("CIMS/Extranet(SP)")) {
                grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym(assignGrantingSystem(fo.getApplyMethod())));
                grantingCapabilityRepo.save(grantingCapability);
                continue;
            } else if (null != fo.getProgramLeadName() && fo.getProgramLeadName()
                .equals("AllanahBrown")) {
                grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym("ResearchNet"));
                grantingCapabilityRepo.save(grantingCapability);
                continue;
            } else if (null != fo.getNameEn() && fo.getNameEn()
                .equals("SubatomicPhysics-Individual(SAPIN)(5840)")) {
                grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym("RP1"));
                grantingCapabilityRepo.save(grantingCapability);
                continue;
            } else if (null != fo.getNameEn() && fo.getNameEn()
                .equals("ThematicResourcesSupportinMathematicsandStatistics(CTRMS)")) {
                grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym("NAMIS"));
                grantingCapabilityRepo.save(grantingCapability);
                continue;
            } else if (null != fo.getNameEn() && fo.getNameEn()
                .equals("ResearchSupportFund(RSF)")) {
                grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym("CIMS"));
                grantingCapabilityRepo.save(grantingCapability);
                continue;
            } else if (null != fo.getNameEn() && fo.getNameEn()
                .equals("DiggingintoData")) {
                grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym("NAMIS"));
                grantingCapabilityRepo.save(grantingCapability);
                continue;
            } else if (null != fo.getNameEn() && fo.getNameEn()
                .equals("SpecialInitiativesFundForResearchSupportAndCollaboration")) {
                grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym("AMIS"));
                grantingCapabilityRepo.save(grantingCapability);
                continue;
            }

            for (Map.Entry<String, GrantingSystem> entry : grantingSystemMap.entrySet()) {
                if (entry != null && entry.getKey() != null && fo.getAwardManagementSystem() != null && fo.getAwardManagementSystem()
                    .equals(entry.getKey())) {
                    grantingCapability.setGrantingSystem(entry.getValue());
                    grantingCapabilityRepo.save(grantingCapability);
                    break;
                } else if (null != fo.getAwardManagementSystem() && null != assignGrantingSystem(fo.getAwardManagementSystem())) {
                    grantingCapability.setGrantingSystem(grantingSystemRepo.findByAcronym(assignGrantingSystem(fo.getAwardManagementSystem())));
                    grantingCapabilityRepo.save(grantingCapability);
                    break;
                }
            }

        }
    }

    // TODO: this method should not be in the release therefore delete it once you
    // are done with it
    private static String assignGrantingSystem(String awardOrApplyMethod) {
        switch (awardOrApplyMethod) {
        case "ApearsinNAMISakaCSYN":
        case "ApearsinNAMISakaSYN":
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
        case "CIMS/Extranet(SP) ":
            return "CIMS";
        case "ResearchNet":
        case "Researchnet":
        case "ResearchNet(CIHR)":
            return "ResearchNet";
        case "CRM":
            return "CRM";
        case "SecureUpload":
        case "Extranet(SP)":
            return "SP Secure Upload";
        case "RP2":
        case "RP2.0(Newsystembeingdeveloped)":
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
