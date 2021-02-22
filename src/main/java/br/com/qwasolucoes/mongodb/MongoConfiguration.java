package br.com.qwasolucoes.mongodb;

import java.util.Collection;
import java.util.Collections;

import org.springframework.core.SpringProperties;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoConfiguration extends AbstractMongoClientConfiguration {
	private static final String DATABASE = "workshop"; //TODO Rename
	private static final String URI = "spring.data.mongodb.uri";
	private static final String PACKAGE = "br.com.qwasolucoes";

	@Override
	protected String getDatabaseName() {
		return DATABASE;
	}

	@Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(SpringProperties.getProperty(URI));
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
        
        return MongoClients.create(mongoClientSettings);
    }
 
    @Override
    public Collection<String> getMappingBasePackages() {
        return Collections.singleton(PACKAGE);
    }
}
