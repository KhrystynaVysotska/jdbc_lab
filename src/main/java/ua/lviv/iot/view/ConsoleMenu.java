package ua.lviv.iot.view;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import ua.lviv.iot.controller.implementation.AccountController;
import ua.lviv.iot.controller.implementation.BankController;

public class ConsoleMenu {
	private static final String EXIT = "Q";
	private static final String SELECT_SUBMENU_OPTION = "SELECT SUBMENU OPTION:\n";
	private static final int NUMBER_OF_TABLES_WITH_NUMERIC_KEY = 9;
	private static final String SELECT_MENU_OPTION = "SELECT MENU OPTION:\n";
	private static final int START_INDEX_OF_TABLE_NAME = 7;
	private Map<String, String> menu;
	private Map<String, Printable> methodsMenu;
	private static Scanner input = new Scanner(System.in);

	public ConsoleMenu() {
		menu = new LinkedHashMap<>();
		methodsMenu = new LinkedHashMap<>();
		generateMenuOptions();
		generateMenuMethods();
		generateSubMenu();
	}

	public void showMenu() {
		String choice;
		do {
			openMainMenu();
			choice = input.nextLine().trim();
			if (choice.matches("[a-dA-D1-9]")) {
				openSubMenu(choice.trim().toUpperCase());
				choice = input.nextLine().trim().toUpperCase();
				if (!choice.equals(EXIT)) {
					if (choice.length() == 2)
						methodsMenu.get(choice).print();
					else
						System.out.println("Incorrect option! Try again\n");
				}
			}
		} while (!choice.equals(EXIT));
	}

	public void openMainMenu() {
		System.out.println(SELECT_MENU_OPTION);
		for (int counter = 1; counter <= NUMBER_OF_TABLES_WITH_NUMERIC_KEY; counter++) {
			System.out.println(menu.get(counter + ""));
		}
		System.out.println(menu.get("A"));
		System.out.println(menu.get("B"));
		System.out.println(menu.get("C"));
		System.out.println(menu.get("D"));
		System.out.println(menu.get(EXIT));
	}

	public void openSubMenu(String key) {
		System.out.println();
		System.out.println(menu.get(key).trim().substring(START_INDEX_OF_TABLE_NAME) + "\n");
		System.out.println(SELECT_SUBMENU_OPTION);
		for (String keyMenu : menu.keySet()) {
			if (keyMenu.length() != 1 && keyMenu.substring(0, 1).equals(key)) {
				System.out.println(menu.get(keyMenu));
			}
		}
	}

	private void generateMenuOptions() {
		menu.put("1", "  1   -  BANK");
		menu.put("2", "  2   -  ACCOUNT");
		menu.put("3", "  3   -  ACCOUNT OWNER");
		menu.put("4", "  4   -  BANK CARD");
		menu.put("5", "  5   -  MONEY TRANSFER");
		menu.put("6", "  6   -  ACCOUNT TYPE");
		menu.put("7", "  7   -  CARD TYPE");
		menu.put("8", "  8   -  ADRESS");
		menu.put("9", "  9   -  CITY");
		menu.put("A", "  A   -  STREET");
		menu.put("B", "  B   -  BUILDING");
		menu.put("C", "  C   -  CURRENCY");
		menu.put("D", "  D   -  PIN CODES");
		menu.put("Q", "  Q   -  EXIT");
	}

	private void generateMenuMethods() {
		methodsMenu.put("11", () -> new BankController().create());
		methodsMenu.put("12", () -> new BankController().getById());
		methodsMenu.put("13", () -> new BankController().deleteById());
		methodsMenu.put("14", () -> new BankController().update());
		methodsMenu.put("15", () -> new BankController().getAll());
		methodsMenu.put("21", () -> new AccountController().create());
		methodsMenu.put("22", () -> new AccountController().getById());
		methodsMenu.put("23", () -> new AccountController().deleteById());
		methodsMenu.put("24", () -> new AccountController().update());
		methodsMenu.put("25", () -> new AccountController().getAll());
	}

	private void generateSubMenu() {
		generateBankTableSubMenu();
		generateAccountTableSubMenu();
		generateAccountOwnerTableSubMenu();
		generateBankCardTableSubMenu();
		generateMoneyTransferTableSubMenu();
		generateAccountTypeTableSubMenu();
		generateCardTypeTableSubMenu();
		generateAdressTableSubMenu();
		generateCityTableSubMenu();
		generateStreetTableSubMenu();
		generateBuildingTableSubMenu();
		generateCurrencyTableSubMenu();
		generatePinCodeTableSubMenu();
	}

	private void generateBankTableSubMenu() {
		menu.put("11", "  11 - CREATE NEW BANK");
		menu.put("12", "  12 - GET DATA ABOUT BANK");
		menu.put("13", "  13 - DELETE BANK");
		menu.put("14", "  14 - UPDATE BANK DATA");
		menu.put("15", "  15 - SEE ALL BANKS");
	}

	private void generateAccountTableSubMenu() {
		menu.put("21", "  21 - CREATE NEW ACCOUNT");
		menu.put("22", "  22 - GET DATA ABOUT ACCOUNT");
		menu.put("23", "  23 - DELETE EXISTING ACCOUNT");
		menu.put("24", "  24 - UPDATE ACCOUNT DATA");
		menu.put("25", "  25 - SEE ALL ACCOUNTS");
	}

	private void generateAccountOwnerTableSubMenu() {
		menu.put("31", "  31 - CREATE NEW OWNER");
		menu.put("32", "  32 - GET DATA ABOUT OWNER");
		menu.put("33", "  33 - DELETE OWNER");
		menu.put("34", "  34 - UPDATE OWNER DATA");
		menu.put("35", "  35 - SEE ALL OWNERS");
	}

	private void generateBankCardTableSubMenu() {
		menu.put("41", "  41 - CREATE NEW BANK CARD");
		menu.put("42", "  42 - GET DATA ABOUT BANK CARD");
		menu.put("43", "  43 - DELETE BANK CARD");
		menu.put("44", "  44 - UPDATE BANK CARD DATA");
		menu.put("45", "  45 - SEE ALL BANK CARDS");
	}

	private void generateMoneyTransferTableSubMenu() {
		menu.put("51", "  51 - MAKE NEW TRANSFER");
		menu.put("52", "  52 - GET DATA ABOUT TRANSFER");
		menu.put("53", "  53 - DELETE TRANSFER FROM DATABASE");
		menu.put("54", "  54 - UPDATE TRANSFER DATA");
		menu.put("55", "  55 - SEE TRANSFERS HISTORY");
	}

	private void generateAccountTypeTableSubMenu() {
		menu.put("61", "  61 - CREATE NEW ACCOUNT TYPE");
		menu.put("62", "  62 - FIND ACCOUNT TYPE");
		menu.put("63", "  63 - DELETE ACCOUNT TYPE");
		menu.put("64", "  64 - UPDATE ACCOUNT TYPE");
		menu.put("65", "  65 - SEE ALL ACCOUNT TYPES");
	}

	private void generateCardTypeTableSubMenu() {
		menu.put("71", "  71 - CREATE NEW CARD TYPE");
		menu.put("72", "  72 - FIND CARD TYPE");
		menu.put("73", "  73 - DELETE CARD TYPE");
		menu.put("74", "  74 - UPDATE CARD TYPE");
		menu.put("75", "  75 - SEE ALL CARD TYPES");
	}

	private void generateAdressTableSubMenu() {
		menu.put("81", "  81 - ADD NEW ADRESS");
		menu.put("82", "  82 - FIND ADRESS");
		menu.put("83", "  83 - DELETE ADRESS");
		menu.put("84", "  84 - UPDATE ADRESS");
		menu.put("85", "  85 - SEE ALL ADRESSES");
	}

	private void generateCityTableSubMenu() {
		menu.put("91", "  91 - ADD NEW CITY");
		menu.put("92", "  92 - FIND CITY");
		menu.put("93", "  93 - DELETE CITY");
		menu.put("94", "  94 - UPDATE CITY");
		menu.put("95", "  95 - SEE ALL CITIES");
	}

	private void generateStreetTableSubMenu() {
		menu.put("A1", "  A1 - ADD NEW STREET");
		menu.put("A2", "  A2 - FIND STREET");
		menu.put("A3", "  A3 - DELETE STREET");
		menu.put("A4", "  A4 - UPDATE STREET");
		menu.put("A5", "  A5 - SEE ALL STREETS");
	}

	private void generateBuildingTableSubMenu() {
		menu.put("B1", "  B1 - ADD NEW BUILDING");
		menu.put("B2", "  B2 - FIND BUILDING");
		menu.put("B3", "  B3 - DELETE BUILDING");
		menu.put("B4", "  B4 - UPDATE BUILDING");
		menu.put("B5", "  B5 - SEE ALL BUILDINGS");
	}

	private void generateCurrencyTableSubMenu() {
		menu.put("C1", "  C1 - ADD NEW CURRENCY");
		menu.put("C2", "  C2 - FIND CURRENCY");
		menu.put("C3", "  C3 - DELETE CURRENCY");
		menu.put("C4", "  C4 - UPDATE CURRENCY");
		menu.put("C5", "  C5 - SEE ALL CURRENCY");
	}

	private void generatePinCodeTableSubMenu() {
		menu.put("D1", "  D1 - CREATE NEW PIN CODE");
		menu.put("D2", "  D2 - FIND PIN CODE");
		menu.put("D3", "  D3 - DELETE PIN CODE");
		menu.put("D4", "  D4 - UPDATE PIN CODE");
		menu.put("D5", "  D5 - SEE ALL PIN CODES");
	}
}
