package ca.gc.tri_agency.granting_data.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.BusinessUnit;
import ca.gc.tri_agency.granting_data.model.auditing.UsernameRevisionEntity;
import ca.gc.tri_agency.granting_data.repo.BusinessUnitRepository;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.BusinessUnitService;

@Service
public class BusinessUnitServiceImpl implements BusinessUnitService {

	private BusinessUnitRepository buRepo;

	@Autowired
	public BusinessUnitServiceImpl(BusinessUnitRepository buRepo) {
		this.buRepo = buRepo;
	}

	@Override
	public BusinessUnit findBusinessUnitById(Long id) {
		return buRepo.findById(id).orElseThrow(() -> new DataRetrievalFailureException("That Business Unit does not exist"));
	}

	@Override
	public List<BusinessUnit> findAllBusinessUnits() {
		return buRepo.findAll();
	}

	@Override
	public List<BusinessUnit> findAllBusinessUnitsByAgency(Agency agency) {
		return buRepo.findAllByAgency(agency);
	}

	@AdminOnly
	@Override
	public BusinessUnit saveBusinessUnit(BusinessUnit bu) {
		return buRepo.save(bu);
	}

	@Override
	public List<String[]> findBusinessUnitRevisionsById(Long buId) {
		List<String[]> auditArrList = new ArrayList<>();

		List<Revision<Long, BusinessUnit>> revisionList = buRepo.findRevisions(buId).getContent();
		if (!revisionList.isEmpty()) {
			revisionList.forEach(rev -> {
				BusinessUnit bu = rev.getEntity();
				UsernameRevisionEntity revEntity = (UsernameRevisionEntity) rev.getMetadata().getDelegate();
				auditArrList.add(new String[] { revEntity.getUsername(),
						rev.getMetadata().getRevisionType().toString(), bu.getNameEn(), bu.getNameFr(),
						bu.getAcronymEn(), bu.getAcronymFr(), revEntity.getRevtstmp().toString() });
			});
		}
		
		auditArrList.sort(Comparator.comparing(strArr -> strArr[6])); // sorts by timestamp
		return auditArrList;
	}

}
