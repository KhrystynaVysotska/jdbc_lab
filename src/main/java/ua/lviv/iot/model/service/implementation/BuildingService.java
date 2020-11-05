package ua.lviv.iot.model.service.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.dao.implementation.BuildingDataAccess;
import ua.lviv.iot.model.entity.BuildingEntity;
import ua.lviv.iot.model.service.AbstractService;

public class BuildingService extends AbstractService<BuildingEntity, Integer> {

	public BuildingService() {

	}

	@Override
	protected AbstractDataAccess<BuildingEntity, Integer> getDao() {
		return new BuildingDataAccess();
	}

}
