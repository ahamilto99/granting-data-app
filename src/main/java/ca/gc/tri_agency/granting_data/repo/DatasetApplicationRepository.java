package ca.gc.tri_agency.granting_data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.gc.tri_agency.granting_data.model.DatasetApplication;

public interface DatasetApplicationRepository extends JpaRepository<DatasetApplication, Long> {

	List<DatasetApplication> findByDatasetId(Long id);

}
