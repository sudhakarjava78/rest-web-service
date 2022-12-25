package com.social.media.webservice.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static int userCount=0;

	
	private static List<User> users=new ArrayList<>();
	
	static {
		users.add(new User(++userCount,"Kamal",LocalDate.now().minusYears(30)));
		users.add(new User(++userCount,"Basava",LocalDate.now().minusYears(25)));
		users.add(new User(++userCount,"Raj",LocalDate.now().minusYears(20)));
		users.add(new User(++userCount,"Vanu",LocalDate.now().minusYears(15)));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User findOne(int id) {
		return users.stream().filter(user->user.getId().equals(id)).findFirst().orElse(null);
	}
	
	public User save(User user) {
		user.setId(++userCount);
		users.add(user);
		return user;
	}
	
	public void deleteById(int id) {
		users.removeIf(user->user.getId().equals(id));
	}
}
