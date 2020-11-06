package ua.lviv.iot.controller.implementation;

import java.util.InputMismatchException;
import java.util.Scanner;
import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.model.entity.CardTypeEntity;
import ua.lviv.iot.model.service.Service;
import ua.lviv.iot.model.service.implementation.CardTypeService;

public class CardTypeController extends AbstractController<CardTypeEntity, Integer> {
	private static Scanner input = new Scanner(System.in, "UTF-8");
	private CardTypeService cardTypeService = new CardTypeService();

	@Override
	protected Service<CardTypeEntity, Integer> getService() {
		return cardTypeService;
	}

	@Override
	public void create() {
		CardTypeEntity cardType = new CardTypeEntity();
		try {
			System.out.println("Enter card name: ");
			String name = input.nextLine();
			cardType.setName(name);
			super.create(cardType);
		} catch (InputMismatchException e) {
			System.out.println("Your input is not valid! Please, follow constraints\n");
			input.next();
		}
	}
}
