package ca.gc.tri_agency.granting_data.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.gc.tri_agency.granting_data.model.FiscalYear;

@Repository
public interface FiscalYearRepository extends JpaRepository<FiscalYear, Long> {

	Optional<FiscalYear> findByYear(Long year);

}
