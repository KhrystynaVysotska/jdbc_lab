package ua.lviv.iot.controller.implementation;

import java.util.InputMismatchException;
import java.util.Scanner;
import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.model.entity.CurrencyEntity;
import ua.lviv.iot.model.service.Service;
import ua.lviv.iot.model.service.implementation.CurrencyService;

public class CurrencyController extends AbstractController<CurrencyEntity, Integer> {

	private static Scanner input = new Scanner(System.in, "UTF-8");
	private CurrencyService currencyService = new CurrencyService();

	public CurrencyController() {

	}

	@Override
	protected Service<CurrencyEntity, Integer> getService() {
		return currencyService;
	}

	@Override
	public void create() {
		CurrencyEntity currency = new CurrencyEntity();
		try {
			System.out.println("Enter currency name: ");
			String name = input.nextLine();
			currency.setName(name);
			super.create(currency);
		} catch (InputMismatchException e) {
			System.out.println("Your input is not valid! Please, follow constraints\n");
			input.next();
		}
	}

}
