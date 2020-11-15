package com.prodev.dms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prodev.dms.domain.DeliProduct;

@Repository
public interface IDeliProductRepository extends CrudRepository<DeliProduct,Integer>{
//Create the customize method here
}
