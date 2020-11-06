package ua.lviv.iot.controller;

import java.sql.SQLException;

public interface Controller<T, K> {
	void getAll() throws SQLException;

	void getById() throws SQLException;

	void create(T entity) throws SQLException;

	void update() throws SQLException;

	void deleteById() throws SQLException;
}
