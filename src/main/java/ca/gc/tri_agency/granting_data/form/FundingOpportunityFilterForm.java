package ca.gc.tri_agency.granting_data.form;

import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.BusinessUnit;
import ca.gc.tri_agency.granting_data.model.GrantingSystem;

public class FundingOpportunityFilterForm {
	private Agency leadAgency;
	private BusinessUnit division;
	private GrantingSystem applySystem;
	private GrantingSystem awardSystem;
	private String type;

	public Agency getLeadAgency() {
		return leadAgency;
	}

	public void setLeadAgency(Agency leadAgency) {
		this.leadAgency = leadAgency;
	}

	public BusinessUnit getDivision() {
		return division;
	}

	public void setDivision(BusinessUnit division) {
		this.division = division;
	}

	public GrantingSystem getApplySystem() {
		return applySystem;
	}

	public void setApplySystem(GrantingSystem applySystem) {
		this.applySystem = applySystem;
	}

	public GrantingSystem getAwardSystem() {
		return awardSystem;
	}

	public void setAwardSystem(GrantingSystem awardSystem) {
		this.awardSystem = awardSystem;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// GRANT TYPE?? BOOLEAN (IS_SCHOLARSHIP) WITH INVERSE LOGIC FOR GRANTS, OR DO WE
	// SUB TYPE??

}
