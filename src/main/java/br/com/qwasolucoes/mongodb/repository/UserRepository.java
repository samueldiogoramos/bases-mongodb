package br.com.qwasolucoes.mongodb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import br.com.qwasolucoes.mongodb.abstracts.AbstractRepository;
import br.com.qwasolucoes.mongodb.model.User;

@Repository
public class UserRepository extends AbstractRepository<User>{
	private MongoTemplate mongoTemplate;
	
	@Autowired
	public UserRepository(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate; 
	}
	
	@Override
	public User create(final User user) {
		return mongoTemplate.save(user);
	}

	@Override
	public void update(final User user) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(user.getId()));
		
		Update update = new Update();
		update.set("name", user.getName());
		update.set("email", user.getEmail());
		update.set("password", user.getPassword());

		mongoTemplate.updateFirst(query, update, User.class);
	}

	@Override
	public void removeById(final String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		
		mongoTemplate.remove(query, User.class);
	}
	
	@Override
	public User findById(final String id) {
		return mongoTemplate.findById(id, User.class);
	}
	
	@Override
	public List<User> findAll() {
		return mongoTemplate.findAll(User.class);
	}


	public void changeStatus(final String id, final boolean status) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		
		Update update = new Update();
		update.set("enabled", status);

		mongoTemplate.updateFirst(query, update, User.class);
	}

}
