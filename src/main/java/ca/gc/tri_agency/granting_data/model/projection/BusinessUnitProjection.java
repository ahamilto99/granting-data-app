package ca.gc.tri_agency.granting_data.model.projection;

public interface BusinessUnitProjection {
	
	Long getId();
	
	String getNameEn();
	
	String getNameFr();
	
	String getAcronymEn();
	
	String getAcronymFr();
	
	Long getAgencyId();
	
	String getAgencyEn();

	String getAgencyFr();
	
	Long getMemRoleId();
	
	String getMemRoleLogin();
	
	String getMemRoleEn();

	String getMemRoleFr();
	
	Boolean getMemRoleEdiAuthorized();
	
}
