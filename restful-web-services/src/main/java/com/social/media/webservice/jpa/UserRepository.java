package com.social.media.webservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.social.media.webservice.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
