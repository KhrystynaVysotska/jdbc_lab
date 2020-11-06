package ua.lviv.iot.model.dao.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.entity.CurrencyEntity;

public class CurrencyDataAccess extends AbstractDataAccess<CurrencyEntity, Integer> {

	public CurrencyDataAccess() {
		super(CurrencyEntity.class);
	}

}
