package ua.lviv.iot.model.dao.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.entity.BankCardEntity;

public class BankCardDataAccess extends AbstractDataAccess<BankCardEntity, Integer> {

	public BankCardDataAccess() {
		super(BankCardEntity.class);
	}

}
