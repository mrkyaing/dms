package com.prodev.dms.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prodev.dms.domain.Role;


@Repository
public interface IRoleRepository extends CrudRepository<Role,Long> {
	
	/*
	 * Find Role by role Name
	 */
	Role findByName(String name);
}