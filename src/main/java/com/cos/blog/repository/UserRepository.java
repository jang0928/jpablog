package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>
{
    //select * from user where username = 1?
    Optional<User> findByUsername(String username);
}
//login 메소드
//select * from user where username = ?1 and password = ?2
//    User findByUsernameAndPassword(String username, String password);

//    @Query(value ="select * from user where username = ?1 and password = ?2",nativeQuery = true)
//    User login(String username , String password);
