package com.sk.sb.code.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sk.sb.code.entity.Role;
import com.sk.sb.code.entity.User;
import com.sk.sb.code.repository.UserRepository;

public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUserName(username)
		         .orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		// TODO Auto-generated method stub
		return roles.stream()
				.map(role-> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
	}

}
