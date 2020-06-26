package ca.gc.tri_agency.granting_data.form;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.context.i18n.LocaleContextHolder;

import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.BusinessUnit;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;

public class ProgramForm {

	private Long id;

	@NotNull
	@Size(min = 2)
	private String nameEn;

	private String nameFr;

	@NotNull
	private BusinessUnit businessUnit;

	@NotNull
	@Size(min = 2)
	private String fundingType;

	@NotNull
	@Size(min = 2)
	private String frequency;

	@NotNull
	private Set<Agency> agencies;

	public ProgramForm(FundingOpportunity p) {
		id = p.getId();
		nameEn = p.getNameEn();
		nameFr = p.getNameFr();
		this.setFundingType(p.getFundingType());
		this.setBusinessUnit(p.getBusinessUnit());
		this.frequency = p.getFrequency();
	}

	public ProgramForm() {
		agencies = new HashSet<Agency>();
	}

	public String getNameEn() {
		return nameEn;
	}

	public String getName() {
		String retval = "";
		if (LocaleContextHolder.getLocale().toString().contains("en")) {
			retval = getNameEn();
		} else {
			retval = getNameFr();
		}
		return retval;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNameFr() {
		return nameFr;
	}

	public void setNameFr(String nameFr) {
		this.nameFr = nameFr;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Set<Agency> getAgencies() {
		return agencies;
	}

	public void setAgencies(Set<Agency> agencies) {
		this.agencies = agencies;
	}

	public String toString() {
		return "" + id + " : " + nameEn + " :: " + nameFr;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFundingType() {
		return fundingType;
	}

	public void setFundingType(String fundingType) {
		this.fundingType = fundingType;
	}

	public BusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
	}
}
