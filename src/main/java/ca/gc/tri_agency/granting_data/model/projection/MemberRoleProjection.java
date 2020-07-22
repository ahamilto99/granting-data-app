package ca.gc.tri_agency.granting_data.model.projection;

public interface MemberRoleProjection {
	
	Long getId();
	
	String getUserLogin();
	
	Boolean getEdiAuthorized();
	
	Long getRoleId();
	
	Long getBusinessUnitId();
	
}
