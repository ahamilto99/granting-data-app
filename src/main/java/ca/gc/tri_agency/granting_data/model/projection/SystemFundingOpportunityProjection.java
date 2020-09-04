package ca.gc.tri_agency.granting_data.model.projection;

public interface SystemFundingOpportunityProjection {

	Long getId();
	
	String getExtId();
	
	String getNameEn();
	
	String getNameFr();
	
	Long getFundingOpportunityId();

	String getFundingOpportunityEn();
	
	String getFundingOpportunityFr();
	
	Long getGrantingSystemId();
	
	String getGrantingSystemEn();
	
	String getGrantingSystemFr();
}
