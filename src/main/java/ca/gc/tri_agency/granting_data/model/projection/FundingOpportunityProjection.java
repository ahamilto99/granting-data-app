package ca.gc.tri_agency.granting_data.model.projection;

public interface FundingOpportunityProjection {
	
	Long getId();
	
	String getNameEn();
	
	String getNameFr();
	
	String getBusinessUnitNameEn();

	String getBusinessUnitNameFr();
	
	Long getGrantingStageId();		// for Golden List table
	
	String getGrantingSystemAcronym();	// for Golden List table

}
