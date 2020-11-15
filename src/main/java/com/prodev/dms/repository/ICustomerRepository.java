package com.prodev.dms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prodev.dms.domain.Customer;

@Repository
public interface ICustomerRepository extends CrudRepository<Customer,Long>{

}
