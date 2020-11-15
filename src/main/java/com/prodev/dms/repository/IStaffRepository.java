package com.prodev.dms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prodev.dms.domain.Staff;

@Repository
public interface IStaffRepository extends CrudRepository<Staff,Long>{

}
