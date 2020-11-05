package ua.lviv.iot.controller.implementation;

import java.util.InputMismatchException;
import java.util.Scanner;
import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.model.entity.AccountOwnerEntity;
import ua.lviv.iot.model.service.Service;
import ua.lviv.iot.model.service.implementation.AccountOwnerService;

public class AccountOwnerController extends AbstractController<AccountOwnerEntity, Integer> {
	private static Scanner input = new Scanner(System.in, "UTF-8");
	private AccountOwnerService accountOwnerService = new AccountOwnerService();

	@Override
	protected Service<AccountOwnerEntity, Integer> getService() {
		return accountOwnerService;
	}

	public void create() {
		AccountOwnerEntity accountOwner = new AccountOwnerEntity();
		System.out.println("Enter personal identification number if exists (up to 10 digits): ");
		try {
			String personalIdentificationNumber = input.nextLine();
			accountOwner.setPersonalIdentificationNumber(personalIdentificationNumber);
			System.out.println("Enter owner's name (up to 45 letters): ");
			String name = input.nextLine();
			accountOwner.setName(name);
			System.out.println("Enter owner's surname (up to 45 letters): ");
			String surname = input.nextLine();
			accountOwner.setSurname(surname);
			System.out.println("Enter owner's patronym (up to 45 letters): ");
			String patronym = input.nextLine();
			accountOwner.setPatronym(patronym);
			System.out.println("Enter owner's mobile number (up to 13 digits): ");
			String mobileNumber = input.nextLine();
			accountOwner.setMobileNumber(mobileNumber);
			System.out.println("Enter owner's email (up to 45 symbols): ");
			String email = input.nextLine();
			while (!email.matches("^[\\w-\\.]+@[\\w]+[\\.][\\w-]{2,4}$")) {
				System.out.println("Wrong format! Please, input correct email");
				email = input.nextLine();
			}
			accountOwner.setEmail(email);
			System.out.println("Enter owner's birth date in yyyy-mm-dd format: ");
			String birthDate = input.nextLine();
			while (!birthDate.matches("[\\d]{4}([-][\\d]{2}){2}")) {
				System.out.println("Wrong format! Please, input date in format yyyy-mm-dd");
				birthDate = input.nextLine();
			}
			try {
				accountOwner.setBirthDate(java.sql.Date.valueOf(birthDate));
			} catch (IllegalArgumentException e) {
				System.out.println("Your date is incorrect! Check and try again!\n");
			}
			System.out.println("Enter owner's address id: ");
			int addressId = input.nextInt();
			input.nextLine();
			accountOwner.setAddressId(addressId);
			super.create(accountOwner);
		} catch (InputMismatchException e) {
			System.out.println("Your input is not valid! Please, follow constraints\n");
			input.next();
		}
	}
}
