package ca.gc.tri_agency.granting_data.ldap;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapUserDetails;

public class ADUserDetails implements LdapUserDetails {

	private static final long serialVersionUID = -775664263411224569L;
	
	private LdapUserDetails ldapUserDetails;
	
	@Autowired
	public ADUserDetails(LdapUserDetails ldapUserDetails) {
		this.ldapUserDetails = ldapUserDetails;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return ldapUserDetails.getAuthorities();
	}

	@Override
	public String getPassword() {
		return ldapUserDetails.getPassword();
	}

	@Override
	public String getUsername() {
		return ldapUserDetails.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return ldapUserDetails.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return ldapUserDetails.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return ldapUserDetails.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return ldapUserDetails.isEnabled();
	}

	@Override
	public String getDn() {
		return ldapUserDetails.getDn();
	}

	@Override
	public void eraseCredentials() {
	}
}