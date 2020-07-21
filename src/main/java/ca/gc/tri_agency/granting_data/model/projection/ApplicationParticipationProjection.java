package ca.gc.tri_agency.granting_data.model.projection;

public interface ApplicationParticipationProjection {
	
	Long getId();
	
	String getApplId();
	
	String getFamilyName();
	
	String getFirstName();
	
	String getRoleEn();
	
	String getRoleFr();
	
	String getOrganizationNameEn();

	String getOrganizationNameFr();
	
	// used verify that only a Program Officer can set AppPart data for his/her BU
	Long getUserRoleId();

}
