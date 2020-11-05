package ua.lviv.iot.model.service.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.dao.implementation.AccountDataAccess;
import ua.lviv.iot.model.entity.AccountEntity;
import ua.lviv.iot.model.service.AbstractService;

public class AccountService extends AbstractService<AccountEntity, Integer> {

	public AccountService() {

	}

	@Override
	protected AbstractDataAccess<AccountEntity, Integer> getDao() {
		return new AccountDataAccess();
	}
}
