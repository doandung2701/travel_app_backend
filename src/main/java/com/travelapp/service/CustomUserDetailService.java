package com.travelapp.service;

import java.nio.file.attribute.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travelapp.exception.ResourceNotFoundException;
import com.travelapp.model.User;
import com.travelapp.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByUsernameOrEmail(username, username).orElseThrow(()->new UsernameNotFoundException("User not found with username or email: "+username));
		return com.travelapp.security.UserPrincipal.create(user);		
	}
	 @Transactional
	    public UserDetails loadUserById(Long id) {
	        User user = userRepository.findById(id).orElseThrow(
	            () -> new ResourceNotFoundException("User", "id", id)
	        );

	        return com.travelapp.security.UserPrincipal.create(user);
	    }
}
