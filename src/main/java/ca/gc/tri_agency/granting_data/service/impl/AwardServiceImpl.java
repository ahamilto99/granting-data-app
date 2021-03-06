package ca.gc.tri_agency.granting_data.service.impl;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.ApplicationParticipation;
import ca.gc.tri_agency.granting_data.model.Award;
import ca.gc.tri_agency.granting_data.model.projection.AwardProjection;
import ca.gc.tri_agency.granting_data.repo.AwardRepository;
import ca.gc.tri_agency.granting_data.security.SecurityUtils;
import ca.gc.tri_agency.granting_data.service.ApplicationParticipationService;
import ca.gc.tri_agency.granting_data.service.AwardService;

@Service
public class AwardServiceImpl implements AwardService {

	private static final String MAIN_APPLICANT = "Applicant";

	private AwardRepository awardRepo;

	private ApplicationParticipationService appPartService;

	@Autowired
	public AwardServiceImpl(AwardRepository awardRepo, ApplicationParticipationService appPartService) {
		this.awardRepo = awardRepo;
		this.appPartService = appPartService;
	}

	// The List<ApplicationParticipation> returned by ApplicationParticipationService's
	// getAllowedRecords() can be passed in
	@Transactional
	@Override
	public List<Award> generateTestAwards(List<ApplicationParticipation> appParts, double percentageOfMainApplicants) {
		if (percentageOfMainApplicants < 0) {
			percentageOfMainApplicants = 0;
		} else if (percentageOfMainApplicants > 100) {
			percentageOfMainApplicants = 100;
		}

		appParts = appParts.stream().filter((ApplicationParticipation appPart) -> !appPart.getIsDeadlinePassed())
				.collect(Collectors.toList());
		appParts.forEach(appPart -> appPart.setIsDeadlinePassed(new Boolean(true)));

		Collections.shuffle(appParts);

		SecureRandom sRand = new SecureRandom();

		List<Award> testAwards = appParts.stream()
				.filter((ApplicationParticipation appPart) -> appPart.getRoleEn() != null
						&& appPart.getRoleEn().equals(MAIN_APPLICANT))
				.limit((long) (percentageOfMainApplicants / 100.0 * appParts.size()))
				.map((ApplicationParticipation appPart) -> new Award(sRand.nextDouble() * 100_000, sRand.nextInt(5) + 2017L,
						appPart.getApplId(), appPart.getFamilyName(), appPart.getGivenName(), appPart.getRoleCode(),
						appPart.getRoleEn(), appPart.getRoleFr(), appPart.getProgramId(), appPart.getProgramEn(),
						appPart.getProgramFr()))
				.collect(Collectors.toList());

		testAwards.forEach(award -> award.setRequestedAmount((sRand.nextDouble() + 1.0) * award.getAwardedAmount()));

		appPartService.saveAllApplicationParticipations(appParts);

		return awardRepo.saveAll(testAwards);
	}

	@Override
	public List<AwardProjection> findAwardsForCurrentUser() {
		if (SecurityUtils.isCurrentUserAdmin()) {
			return awardRepo.findAllAdminOnly();
		}
		return awardRepo.findForCurrentUser(SecurityUtils.getCurrentUsername());
	}

}
