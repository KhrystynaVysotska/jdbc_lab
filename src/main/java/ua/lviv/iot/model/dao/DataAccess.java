package ua.lviv.iot.model.dao;

import java.sql.SQLException;
import java.util.List;

public interface DataAccess<T, ID> {
	List<T> findAll() throws SQLException;

	T findById(ID id) throws SQLException;

	void create(T entity) throws SQLException;

	void update(T entity) throws SQLException;

	int delete(ID id) throws SQLException;
}
