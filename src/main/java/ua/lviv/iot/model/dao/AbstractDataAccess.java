package ua.lviv.iot.model.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import ua.lviv.iot.annotation.Column;
import ua.lviv.iot.annotation.PrimaryKey;
import ua.lviv.iot.annotation.PrimaryKeyComposite;
import ua.lviv.iot.annotation.Table;
import ua.lviv.iot.persistant.ConnectionManager;
import ua.lviv.iot.transformer.Transformer;

public abstract class AbstractDataAccess<T, ID> implements DataAccess<T, ID> {
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
		try (Connection connection = ConnectionManager.getConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(String.format(FIND_ALL, tableName))) {
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						entities.add((T) new Transformer<T>(clazz).convertResultSetToEntity(resultSet));
					}
				}
			}
		}
		return entities;
	}

	@Override
	public T findById(ID id) throws SQLException {
		T entity = null;
		try (Connection connection = ConnectionManager.getConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(String.format(FIND_BY_ID, tableName))) {
				preparedStatement.setObject(1, id);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						entity = (T) new Transformer<T>(clazz).convertResultSetToEntity(resultSet);
						break;
					}
				}
			}
		}
		return entity;
	}

	private String getColumnNames(Field[] fields, T entity) {
		String columnNames = "";
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (!field.isAnnotationPresent(PrimaryKey.class)) {
					if (field.isAnnotationPresent(PrimaryKeyComposite.class)) {
						Object compositePrimaryKey = field.get(entity);
						Field[] innerFields = compositePrimaryKey.getClass().getDeclaredFields();
						columnNames += getColumnNames(innerFields, (T) compositePrimaryKey) + ", ";
					} else {
						columnNames += field.getAnnotation(Column.class).name() + ", ";
					}
				}

			} catch (SecurityException | IllegalAccessException | IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return columnNames.substring(0, columnNames.length() - 2);
	}

	private String getValuesToInsert(Field[] fields, T entity) {
		String valuesToInsert = "";
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (!field.isAnnotationPresent(PrimaryKey.class)) {
					if (field.isAnnotationPresent(PrimaryKeyComposite.class)) {
						Object compositePrimaryKey = field.get(entity);
						Field[] innerFields = compositePrimaryKey.getClass().getDeclaredFields();
						valuesToInsert += getValuesToInsert(innerFields, (T) compositePrimaryKey) + ", ";
					} else {
						valuesToInsert += field.get(entity).toString() + ", ";
					}
				}

			} catch (SecurityException | IllegalAccessException | IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return valuesToInsert.substring(0, valuesToInsert.length() - 2);
	}

	@Override
	public int create(T entity) throws SQLException {
		Field[] fields = entity.getClass().getDeclaredFields();
		String columnNames = getColumnNames(fields, entity);
		String valuesToInsert = getValuesToInsert(fields, entity);
		String insert = "INSERT INTO " + tableName + " (" + columnNames + ") VALUES (" + valuesToInsert + ");";
		try (Connection connection = ConnectionManager.getConnection()) {
			try (Statement statement = connection.createStatement()) {
				return statement.executeUpdate(insert);
			}
		}
	}

	@Override
	public void update(T entity) throws SQLException {

	}

	@Override
	public int delete(ID id) throws SQLException {
		try (Connection connection = ConnectionManager.getConnection()) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(String.format(DELETE, tableName))) {
				preparedStatement.setObject(1, id);
				return preparedStatement.executeUpdate();
			}
		}
	}

}
