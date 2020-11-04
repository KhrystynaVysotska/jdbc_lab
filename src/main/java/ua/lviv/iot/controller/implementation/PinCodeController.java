package ua.lviv.iot.controller.implementation;

import java.util.InputMismatchException;
import java.util.Scanner;
import ua.lviv.iot.controller.AbstractController;
import ua.lviv.iot.model.entity.PinCodeEntity;
import ua.lviv.iot.model.service.Service;
import ua.lviv.iot.model.service.implementation.PinCodeService;

public class PinCodeController extends AbstractController<PinCodeEntity, Integer> {
	private static Scanner input = new Scanner(System.in);
	private PinCodeService pinCodeService = new PinCodeService();

	@Override
	protected Service<PinCodeEntity, Integer> getService() {
		return pinCodeService;
	}

	@Override
	public void create() {
		PinCodeEntity pinCode = new PinCodeEntity();
		try {
			System.out.println("Enter your pin code: ");
			String pin = input.nextLine();
			pinCode.setPin(pin);
			super.create(pinCode);
		} catch (InputMismatchException e) {
			System.out.println("Your input is too long! Please, follow constraints for its length\n");
			input.next();
		}
	}

}
