package ua.lviv.iot.model.dao.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.entity.AccountOwnerEntity;

public class AccountOwnerDataAccess extends AbstractDataAccess<AccountOwnerEntity, Integer> {

	public AccountOwnerDataAccess() {
		super(AccountOwnerEntity.class);
	}

}
