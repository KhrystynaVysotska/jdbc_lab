package ua.lviv.iot.transformer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import ua.lviv.iot.annotation.Column;
import ua.lviv.iot.annotation.PrimaryKeyComposite;
import ua.lviv.iot.annotation.Table;

public class Transformer<T> {
	private final Class<T> clazz;

	public Transformer(Class<T> clazz) {
		this.clazz = clazz;
	}

	public Object convertResultSetToEntity(ResultSet resultSet) {
		Object entity = null;
		try {
			entity = clazz.getConstructor().newInstance();
			if (clazz.isAnnotationPresent(Table.class)) {
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					if (field.isAnnotationPresent(Column.class)) {
						Column column = field.getAnnotation(Column.class);
						String columnName = column.name();
						field.setAccessible(true);
						Class<?> fieldDataType = field.getType();
						if (fieldDataType == String.class) {
							field.set(entity, resultSet.getString(columnName));
						} else if (fieldDataType == Integer.class) {
							field.set(entity, resultSet.getInt(columnName));
						} else if (fieldDataType == Date.class) {
							field.set(entity, resultSet.getDate(columnName));
						} else if (fieldDataType == Long.class) {
							field.set(entity, resultSet.getLong(columnName));
						} else if (fieldDataType == Time.class) {
							field.set(entity, resultSet.getTime(columnName));
						}
					}
					if (field.isAnnotationPresent(PrimaryKeyComposite.class)) {
						Class<?> fieldDataType = field.getType();
						Object compositePrimaryKey = fieldDataType.getConstructor().newInstance();
						Field[] fieldsOfCompositePrimaryKey = fieldDataType.getDeclaredFields();
						for (Field fieldOfCompositePrimaryKey : fieldsOfCompositePrimaryKey) {
							fieldOfCompositePrimaryKey.setAccessible(true);
							if (fieldOfCompositePrimaryKey.isAnnotationPresent(Column.class)) {
								Column column = fieldOfCompositePrimaryKey.getAnnotation(Column.class);
								String columnName = column.name();
								Class<?> fieldOfCompositePrimaryKeyDataType = fieldOfCompositePrimaryKey.getType();
								if (fieldOfCompositePrimaryKeyDataType == Integer.class) {
									fieldOfCompositePrimaryKey.set(compositePrimaryKey, resultSet.getInt(columnName));
								} else if (fieldOfCompositePrimaryKeyDataType == String.class) {
									fieldOfCompositePrimaryKey.set(compositePrimaryKey,
											resultSet.getString(columnName));
								}
							}
						}
						field.set(entity, compositePrimaryKey);
					}
				}
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}
}
