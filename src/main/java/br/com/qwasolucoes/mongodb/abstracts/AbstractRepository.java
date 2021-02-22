package br.com.qwasolucoes.mongodb.abstracts;

import java.util.List;

public abstract class AbstractRepository<T> {

	public abstract T create(T t);

	public abstract void update(T t);

	public abstract void removeById(String id);

	public abstract T findById(String id);

	public abstract List<T> findAll();
}