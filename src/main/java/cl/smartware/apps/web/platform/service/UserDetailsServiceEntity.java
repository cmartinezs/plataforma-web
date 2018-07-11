package cl.smartware.apps.web.platform.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.smartware.apps.web.platform.repository.dao.RoleEntityDAO;
import cl.smartware.apps.web.platform.repository.dao.UserEntityDAO;
import cl.smartware.apps.web.platform.repository.entity.UserEntity;

@Service
public class UserDetailsServiceEntity implements UserDetailsService
{
	@Autowired
	private UserEntityDAO userEntityDAO;

	@Autowired
	private RoleEntityDAO roleEntityDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		UserEntity userEntity = userEntityDAO.findByUsername(username);

		if (userEntity == null)
		{
			System.out.println("User not found! " + username);
			throw new UsernameNotFoundException("User '" + username + "' was not found in the database");
		}

		System.out.println("Found User: " + userEntity);

		// [ROLE_USER, ROLE_ADMIN,..]
		List<String> roleNames = this.roleEntityDAO.getRoleNames(userEntity.getId());

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roleNames != null)
		{
			for (String role : roleNames)
			{
				// ROLE_USER, ROLE_ADMIN,..
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
			}
		}

		UserDetails userDetails = (UserDetails) new User(userEntity.getUsername(), //
				userEntity.getPassword(), grantList);

		return userDetails;
	}
}
