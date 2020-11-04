package ua.lviv.iot.model.dao.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.entity.PinCodeEntity;

public class PinCodeDataAccess extends AbstractDataAccess<PinCodeEntity, Integer> {

	public PinCodeDataAccess() {
		super(PinCodeEntity.class);
	}

}
