package ca.gc.tri_agency.granting_data.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import ca.gc.tri_agency.granting_data.model.util.LocalizedParametersModel;

@Entity
public class VisibleMinority implements LocalizedParametersModel {

	@Id
	@SequenceGenerator(name = "SEQ_VISIBLE_MINORITY", sequenceName = "SEQ_VISIBLE_MINORITY", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VISIBLE_MINORITY")
	public Long id;
	
	private String nameEn;
	
	private String nameFr;
	
	@ManyToMany(mappedBy = "visibleMinorities")
	private Set<ApplicationParticipation> applicationParticipations = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<ApplicationParticipation> getApplicationParticipations() {
		return applicationParticipations;
	}

	public void setApplicationParticipations(Set<ApplicationParticipation> applicationParticipations) {
		this.applicationParticipations = applicationParticipations;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		
		return id != null && id.equals(((VisibleMinority) obj).id);
	}
	
	@Override
	public int hashCode() {
		return 2020;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VisibleMinority [id=");
		builder.append(id);
		builder.append(", nameEn=");
		builder.append(nameEn);
		builder.append(", nameFr=");
		builder.append(nameFr);
		builder.append("]");
		return builder.toString();
	}
	
}































