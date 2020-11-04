package ua.lviv.iot.model.service.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.dao.implementation.StreetDataAccess;
import ua.lviv.iot.model.entity.StreetEntity;
import ua.lviv.iot.model.service.AbstractService;

public class StreetService extends AbstractService<StreetEntity, Integer> {

	public StreetService() {

	}

	@Override
	protected AbstractDataAccess<StreetEntity, Integer> getDAO() {
		return new StreetDataAccess();
	}

}
