package ua.lviv.iot.controller.implementation;

import java.util.InputMismatchException;
import java.util.Scanner;

import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.model.entity.AddressEntity;
import ua.lviv.iot.model.service.Service;
import ua.lviv.iot.model.service.implementation.AddressService;

public class AddressController extends AbstractController<AddressEntity, Integer> {
	private static Scanner input = new Scanner(System.in);
	private AddressService addressService = new AddressService();

	@Override
	protected Service<AddressEntity, Integer> getService() {
		return addressService;
	}

	@Override
	public void create() {
		AddressEntity address = new AddressEntity();
		try {
			System.out.println("Enter city id: ");
			Integer cityId = input.nextInt();
			input.nextLine();
			address.setCityId(cityId);
			System.out.println("Enter street id: ");
			Integer streetId = input.nextInt();
			input.nextLine();
			address.setStreetId(streetId);
			System.out.println("Enter building id: ");
			Integer buildingId = input.nextInt();
			input.nextLine();
			address.setBuildingId(buildingId);
			super.create(address);
		} catch (InputMismatchException e) {
			System.out.println("Your input is too long! Please, follow constraints for its length\n");
			input.next();
		}
	}
}
