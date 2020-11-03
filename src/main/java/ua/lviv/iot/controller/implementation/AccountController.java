package ua.lviv.iot.controller.implementation;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Scanner;

import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.model.entity.AccountEntity;
import ua.lviv.iot.model.entity.AccountEntityPrimaryKey;
import ua.lviv.iot.model.service.Service;
import ua.lviv.iot.model.service.implementation.AccountService;

public class AccountController extends AbstractController<AccountEntity, Integer> {
	private static final String ERROR_MESSAGE = "Oops...something went wrong";
	private static Scanner input = new Scanner(System.in);

	private AccountService accountService = new AccountService();

	@Override
	protected Service<AccountEntity, Integer> getService() {
		return accountService;
	}

	public void update() {
		System.out.println("Enter id of account you want to update: ");
		int id = Integer.parseInt(input.nextLine());
		try {
			AccountEntity account = accountService.findById(id);
			if (account != null) {
				System.out.println("\nChoose and enter the name of field you want to update: \n");
				Field[] fieldNames = account.getClass().getDeclaredFields();
				for (Field field : fieldNames) {
					System.out.println(field.getName() + "\n");
				}
				String fieldToUpdate = input.nextLine();
				for (Field field : fieldNames) {
					field.setAccessible(true);
					if (field.getName().equals(fieldToUpdate)) {
						System.out.println("Enter new value for " + fieldToUpdate + ": ");
						String value = input.nextLine();
						try {
							field.set(account, Integer.parseInt(value));
							super.update(id, account);
						} catch (IllegalArgumentException | IllegalAccessException e) {
							System.out.println("Something went wrong....please, check your inputs and try again");
						}
						break;
					}
				}
			} else {
				System.out.println("Oops...such object does not exist!");
			}
		} catch (SQLException e1) {
			System.out.println(ERROR_MESSAGE);
		}

	}

	public void create() {
		AccountEntity account = new AccountEntity();
		System.out.println("Enter your current account number: ");
		long currentAccountNumber = input.nextLong();
		account.setCurrentAccountNumber(currentAccountNumber);
		System.out.println("Enter pin code id: ");
		int pinCodeId = input.nextInt();
		account.setAccountPrimaryKey(new AccountEntityPrimaryKey(pinCodeId));
		System.out.println("Enter amount of money to keep: ");
		int amount = input.nextInt();
		account.setAmount(amount);
		System.out.println("Enter owner id: ");
		int accountOwnerId = input.nextInt();
		account.setAccountOwnerId(accountOwnerId);
		System.out.println("Enter bank identification code: ");
		int bankIdentificationCode = input.nextInt();
		account.setBankIdentificationCode(bankIdentificationCode);
		System.out.println("Enter currency id: ");
		int currencyId = input.nextInt();
		account.setCurrencyId(currencyId);
		System.out.println("Enter account type id: ");
		int accountTypeId = input.nextInt();
		input.nextLine();
		account.setAccountTypeId(accountTypeId);
		super.create(account);
	}
}
