package ca.gc.tri_agency.granting_data.model.projection;

public interface ApplicationParticipationProjection {
	
	Long getId();
	
	String getApplId();
	
	String getProgramId();
	
	String getFamilyName();
	
	String getFirstName();
	
	String getRoleEn();
	
	String getRoleFr();
	
	String getOrganizationNameEn();

	String getOrganizationNameFr();
	
}
