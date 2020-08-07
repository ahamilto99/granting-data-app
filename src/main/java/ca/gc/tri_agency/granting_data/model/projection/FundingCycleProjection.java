package ca.gc.tri_agency.granting_data.model.projection;

import java.time.LocalDate;

public interface FundingCycleProjection {

	Long getId();
	
	Long getNumAppsExpected();

	Boolean getIsOpenEnded();
	
	LocalDate getStartDate();
	
	LocalDate getEndDate();
	
	LocalDate getStartDateNOI();
	
	LocalDate getEndDateNOI();
	
	LocalDate getStartDateLOI();
	
	LocalDate getEndDateLOI();
	
	Long getFundingOpportunityId();

	String getFundingOpportunityNameEn();
	
	String getFundingOpportunityNameFr();
	
	Long getFiscalYearId();
	
	Long getFiscalYear();
}
