package ua.lviv.iot.controller.implementation;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;
import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.model.entity.TransferEntity;
import ua.lviv.iot.model.service.Service;
import ua.lviv.iot.model.service.implementation.TransferService;

public class TransferController extends AbstractController<TransferEntity, Integer> {
	private static Scanner input = new Scanner(System.in, "UTF-8");
	private TransferService transferService = new TransferService();

	@Override
	protected Service<TransferEntity, Integer> getService() {
		return transferService;
	}

	@Override
	public void create() {
		TransferEntity transfer = new TransferEntity();
		System.out.println("Enter sender account id: ");
		try {
			int senderAccountId = input.nextInt();
			input.nextLine();
			transfer.setSenderAccountId(senderAccountId);
			System.out.println("Enter recipient account id: ");
			int recipientAccountId = input.nextInt();
			input.nextLine();
			transfer.setRecipientAccountId(recipientAccountId);
			System.out.println("Enter amount of money to transfer (up to 1 million): ");
			int amount = input.nextInt();
			input.nextLine();
			transfer.setAmount(amount);
			System.out.println("Enter currency id: ");
			int currencyId = input.nextInt();
			input.nextLine();
			transfer.setCurrencyId(currencyId);
			Date date = new Date(new java.util.Date().getTime());
			transfer.setDate(date);
			Time time = Time.valueOf(LocalTime.now());
			transfer.setTime(time);
			System.out.println("Enter purpose of payment: ");
			String purposeOfPayment = input.nextLine();
			transfer.setPurposeOfPayment(purposeOfPayment);
			transferService.makeTransaction(transfer);
		} catch (InputMismatchException e) {
			System.out.println("Your input is not valid! Please, follow constraints\n");
			input.next();
		} catch (SQLException e1) {
			System.out.println("Transaction failed!" + e1.getMessage());
		}
	}
}
