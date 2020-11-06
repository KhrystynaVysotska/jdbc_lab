package ua.lviv.iot.model.dao.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.entity.StreetEntity;

public class StreetDataAccess extends AbstractDataAccess<StreetEntity, Integer> {

	public StreetDataAccess() {
		super(StreetEntity.class);
	}

}
