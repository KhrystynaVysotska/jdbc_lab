package ua.lviv.iot.model.service.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.dao.implementation.PinCodeDataAccess;
import ua.lviv.iot.model.entity.PinCodeEntity;
import ua.lviv.iot.model.service.AbstractService;

public class PinCodeService extends AbstractService<PinCodeEntity, Integer> {

	public PinCodeService() {

	}

	@Override
	protected AbstractDataAccess<PinCodeEntity, Integer> getDao() {
		return new PinCodeDataAccess();
	}

}
