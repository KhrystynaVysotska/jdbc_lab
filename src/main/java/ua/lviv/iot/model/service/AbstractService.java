package ua.lviv.iot.model.service;

import java.sql.SQLException;
import java.util.List;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

import ua.lviv.iot.model.dao.AbstractDataAccess;

public abstract class AbstractService<T, ID> implements Service<T, ID> {
	protected abstract AbstractDataAccess<T, ID> getDAO();

	@Override
	public List<T> findAll() throws SQLException {
		return getDAO().findAll();
	}

	@Override
	public T findById(ID id) throws SQLException {
		return getDAO().findById(id);
	}

	@Override
	public T create(T entity) throws SQLException, MysqlDataTruncation {
		int result = getDAO().create(entity);
		if (result != 0) {
			List<T> entities = findAll();
			T createdEntity = entities.get(entities.size() - 1);
			return createdEntity;
		} else {
			return null;
		}
	}

	@Override
	public T update(ID id, T entity) throws SQLException {
		T oldEntity = findById(id);
		getDAO().update(entity);
		return oldEntity;
	}

	@Override
	public boolean delete(ID id) throws SQLException {
		return getDAO().delete(id) != 0;
	}

}
