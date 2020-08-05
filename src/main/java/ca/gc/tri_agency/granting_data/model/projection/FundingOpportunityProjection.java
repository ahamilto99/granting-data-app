package ca.gc.tri_agency.granting_data.model.projection;

public interface FundingOpportunityProjection {
	
	Long getId();
	
	String getNameEn();
	
	String getNameFr();
	
	String getFundingType();
	
	String getFrequency();
	
	String getPartnerOrg();
	
	Long getBusinessUnitId();
	
	String getBusinessUnitNameEn();

	String getBusinessUnitNameFr();
	
	String getAgencyAcronymEn();		// for /browse/viewFO
	
	String getAgencyAcronymFr();		// for /browse/viewFO
	
	Long getGrantingStageId();		// for Golden List table
	
	String getGrantingSystemAcronym();	// for Golden List table

}
