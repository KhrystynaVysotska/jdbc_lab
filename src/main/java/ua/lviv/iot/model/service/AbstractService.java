package ua.lviv.iot.model.service;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import java.sql.SQLException;
import java.util.List;
import ua.lviv.iot.model.dao.AbstractDataAccess;

public abstract class AbstractService<T, K> implements Service<T, K> {
	protected abstract AbstractDataAccess<T, K> getDao();

	@Override
	public List<T> findAll() throws SQLException {
		return getDao().findAll();
	}

	@Override
	public T findById(K id) throws SQLException {
		return getDao().findById(id);
	}

	@Override
	public T create(T entity) throws SQLException, MysqlDataTruncation {
		int result = getDao().create(entity);
		if (result != 0) {
			List<T> entities = findAll();
			T createdEntity = entities.get(entities.size() - 1);
			return createdEntity;
		} else {
			return null;
		}
	}

	@Override
	public T update(K id, T entity) throws SQLException {
		T oldEntity = findById(id);
		getDao().update(entity);
		return oldEntity;
	}

	@Override
	public boolean delete(K id) throws SQLException {
		return getDao().delete(id) != 0;
	}

}
