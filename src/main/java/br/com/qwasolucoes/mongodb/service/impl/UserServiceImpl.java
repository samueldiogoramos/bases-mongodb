package br.com.qwasolucoes.mongodb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.qwasolucoes.mongodb.model.User;
import br.com.qwasolucoes.mongodb.repository.UserRepository;
import br.com.qwasolucoes.mongodb.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	private static final boolean ENABLE = true;
	
	private static final boolean DISABLE = true;
	
	@Override
	public User create(User user) {
		user.setPassword(criptografyPassword(user.getPassword()));
		
		return userRepository.create(user);
	}

	@Override
	public void update(User user) {
		user.setPassword(criptografyPassword(user.getPassword()));
		
		userRepository.update(user);;
	}

	@Override
	public List<User> list() {
		return userRepository.findAll();
	}

	@Override
	public User findById(String id) {
		return userRepository.findById(id);
	}

	@Override
	public void removeById(String id) {
		userRepository.removeById(id);
	}

	@Override
	public void enableUser(String id) {
		userRepository.changeStatus(id, ENABLE);
	}

	@Override
	public void disableUser(String id) {
		userRepository.changeStatus(id, DISABLE);
	}

	private String criptografyPassword(final String password) {
		return passwordEncoder.encode(password);
	}

}
