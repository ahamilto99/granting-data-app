package ca.gc.tri_agency.granting_data.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.gc.tri_agency.granting_data.model.projection.SystemFundingOpportunityProjection;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.FundingOpportunityService;
import ca.gc.tri_agency.granting_data.service.SystemFundingOpportunityService;

@AdminOnly
@Controller
public class SystemFundingOpportunityController {

	private SystemFundingOpportunityService sfoService;

	private FundingOpportunityService foService;

	private MessageSource msgSource;

	@Autowired
	public SystemFundingOpportunityController(SystemFundingOpportunityService sfoService, FundingOpportunityService foService,
			MessageSource msgSource) {
		this.sfoService = sfoService;
		this.foService = foService;
		this.msgSource = msgSource;
	}

	@GetMapping("/admin/viewSFO")
	public String viewSystemFundingOpportunity(@RequestParam Long id, Model model, HttpServletRequest request) {
		SystemFundingOpportunityProjection sfoProjection = sfoService.findSystemFundingOpportunityAndLinkedFOName(id);

		model.addAttribute("sfo", sfoProjection);
		model.addAttribute("revisionList", sfoService.findSystemFundingOpportunityRevisionById(id));

		if (sfoProjection.getFundingOpportunityId() == null) {
			model.addAttribute("fosForLink", foService.findAllFundingOpportunityNames());
		}

		return "admin/viewSystemFO";
	}

	@GetMapping("/admin/analyzeSFOs")
	public String analyzeSystemFundingOpportunities(Model model) {
		model.addAttribute("sfos", sfoService.findAllSystemFundingOpportunitiesAndLinkedFONameAndGSysName());

		return "admin/analyzeSystemFOs";
	}

	@GetMapping("/admin/confirmUnlink")
	public String unlinkSFOFromFOGet(@RequestParam Long sfoId, Model model) {
		SystemFundingOpportunityProjection sfoProjection = sfoService.findSystemFundingOpportunityNameAndLinkedFOName(sfoId);

		model.addAttribute("sfo", sfoProjection);

		return "/admin/confirmUnlink";
	}

	@PostMapping("/admin/confirmUnlink")
	public String unlinkSFOFromFOPost(@RequestParam("sfoId") Long sfoId, @RequestParam("foId") Long foId,
			@RequestParam("sfoName") String sfoName, @RequestParam("foName") String foName, Model model,
			RedirectAttributes redirectAttributes) {
		sfoService.unlinkSystemFundingOpportunity(sfoId, foId);

		String actionMsg = msgSource.getMessage("msg.unlinkedPerformedMsg", new String[] { sfoName, foName },
				LocaleContextHolder.getLocale());
		redirectAttributes.addFlashAttribute("actionMsg", actionMsg);

		return "redirect:/admin/viewSFO?id=" + sfoId;
	}

	@PostMapping("/admin/registerFOLink")
	public String registerProgramLinkPost(@RequestParam("id") Long id, @RequestParam("foId") Long foId,
			@RequestParam("sfoName") String sfoName, @RequestParam("foName") String foName, RedirectAttributes redirectAttributes) {
		sfoService.linkSystemFundingOpportunity(id, foId);

		String actionMsg = msgSource.getMessage("msg.linkPerformed", new String[] { sfoName, foName }, LocaleContextHolder.getLocale());
		redirectAttributes.addFlashAttribute("actionMsg", actionMsg);

		return "redirect:/admin/viewSFO?id=" + id;
	}

	@GetMapping("/admin/auditLogSFO")
	public String systemFundingOpportunityAuditLog(Model model) {
		model.addAttribute("revisionList", sfoService.findAllSystemFundingOpportunityRevisions());

		return "admin/systemFundingOpportunityAuditLog";
	}

}
