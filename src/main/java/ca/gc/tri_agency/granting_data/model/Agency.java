package ca.gc.tri_agency.granting_data.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.envers.Audited;

import ca.gc.tri_agency.granting_data.model.util.LocalizedParametersModel;

@Entity
@Audited
public class Agency implements LocalizedParametersModel {
	
	@Id
	@SequenceGenerator(name = "SEQ_AGENCY", sequenceName = "SEQ_AGENCY", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AGENCY")
	private Long id;

	private String nameEn;

	private String nameFr;

	private String acronymFr;

	private String acronymEn;
	
	@ManyToMany(mappedBy = "participatingAgencies")
	private Set<FundingOpportunity> fundingOpportunities = new HashSet<>();

	public Agency() {

	}

	public Agency(String nameEn, String nameFr, String acronymEn, String acronymnFr) {
		this.setNameEn(nameEn);
		this.setNameFr(nameFr);
		this.setAcronymEn(acronymEn);
		this.setAcronymFr(acronymnFr);
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

	public String getAcronymFr() {
		return acronymFr;
	}

	public void setAcronymFr(String acronymFr) {
		this.acronymFr = acronymFr;
	}

	public String getAcronymEn() {
		return acronymEn;
	}

	public void setAcronymEn(String acronymEn) {
		this.acronymEn = acronymEn;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public Set<FundingOpportunity> getFundingOpportunities() {
		return fundingOpportunities;
	}

	public void setFundingOpportunities(Set<FundingOpportunity> fundingOpportunities) {
		this.fundingOpportunities = fundingOpportunities;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		
		return id != null && id.equals(((Agency) obj).getId());
	}
	
	@Override
	public int hashCode() {
		return 2020;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Agency [id=");
		builder.append(id);
		builder.append(", nameEn=");
		builder.append(nameEn);
		builder.append(", nameFr=");
		builder.append(nameFr);
		builder.append(", acronymFr=");
		builder.append(acronymFr);
		builder.append(", acronymEn=");
		builder.append(acronymEn);
		builder.append("]");
		return builder.toString();
	}

}
