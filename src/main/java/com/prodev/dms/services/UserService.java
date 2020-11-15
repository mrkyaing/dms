package com.prodev.dms.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prodev.dms.domain.User;
import com.prodev.dms.domain.UserRole;
import com.prodev.dms.repository.IUserRepository;

@Service
@Transactional(readOnly = true)
public class UserService {

	@Autowired
	private IUserRepository userRepository;


	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/** The application logger */
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Transactional
	public User createUser(User user) {
		String encryptedPassword =  passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		// Make sure userRole is set with user
		for (UserRole ur : user.getUserRoles()) {
			ur.setUser(user);
		}
		user = userRepository.save(user);

		return user;
	}
	

	public User findByUserName(String username) {
		return userRepository.findByUsername(username);
	}
	
	public Iterable<User> getUserList(){
		return  userRepository.findAll();
	}
	
	public User UsergetByUserId(long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public void DeleteByUserId(long id) {
		userRepository.deleteById(id);
	}

}
