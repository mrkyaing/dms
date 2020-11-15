package com.prodev.dms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prodev.dms.domain.City;

@Repository
public interface ICityRepository  extends CrudRepository<City, Long> {

}
