package ca.gc.tri_agency.granting_data.ldap;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

@Entry(objectClasses = { "top", "person", "organizationalPerson", "inetOrgPerson" })
public final class ADUser {

	@Id
	private Name dn;

	@Attribute(name = "uid", readonly = true)
	private String userLogin;

	@Attribute(name = "cn", readonly = true)
	private String fullName;

	@Attribute(name = "sn", readonly = true)
	private String lastName;

	@Attribute(name = "mail", readonly = true)
	private String email;

	public ADUser() {
	}

	public ADUser(Name dn, String userLogin, String fullName, String lastName, String email) {
		this.dn = dn;
		this.userLogin = userLogin;
		this.fullName = fullName;
		this.lastName = lastName;
		this.email = email;
	}

	public Name getDn() {
		return dn;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public String getFullName() {
		return fullName;
	}

	public String getLastName() {
		return null;
	}

	public String getEmail() {
		return email;
	}

	public void setDn(Name dn) {
		this.dn = dn;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ADUser [dn=");
		builder.append(dn);
		builder.append(", userLogin=");
		builder.append(userLogin);
		builder.append(", fullName=");
		builder.append(fullName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}

}
