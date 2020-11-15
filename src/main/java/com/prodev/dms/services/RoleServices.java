package com.prodev.dms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prodev.dms.domain.Role;
import com.prodev.dms.repository.IRoleRepository;

@Service
public class RoleServices {
@Autowired
IRoleRepository repo;

public void SaveRole(Role r) {
	repo.save(r);
}

public Iterable<Role> getAllRole(){
	return repo.findAll();
}
public Role getRoleBy(String roleName){
	return repo.findByName(roleName);
}
public Role getRoleId(long Id){
	return repo.findById(Id).orElse(null);
}
}
