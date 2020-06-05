package ca.gc.tri_agency.granting_data.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
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
		Map<String, ?> inFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (inFlashMap != null) {
			model.addAttribute("unlinkedPerformedMsg", inFlashMap.get("actionMessage"));
		}

		model.addAttribute("systemFO", sfoService.findSystemFundingOpportunityById(id));
		model.addAttribute("fosForLink", foService.findAllFundingOpportunities());
		model.addAttribute("revisionList", sfoService.findSystemFundingOpportunityRevisionById(id));
		return "admin/viewSystemFO";
	}

	@GetMapping("/admin/analyzeSFOs")
	public String analyzeSystemFundingOpportunities(Model model) {
		model.addAttribute("systemFOs", sfoService.findAllSystemFundingOpportunities());
		return "admin/analyzeSystemFOs";
	}

	@GetMapping("/admin/confirmUnlink")
	public String unlinkSFOFromFOGet(@RequestParam Long sfoId, Model model) {
		SystemFundingOpportunity sfo = sfoService.findSystemFundingOpportunityById(sfoId);
		FundingOpportunity fo = sfo.getLinkedFundingOpportunity();
		if (fo == null) {
			throw new DataRetrievalFailureException(
					"That System Funding Opportunity is not linked to a Funding Opportunity");
		}
		model.addAttribute("sfo", sfo);
		model.addAttribute("fo", fo);

		return "/admin/confirmUnlink";
	}

	@PostMapping("/admin/confirmUnlink")
	public String unlinkSFOFromFOPost(@RequestParam Long sfoId, Model model, RedirectAttributes redirectAttributes) {
		SystemFundingOpportunity sfo = sfoService.findSystemFundingOpportunityById(sfoId);
		FundingOpportunity fo = sfo.getLinkedFundingOpportunity();

		sfoService.unlinkSystemFundingOpportunity(sfoId, fo.getId());

		String wasUnlinkedFrom = msgSource.getMessage("msg.unlinkedPerformedMsg", null, LocaleContextHolder.getLocale());
		redirectAttributes.addFlashAttribute("actionMessage",
				sfo.getLocalizedAttribute("name") + wasUnlinkedFrom + fo.getLocalizedAttribute("name"));

		model.addAttribute("systemFO", sfo);
		model.addAttribute("fosForLink", foService.findAllFundingOpportunities());

		return "redirect:/admin/viewSFO?id=" + sfoId;
	}

	@PostMapping("/registerFOLink")
	public String registerProgramLinkPost(@ModelAttribute("id") Long id, @ModelAttribute("foId") Long foId) {
		sfoService.linkSystemFundingOpportunity(id, foId);
		return "redirect:analyzeSFOs";
	}
	
	@GetMapping("/admin/auditLogSFO")
	public String systemFundingOpportunityAuditLog(Model model) {
		model.addAttribute("revisionList", sfoService.findAllSystemFundingOpportunityRevisions());
		return "admin/systemFundingOpportunityAuditLog";
	}

}
