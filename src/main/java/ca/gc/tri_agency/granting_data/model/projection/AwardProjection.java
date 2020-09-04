package ca.gc.tri_agency.granting_data.model.projection;

public interface AwardProjection {
	
	Long getId();
	
	Double getAwardedAmt();
	
	Long getFundingYr();
	
	String getApplId();
	
	String getFamilyName();
	
	String getFirstName();
	
	String getRoleCode();
	
	String getRoleEn();
	
	String getRoleFr();
	
	Double getRequestedAmt();
	
	String getProgramId();
	
	String getProgramEn();
	
	String getProgramFr();

}
