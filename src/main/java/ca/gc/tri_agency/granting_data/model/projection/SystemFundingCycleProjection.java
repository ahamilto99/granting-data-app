package ca.gc.tri_agency.granting_data.model.projection;

public interface SystemFundingCycleProjection {

	Long getId();

	String getExtId();

	Long getFiscalYear();

	Long getNumAppsReceived();

	String getSystemFundingOpportunityNameEn(); // for /browse/viewFO

	String getSystemFundingOpportunityNameFr(); // for /browse/viewFO
}
