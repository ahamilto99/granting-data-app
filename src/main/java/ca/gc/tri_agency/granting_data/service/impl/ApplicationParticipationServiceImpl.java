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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.javafaker.Faker;

import ca.gc.tri_agency.granting_data.model.ApplicationParticipation;
import ca.gc.tri_agency.granting_data.model.Gender;
import ca.gc.tri_agency.granting_data.model.GrantingSystem;
import ca.gc.tri_agency.granting_data.model.IndigenousIdentity;
import ca.gc.tri_agency.granting_data.model.MemberRole;
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.model.VisibleMinority;
import ca.gc.tri_agency.granting_data.model.dto.AppPartEdiAuthorizedDto;
import ca.gc.tri_agency.granting_data.model.projection.ApplicationParticipationProjection;
import ca.gc.tri_agency.granting_data.repo.ApplicationParticipationRepository;
import ca.gc.tri_agency.granting_data.security.SecurityUtils;
import ca.gc.tri_agency.granting_data.service.ApplicationParticipationService;
import ca.gc.tri_agency.granting_data.service.GenderService;
import ca.gc.tri_agency.granting_data.service.IndigenousIdentitySerivce;
import ca.gc.tri_agency.granting_data.service.MemberRoleService;
import ca.gc.tri_agency.granting_data.service.SystemFundingOpportunityService;
import ca.gc.tri_agency.granting_data.service.VisibleMinorityService;

@Service
public class ApplicationParticipationServiceImpl implements ApplicationParticipationService {

	Map<GrantingSystem, ReferenceBean[]> roleMap;

	private SecureRandom sRand = new SecureRandom();

	private ApplicationParticipationRepository appParticipationRepo;

	private SystemFundingOpportunityService sfoService;

	private MemberRoleService mrService;

	private GenderService genderService;

	private IndigenousIdentitySerivce indIdentityService;

	private VisibleMinorityService vMinorityService;

	private static int applIdIncrementer = 0;

	@Autowired
	public ApplicationParticipationServiceImpl(ApplicationParticipationRepository appParticipationRepo,
			SystemFundingOpportunityService sfoService, MemberRoleService mrService, GenderService genderService,
			IndigenousIdentitySerivce indIdentityService, VisibleMinorityService vMinorityService) {
		this.appParticipationRepo = appParticipationRepo;
		this.sfoService = sfoService;
		this.mrService = mrService;
		this.genderService = genderService;
		this.indIdentityService = indIdentityService;
		this.vMinorityService = vMinorityService;
	}

	@Transactional
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
		setAppPartDisability(appParts);
		setAppPartGender(appParts);
		setAppPartIndigenous(appParts);
		setAppPartEthnicity(appParts);

		return appParts;
	}

	@Transactional
	private List<ApplicationParticipation> setAppPartGender(List<ApplicationParticipation> appParts) {
		Gender male = genderService.findGenderByNameEn("Male");
		Gender female = genderService.findGenderByNameEn("Female");
		Gender nonBinary = genderService.findGenderByNameEn("Non-Binary");

		Collections.shuffle(appParts);
		int i = 0;
		for (; i < (int) (appParts.size() * 0.5); i++) {
			appParts.get(i).setGender(female);
		}
		for (; i < (int) (appParts.size() * 0.93); i++) {
			appParts.get(i).setGender(male);
		}
		for (; i < (int) appParts.size(); i++) {
			appParts.get(i).setGender(nonBinary);
		}

		return appParts;
	}

	@Transactional
	private List<ApplicationParticipation> setAppPartDisability(List<ApplicationParticipation> appParts) {
		Collections.shuffle(appParts);

		int i = 0;
		for (; i < (int) (appParts.size() * 0.04); i++) {
			appParts.get(i).setDisabilityResponse("Physical");
		}
		for (; i < (int) (appParts.size() * 0.06); i++) {
			appParts.get(i).setDisabilityResponse("Deaf");
		}
		for (; i < (int) (appParts.size() * 0.08); i++) {
			appParts.get(i).setDisabilityResponse("Other");
		}
		for (; i < (int) (appParts.size() * 0.09); i++) {
			appParts.get(i).setDisabilityResponse("Blind");
		}
		return appParts;
	}

	@Transactional
	private List<ApplicationParticipation> setAppPartIndigenous(List<ApplicationParticipation> appParts) {
		Collections.shuffle(appParts);

		int i = 0;
		IndigenousIdentity identity = indIdentityService.findIndigenousIdentityByNameEn("Métis");
		for (; i < (int) (appParts.size() * 0.05); i++) {
			appParts.get(i).addIndigenousIdentity(identity);
		}
		identity = indIdentityService.findIndigenousIdentityByNameEn("Inuit");
		for (; i < (int) (appParts.size() * 0.08); i++) {
			appParts.get(i).addIndigenousIdentity(identity);
		}
		identity = indIdentityService.findIndigenousIdentityByNameEn("Mi'kmaq");
		for (; i < (int) (appParts.size() * 0.10); i++) {
			appParts.get(i).addIndigenousIdentity(identity);
		}
		identity = indIdentityService.findIndigenousIdentityByNameEn("Iroquois");
		for (; i < (int) (appParts.size() * 0.11); i++) {
			appParts.get(i).addIndigenousIdentity(identity);
		}
		identity = indIdentityService.findIndigenousIdentityByNameEn("Haida");
		for (; i < (int) (appParts.size() * 0.12); i++) {
			appParts.get(i).addIndigenousIdentity(identity);
		}
		identity = indIdentityService.findIndigenousIdentityByNameEn("Dalelh");
		for (; i < (int) (appParts.size() * 0.13); i++) {
			appParts.get(i).addIndigenousIdentity(identity);
		}

		return appParts;
	}

	@Transactional
	private List<ApplicationParticipation> setAppPartEthnicity(List<ApplicationParticipation> appParts) {
		int i = (int) (0.12 * appParts.size());
		VisibleMinority minority = vMinorityService.findVisibleMinorityByNameEn("Latin American");
		for (; i < (int) (appParts.size() * 0.18); i++) {
			appParts.get(i).addVisibleMinority(minority);
		}
		minority = vMinorityService.findVisibleMinorityByNameEn("Caribbean");
		for (; i < (int) (appParts.size() * 0.24); i++) {
			appParts.get(i).addVisibleMinority(minority);
		}
		minority = vMinorityService.findVisibleMinorityByNameEn("Middle Eastern");
		for (; i < (int) (appParts.size() * 0.32); i++) {
			appParts.get(i).addVisibleMinority(minority);
		}
		minority = vMinorityService.findVisibleMinorityByNameEn("Central Asian");
		for (; i < (int) (appParts.size() * 0.36); i++) {
			appParts.get(i).addVisibleMinority(minority);
		}
		minority = vMinorityService.findVisibleMinorityByNameEn("East Asian");
		for (; i < (int) (appParts.size() * 0.51); i++) {
			appParts.get(i).addVisibleMinority(minority);
		}
		minority = vMinorityService.findVisibleMinorityByNameEn("South Asian");
		for (; i < (int) (appParts.size() * 0.58); i++) {
			appParts.get(i).addVisibleMinority(minority);
		}
		minority = vMinorityService.findVisibleMinorityByNameEn("South East Asian");
		for (; i < (int) (appParts.size() * 0.61); i++) {
			appParts.get(i).addVisibleMinority(minority);
		}
		minority = vMinorityService.findVisibleMinorityByNameEn("West Asian");
		for (; i < (int) (appParts.size() * 0.62); i++) {
			appParts.get(i).addVisibleMinority(minority);
		}
		minority = vMinorityService.findVisibleMinorityByNameEn("African");
		for (; i < (int) (appParts.size() * 0.72); i++) {
			appParts.get(i).addVisibleMinority(minority);
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

	@Transactional(readOnly = true)
	@Override
	public List<ApplicationParticipationProjection> findAppPartsForCurrentUser() {
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities()
				.contains(new SimpleGrantedAuthority("ROLE_MDM ADMIN"))) {
			return appParticipationRepo.findAllForAdmin();
		}
		return appParticipationRepo.findForCurrentUser(SecurityContextHolder.getContext().getAuthentication().getName());
	}

	@Override
	public List<AppPartEdiAuthorizedDto> findAppPartsForCurrentUserWithEdiAuth() {
		List<AppPartEdiAuthorizedDto> dtoList = new ArrayList<>();

		List<ApplicationParticipationProjection> projectionList = findAppPartsForCurrentUser();
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities()
				.contains(new SimpleGrantedAuthority("ROLE_MDM ADMIN"))) {
			projectionList = appParticipationRepo.findAllForAdmin();
			projectionList.forEach(p -> dtoList.add(new AppPartEdiAuthorizedDto(p.getId(), p.getApplId(),
					p.getFamilyName(), p.getFirstName(), p.getRoleEn(), p.getRoleFr(), p.getOrganizationNameEn(),
					p.getOrganizationNameFr(), true)));
		} else {
			String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
			projectionList = appParticipationRepo.findForCurrentUser(userLogin);
			List<ApplicationParticipationProjection> idList = appParticipationRepo
					.findForCurrentUserEdiAuthorized(userLogin);

			// this works b/c the queries that return the idList and the projectionList both order the results
			// by id
			int k = 0;
			outerLoop: for (int i = 0; i < projectionList.size(); ++i) {
				ApplicationParticipationProjection p = projectionList.get(i);

				innerLoop: for (int j = k; j < idList.size();) {
					long ediAuthorizedId = idList.get(j).getId();
					if (ediAuthorizedId == p.getId()) {
						dtoList.add(new AppPartEdiAuthorizedDto(p.getId(), p.getApplId(), p.getFamilyName(),
								p.getFirstName(), p.getRoleEn(), p.getRoleFr(),
								p.getOrganizationNameEn(), p.getOrganizationNameFr(), true));
						++k;
						continue outerLoop;
					}
					break innerLoop;
				}

				dtoList.add(new AppPartEdiAuthorizedDto(p.getId(), p.getApplId(), p.getFamilyName(), p.getFirstName(),
						p.getRoleEn(), p.getRoleFr(), p.getOrganizationNameEn(), p.getOrganizationNameFr(),
						false));
			}
		}

		return dtoList;
	}

}
