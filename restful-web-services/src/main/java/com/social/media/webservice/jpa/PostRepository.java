package com.social.media.webservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.media.webservice.user.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
