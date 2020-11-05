package ua.lviv.iot.model.service.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.dao.implementation.AccountOwnerDataAccess;
import ua.lviv.iot.model.entity.AccountOwnerEntity;
import ua.lviv.iot.model.service.AbstractService;

public class AccountOwnerService extends AbstractService<AccountOwnerEntity, Integer> {

	public AccountOwnerService() {

	}

	@Override
	protected AbstractDataAccess<AccountOwnerEntity, Integer> getDao() {
		return new AccountOwnerDataAccess();
	}

}
