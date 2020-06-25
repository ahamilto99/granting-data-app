package ca.gc.tri_agency.granting_data.service.impl;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.javafaker.Faker;

import ca.gc.tri_agency.granting_data.model.ApplicationParticipation;
import ca.gc.tri_agency.granting_data.model.GrantingSystem;
import ca.gc.tri_agency.granting_data.model.MemberRole;
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.repo.ApplicationParticipationRepository;
import ca.gc.tri_agency.granting_data.security.SecurityUtils;
import ca.gc.tri_agency.granting_data.service.ApplicationParticipationService;
import ca.gc.tri_agency.granting_data.service.MemberRoleService;
import ca.gc.tri_agency.granting_data.service.SystemFundingOpportunityService;

@Service
public class ApplicationParticipationServiceImpl implements ApplicationParticipationService {

	Map<GrantingSystem, ReferenceBean[]> roleMap;

	private SecureRandom sRand = new SecureRandom();

	private ApplicationParticipationRepository appParticipationRepo;

	private SystemFundingOpportunityService sfoService;

	private MemberRoleService mrService;

	private static int applIdIncrementer = 0;

	@Autowired
	public ApplicationParticipationServiceImpl(ApplicationParticipationRepository appParticipationRepo,
			SystemFundingOpportunityService sfoService, MemberRoleService mrService) {
		this.appParticipationRepo = appParticipationRepo;
		this.sfoService = sfoService;
		this.mrService = mrService;
	}

	@Override
	public List<ApplicationParticipation> generateTestAppParticipations(SystemFundingOpportunity sfo, Instant createDate,
			long maxApplications, long maxParticipants) {
		GrantingSystem gs = sfo.getGrantingSystem();
		List<ApplicationParticipation> appPartList = new ArrayList<>();

		int maxApps = sRand.nextInt((int) maxApplications); // nextLong() does not take in a bounds
		for (int i = 0; i < maxApps; i++) {

			String appId = generateTestAppId(gs);
			String applicationIdentifier = generateTestApplicationIdentifier(appId, gs);

			int maxParts = sRand.nextInt((int) maxParticipants);
			for (int j = 0; j < maxParts; j++) {

				ApplicationParticipation app = new ApplicationParticipation();
				app.setApplId(appId + String.format("%04d", ++applIdIncrementer));
				app.setApplicationIdentifier(applicationIdentifier);

				app.setProgramId(sfo.getExtId());
				app.setProgramEn(sfo.getNameEn());
				app.setProgramFr(sfo.getNameFr());

				app.setPersonIdentifier(sRand.nextInt(900_000_000) + 100_000_000L);

				app.setCreateDate(createDate);

				fillInRandomRole(app, gs);
				fillInRandomOrg(app, gs);
				fillInRandomPerson(app, gs);

				int day = generateRandNumBtw(1, 28);
				int month = generateRandNumBtw(1, 12);
				int year = generateRandNumBtw(1940, 55);
				app.setDateOfBirth(LocalDate.of(year, month, day));
				
				appPartList.add(app);
			}

		}
		return appPartList;
	}

	@Override
	public String generateTestAppId(GrantingSystem system) {
		String acronym = system.getAcronym();
		if (acronym.equals("NAMIS")) {
			return String.format("%d", sRand.nextInt(10) + 40);
		} else if (acronym.equals("AMIS")) {
			return Integer.toString(sRand.nextInt(10) + 40);
		} else if (acronym.equals("CRM")) {
			return String.format("%d-%d-%02d", sRand.nextInt(500) + 100, sRand.nextInt(6) + 2017, sRand.nextInt(100));
		}
		return null;
	}

	@Override
	public String generateTestApplicationIdentifier(String appId, GrantingSystem system) {
		String acronym = system.getAcronym();
		if (acronym.equals("NAMIS")) {
			return String.format("%s-%d", appId, sRand.nextInt(6) + 2017);
		} else if (acronym.equals("AMIS")) {
			return String.format("%d-%d-%04d", sRand.nextInt(900) + 100, sRand.nextInt(6) + 2017, sRand.nextInt(3_000));
		} else if (acronym.equals("CRM")) {
			return String.format("%04X%04X-%04X-4%03X-%04X-%06X%06X", sRand.nextInt(65_536), sRand.nextInt(65_536),
					sRand.nextInt(65_536), sRand.nextInt(4_096), sRand.nextInt(65_536), sRand.nextInt(16_777_216),
					sRand.nextInt(16_777_216));
		}
		return null;
	}

	@Override
	public void fillInRandomRole(ApplicationParticipation app, GrantingSystem sys) {
		String[] roleCodeArr = { "1", "2", "147", "8878CE1D-6020-E211-AF1A-005056AD024F",
				"8F78CE1D-6020-E211-AF1A-005056AD024F", "A878CE1D-6020-E211-AF1A-005056AD024F",
				"A978CE1D-6020-E211-AF1A-005056AD024F", "A778CE1D-6020-E211-AF1A-005056AD024F",
				"8C78CE1D-6020-E211-AF1A-005056AD024F" };
		String[] roleEnArr = { "Scholarship Applicant", "Applicant", "Co-Applicant", "Collaborator", "Reader A", "Reader B",
				"Reader C" };
		String[] roleFrArr = { "Candidat à une bourse", "Candidat", "Applicant", "Co-Applicant", "Collaborator", "Lecteur A",
				"Lecteur B", "Lecteur C" };
		String acronym = sys.getAcronym();
		if (acronym.equals("NAMIS")) {
			int idx = sRand.nextInt(2);
			app.setRoleCode(roleCodeArr[idx]);
			app.setRoleEn(roleEnArr[idx]);
			app.setRoleFr(roleFrArr[idx]);
		} else if (acronym.equals("AMIS")) {
			app.setRoleCode(roleCodeArr[2]);
			app.setRoleEn(roleEnArr[1]);
			app.setRoleFr(roleFrArr[1]);
		} else if (acronym.equals("CRM")) {
			int idx = sRand.nextInt(6);
			app.setRoleCode(roleCodeArr[idx + 3]);
			app.setRoleEn(roleEnArr[idx + 1]);
			app.setRoleFr(roleFrArr[idx + 2]);
		}
	}

	@Override
	public void fillInRandomOrg(ApplicationParticipation app, GrantingSystem sys) {
		String acronym = sys.getAcronym();
		int idx = sRand.nextInt(6);
		if (acronym.equals("NAMIS")) {
			String[] idArr = { "27", "28", "24", "89", "43", "9" };
			String[] nameArr = { "McMaster University", "University of Ottawa", "Guelph University",
					"Memorial Univ. of Nfld", "Ryerson University", "University of Alberta" };
			app.setOrganizationId(idArr[idx]);
			app.setOrganizationNameEn(nameArr[idx]);
			app.setOrganizationNameFr(nameArr[idx]);
		} else if (acronym.equals("AMIS")) {
			String[] idArr = { "1240613", "3248462", "1591011", "1351411", "1350711", "1350511" };
			String[] nameEnArr = { "University of Quebec at Montreal",
					"Consolidation of Native Friendship Centers in Quebec",
					"University of Northern British Columbia", "York University", "University of Ottawa",
					"Laurentian University" };
			String[] nameFrArr = { "Université du Québec à Montréal",
					"Regroupement des centres d'amitié autochtones du Québec",
					"University of Northern British Columbia", "Université York", "Université d'Ottawa",
					"Université Laurentienne" };
			app.setOrganizationId(idArr[idx]);
			app.setOrganizationNameEn(nameEnArr[idx]);
			app.setOrganizationNameFr(nameFrArr[idx]);
		} else if (acronym.equals("CRM")) {
			String[] idArr = { "FDCA4C51-4421-E211-AEEA-005056AD550D", "9384544B-4421-E211-AEEA-005056AD550D",
					"260F4557-4421-E211-AEEA-005056AD550D", "DAFE633F-4421-E211-AEEA-005056AD550D",
					"02CB4C51-4421-E211-AEEA-005056AD550D", "28CA4C51-4421-E211-AEEA-005056AD550D" };
			String[] nameArr = { "University of Calgary: Psychology", "McMaster University: School of Social Work",
					"Wilfrid Laurier University: School of International Policy and Governance",
					"Sheridan College Institute of Technology and Advanced Learning: International Centre",
					"University of Ottawa: Criminology", "University of Saskatchewan: Philosophy" };
			app.setOrganizationId(idArr[idx]);
			app.setOrganizationNameEn(nameArr[idx]);
			app.setOrganizationNameFr(nameArr[idx]);
		}
	}

	@Override
	public void fillInRandomPerson(ApplicationParticipation app, GrantingSystem sys) {
		String acronym = sys.getAcronym();
		if (acronym.equals("NAMIS") || acronym.equals("AMIS") || acronym.equals("CRM")) {
			Faker nameFaker = new Faker();
			app.setFamilyName(nameFaker.name().lastName());
			app.setGivenName(nameFaker.name().firstName());
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<ApplicationParticipation> getAllowedRecords() {
		List<ApplicationParticipation> retval = null;
		if (SecurityUtils.hasRole("MDM ADMIN")) {
			retval = appParticipationRepo.findAll();
		} else {
			String username = SecurityUtils.getCurrentUsername();
			retval = appParticipationRepo.findAllowedRecords(SecurityUtils.getCurrentUsername());
		}
		return retval;
	}

	private class ReferenceBean {
		String id;
		String nameEn;
		String nameFr;

	}

	@Transactional
	@Override
	public long generateTestAppParicipationsForAllSystemFundingOpportunities() {
		List<ApplicationParticipation> participations = new ArrayList<ApplicationParticipation>();
		Instant inst = Instant.parse("2020-02-02T00:00:00.00Z");

		for (SystemFundingOpportunity sfo : sfoService.findAllSystemFundingOpportunities()) {
			participations.addAll(generateTestAppParticipations(sfo, inst, 3, 5));
		}

		generateEdiData(participations);

		participations = appParticipationRepo.saveAll(participations);

		return participations.size();
	}

	@Override
	public ApplicationParticipation findAppPartByApplId(String applId) {
		return appParticipationRepo.findByApplId(applId);
	}

	private List<ApplicationParticipation> generateEdiData(List<ApplicationParticipation> appParts) {
		setAppPartGender(appParts);
		setAppPartDisability(appParts);
		setAppPartEthnicity(appParts);
		setAppPartIndiginous(appParts);

		return appParts;
	}

	private List<ApplicationParticipation> setAppPartGender(List<ApplicationParticipation> appParts) {
		Collections.shuffle(appParts);

		int i = 0;
		for (; i < (int) (appParts.size() * 0.5); i++) {
			appParts.get(i).setGenderSelection("female");
		}
		for (; i < (int) (appParts.size() * 0.93); i++) {
			appParts.get(i).setGenderSelection("male");
		}
		for (; i < (int) appParts.size(); i++) {
			appParts.get(i).setGenderSelection("non-binary");
		}

		return appParts;
	}

	private List<ApplicationParticipation> setAppPartDisability(List<ApplicationParticipation> appParts) {
		Collections.shuffle(appParts);

		int i = 0;
		for (; i < (int) (appParts.size() * 0.04); i++) {
			appParts.get(i).setDisabilityResponse("physical");
		}
		for (; i < (int) (appParts.size() * 0.06); i++) {
			appParts.get(i).setDisabilityResponse("deaf");
		}
		for (; i < (int) (appParts.size() * 0.08); i++) {
			appParts.get(i).setDisabilityResponse("other");
		}
		for (; i < (int) (appParts.size() * 0.09); i++) {
			appParts.get(i).setDisabilityResponse("blind");
		}
		return appParts;
	}

	private List<ApplicationParticipation> setAppPartIndiginous(List<ApplicationParticipation> appParts) {
		Collections.shuffle(appParts);

		int i = 0;
		for (; i < (int) (appParts.size() * 0.05); i++) {
			appParts.get(i).setIndIdentityResponse("Métis");
		}
		for (; i < (int) (appParts.size() * 0.08); i++) {
			appParts.get(i).setIndIdentityResponse("Inuit");
		}
		for (; i < (int) (appParts.size() * 0.10); i++) {
			appParts.get(i).setIndIdentityResponse("Mi'kmaq");
		}
		for (; i < (int) (appParts.size() * 0.11); i++) {
			appParts.get(i).setIndIdentityResponse("Iroquois");
		}
		for (; i < (int) (appParts.size() * 0.12); i++) {
			appParts.get(i).setIndIdentityResponse("Haida");
		}
		for (; i < (int) (appParts.size() * 0.13); i++) {
			appParts.get(i).setIndIdentityResponse("Dalelh");
		}

		return appParts;
	}

	private List<ApplicationParticipation> setAppPartEthnicity(List<ApplicationParticipation> appParts) {
		Collections.shuffle(appParts);

		int i = 0;
		for (; i < (int) (appParts.size() * 0.06); i++) {
			appParts.get(i).setVisibleMinorityResponse("Latin American");
		}
		for (; i < (int) (appParts.size() * 0.12); i++) {
			appParts.get(i).setVisibleMinorityResponse("Caribbean");
		}
		for (; i < (int) (appParts.size() * 0.20); i++) {
			appParts.get(i).setVisibleMinorityResponse("Middle Eastern");
		}
		for (; i < (int) (appParts.size() * 0.24); i++) {
			appParts.get(i).setVisibleMinorityResponse("Central Asian");
		}
		for (; i < (int) (appParts.size() * 0.39); i++) {
			appParts.get(i).setVisibleMinorityResponse("East Asian");
		}
		for (; i < (int) (appParts.size() * 0.46); i++) {
			appParts.get(i).setVisibleMinorityResponse("South Asian");
		}
		for (; i < (int) (appParts.size() * 0.49); i++) {
			appParts.get(i).setVisibleMinorityResponse("South East Asian");
		}
		for (; i < (int) (appParts.size() * 0.50); i++) {
			appParts.get(i).setVisibleMinorityResponse("West Asian");
		}
		for (; i < (int) (appParts.size() * 0.60); i++) {
			appParts.get(i).setVisibleMinorityResponse("African");
		}

		return appParts;
	}

	@Override
	public void saveAllApplicationParticipations(List<ApplicationParticipation> appParticipations) {
		appParticipationRepo.saveAll(appParticipations);
	}

	@Override
	public List<String> getExtIdsQualifiedForEdi() {
		List<String> retval = new ArrayList<String>();
		// 1. get users member roles with EDI,
		List<MemberRole> mrList = mrService.findMRsByUserLoginAndEdiAuthorizedTrue(SecurityUtils.getCurrentUsername());
		// 2. use that to collect list of business unit IDs
		List<Long> targetBuIds = new ArrayList<Long>();
		for (MemberRole mr : mrList) {
			targetBuIds.add(mr.getBusinessUnit().getId());
		}
		// 3. use that to query system funding opportunities
		List<SystemFundingOpportunity> targetSFOs = sfoService.findSFOsByLinkedFundingOpportunityBusinessUnitIdIn(targetBuIds);
		// 4. extract list of extIds
		for (SystemFundingOpportunity sfo : targetSFOs) {
			retval.add(sfo.getExtId());
		}

		return retval;
	}

	@Override
	public ApplicationParticipation getAllowdRecord(Long id) {
		ApplicationParticipation retval = appParticipationRepo.findById(id).orElseThrow(
				() -> new DataRetrievalFailureException("That Application Participation record does not exist"));
		if (SecurityUtils.hasRole("MDM ADMIN") == false) {
			List<String> allowedExtIds = getExtIdsQualifiedForEdi();
			if (!allowedExtIds.contains(retval.getProgramId())) {
				throw new AccessDeniedException("not authorized to access this record");
			}
		}
		return retval;
	}
	
	private int generateRandNumBtw(int start, int end) {
		return sRand.nextInt(end) + start;
	}

//	private String[] createAuditedApplicationParticipationStrArr(ApplicationParticipation ap, String revType,
//			UsernameRevisionEntity revEntity) {
//		switch (revType) {
//		case "ADD":
//			revType = "INSERT";
//			break;
//		case "MOD":
//			revType = "UPDATE";
//			break;
//		case "DEL":
//			revType = "DELETE";
//		}
//
//		return new String[] { revEntity.getUsername(), revType, ap.getApplicationIdentifier(), ap.getApplId(),
//				(ap.getCompetitionYear() != null) ? ap.getCompetitionYear().toString() : null, ap.getCountry(),
//				(ap.getCreateDate() != null) ? ap.getCreateDate().toString() : null, ap.getCreateUserId(),
//				(ap.getDateOfBirth() != null) ? ap.getDateOfBirth().toString() : null,
//				ap.getDateOfBirthIndicator().toString(), ap.getDisabilityResponse(),
//				ap.getEdiNotApplicable().toString(), ap.getFamilyName(), ap.getFreeformAddress1(),
//				ap.getFreeformAddress2(), ap.getFreeformAddress3(), ap.getFreeformAddress4(), ap.getGenderSelection(),
//				ap.getGivenName(), ap.getIndIdentityPrefNotTo().toString(), ap.getIndIdentityResponse(),
//				(ap.getIndIdentitySelection1() != null) ? ap.getIndIdentitySelection1().toString() : null,
//				(ap.getIndIdentitySelection2() != null) ? ap.getIndIdentitySelection2().toString() : null,
//				(ap.getIndIdentitySelection3() != null) ? ap.getIndIdentitySelection3().toString() : null,
//				ap.getMunicipality(), ap.getOrganizationId(), ap.getOrganizationNameEn(), ap.getOrganizationNameFr(),
//				(ap.getPersonIdentifier() != null) ? ap.getPersonIdentifier().toString() : null, ap.getPostalZipCode(),
//				ap.getProgramEn(), ap.getProgramFr(), ap.getProgramId(), ap.getProvinceStateCode(), ap.getRoleCode(),
//				ap.getRoleEn(), ap.getRoleEn(), ap.getRoleFr(), ap.getVisibleMinorityResponse(),
//				ap.getVisibleMinPrefNotTo().toString(),
//				(ap.getVisibleMinSelection1() != null) ? ap.getVisibleMinSelection1().toString() : null,
//				(ap.getVisibleMinSelection2() != null) ? ap.getVisibleMinSelection2().toString() : null,
//				(ap.getVisibleMinSelection3() != null) ? ap.getVisibleMinSelection3().toString() : null,
//				(ap.getVisibleMinSelection4() != null) ? ap.getVisibleMinSelection4().toString() : null,
//				(ap.getVisibleMinSelection5() != null) ? ap.getVisibleMinSelection5().toString() : null,
//				(ap.getVisibleMinSelection6() != null) ? ap.getVisibleMinSelection6().toString() : null,
//				(ap.getVisibleMinSelection7() != null) ? ap.getVisibleMinSelection7().toString() : null,
//				(ap.getVisibleMinSelection8() != null) ? ap.getVisibleMinSelection8().toString() : null,
//				(ap.getVisibleMinSelection9() != null) ? ap.getVisibleMinSelection9().toString() : null,
//				(ap.getVisibleMinSelection10() != null) ? ap.getVisibleMinSelection10().toString() : null,
//				(ap.getVisibleMinSelection11() != null) ? ap.getVisibleMinSelection11().toString() : null,
//				revEntity.getRevTimestamp().toString() };
//	}
//
//	@AdminOnly
//	@Override
//	public List<String[]> findApplicationParticipationRevisionsById(Long apId) {
//		List<String[]> auditedArrList = new ArrayList<>();
//
//		List<Revision<Long, ApplicationParticipation>> revisionList = appParticipationRepo.findRevisions(apId).getContent();
//		if (!revisionList.isEmpty()) {
//			revisionList.forEach(rev -> {
//				ApplicationParticipation ap = rev.getEntity();
//				UsernameRevisionEntity revEntity = (UsernameRevisionEntity) rev.getMetadata().getDelegate();
//				auditedArrList.add(createAuditedApplicationParticipationStrArr(ap,
//						rev.getMetadata().getRevisionType().toString(), revEntity));
//			});
//		}
//
//		auditedArrList.sort(Comparator.comparing(strArr -> strArr[51]));
//		return auditedArrList;
//	}
//
//	@AdminOnly
//	@Transactional(readOnly = true)
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<String[]> findAllApplicationParticipationRevisions() {
//		AuditReader auditReader = AuditReaderFactory.get(em);
//		AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(ApplicationParticipation.class, false, true);
//
//		List<String[]> auditedArrList = new ArrayList<>();
//
//		List<Object[]> revisionList = auditQuery.getResultList();
//		revisionList.forEach(objArr -> {
//			ApplicationParticipation ap = (ApplicationParticipation) objArr[0];
//			UsernameRevisionEntity revEntity = (UsernameRevisionEntity) objArr[1];
//			RevisionType revType = (RevisionType) objArr[2];
//			auditedArrList.add(createAuditedApplicationParticipationStrArr(ap, revType.toString(), revEntity));
//		});
//
//		auditedArrList.sort(Comparator.comparing(strArr -> strArr[51]));
//		return auditedArrList;
//	}

}
