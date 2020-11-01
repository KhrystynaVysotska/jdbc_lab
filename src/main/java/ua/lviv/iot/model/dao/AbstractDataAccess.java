package ua.lviv.iot.model.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
	private static final String FIND_ALL = "SELECT * FROM ?";
	private static final String FIND_BY_ID = "SELECT * FROM ? WHERE id = ?";
	private static final String DELETE = "DELETE FROM ? WHERE id = ?";

	public AbstractDataAccess(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public List<T> findAll() throws SQLException {
		List<T> entities = new LinkedList<>();
		String tableName = clazz.getAnnotation(Table.class).name();
		try (Connection connection = ConnectionManager.getConnection()) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
				preparedStatement.setString(1, tableName);
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
		String tableName = clazz.getAnnotation(Table.class).name();
		try (Connection connection = ConnectionManager.getConnection()) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
				preparedStatement.setString(1, tableName);
				preparedStatement.setString(2, id.toString());
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
						Class<?> fieldDataType = field.getType();
						Object compositePrimaryKey = fieldDataType.getConstructor().newInstance();
						Field[] innerFields = compositePrimaryKey.getClass().getDeclaredFields();
						for (Field innerField : innerFields) {
							if (!innerField.isAnnotationPresent(PrimaryKey.class)) {
								columnNames += innerField.getAnnotation(Column.class).name() + ", ";
							}
						}

					} else {
						columnNames += field.getAnnotation(Column.class).name() + ", ";
					}
				}

			} catch (InstantiationException | InvocationTargetException | NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return columnNames.substring(0, columnNames.length() - 1);
	}

	private String getValuesToInsert(Field[] fields, T entity) {
		String valuesToInsert = "";
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (!field.isAnnotationPresent(PrimaryKey.class)) {
					if (field.isAnnotationPresent(PrimaryKeyComposite.class)) {
						Class<?> fieldDataType = field.getType();
						Object compositePrimaryKey = fieldDataType.getConstructor().newInstance();
						Field[] innerFields = compositePrimaryKey.getClass().getDeclaredFields();
						for (Field innerField : innerFields) {
							if (!innerField.isAnnotationPresent(PrimaryKey.class)) {
								valuesToInsert += innerField.get(entity).toString() + ", ";
							}
						}

					} else {
						valuesToInsert += field.get(entity).toString() + ", ";
					}
				}

			} catch (InstantiationException | InvocationTargetException | NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return valuesToInsert.substring(0, valuesToInsert.length()-1);
	}

	@Override
	public void create(T entity) throws SQLException {
		String tableName = clazz.getAnnotation(Table.class).name();
		Field[] fields = entity.getClass().getDeclaredFields();
		String columnNames = getColumnNames(fields, entity);
		String valuesToInsert = getValuesToInsert(fields, entity);
		String insert = "INSERT INTO " + tableName + " (" + columnNames + ") VALUES (" + valuesToInsert + ");";
		try (Connection connection = ConnectionManager.getConnection()) {
			try (Statement statement = connection.createStatement()) {
				statement.executeQuery(insert);
			}
		}
	}

	@Override
	public void update(T entity) throws SQLException {
	
	}

	@Override
	public int delete(ID id) throws SQLException {
		String tableName = clazz.getAnnotation(Table.class).name();
		try (Connection connection = ConnectionManager.getConnection()) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
				preparedStatement.setString(1, tableName);
				preparedStatement.setString(2, id.toString());
				return preparedStatement.executeUpdate();
			}
		}
	}

}
