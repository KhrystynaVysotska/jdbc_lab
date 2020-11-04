package ua.lviv.iot.controller;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

import ua.lviv.iot.model.entity.formatter.Formatter;
import ua.lviv.iot.model.service.Service;
import ua.lviv.iot.persistant.ConnectionManager;

public abstract class AbstractController<T, ID> implements Controller<T, ID> {

	private static final String ERROR_MESSAGE = "Oops...something went wrong\n";
	private static Scanner input = new Scanner(System.in);

	protected abstract Service<T, ID> getService();

	@Override
	public void getAll() {
		List<T> entities;
		Formatter<T> formatter = new Formatter<>();
		try {
			entities = getService().findAll();
			formatter.formatTable(entities);
		} catch (SQLException e) {
			System.out.println(ERROR_MESSAGE + e.getMessage());
		}

	}

	@Override
	public void getById() {
		T foundedEntity = null;
		System.out.println("Enter id: ");
		ID id = (ID) input.next();
		input.nextLine();
		try {
			foundedEntity = getService().findById(id);
			if (foundedEntity != null) {
				System.out.println("Your search result is:\n" + "\n" + foundedEntity + "\n");
			} else {
				System.out.println("Oops...it couldn't be found!\n");
			}
		} catch (Exception e) {
			System.out.println(ERROR_MESSAGE + e.getMessage());
		}
	}

	@Override
	public void create(T entity) {
		T createdEntity;
		try {
			createdEntity = getService().create(entity);
			if (createdEntity != null) {
				System.out.println("Your have just created:\n" + "\n" + createdEntity + "\n");
			} else {
				System.out.println("Oops...it couldn't be created!\n");
			}
		} catch (MysqlDataTruncation e) {
			System.out.println(e.getMessage() + "!Please, follow constraints\n");
		} catch (Exception e1) {
			System.out.println(ERROR_MESSAGE + e1.getMessage());
		}
	}

	@Override
	public void update() {
		System.out.println("Enter id of entity you want to update: ");
		ID id = (ID) input.nextLine();
		try {
			T entity = getService().findById(id);
			if (entity != null) {
				System.out.println("\nChoose and enter the name of field you want to update:\n");
				Field[] fieldNames = entity.getClass().getDeclaredFields();
				for (Field field : fieldNames) {
					System.out.println(field.getName() + "\n");
				}
				String fieldToUpdate = input.nextLine();
				for (Field field : fieldNames) {
					field.setAccessible(true);
					if (field.getName().equals(fieldToUpdate)) {
						System.out.println("Enter new value for " + fieldToUpdate + ": ");
						Class<?> dataType = field.getType();
						String value = input.nextLine();
						try {
							if (dataType == String.class) {
								field.set(entity, value);
							} else if (dataType == Integer.class) {
								field.set(entity, Integer.valueOf(value));
							} else if (dataType == Date.class) {
								while (!value.matches("[\\d]{4}([-][\\d]{2}){2}")) {
									System.out.println("Wrong format! Please, input date in format yyyy-mm-dd");
									value = input.nextLine();
								}
								try {
									field.set(entity, Date.valueOf(value));
								} catch (IllegalArgumentException e) {
									System.out.println("Your date is incorrect! Check and try again!\n");
								}
							} else if (dataType == Long.class) {
								field.set(entity, Long.valueOf(value));
							} else if (dataType == Time.class) {
								while (!value.matches("[\\d]{2}([:][\\d]{2}){2}")) {
									System.out.println("Wrong format! Please, input time in format hh-mm-ss");
									value = input.nextLine();
								}
								try {
									field.set(entity, Time.valueOf(value));
								} catch (IllegalArgumentException e) {
									System.out.println("Your date is incorrect! Check and try again!\n");
								}
							}
							T updatedEntity = getService().update(id, entity);
							if (updatedEntity != null) {
								System.out.println(
										"The modification was performed successfully! Here is your previous object:\n"
												+ "\n" + updatedEntity + "\n");
							} else {
								System.out.println("Oops...it couldn't be updated!\n");
							}
						} catch (IllegalArgumentException | IllegalAccessException e) {
							System.out.println("Something went wrong....please, check your inputs and try again\n");
							e.printStackTrace();
						}
						break;
					}
				}
			} else {
				System.out.println("Oops...such object does not exist!\n");
			}
		} catch (SQLException | SecurityException | IllegalArgumentException e1) {
			System.out.println(ERROR_MESSAGE + e1.getMessage());
		}
	}

	@Override
	public void deleteById() {
		boolean deleteStatus;
		System.out.println("Enter id: ");
		ID id = (ID) input.next();
		input.nextLine();
		try {
			deleteStatus = getService().delete(id);
			if (deleteStatus) {
				System.out.println("Deleted successfully!\n");
			} else {
				System.out.println("Oops...it couldn't be deleted!\n");
			}
		} catch (SQLException e) {
			System.out.println(ERROR_MESSAGE + e.getMessage());
		}
	}

	public abstract void create();

	public void exit() {
		input.close();
		ConnectionManager.closeConnection();
	}
}
