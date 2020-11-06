package ua.lviv.iot.model.dao.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.entity.AccountTypeEntity;

public class AccountTypeDataAccess extends AbstractDataAccess<AccountTypeEntity, Integer> {

	public AccountTypeDataAccess() {
		super(AccountTypeEntity.class);
	}

}
