package ca.gc.tri_agency.granting_data.model.projection;

import java.time.Instant;

public interface ApplicationParticipationProjection {
	
	Long getId();
	
	String getApplId();
	
	String getProgramId();
	
	String getFamilyName();
	
	String getFirstName();
	
	String getRoleEn();
	
	String getRoleFr();
	
	String getOrganizationNameEn();

	String getOrganizationNameFr();
	
	String getProgramNameEn();

	String getProgramNameFr();
	
	Long getCompetitionYear();
	
	String getApplicationIdentifier();
	
	Instant getCreatedDate();
	
	Long getPersonIdentifier();
	
	String getCity();
	
	String getProvinceState();
	
	String getCountry();
	
}
