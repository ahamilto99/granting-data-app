package ca.gc.tri_agency.granting_data.ldap;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapUserDetails;

import ca.gc.tri_agency.granting_data.security.SecurityUtils;

public class ADUserDetails implements LdapUserDetails {

	private static final long serialVersionUID = -775664263411224569L;
	
	private ADUserService adUserService;
	
	private LdapUserDetails ldapUserDetails;
	
	private ADUser adUser;
	
	@Autowired
	public ADUserDetails(LdapUserDetails ldapUserDetails, ADUserService adUserService) {
		this.ldapUserDetails = ldapUserDetails;
		this.adUserService = adUserService;
	}

	public String getFullName() {
		if (adUser == null) {
			adUser = adUserService.findAllADUsers().get(4);
		}
		System.out.println(adUser.getDn().toString());
		return adUser.getFullName();
	}
	
	public String getLastName() {
		if (adUser == null) {
			adUser = adUserService.findADUserByDn(getDn());
		}
		return adUser.getLastName();
	}
	
	public String getEmail() {
		if (adUser == null) {
			adUser = adUserService.findADUserByDn(getDn());
		}
		return adUser.getEmail();
	}
	
	@Override
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