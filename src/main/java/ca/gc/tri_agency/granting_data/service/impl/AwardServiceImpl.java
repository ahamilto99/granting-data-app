package ca.gc.tri_agency.granting_data.service.impl;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.ApplicationParticipation;
import ca.gc.tri_agency.granting_data.model.Award;
import ca.gc.tri_agency.granting_data.repo.AwardRepository;
import ca.gc.tri_agency.granting_data.service.AwardService;

@Service
public class AwardServiceImpl implements AwardService {

	private static final String MAIN_APPLICANT = "Applicant";

	private AwardRepository awardRepo;

	@Autowired
	public AwardServiceImpl(AwardRepository awardRepo) {
		this.awardRepo = awardRepo;
	}

	@Override
	public Award findAwardById(Long awardId) {
		return awardRepo.findById(awardId).orElseThrow(() -> new DataRetrievalFailureException("That Award does not exist"));
	}

	@Override
	public List<Award> findAllAwards() {
		return awardRepo.findAll();
	}

	// The List<ApplicationParticipation> returned by ApplicationParticipationService's getAllowedRecords()
	@Transactional
	@Override
	public List<Award> generateTestAwards(List<ApplicationParticipation> appParts, double percentageOfMainApplicants) {
		if (percentageOfMainApplicants < 0) {
			percentageOfMainApplicants = 0;
		} else if (percentageOfMainApplicants > 100) {
			percentageOfMainApplicants = 100;
		}

		long numOfAwards = (long) (percentageOfMainApplicants / 100.0 * appParts.size());
		SecureRandom sRand = new SecureRandom();

		Collections.shuffle(appParts);
		List<Award> testAwards = appParts.stream().limit(numOfAwards)
				.filter((ApplicationParticipation appPart) -> appPart.getRoleEn().equals(MAIN_APPLICANT))
				.map((ApplicationParticipation appPart) -> new Award(sRand.nextDouble() * 100_000,
						appPart.getCompetitionYear() != null ? appPart.getCompetitionYear() + 1L
								: sRand.nextInt(5) + 2017,
						appPart.getPersonIdentifier(), appPart.getFamilyName(), appPart.getGivenName(),
						appPart.getRoleCode(), appPart.getRoleEn(), appPart.getRoleFr()))
				.collect(Collectors.toList());

		return testAwards;
	}

}
