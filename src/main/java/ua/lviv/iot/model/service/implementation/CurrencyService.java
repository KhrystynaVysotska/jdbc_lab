package ua.lviv.iot.model.service.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.dao.implementation.CurrencyDataAccess;
import ua.lviv.iot.model.entity.CurrencyEntity;
import ua.lviv.iot.model.service.AbstractService;

public class CurrencyService extends AbstractService<CurrencyEntity, Integer> {

	public CurrencyService() {

	}

	@Override
	protected AbstractDataAccess<CurrencyEntity, Integer> getDao() {
		return new CurrencyDataAccess();
	}

}
