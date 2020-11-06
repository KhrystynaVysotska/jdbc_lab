package ua.lviv.iot.model.dao.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.entity.BuildingEntity;

public class BuildingDataAccess extends AbstractDataAccess<BuildingEntity, Integer> {

	public BuildingDataAccess() {
		super(BuildingEntity.class);
	}

}
