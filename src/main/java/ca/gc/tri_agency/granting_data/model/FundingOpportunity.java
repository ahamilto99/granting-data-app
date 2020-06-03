package ca.gc.tri_agency.granting_data.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import ca.gc.tri_agency.granting_data.model.util.LocalizedParametersModel;

@Entity
@Audited
public class FundingOpportunity implements LocalizedParametersModel {

	@Id
	@SequenceGenerator(name = "SEQ_FUNDING_OPPORTUNITY", sequenceName = "SEQ_FUNDING_OPPORTUNITY", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FUNDING_OPPORTUNITY")
	private Long id;

	@NotBlank
	private String nameEn;

	@NotBlank
	private String nameFr;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lead_agency_id")
	@NotNull
	private Agency leadAgency;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable
	private Set<Agency> participatingAgencies;

	private String fundingType; // could be dropped

	private String frequency;
	
	private Boolean isJointInitiative = false;

	public Boolean isNOI = false;

	private Boolean isLOI = false;
	
	private String partnerOrg;

	private Boolean isEdiRequired = false;

	private Boolean isComplex = false;

	private String programLeadName;

	private String programLeadDn;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "business_unit_id")
	private BusinessUnit businessUnit;

	public FundingOpportunity() {
		setParticipatingAgencies(new HashSet<Agency>());
	}

	public void loadFromForm(FundingOpportunity f) {
		this.setFundingType(f.getFundingType());
		this.setFrequency(f.getFrequency());
		this.setLeadAgency(f.getLeadAgency());
		this.setNameEn(f.getNameEn());
		this.setNameFr(f.getNameFr());
		this.setProgramLeadName(f.getProgramLeadName());
	}

	public String getNameEn() {
		return nameEn;
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

	public String getProgramLeadName() {
		return programLeadName;
	}

	public void setProgramLeadName(String programLeadName) {
		this.programLeadName = programLeadName;
	}

	public Agency getLeadAgency() {
		return leadAgency;
	}

	public void setLeadAgency(Agency leadAgency) {
		this.leadAgency = leadAgency;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getFundingType() {
		return fundingType;
	}

	public void setFundingType(String fundingType) {
		this.fundingType = fundingType;
	}

	public Set<Agency> getParticipatingAgencies() {
		return participatingAgencies;
	}

	public void setParticipatingAgencies(Set<Agency> participatingAgencies) {
		this.participatingAgencies = participatingAgencies;
	}

	public String getProgramLeadDn() {
		return programLeadDn;
	}

	public void setProgramLeadDn(String programLeadDn) {
		this.programLeadDn = programLeadDn;
	}

	public Boolean isJointInitiative() {
		return isJointInitiative;
	}

	public Boolean getIsJointInitiative() {
		return isJointInitiative;
	}

	public void setIsJointInitiative(Boolean isJointInitiative) {
		this.isJointInitiative = isJointInitiative;
	}

	public String getPartnerOrg() {
		return partnerOrg;
	}

	public void setPartnerOrg(String partnerOrg) {
		this.partnerOrg = partnerOrg;
	}

	public Boolean getIsEdiRequired() {
		return isEdiRequired;
	}

	public void setIsEdiRequired(Boolean isEdiRequired) {
		this.isEdiRequired = isEdiRequired;
	}

	public Boolean getIsComplex() {
		return isComplex;
	}

	public void setIsComplex(Boolean isComplex) {
		this.isComplex = isComplex;
	}

	public Boolean getIsNOI() {
		return isNOI;
	}

	public void setIsNOI(Boolean nOI) {
		isNOI = nOI;
	}

	public Boolean getIsLOI() {
		return isLOI;
	}

	public void setIsLOI(Boolean islOI) {
		isLOI = islOI;
	}

	public BusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
	}

}
