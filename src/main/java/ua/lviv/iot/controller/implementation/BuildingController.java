package ua.lviv.iot.controller.implementation;

import java.util.InputMismatchException;
import java.util.Scanner;
import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.model.entity.BuildingEntity;
import ua.lviv.iot.model.service.Service;
import ua.lviv.iot.model.service.implementation.BuildingService;

public class BuildingController extends AbstractController<BuildingEntity, Integer> {
	private static Scanner input = new Scanner(System.in);
	private BuildingService buildingService = new BuildingService();

	@Override
	protected Service<BuildingEntity, Integer> getService() {
		return buildingService;
	}

	@Override
	public void create() {
		BuildingEntity building = new BuildingEntity();
		try {
			System.out.println("Enter house number: ");
			String houseNumber = input.nextLine();
			building.setHouseNumber(houseNumber);
			System.out.println("Enter flat number: ");
			String flatNumber = input.nextLine();
			building.setFlatNumber(flatNumber);
			super.create(building);
		} catch (InputMismatchException e) {
			System.out.println("Your input is too long! Please, follow constraints for its length\n");
			input.next();
		}
	}

}
