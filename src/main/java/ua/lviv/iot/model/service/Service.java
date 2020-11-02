package ua.lviv.iot.model.service;

import java.sql.SQLException;
import java.util.List;

public interface Service<T, ID> {
	List<T> findAll() throws SQLException;

	T findById(ID id) throws SQLException;

	T create(T entity) throws SQLException;

	T update(ID id, T entity) throws SQLException;

	boolean delete(ID id) throws SQLException;
}
