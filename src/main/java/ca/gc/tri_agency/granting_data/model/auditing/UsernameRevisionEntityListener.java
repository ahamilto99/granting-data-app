package ca.gc.tri_agency.granting_data.model.auditing;

import java.time.Instant;
import java.util.Date;

import org.hibernate.envers.RevisionListener;

import ca.gc.tri_agency.granting_data.security.SecurityUtils;

public class UsernameRevisionEntityListener implements RevisionListener {

	@Override
	public void newRevision(Object revEntity) {
		UsernameRevisionEntity usernameRevEntity = (UsernameRevisionEntity) revEntity;
		try {
			usernameRevEntity.setUsername(SecurityUtils.getLdapUserDn());
		} catch (ClassCastException cce) {
			// this is only required for testing purposes since tests are executed with mock users
			usernameRevEntity.setUsername(SecurityUtils.getCurrentUsername());
		}
		usernameRevEntity.setRevtstmp(Date.from(Instant.now()));
	}

}
