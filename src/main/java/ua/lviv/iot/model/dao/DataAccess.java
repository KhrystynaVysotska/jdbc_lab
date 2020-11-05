package ua.lviv.iot.model.dao;

import java.sql.SQLException;
import java.util.List;

public interface DataAccess<T, K> {
	List<T> findAll() throws SQLException;

	T findById(K id) throws SQLException;

	int create(T entity) throws SQLException;

	int update(T entity) throws SQLException;

	int delete(K id) throws SQLException;
}
