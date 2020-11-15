package com.prodev.dms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.prodev.dms.domain.TownShip;

@Repository
public interface ITownShipRepository  extends CrudRepository<TownShip, Long> {
	//@Query("select id,description from TownShip where TownShip.cityid = :cityID")
	//public List<TownShip> findByCityID(@Param("cityID") int Cityd); 
}//end of repository
