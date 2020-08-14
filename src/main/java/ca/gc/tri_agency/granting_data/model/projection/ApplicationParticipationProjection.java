package ca.gc.tri_agency.granting_data.model.projection;

import java.time.Instant;
import java.time.LocalDate;

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

	LocalDate getDateOfBirth();

	String getDisability(); // EDI data

	String getGenderEn(); // EDI data

	String getGenderFr(); // EDI data

	Long getIndIdentityId(); // EDI data

	String getIndIdentityEn(); // EDI data

	String getIndIdentityFr(); // EDI data

	Long getVisMinorityId(); // EDI data

	String getVisMinorityEn(); // EDI data

	String getVisMinorityFr(); // EDI data

}
