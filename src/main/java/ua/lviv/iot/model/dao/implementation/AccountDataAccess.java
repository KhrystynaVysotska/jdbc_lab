package ua.lviv.iot.model.dao.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.entity.AccountEntity;

public class AccountDataAccess extends AbstractDataAccess<AccountEntity, Integer> {
	public AccountDataAccess() {
		super(AccountEntity.class);
	}
}
