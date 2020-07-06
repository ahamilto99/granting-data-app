package ca.gc.tri_agency.granting_data.model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ca.gc.tri_agency.granting_data.model.util.LocalizedParametersModel;

@Entity
public class ApplicationParticipation implements LocalizedParametersModel {

	@Id
	@SequenceGenerator(name = "SEQ_APPLICATION_PARTICIPATION", sequenceName = "SEQ_APPLICATION_PARTICIPATION", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_APPLICATION_PARTICIPATION")
	private Long id;

	@Column(name = "application_identifier")
	private String applicationIdentifier;

	@Column(name = "appl_id")
	private String applId;

	private Long competitionYear;

	private String programId;

	private String programEn;

	private String programFr;

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

	@ManyToOne
	@JoinColumn(name = "gender_id")
	private Gender gender;

	private Boolean indIdentityPrefNotTo = false;

	private String disabilityResponse;

	private Boolean visibleMinPrefNotTo = false;

	@Size(max = 3)
	private String createUserId;

	// TODO: REMOVE AFTER DEMO
	@NotNull
	private Boolean isDeadlinePassed = false;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "app_part_indigenous_identity", joinColumns = @JoinColumn(name = "application_participation_id"), inverseJoinColumns = @JoinColumn(name = "indigenous_identity_id"))
	private Set<IndigenousIdentity> indigenousIdentities = new HashSet<>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "app_part_visible_minority", joinColumns = @JoinColumn(name = "application_participation_id"), inverseJoinColumns = @JoinColumn(name = "visible_minority_id"))
	private Set<VisibleMinority> visibleMinorities = new HashSet<>();

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

	public String getApplId() {
		return applId;
	}

	public void setApplId(String applId) {
		this.applId = applId;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Boolean getIndIdentityPrefNotTo() {
		return indIdentityPrefNotTo;
	}

	public void setIndIdentityPrefNotTo(Boolean indIdentityPrefNotTo) {
		this.indIdentityPrefNotTo = indIdentityPrefNotTo;
	}

	public String getDisabilityResponse() {
		return disabilityResponse;
	}

	public void setDisabilityResponse(String disabilityResponse) {
		this.disabilityResponse = disabilityResponse;
	}

	public Boolean getVisibleMinPrefNotTo() {
		return visibleMinPrefNotTo;
	}

	public void setVisibleMinPrefNotTo(Boolean visibleMinPrefNotTo) {
		this.visibleMinPrefNotTo = visibleMinPrefNotTo;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Boolean getIsDeadlinePassed() {
		return isDeadlinePassed;
	}

	public void setIsDeadlinePassed(Boolean isDeadlinePassed) {
		this.isDeadlinePassed = isDeadlinePassed;
	}

	public Set<IndigenousIdentity> getIndigenousIdentities() {
		return indigenousIdentities;
	}

	public void setIndigenousIdentities(Set<IndigenousIdentity> indigenousIdentities) {
		this.indigenousIdentities = indigenousIdentities;
	}

	public Set<VisibleMinority> getVisibleMinorities() {
		return visibleMinorities;
	}

	public void setVisibleMinorities(Set<VisibleMinority> visibleMinorities) {
		this.visibleMinorities = visibleMinorities;
	}

	public void addIndigenousIdentity(IndigenousIdentity indigenousIdentity) {
		indigenousIdentities.add(indigenousIdentity);
		indigenousIdentity.getApplicationParticipations().add(this);
	}

	public void removeIndigenousIdentity(IndigenousIdentity indigenousIdentity) {
		indigenousIdentities.remove(indigenousIdentity);
		indigenousIdentity.getApplicationParticipations().remove(this);
	}

	public void removeIndigenousIdentities() {
		Iterator<IndigenousIdentity> iter = indigenousIdentities.iterator();
		iter.forEachRemaining(identity -> {
			identity.getApplicationParticipations().remove(this);
			iter.remove();
		});
	}

	public void addVisibleMinority(VisibleMinority visibleMinority) {
		visibleMinorities.add(visibleMinority);
		visibleMinority.getApplicationParticipations().add(this);
	}

	public void removeVisibleMinority(VisibleMinority visibleMinority) {
		visibleMinorities.remove(visibleMinority);
		visibleMinority.getApplicationParticipations().remove(this);
	}

	public void removeVisibleMinorities() {
		Iterator<VisibleMinority> iter = visibleMinorities.iterator();
		iter.forEachRemaining(minority -> {
			minority.getApplicationParticipations().remove(this);
			iter.remove();
		});
	}

	@Override
	public int hashCode() {
		return 2020;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		ApplicationParticipation other = (ApplicationParticipation) obj;

		return id != null && id.equals(other.getId());

	}

}
