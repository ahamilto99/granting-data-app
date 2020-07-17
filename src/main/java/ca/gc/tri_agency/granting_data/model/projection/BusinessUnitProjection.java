package ca.gc.tri_agency.granting_data.model.projection;

public interface BusinessUnitProjection {
	
	Long getId();
	
	String getNameEn();
	
	String getNameFr();
	
	String getAcronymEn();
	
	String getAcronymFr();
	
	Long getNumIdigenousApps(); 	// EDI data
	
	Long getNumVisMinorityApps();	// EDI data
	
	Long getNumDisabledApps();	// EDI data
	
	Long getNumFemaleApps();	// EDI data
	
	Long getNumMaleApps();		// EDI data
	
	Long getNumNonBinaryApps();	// EDI data

}
