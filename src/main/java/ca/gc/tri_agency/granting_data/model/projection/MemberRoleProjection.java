package ca.gc.tri_agency.granting_data.model.projection;

public interface MemberRoleProjection {
	
	Long getId();
	
	String getUserLogin();
	
	Boolean getEdiAuthorized();
	
	Long getRoleId();
	
	Long getBusinessUnitId();
	
	String getBusinessUnitNameEn();
	
	String getBusinessUnitNameFr();
	
	String getBusinessUnitAcronymEn();
	
	String getBusinessUnitAcronymFr();
	
	String getRoleEn();

	String getRoleFr();
}
