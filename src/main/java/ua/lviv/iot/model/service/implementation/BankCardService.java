package ua.lviv.iot.model.service.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.dao.implementation.BankCardDataAccess;
import ua.lviv.iot.model.entity.BankCardEntity;
import ua.lviv.iot.model.service.AbstractService;

public class BankCardService extends AbstractService<BankCardEntity, Integer> {

	public BankCardService() {

	}

	@Override
	protected AbstractDataAccess<BankCardEntity, Integer> getDAO() {
		return new BankCardDataAccess();
	}

}
