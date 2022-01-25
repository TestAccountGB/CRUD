package database.model.dao;

import java.sql.Connection;
import java.util.List;

public interface GenericDao<T> {

	void create(T t);

	List<T> getAll();

	void update(T t, int id);

	void delete(T t);

	void deleteById(int id);

	T find(T t);

	T findById(int id);

	Connection getConnection();

	void openConnection();

}
