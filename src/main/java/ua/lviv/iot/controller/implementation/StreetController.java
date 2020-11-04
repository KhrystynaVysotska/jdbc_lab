package ua.lviv.iot.controller.implementation;

import java.util.InputMismatchException;
import java.util.Scanner;
import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.model.entity.StreetEntity;
import ua.lviv.iot.model.service.Service;
import ua.lviv.iot.model.service.implementation.StreetService;

public class StreetController extends AbstractController<StreetEntity, Integer> {
	private static Scanner input = new Scanner(System.in);
	private StreetService streetService = new StreetService();

	@Override
	protected Service<StreetEntity, Integer> getService() {
		return streetService;
	}

	@Override
	public void create() {
		StreetEntity street = new StreetEntity();
		try {
			System.out.println("Enter street name: ");
			String name = input.nextLine();
			street.setName(name);
			super.create(street);
		} catch (InputMismatchException e) {
			System.out.println("Your input is too long! Please, follow constraints for its length\n");
			input.next();
		}

	}

}
