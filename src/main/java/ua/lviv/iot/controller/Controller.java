package ua.lviv.iot.controller;

import java.sql.SQLException;

public interface Controller<T, ID> {
	void getAll() throws SQLException;
	void getById() throws SQLException;
	void create(T entity) throws SQLException;
	void update(ID id, T entity) throws SQLException;
	void deleteById() throws SQLException;
}
