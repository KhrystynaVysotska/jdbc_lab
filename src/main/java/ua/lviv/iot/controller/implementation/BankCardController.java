package ua.lviv.iot.controller.implementation;

import java.util.InputMismatchException;
import java.util.Scanner;

import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.model.entity.BankCardEntity;
import ua.lviv.iot.model.service.Service;
import ua.lviv.iot.model.service.implementation.BankCardService;

public class BankCardController extends AbstractController<BankCardEntity, Integer>{
	private static Scanner input = new Scanner(System.in);
	private BankCardService bankCardService = new BankCardService();
	@Override
	protected Service<BankCardEntity, Integer> getService() {
		return bankCardService;
	}
	
	@Override
	public void create() {
		BankCardEntity bankCard = new BankCardEntity();
		try {
			System.out.println("Enter your account id: ");
			int accountId = input.nextInt();
			input.nextLine();
			bankCard.setAccountId(accountId);
			System.out.println("Enter card type id: ");
			int cardTypeId = input.nextInt();
			input.nextLine();
			bankCard.setCardTypeId(cardTypeId);
			System.out.println("Enter cvc2 of your card (if exists): ");
			int cvc2 = input.nextInt();
			input.nextLine();
			bankCard.setCvc2(cvc2);
			System.out.println("Enter date of your card's expire in yyyy-mm-dd format: ");
			String dateOfExpire = input.nextLine();
			while (!dateOfExpire.matches("[\\d]{4}([-][\\d]{2}){2}")) {
				System.out.println("Wrong format! Please, input date in format yyyy-mm-dd");
				dateOfExpire = input.nextLine();
			}
			try {
				bankCard.setDateOfExpire(java.sql.Date.valueOf(dateOfExpire));
			} catch (IllegalArgumentException e) {
				System.out.println("Your date is incorrect! Check and try again!\n");
			}
			super.create(bankCard);
		} catch (InputMismatchException e) {
			System.out.println("Your input is too long! Please, follow constraints for its length\n");
			input.next();
		}
	} 

}
