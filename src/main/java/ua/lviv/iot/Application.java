package ua.lviv.iot;

import java.sql.SQLException;

import ua.lviv.iot.model.dao.implementation.AccountDataAccess;
import ua.lviv.iot.model.entity.AccountEntity;
import ua.lviv.iot.model.entity.AccountEntityPrimaryKey;
import ua.lviv.iot.model.service.implementation.AccountService;
import ua.lviv.iot.persistant.ConnectionManager;
import ua.lviv.iot.view.ConsoleMenu;

public class Application {
	
	public static void main(String[] args) throws SQLException {
		new ConsoleMenu().showMenu();
		ConnectionManager.closeConnection();
	}

}
