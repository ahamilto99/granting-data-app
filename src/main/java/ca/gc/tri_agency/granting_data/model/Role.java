package ca.gc.tri_agency.granting_data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import ca.gc.tri_agency.granting_data.model.util.LocalizedParametersModel;

@Entity
public class Role implements LocalizedParametersModel{

	@Id
	@SequenceGenerator(name = "SEQ_ROLE", sequenceName = "SEQ_ROLE", initialValue = 1, allocationSize = 1) 
	@GeneratedValue(generator = "SEQ_ROLE", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String nameEn;
	
	private String nameFr;
	
	public Role() {
	}

	public Role(String nameEn, String nameFr) {
		this.nameEn = nameEn;
		this.nameFr = nameFr;
	}

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
	
	
}
