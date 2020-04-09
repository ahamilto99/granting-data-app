package ca.gc.tri_agency.granting_data.repoLdap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.gc.tri_agency.granting_data.model.ldap.MemberRole;

@Repository
public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {

}
