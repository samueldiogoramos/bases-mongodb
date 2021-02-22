package br.com.qwasolucoes.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.qwasolucoes.mongodb.model.User;
import br.com.qwasolucoes.mongodb.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public ResponseEntity<List<User>> list(){
		final List<User> users = userService.list();
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<User> create(@RequestBody User user) {
		final User createdUser = userService.create(user);
		
		return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
	}

	@GetMapping("/find-by-id/{id}")
	public ResponseEntity<User> findById(@PathVariable String id) {
		final User foundUser = userService.findById(id);
				
		return new ResponseEntity<User>(foundUser, null != foundUser ? HttpStatus.FOUND : HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable String id, @RequestBody User user) {
		user.setId(id);

		userService.update(user);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/remove-by-id/{id}")
	public ResponseEntity<String> removeById(@PathVariable String id) {
		userService.removeById(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/enable/{id}")
	public ResponseEntity<String> enable(@PathVariable String id) {
		userService.enableUser(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/disable/{id}")
	public ResponseEntity<String> disable(@PathVariable String id) {
		userService.disableUser(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
