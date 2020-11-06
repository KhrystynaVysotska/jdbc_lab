package ua.lviv.iot.model.service.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.dao.implementation.AccountTypeDataAccess;
import ua.lviv.iot.model.entity.AccountTypeEntity;
import ua.lviv.iot.model.service.AbstractService;

public class AccountTypeService extends AbstractService<AccountTypeEntity, Integer> {

	private AccountTypeDataAccess accountTypeDataAccess;

	public AccountTypeService() {
		accountTypeDataAccess = new AccountTypeDataAccess();
	}

	@Override
	protected AbstractDataAccess<AccountTypeEntity, Integer> getDao() {
		return accountTypeDataAccess;
	}
}
