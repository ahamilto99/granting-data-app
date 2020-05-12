package ca.gc.tri_agency.granting_data.model;

import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;

import ca.gc.tri_agency.granting_data.model.util.LocalizedParametersModel;

@Entity
public class ApplicationParticipation implements LocalizedParametersModel {

	@Id
	@SequenceGenerator(name = "SEQ_APPLICATION_PARTICIPATION", sequenceName = "SEQ_APPLICATION_PARTICIPATION", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQ_APPLICATION_PARTICIPATION")
	private Long id;

	private String applicationIdentifier;

	private String applicationId;

	private Long competitionYear;

	private String programId;

	private String programEn;

	private String programFr;

	@CreatedDate
	private Instant createDate;

	private String roleCode;

	private String roleEn;

	private String roleFr;

	private String familyName;

	private String givenName;

	private Long personIdentifier;

	private String organizationId;

	private String organizationNameEn;

	private String organizationNameFr;

	@Column(name = "freeform_address_1")
	private String freeformAddress1;

	@Column(name = "freeform_address_2")
	private String freeformAddress2;

	@Column(name = "freeform_address_3")
	private String freeformAddress3;

	@Column(name = "freeform_address_4")
	private String freeformAddress4;

	private String municipality;

	@Size(max = 7)
	private String postalZipCode;

	@Size(max = 3)
	private String provinceStateCode;

	private String country;
	
	private Boolean ediNotApplicable = false;
	
	private Boolean dateOfBirthIndicator = false;
	
	private LocalDate dateOfBirth;
	
	@Column(name = "gender_selection_1")
	private Long genderSelection1;
	
	private Long indIdentityResponse;
	
	private Boolean indIdentityPrefNotTo = false;
	
	@Column(name = "ind_identity_selection_1")
	private Long indIdentitySelection1;

	@Column(name = "ind_identity_selection_2")
	private Long indIdentitySelection2;

	@Column(name = "ind_identity_selection_3")
	private Long indIdentitySelection3;
	
	@Column(name = "disability_selection_1")
	private Long disabilitySelection1;
	
	private Boolean visibleMinPrefNotTo = false;
	
	@Column(name = "visible_min_selection_1")
	private Long visibleMinSelection1;
	
	@Column(name = "visible_min_selection_2")
	private Long visibleMinSelection2;
	
	@Column(name = "visible_min_selection_3")
	private Long visibleMinSelection3;
	
	@Column(name = "visible_min_selection_4")
	private Long visibleMinSelection4;
	
	@Column(name = "visible_min_selection_5")
	private Long visibleMinSelection5;
	
	@Column(name = "visible_min_selection_6")
	private Long visibleMinSelection6;
	
	@Column(name = "visible_min_selection_7")
	private Long visibleMinSelection7;
	
	@Column(name = "visible_min_selection_8")
	private Long visibleMinSelection8;
	
	@Column(name = "visible_min_selection_9")
	private Long visibleMinSelection9;
	
	@Column(name = "visible_min_selection_10")
	private Long visibleMinSelection10;
	
	@Column(name = "visible_min_selection_11")
	private Long visibleMinSelection11;
	
	private String visibleMinOther;
	
	@Size(max = 3)
	private String createUserId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplicationIdentifier() {
		return applicationIdentifier;
	}

	public void setApplicationIdentifier(String applicationIdentifier) {
		this.applicationIdentifier = applicationIdentifier;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public Long getCompetitionYear() {
		return competitionYear;
	}

	public void setCompetitionYear(Long competitionYear) {
		this.competitionYear = competitionYear;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public String getProgramEn() {
		return programEn;
	}

	public void setProgramEn(String programEn) {
		this.programEn = programEn;
	}

	public String getProgramFr() {
		return programFr;
	}

	public void setProgramFr(String programFr) {
		this.programFr = programFr;
	}

	public Instant getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Instant createDate) {
		this.createDate = createDate;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleEn() {
		return roleEn;
	}

	public void setRoleEn(String roleEn) {
		this.roleEn = roleEn;
	}

	public String getRoleFr() {
		return roleFr;
	}

	public void setRoleFr(String roleFr) {
		this.roleFr = roleFr;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public Long getPersonIdentifier() {
		return personIdentifier;
	}

	public void setPersonIdentifier(Long personIdentifier) {
		this.personIdentifier = personIdentifier;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationNameEn() {
		return organizationNameEn;
	}

	public void setOrganizationNameEn(String organizationNameEn) {
		this.organizationNameEn = organizationNameEn;
	}

	public String getOrganizationNameFr() {
		return organizationNameFr;
	}

	public void setOrganizationNameFr(String organizationNameFr) {
		this.organizationNameFr = organizationNameFr;
	}

	public String getFreeformAddress1() {
		return freeformAddress1;
	}

	public void setFreeformAddress1(String freeformAddress1) {
		this.freeformAddress1 = freeformAddress1;
	}

	public String getFreeformAddress2() {
		return freeformAddress2;
	}

	public void setFreeformAddress2(String freeformAddress2) {
		this.freeformAddress2 = freeformAddress2;
	}

	public String getFreeformAddress3() {
		return freeformAddress3;
	}

	public void setFreeformAddress3(String freeformAddress3) {
		this.freeformAddress3 = freeformAddress3;
	}

	public String getFreeformAddress4() {
		return freeformAddress4;
	}

	public void setFreeformAddress4(String freeformAddress4) {
		this.freeformAddress4 = freeformAddress4;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public String getPostalZipCode() {
		return postalZipCode;
	}

	public void setPostalZipCode(String postalZipCode) {
		this.postalZipCode = postalZipCode;
	}

	public String getProvinceStateCode() {
		return provinceStateCode;
	}

	public void setProvinceStateCode(String provinceStateCode) {
		this.provinceStateCode = provinceStateCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Boolean getEdiNotApplicable() {
		return ediNotApplicable;
	}

	public void setEdiNotApplicable(Boolean ediNotApplicable) {
		this.ediNotApplicable = ediNotApplicable;
	}

	public Boolean getDateOfBirthIndicator() {
		return dateOfBirthIndicator;
	}

	public void setDateOfBirthIndicator(Boolean dateOfBirthIndicator) {
		this.dateOfBirthIndicator = dateOfBirthIndicator;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Long getGenderSelection1() {
		return genderSelection1;
	}

	public void setGenderSelection1(Long genderSelection1) {
		this.genderSelection1 = genderSelection1;
	}

	public Long getIndIdentityResponse() {
		return indIdentityResponse;
	}

	public void setIndIdentityResponse(Long indIdentityResponse) {
		this.indIdentityResponse = indIdentityResponse;
	}

	public Boolean getIndIdentityPrefNotTo() {
		return indIdentityPrefNotTo;
	}

	public void setIndIdentityPrefNotTo(Boolean indIdentityPrefNotTo) {
		this.indIdentityPrefNotTo = indIdentityPrefNotTo;
	}

	public Long getIndIdentitySelection1() {
		return indIdentitySelection1;
	}

	public void setIndIdentitySelection1(Long indIdentitySelection1) {
		this.indIdentitySelection1 = indIdentitySelection1;
	}

	public Long getIndIdentitySelection2() {
		return indIdentitySelection2;
	}

	public void setIndIdentitySelection2(Long indIdentitySelection2) {
		this.indIdentitySelection2 = indIdentitySelection2;
	}

	public Long getIndIdentitySelection3() {
		return indIdentitySelection3;
	}

	public void setIndIdentitySelection3(Long indIdentitySelection3) {
		this.indIdentitySelection3 = indIdentitySelection3;
	}

	public Long getDisabilitySelection1() {
		return disabilitySelection1;
	}

	public void setDisabilitySelection1(Long disabilitySelection1) {
		this.disabilitySelection1 = disabilitySelection1;
	}

	public Boolean getVisibleMinPrefNotTo() {
		return visibleMinPrefNotTo;
	}

	public void setVisibleMinPrefNotTo(Boolean visibleMinPrefNotTo) {
		this.visibleMinPrefNotTo = visibleMinPrefNotTo;
	}

	public Long getVisibleMinSelection1() {
		return visibleMinSelection1;
	}

	public void setVisibleMinSelection1(Long visibleMinSelection1) {
		this.visibleMinSelection1 = visibleMinSelection1;
	}

	public Long getVisibleMinSelection2() {
		return visibleMinSelection2;
	}

	public void setVisibleMinSelection2(Long visibleMinSelection2) {
		this.visibleMinSelection2 = visibleMinSelection2;
	}

	public Long getVisibleMinSelection3() {
		return visibleMinSelection3;
	}

	public void setVisibleMinSelection3(Long visibleMinSelection3) {
		this.visibleMinSelection3 = visibleMinSelection3;
	}

	public Long getVisibleMinSelection4() {
		return visibleMinSelection4;
	}

	public void setVisibleMinSelection4(Long visibleMinSelection4) {
		this.visibleMinSelection4 = visibleMinSelection4;
	}

	public Long getVisibleMinSelection5() {
		return visibleMinSelection5;
	}

	public void setVisibleMinSelection5(Long visibleMinSelection5) {
		this.visibleMinSelection5 = visibleMinSelection5;
	}

	public Long getVisibleMinSelection6() {
		return visibleMinSelection6;
	}

	public void setVisibleMinSelection6(Long visibleMinSelection6) {
		this.visibleMinSelection6 = visibleMinSelection6;
	}

	public Long getVisibleMinSelection7() {
		return visibleMinSelection7;
	}

	public void setVisibleMinSelection7(Long visibleMinSelection7) {
		this.visibleMinSelection7 = visibleMinSelection7;
	}

	public Long getVisibleMinSelection8() {
		return visibleMinSelection8;
	}

	public void setVisibleMinSelection8(Long visibleMinSelection8) {
		this.visibleMinSelection8 = visibleMinSelection8;
	}

	public Long getVisibleMinSelection9() {
		return visibleMinSelection9;
	}

	public void setVisibleMinSelection9(Long visibleMinSelection9) {
		this.visibleMinSelection9 = visibleMinSelection9;
	}

	public Long getVisibleMinSelection10() {
		return visibleMinSelection10;
	}

	public void setVisibleMinSelection10(Long visibleMinSelection10) {
		this.visibleMinSelection10 = visibleMinSelection10;
	}

	public Long getVisibleMinSelection11() {
		return visibleMinSelection11;
	}

	public void setVisibleMinSelection11(Long visibleMinSelection11) {
		this.visibleMinSelection11 = visibleMinSelection11;
	}

	public String getVisibleMinOther() {
		return visibleMinOther;
	}

	public void setVisibleMinOther(String visibleMinOther) {
		this.visibleMinOther = visibleMinOther;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}


}
