package com.example.demo.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UpdateUserDetailsRequestModel;
import com.example.demo.model.User;
import com.example.demo.model.UserDetailsRequestModel;
import com.example.demo.userservice.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	HashMap<String, User> users;
	
	@Autowired
	UserService impl;

	@GetMapping(produces="application/xml")
	public String getUsers(@RequestParam(value="page", defaultValue="1", required = false) int page,
			@RequestParam(value="limit", defaultValue = "15") String limit){
		return "All users with page " + page + " and limit " + limit;
	}
	
	@GetMapping(path="/{id}", produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> getUser(@PathVariable("id") String id) throws Exception {
		
//		if(1>0) {
//			//throw new NullPointerException();
//			throw new UserServiceExceptioin("An error occurred");
//		}
		
		int v = 1/0;
		
		User user = new User();
		user.setId(id);
		user.setEmail("munna@munna.com");
		user.setFirstname("Manoj");
		user.setLastname("Sethiya");
		
		if(users.containsKey(id)) {
			return new ResponseEntity<>(users.get(id), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
//	@GetMapping("/{id}")
//	public String getUser(@PathVariable("id") String id) {
//		return "Get user called with id " + id;
//	}

	@PostMapping(produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			consumes= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	//post body of json will be converted to userdetailsrequestmodel obj.s
	public ResponseEntity<User> createUser(@Valid @RequestBody UserDetailsRequestModel model) {
		User user = impl.createUser(model);
		if(users == null) {
			users = new HashMap<>();
			users.put(user.getId(), user);
		}
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@PutMapping(path="/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			consumes= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody UpdateUserDetailsRequestModel model) {
		User storedUser = users.get(id);
		
		storedUser.setFirstname(model.getFirstname());
		storedUser.setLastname(model.getLastname());
		
		users.put(id, storedUser);
		return new ResponseEntity<User>(storedUser, HttpStatus.OK);
	}

	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		users.remove(id);
		return ResponseEntity.noContent().build();
	}

//	static List<User> list = new Arraylist<User>();
//
	
}
