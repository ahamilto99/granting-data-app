package ca.gc.tri_agency.granting_data.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.model.file.FundingCycleDatasetRow;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.AdminService;
import ca.gc.tri_agency.granting_data.service.AgencyService;
import ca.gc.tri_agency.granting_data.service.FundingOpportunityService;
import ca.gc.tri_agency.granting_data.service.SystemFundingOpportunityService;

@Controller
@RequestMapping("/admin")
@AdminOnly
public class AdminController {

	private AdminService adminService;

	private AgencyService agencyService;
	
	private SystemFundingOpportunityService sfoService;

	private FundingOpportunityService foService;

	private MessageSource msgSource;

	@Autowired
	public AdminController(AdminService adminService, AgencyService agencyService, SystemFundingOpportunityService sfoService,
			FundingOpportunityService foService, MessageSource msgSource) {
		this.adminService = adminService;
		this.agencyService = agencyService;
		this.sfoService = sfoService;
		this.foService = foService;
		this.msgSource = msgSource;
	}

	@GetMapping("/selectFileForComparison")
	public String compareData_selectDatasetUploadFile(Model model) {
		model.addAttribute("datasetFiles", adminService.getDatasetFiles());
		return "admin/selectFileForComparison";
	}

	@GetMapping(value = "/analyzeFoUploadData", params = "filename")
	public String compareData_showDatasetUploadAdditions(@RequestParam String filename, Model model) {
		model.addAttribute("filename", filename);
		List<FundingCycleDatasetRow> fileRows = adminService.getFundingCyclesFromFile(filename);
		model.addAttribute("fileRows", fileRows);
		model.addAttribute("actionRowIds", adminService.generateActionableFoCycleIds(fileRows));
		return "admin/analyzeFoUploadData";
	}

	@PostMapping("/analyzeFoUploadData")
	public String compareData_uploadSelectedNames_post(@RequestParam String filename,
			@RequestParam("idToAction") String[] idsToAction, final RedirectAttributes redirectAttrs) {
		long numChances = adminService.applyChangesFromFileByIds(filename, idsToAction);
		redirectAttrs.addFlashAttribute("actionMessage", "Successfully applied " + numChances + " Funcing Cycles");
		return "redirect:/admin/home";
	}

	@GetMapping("/home")
	public String home(Model model) {

		return "admin/home";

	}

	@PostMapping(value = "/registerFOLink")
	public String registerProgramLinkPost(@ModelAttribute("id") Long id, @ModelAttribute("foId") Long foId) {
		sfoService.linkSystemFundingOpportunity(id, foId);
		return "redirect:analyzeSFOs";
	}

	@GetMapping(value = "/createFo")
	public String addFo(Model model, @RequestParam(name = "sfoId", required = false) Optional<Long> sfoId) {
		FundingOpportunity fo = new FundingOpportunity();
		if (sfoId.isPresent()) {
			SystemFundingOpportunity sfo = sfoService.findSystemFundingOpportunityById(sfoId.get());
			fo.setNameEn(sfo.getNameEn());
			fo.setNameFr(sfo.getNameFr());
		}
		List<Agency> allAgencies = agencyService.findAllAgencies();
		model.addAttribute("fo", fo);
		model.addAttribute("allAgencies", allAgencies);
		return "admin/createFo";
	}

	@PostMapping(value = "/createFo")
	public String addFoPost(@Valid @ModelAttribute("fo") FundingOpportunity command, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) throws Exception {
		if (bindingResult.hasErrors()) {
			// required in order to re-populate the drop-down list
			List<Agency> allAgencies = agencyService.findAllAgencies();
			model.addAttribute("allAgencies", allAgencies);
			return "admin/createFo";
		}
		foService.saveFundingOpportunity(command);
		String createdFo = msgSource.getMessage("h.createdFo", null, LocaleContextHolder.getLocale());
		redirectAttributes.addFlashAttribute("actionMessage", createdFo + command.getLocalizedAttribute("name"));
		return "redirect:/admin/home";
	}

}
