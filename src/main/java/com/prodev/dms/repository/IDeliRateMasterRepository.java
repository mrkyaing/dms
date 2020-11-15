package com.prodev.dms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prodev.dms.common.DeliRateMasterVM;
import com.prodev.dms.domain.DeliRateMaster;

@Repository
public interface IDeliRateMasterRepository extends CrudRepository<DeliRateMaster,Long>{
	//@Query("SELECT m.*,d.* FROM DeliRateDetail d INNER JOIN DeliRateMaster m ON m.id= d.deliRateMasterID;")
   //List<DeliRateMasterVM> findDeliMasterDetailAll();
}
