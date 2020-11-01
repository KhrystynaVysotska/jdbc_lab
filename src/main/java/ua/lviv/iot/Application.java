package ua.lviv.iot;

import java.sql.SQLException;

import ua.lviv.iot.model.dao.implementation.AccountDataAccess;
import ua.lviv.iot.model.entity.AccountEntity;
import ua.lviv.iot.model.entity.AccountEntityPrimaryKey;

public class Application {

	public Application() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SQLException {
		AccountEntity account = new AccountEntity(new AccountEntityPrimaryKey(1, 2), Long.parseLong("363436314412"),
				123, 3, 4, 2, 5);
		AccountDataAccess data = new AccountDataAccess();
		data.create(account);
	}

}
