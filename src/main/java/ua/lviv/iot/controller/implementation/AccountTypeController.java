package ua.lviv.iot.controller.implementation;

import java.util.InputMismatchException;
import java.util.Scanner;

import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.model.entity.AccountTypeEntity;
import ua.lviv.iot.model.service.Service;
import ua.lviv.iot.model.service.implementation.AccountTypeService;

public class AccountTypeController extends AbstractController<AccountTypeEntity, Integer> {
	private static Scanner input = new Scanner(System.in);
	private AccountTypeService accountTypeService = new AccountTypeService();

	@Override
	protected Service<AccountTypeEntity, Integer> getService() {
		return accountTypeService;
	}

	@Override
	public void create() {
		AccountTypeEntity accountType = new AccountTypeEntity();
		try {
			System.out.println("Enter account type: ");
			String type = input.nextLine();
			accountType.setType(type);
			super.create(accountType);
		} catch (InputMismatchException e) {
			System.out.println("Your input is too long! Please, follow constraints for its length\n");
			input.next();
		}
	}
}
