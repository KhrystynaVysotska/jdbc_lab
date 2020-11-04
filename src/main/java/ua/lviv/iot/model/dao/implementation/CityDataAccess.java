package ua.lviv.iot.model.dao.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.entity.CityEntity;

public class CityDataAccess extends AbstractDataAccess<CityEntity, Integer> {

	public CityDataAccess() {
		super(CityEntity.class);
	}

}
