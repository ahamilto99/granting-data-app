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
	
	private Double awarded_amount;
	
	private Long funding_year;
	
	private Long personIdentifier;
	
	private String familyName;
	
	private String givenName;
	
	private String roleCode;
	
	private String roleEn;
	
	private String roleFr;
	
	public Award() {
	}

	public Award(Double awarded_amount, Long funding_year, Long personIdentifier, String familyName, String givenName,
			String roleCode, String roleEn, String roleFr) {
		this.awarded_amount = awarded_amount;
		this.funding_year = funding_year;
		this.personIdentifier = personIdentifier;
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

	public Double getAwarded_amount() {
		return awarded_amount;
	}

	public void setAwarded_amount(Double awarded_amount) {
		this.awarded_amount = awarded_amount;
	}

	public Long getFunding_year() {
		return funding_year;
	}

	public void setFunding_year(Long funding_year) {
		this.funding_year = funding_year;
	}

	public Long getPersonIdentifier() {
		return personIdentifier;
	}

	public void setPersonIdentifier(Long personIdentifier) {
		this.personIdentifier = personIdentifier;
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
		builder.append(awarded_amount);
		builder.append(", funding_year=");
		builder.append(funding_year);
		builder.append(", personIdentifier=");
		builder.append(personIdentifier);
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
