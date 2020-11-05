package ua.lviv.iot.model.service;

import java.sql.SQLException;
import java.util.List;

public interface Service<T, K> {
	List<T> findAll() throws SQLException;

	T findById(K id) throws SQLException;

	T create(T entity) throws SQLException;

	T update(K id, T entity) throws SQLException;

	boolean delete(K id) throws SQLException;
}
