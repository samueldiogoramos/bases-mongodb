package br.com.qwasolucoes.mongodb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.qwasolucoes.mongodb.model.User;

@Service
public interface UserService {
	User create(User usuario);
	
	void update(User usuario);

	List<User> list();

	User findById(String id);

	void removeById(String id);
	
	void enableUser(String id);
	
	void disableUser(String id);
}
