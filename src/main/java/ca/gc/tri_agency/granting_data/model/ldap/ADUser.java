package ca.gc.tri_agency.granting_data.model.ldap;

public class ADUser {

	private String email;

	private String fullName;
	private String lastName;
	private String uid;

	public ADUser() {
	}

	public ADUser(String fullName, String lastName, String uid) {
		this.fullName = fullName;
		this.lastName = lastName;
		this.uid = uid;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "Person{" + "fullName='" + fullName + '\'' + ", lastName='" + lastName + '\'' + '}';
	}

}
