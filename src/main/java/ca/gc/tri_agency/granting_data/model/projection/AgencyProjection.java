package ca.gc.tri_agency.granting_data.model.projection;

public interface AgencyProjection {

	Long getId();
	
	String getNameEn();
	
	String getNameFr();
	
	String getAcronymEn();

	String getAcronymFr();
	
	Long getFoId();			// for /browse/viewAgency
	
	String getFoNameEn();		// for /browse/viewAgency
	
	String getFoNameFr();		// for /browse/viewAgency
	
	String getFoFundingType();	// for /browse/viewAgency
	
	String getFoFrequency();	// for /browse/viewAgency
	
	Boolean getFoIsJointInitiative();	// for /browse/viewAgency
	
	Long getBuId();			// for /browse/viewAgency
	
	String getBuNameEn();		// for /browse/viewAgency
		
	String getBuNameFr();		// for /browse/viewAgency
	
	String getBuAcronymEn();	// for /browse/viewAgency
	
	String getBuAcronymFr();	// for /browse/viewAgency
}
