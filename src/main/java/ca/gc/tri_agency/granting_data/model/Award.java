package ca.gc.tri_agency.granting_data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Award {

	@Id
	@SequenceGenerator(name = "SEQ_AWARD", sequenceName = "SEQ_AWARD", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AWARD")
	private Long id;
	
	private Double awardedAmount;
	
	private Long fundingYear;
	
	private String applId;
	
	private String familyName;
	
	private String givenName;
	
	private String roleCode;
	
	private String roleEn;
	
	private String roleFr;
	
	public Award() {
	}

	public Award(Double awardedAmount, Long fundingYear, String applId, String familyName, String givenName,
			String roleCode, String roleEn, String roleFr) {
		this.awardedAmount = awardedAmount;
		this.fundingYear = fundingYear;
		this.applId = applId;
		this.familyName = familyName;
		this.givenName = givenName;
		this.roleCode = roleCode;
		this.roleEn = roleEn;
		this.roleFr = roleFr;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAwardedAmount() {
		return awardedAmount;
	}

	public void setAwardedAmount(Double awardedAmount) {
		this.awardedAmount = awardedAmount;
	}

	public Long getFundingYear() {
		return fundingYear;
	}

	public void setFundingYear(Long fundingYear) {
		this.fundingYear = fundingYear;
	}

	public String getApplId() {
		return applId;
	}

	public void setPersonIdentifier(String applId) {
		this.applId = applId;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleEn() {
		return roleEn;
	}

	public void setRoleEn(String roleEn) {
		this.roleEn = roleEn;
	}

	public String getRoleFr() {
		return roleFr;
	}

	public void setRoleFr(String roleFr) {
		this.roleFr = roleFr;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Award [id=");
		builder.append(id);
		builder.append(", awarded_amount=");
		builder.append(awardedAmount);
		builder.append(", funding_year=");
		builder.append(fundingYear);
		builder.append(", personIdentifier=");
		builder.append(applId);
		builder.append(", familyName=");
		builder.append(familyName);
		builder.append(", givenName=");
		builder.append(givenName);
		builder.append(", roleCode=");
		builder.append(roleCode);
		builder.append(", roleEn=");
		builder.append(roleEn);
		builder.append(", roleFr=");
		builder.append(roleFr);
		builder.append("]");
		return builder.toString();
	}
}
