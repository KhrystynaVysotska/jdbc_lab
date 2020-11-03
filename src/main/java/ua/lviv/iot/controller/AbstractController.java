package ua.lviv.iot.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import ua.lviv.iot.model.service.Service;

public abstract class AbstractController<T, ID> implements Controller<T, ID> {

	private static final String ERROR_MESSAGE = "Oops...something went wrong";
	private static Scanner input = new Scanner(System.in);

	protected abstract Service<T, ID> getService();

	@Override
	public void getAll() {
		List<T> entities;
		try {
			entities = getService().findAll();
			for (T entity : entities) {
				System.out.println(entity);
			}
		} catch (SQLException e) {
			System.out.println(ERROR_MESSAGE);
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
				System.out.println("Your search result is: \n" + foundedEntity);
			} else {
				System.out.println("Oops...it couldn't be found!");
			}
		} catch (Exception e) {
			System.out.println(ERROR_MESSAGE);
		}
	}

	@Override
	public void create(T entity) {
		T createdEntity;
		try {
			createdEntity = getService().create(entity);
			if (createdEntity != null) {
				System.out.println("Your have just created: \n" + createdEntity);
			} else {
				System.out.println("Oops...it couldn't be created!");
			}
		} catch (Exception e) {
			System.out.println(ERROR_MESSAGE);
		}
	}

	@Override
	public void update(ID id, T entity) {
		T updatedEntity;
		try {
			updatedEntity = getService().update(id, entity);
			if (updatedEntity != null) {
				System.out.println("The modification was performed successfully! Here is your previous object: \n"
						+ updatedEntity);
			} else {
				System.out.println("Oops...it couldn't be updated!");
			}
		} catch (Exception e) {
			System.out.println(ERROR_MESSAGE);
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
				System.out.println("Deleted successfully!");
			} else {
				System.out.println("Oops...it couldn't be deleted!");
			}
		} catch (SQLException e) {
			System.out.println(ERROR_MESSAGE);
		}
	}
}
