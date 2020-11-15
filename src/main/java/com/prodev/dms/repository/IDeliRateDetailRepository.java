package com.prodev.dms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prodev.dms.domain.DeliRateDetail;

@Repository
@Transactional
public interface IDeliRateDetailRepository extends CrudRepository<DeliRateDetail,Long>{
	
	@Query("FROM  DeliRateDetail  WHERE deliRateMasterID= :deliRateMasterID")
    List<DeliRateDetail> findAllDeliRateDetailBymasterId(@Param("deliRateMasterID") long deliRateMasterID);
	
	@Modifying
    @Query("DELETE  FROM DeliRateDetail WHERE deliRateMasterID=:deliRateMasterID")
    void DeliRateDetailDeleteBymasterId(@Param("deliRateMasterID") long deliRateMasterID);
	
}
