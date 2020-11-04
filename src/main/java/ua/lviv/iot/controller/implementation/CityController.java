package ua.lviv.iot.controller.implementation;

import java.util.InputMismatchException;
import java.util.Scanner;

import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.model.entity.CityEntity;
import ua.lviv.iot.model.service.Service;
import ua.lviv.iot.model.service.implementation.CityService;

public class CityController extends AbstractController<CityEntity, Integer> {
	private static Scanner input = new Scanner(System.in);
	private CityService cityService = new CityService();

	@Override
	protected Service<CityEntity, Integer> getService() {
		return cityService;
	}

	@Override
	public void create() {
		CityEntity city = new CityEntity();
		try {
			System.out.println("Enter city name: ");
			String name = input.nextLine();
			city.setName(name);
			System.out.println("Enter zip code: ");
			int zipCode = input.nextInt();
			input.nextLine();
			city.setZipCode(zipCode);
			System.out.println("Enter phone code: ");
			String phoneCode = input.nextLine();
			city.setPhoneCode(phoneCode);
			super.create(city);
		} catch (InputMismatchException e) {
			System.out.println("Your input is too long! Please, follow constraints for its length\n");
			input.next();
		}

	}

}
