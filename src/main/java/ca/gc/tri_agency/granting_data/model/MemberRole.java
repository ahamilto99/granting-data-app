package ca.gc.tri_agency.granting_data.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class MemberRole {

	@Id
	@SequenceGenerator(name = "SEQ_MEMBER_ROLE", sequenceName = "SEQ_MEMBER_ROLE", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MEMBER_ROLE")
	private Long id;

	@Size(min = 3, max = 3)
	private String userLogin;

	@ManyToOne
	@JoinColumn(name = "role_id")
	@NotNull
	private Role role;

	@ManyToOne
	@JoinColumn(name = "business_unit_id")
	@NotNull
	private BusinessUnit businessUnit;

	public MemberRole() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public BusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberRole [id=");
		builder.append(id);
//		builder.append(", userLogin=");
//		builder.append(userLogin);
		builder.append(", role=");
		builder.append(role);
//		builder.append(", businessUnit=");
//		builder.append(businessUnit.getName());
		builder.append("]");
		return builder.toString();
	}

	
}