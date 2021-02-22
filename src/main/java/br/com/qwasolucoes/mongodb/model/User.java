package br.com.qwasolucoes.mongodb.model;

import org.springframework.data.annotation.Id;

import br.com.qwasolucoes.mongodb.interfaces.Collection;
import lombok.Data;

@Data
public class User implements Collection {
	private static final long serialVersionUID = 7829965695524788959L;

	@Id
	private String id;

	private String name;
	private String email;
	private String username;
	private String password;
	private boolean enabled = true;

}
