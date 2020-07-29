package ca.gc.tri_agency.granting_data.model.dto;

public class AppPartEdiAuthorizedDto {

	/*
	 * This DTO acts as a security mechanism for the EDI data of an ApplicationParticipation.
	 * When the query for this DTO is executed, the ediAuthorized field is set to false
	 * and a separate query returns the id value for each ApplicationParticipation
	 * that the current user is EDI authorized for. A method will loop through the id query
	 * and set the matching DTO's ediAuthorized field to true. We then use this information
	 * to allow the user to only be able to link to the EDI data for those ApplicationParticpations.
	 */
	
	private Long id;

	private String applId;
	
	private String programId;

	private String familyName;

	private String firstName;

	private String roleEn;

	private String roleFr;

	private String organizationNameEn;

	private String organizationNameFr;

	// unless user is an admin, he/she can only see the AppPart EDI data if authorized
	private boolean ediAuthorized;

	public AppPartEdiAuthorizedDto(Long id, String applId, String programId, String familyName, String firstName, String roleEn, String roleFr,
			String organziationNameEn, String organizationNameFr, boolean ediAuthorized) {
		this.id = id;
		this.applId = applId;
		this.programId = programId;
		this.familyName = familyName;
		this.firstName = firstName;
		this.roleEn = roleEn;
		this.roleFr = roleFr;
		this.organizationNameEn = organziationNameEn;
		this.organizationNameFr = organizationNameFr;
		this.ediAuthorized = ediAuthorized;
	}

	public Long getId() {
		return id;
	}

	public String getApplId() {
		return applId;
	}
	
	public String getProgramId() {
		return programId;
	}

	public String getFamilyName() {
		return familyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getRoleEn() {
		return roleEn;
	}

	public String getRoleFr() {
		return roleFr;
	}

	public String getOrganizationNameEn() {
		return organizationNameEn;
	}

	public String getOrganizationNameFr() {
		return organizationNameFr;
	}

	public boolean getEdiAuthorized() {
		return ediAuthorized;
	}

}
