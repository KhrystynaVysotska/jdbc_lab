package ua.lviv.iot.controller.implementation;

import java.util.InputMismatchException;
import java.util.Scanner;
import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.model.entity.BankEntity;
import ua.lviv.iot.model.service.Service;
import ua.lviv.iot.model.service.implementation.BankService;

public class BankController extends AbstractController<BankEntity, Integer> {
	private static Scanner input = new Scanner(System.in, "UTF-8");
	private BankService bankService = new BankService();

	@Override
	protected Service<BankEntity, Integer> getService() {
		return bankService;
	}

	public void create() {
		BankEntity bank = new BankEntity();
		System.out.println("Enter bank's identification code (up to 10 digits): ");
		try {
			int identificationCode = input.nextInt();
			input.nextLine();
			bank.setIdentificationCode(identificationCode);
			System.out.println("Enter bank's state registration code (up to 10 digits): ");
			int stateRegistrationCode = input.nextInt();
			input.nextLine();
			bank.setStateRegistrationCode(stateRegistrationCode);
			System.out.println("Enter full bank name: ");
			String fullBankName = input.nextLine();
			bank.setFullBankName(fullBankName);
			System.out.println("Enter short bank name: ");
			String shortBankName = input.nextLine();
			bank.setShortBankName(shortBankName);
			System.out.println("Enter bank license number (up to 10 digits): ");
			int bankLicenseNumber = input.nextInt();
			input.nextLine();
			bank.setBankLicenseNumber(bankLicenseNumber);
			System.out.println("Enter bank license date in yyyy-mm-dd format: ");
			String bankLicenseDate = input.nextLine();
			while (!bankLicenseDate.matches("[\\d]{4}([-][\\d]{2}){2}")) {
				System.out.println("Wrong format! Please, input date in format yyyy-mm-dd");
				bankLicenseDate = input.nextLine();
			}
			try {
				bank.setBankLicenseDate(java.sql.Date.valueOf(bankLicenseDate));
			} catch (IllegalArgumentException e) {
				System.out.println("Your date is incorrect! Check and try again!\n");
			}
			super.create(bank);
		} catch (InputMismatchException e) {
			System.out.println("Your input is not valid! Please, follow constraints\n");
			input.next();
		}
	}
}
