package com.paractice.rest.webservices.restfulwebservices.repository;

import com.paractice.rest.webservices.restfulwebservices.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
