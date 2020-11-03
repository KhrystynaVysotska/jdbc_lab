package ua.lviv.iot.controller.implementation;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.model.entity.BankEntity;
import ua.lviv.iot.model.service.Service;
import ua.lviv.iot.model.service.implementation.BankService;

public class BankController extends AbstractController<BankEntity, Integer> {
	private static Scanner input = new Scanner(System.in);
	private static final String ERROR_MESSAGE = "Oops...something went wrong\n";
	private BankService bankService = new BankService();

	@Override
	protected Service<BankEntity, Integer> getService() {
		return bankService;
	}

	@Override
	public void getById() {
		BankEntity foundedEntity = null;
		System.out.println("Enter identification code of your bank: ");
		int identificationCode = input.nextInt();
		input.nextLine();
		try {
			foundedEntity = bankService.findById(identificationCode);
			if (foundedEntity != null) {
				System.out.println("Your search result is:\n" + "\n" + foundedEntity + "\n");
			} else {
				System.out.println("Oops...it couldn't be found!\n");
			}
		} catch (Exception e) {
			System.out.println(ERROR_MESSAGE);
		}
	}

	@Override
	public void deleteById() {
		boolean deleteStatus;
		System.out.println("Enter identification code: ");
		int identificationCode = input.nextInt();
		input.nextLine();
		try {
			deleteStatus = bankService.delete(identificationCode);
			if (deleteStatus) {
				System.out.println("Deleted successfully!\n");
			} else {
				System.out.println("Oops...it couldn't be deleted!\n");
			}
		} catch (SQLException e) {
			System.out.println(ERROR_MESSAGE);
		}
	}

	public void create() {
		BankEntity bank = new BankEntity();
		System.out.println("Enter bank's identification code: ");
		int identificationCode = input.nextInt();
		bank.setIdentificationCode(identificationCode);
		System.out.println("Enter bank's state registration code: ");
		int stateRegistrationCode = input.nextInt();
		input.nextLine();
		bank.setStateRegistrationCode(stateRegistrationCode);
		System.out.println("Enter full bank name: ");
		String fullBankName = input.nextLine();
		bank.setFullBankName(fullBankName);
		System.out.println("Enter short bank name: ");
		String shortBankName = input.nextLine();
		bank.setShortBankName(shortBankName);
		System.out.println("Enter bank license number: ");
		int bankLicenseNumber = input.nextInt();
		input.nextLine();
		bank.setBankLicenseNumber(bankLicenseNumber);
		System.out.println("Enter bank license date in yyyy-mm-dd format: ");
		String bankLicenseDate = input.nextLine();
		try {
			bank.setBankLicenseDate(java.sql.Date.valueOf(bankLicenseDate));
		} catch (IllegalArgumentException e) {
			System.out.println("Your date is incorrect! Check and try again!\n");
		}
		super.create(bank);
	}

	@Override
	public void update() {
		System.out.println("Enter identification code of bank you want to update: ");
		int identificationCode = Integer.parseInt(input.nextLine());
		try {
			BankEntity bank = bankService.findById(identificationCode);
			if (bank != null) {
				System.out.println("\nChoose and enter the name of field you want to update:\n");
				Field[] fieldNames = bank.getClass().getDeclaredFields();
				for (Field field : fieldNames) {
					System.out.println(field.getName() + "\n");
				}
				String fieldToUpdate = input.nextLine();
				for (Field field : fieldNames) {
					field.setAccessible(true);
					if (field.getName().equals(fieldToUpdate)) {
						System.out.println("Enter new value for " + fieldToUpdate + ": ");
						Class<?> dataType = field.getType();
						String value = input.nextLine();
						try {
							if (dataType == String.class) {
								field.set(bank, value);
							} else if (dataType == Integer.class) {
								field.set(bank, Integer.valueOf(value));
							} else if (dataType == Date.class) {
								try {
									field.set(bank, Date.valueOf(value));
								} catch (IllegalArgumentException e) {
									System.out.println("Your date is incorrect! Check and try again!\n");
								}
							} else if (dataType == Long.class) {
								field.set(bank, Long.valueOf(value));
							}
							BankEntity updatedEntity = bankService.update(identificationCode, bank);
							if (updatedEntity != null) {
								System.out.println(
										"The modification was performed successfully! Here is your previous object:\n"
												+ "\n" + updatedEntity + "\n");
							} else {
								System.out.println("Oops...it couldn't be updated!\n");
							}
						} catch (IllegalArgumentException | IllegalAccessException e) {
							System.out.println("Something went wrong....please, check your inputs and try again\n");
							e.printStackTrace();
						}
						break;
					}
				}
			} else {
				System.out.println("Oops...such object does not exist!\n");
			}
		} catch (SQLException | SecurityException | IllegalArgumentException e1) {
			System.out.println(ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}
}
