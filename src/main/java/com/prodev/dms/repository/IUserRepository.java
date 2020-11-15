package com.prodev.dms.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prodev.dms.domain.User;


@Repository
@Transactional(readOnly = true)
public interface IUserRepository extends CrudRepository<User,Long> {

    User findByUsername(String username);

    @Modifying
    @Query("update User u set u.password = :password where u.id = :userId")
    void updateUserPassword(@Param("userId") long userId, @Param("password") String password);
    
    @Query("SELECT u FROM User u WHERE u.username !='root'")
    List<User> findAllUsersWithoutRoot();

}