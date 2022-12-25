package com.social.media.webservice.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.social.media.webservice.jpa.PostRepository;
import com.social.media.webservice.jpa.UserRepository;
import com.social.media.webservice.user.exception.UserNotfoundException;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	/*
	 * public UserJpaResource(UserRepository userRepository,PostRepository
	 * postRepository) { this.userRepository=userRepository;
	 * this.postRepository=postRepository; }
	 */

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public EntityModel<Optional<User>> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotfoundException("id :"+id);
		}
		
		EntityModel<Optional<User>> entityModel = EntityModel.of(user);
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostForUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty()) {
			throw new UserNotfoundException("id :"+id);
		}
		
		return user.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id,@Valid @RequestBody Post post) {
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty()) {
			throw new UserNotfoundException("id :"+id);
		}
		
		post.setUser(user.get());
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
}
