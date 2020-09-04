package ca.gc.tri_agency.granting_data.service;

import java.util.List;

public interface AuditService {
	
	List<Object[]> findRevisionsForAllFOs();

	List<Object[]> findRevisionsForOneFO(Long foId);
	
	List<Object[]> findRevisionsForAllMRs();

	List<Object[]> findRevisionsForOneMR(Long mrId);

	List<Object[]> findRevisionsForAllSFOs();

	List<Object[]> findRevisionsForOneSFO(Long sfoId);
	
	List<Object[]> findRevisionsForAllBUs();

	List<Object[]> findRevisionsForOneBU(Long buId);
}
