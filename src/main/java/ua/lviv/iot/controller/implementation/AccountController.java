package ua.lviv.iot.controller.implementation;

import java.util.InputMismatchException;
import java.util.Scanner;
import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.model.entity.AccountEntity;
import ua.lviv.iot.model.entity.AccountEntityPrimaryKey;
import ua.lviv.iot.model.service.Service;
import ua.lviv.iot.model.service.implementation.AccountService;

public class AccountController extends AbstractController<AccountEntity, Integer> {
	private static Scanner input = new Scanner(System.in, "UTF-8");

	private AccountService accountService = new AccountService();

	@Override
	protected Service<AccountEntity, Integer> getService() {
		return accountService;
	}

	@Override
	public void create() {
		AccountEntity account = new AccountEntity();
		try {
			System.out.println("Enter your current account number: ");
			long currentAccountNumber = input.nextLong();
			account.setCurrentAccountNumber(currentAccountNumber);
			System.out.println("Enter pin code id: ");
			int pinCodeId = input.nextInt();
			account.setAccountPrimaryKey(new AccountEntityPrimaryKey(pinCodeId));
			System.out.println("Enter amount of money to keep (up to 1 million): ");
			int amount = input.nextInt();
			account.setAmount(amount);
			System.out.println("Enter owner id: ");
			int accountOwnerId = input.nextInt();
			account.setAccountOwnerId(accountOwnerId);
			System.out.println("Enter bank identification code (up to 9 digits): ");
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
		} catch (InputMismatchException e) {
			System.out.println("Your input is not valid! Please, follow constraints!\n");
			input.next();
		}
	}
}
