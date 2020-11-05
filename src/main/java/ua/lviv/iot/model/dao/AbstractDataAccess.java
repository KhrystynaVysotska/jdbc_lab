package ua.lviv.iot.model.dao;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import ua.lviv.iot.annotation.Autoincremented;
import ua.lviv.iot.annotation.Column;
import ua.lviv.iot.annotation.PrimaryKey;
import ua.lviv.iot.annotation.PrimaryKeyComposite;
import ua.lviv.iot.annotation.Table;
import ua.lviv.iot.persistant.ConnectionManager;
import ua.lviv.iot.transformer.Transformer;

public abstract class AbstractDataAccess<T, K> implements DataAccess<T, K> {
	private Class<T> clazz;
	private String tableName;
	private static final String FIND_ALL = "SELECT * FROM %s;";
	private static final String FIND_BY_ID = "SELECT * FROM %s WHERE id = ?;";
	private static final String DELETE = "DELETE FROM %s WHERE id = ?;";

	public AbstractDataAccess(Class<T> clazz) {
		this.clazz = clazz;
		this.tableName = clazz.getAnnotation(Table.class).name();
	}

	@Override
	public List<T> findAll() throws SQLException {
		List<T> entities = new LinkedList<>();
		Connection connection = ConnectionManager.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(String.format(FIND_ALL, tableName))) {
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					entities.add((T) new Transformer<T>(clazz).convertResultSetToEntity(resultSet));
				}
			}
		}

		return entities;
	}

	@Override
	public T findById(K id) throws SQLException {
		T entity = null;
		Connection connection = ConnectionManager.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(String.format(FIND_BY_ID, tableName))) {
			preparedStatement.setObject(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					entity = (T) new Transformer<T>(clazz).convertResultSetToEntity(resultSet);
				}
			}

		}
		return entity;
	}

	private String getColumnNames(Field[] fields, T entity) {
		StringBuffer buffer = new StringBuffer();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (!field.isAnnotationPresent(Autoincremented.class)) {
					if (field.isAnnotationPresent(PrimaryKeyComposite.class)) {
						Object compositePrimaryKey = field.get(entity);
						Field[] innerFields = compositePrimaryKey.getClass().getDeclaredFields();
						buffer.append(getColumnNames(innerFields, (T) compositePrimaryKey)).append(", ");
					} else {
						buffer.append(field.getAnnotation(Column.class).name()).append(", ");
					}
				}

			} catch (SecurityException | IllegalAccessException | IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return buffer.substring(0, buffer.length() - 2);
	}

	private String getValuesToInsert(Field[] fields, T entity) {
		StringBuffer buffer = new StringBuffer();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (!field.isAnnotationPresent(Autoincremented.class)) {
					if (field.isAnnotationPresent(PrimaryKeyComposite.class)) {
						Object compositePrimaryKey = field.get(entity);
						Field[] innerFields = compositePrimaryKey.getClass().getDeclaredFields();
						buffer.append(getValuesToInsert(innerFields, (T) compositePrimaryKey)).append(", ");
					} else {
						buffer.append("'").append(field.get(entity).toString()).append("',");
					}
				}

			} catch (SecurityException | IllegalAccessException | IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return buffer.substring(0, buffer.length() - 1);
	}

	@Override
	public int create(T entity) throws SQLException, MysqlDataTruncation {
		Field[] fields = entity.getClass().getDeclaredFields();
		String columnNames = getColumnNames(fields, entity);
		String valuesToInsert = getValuesToInsert(fields, entity);
		String insert = "INSERT INTO " + tableName + " (" + columnNames + ") VALUES (" + valuesToInsert + ");";
		Connection connection = ConnectionManager.getConnection();
		try (Statement statement = connection.createStatement()) {
			return statement.executeUpdate(insert);
		}
	}

	private String getCondition(Field[] fields, T entity) {
		StringBuffer buffer = new StringBuffer();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (field.isAnnotationPresent(PrimaryKey.class) && field.isAnnotationPresent(Column.class)) {
					String fieldName = field.getAnnotation(Column.class).name();
					buffer.append(fieldName).append("=");
					String fieldValue = field.get(entity).toString();
					buffer.append(fieldValue);
				} else if (field.isAnnotationPresent(PrimaryKeyComposite.class)) {
					Object compositePrimaryKey;
					compositePrimaryKey = field.get(entity);
					Field[] innerFields = compositePrimaryKey.getClass().getDeclaredFields();
					buffer.append(getCondition(innerFields, (T) compositePrimaryKey));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return buffer.toString();
	}

	private String getValuesToUpdate(Field[] fields, T entity) {
		String[] columnNames = getColumnNames(fields, entity).split(",");
		String[] values = getValuesToInsert(fields, entity).split(",");
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < columnNames.length; i++) {
			buffer.append(columnNames[i].trim()).append("=").append(values[i].trim()).append(", ");
		}
		return buffer.substring(0, buffer.length() - 2);
	}

	@Override
	public int update(T entity) throws SQLException {
		Field[] fields = entity.getClass().getDeclaredFields();
		String valuesToUpdate = getValuesToUpdate(fields, entity);
		String condition = getCondition(fields, entity);
		String update = "UPDATE " + tableName + " SET " + valuesToUpdate + " WHERE " + condition + ";";
		Connection connection = ConnectionManager.getConnection();
		try (Statement statement = connection.createStatement()) {
			return statement.executeUpdate(update);
		}
	}

	@Override
	public int delete(K id) throws SQLException {
		Connection connection = ConnectionManager.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(String.format(DELETE, tableName))) {
			preparedStatement.setObject(1, id);
			return preparedStatement.executeUpdate();
		}
	}

}
