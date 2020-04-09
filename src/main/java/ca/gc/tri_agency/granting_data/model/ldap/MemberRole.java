package ca.gc.tri_agency.granting_data.model.ldap;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import ca.gc.tri_agency.granting_data.model.BusinessUnit;

@Entity
public class MemberRole {

	@Id
	@SequenceGenerator(name = "SEQ_MEMBER_ROLE", sequenceName = "SEQ_MEMBER_ROLE", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MEMBER_ROLE")
	private Long id;

	@Transient
	private ADUser adUser;

	private String fullName;

	private String lastName;

	private String email;

	@Enumerated(EnumType.STRING)
	private Role role;

	@ManyToOne
	@JoinColumn(name = "business_unit_id")
	@NotNull
	private BusinessUnit bu;

	public MemberRole() {
	}

	public MemberRole(ADUser adUser, Role role, @NotNull BusinessUnit bu) {
		this.adUser = adUser;
		fullName = adUser.getFullName();
		lastName = adUser.getLastName();
		email = adUser.getEmail();
		this.role = role;
		this.bu = bu;
	}

	public Long getId() {
		return id;
	}

	public ADUser getAdUser() {
		return adUser;
	}

	public String getFullName() {
		return fullName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public Role getRole() {
		return role;
	}

	public BusinessUnit getBusinessUnit() {
		return bu;
	}

}
