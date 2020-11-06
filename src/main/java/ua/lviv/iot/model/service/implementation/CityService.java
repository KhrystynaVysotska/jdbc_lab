package ua.lviv.iot.model.service.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.dao.implementation.CityDataAccess;
import ua.lviv.iot.model.entity.CityEntity;
import ua.lviv.iot.model.service.AbstractService;

public class CityService extends AbstractService<CityEntity, Integer> {

	public CityService() {

	}

	@Override
	protected AbstractDataAccess<CityEntity, Integer> getDao() {
		return new CityDataAccess();
	}

}
