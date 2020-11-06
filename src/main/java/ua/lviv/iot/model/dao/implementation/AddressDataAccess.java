package ua.lviv.iot.model.dao.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.entity.AddressEntity;

public class AddressDataAccess extends AbstractDataAccess<AddressEntity, Integer> {

	public AddressDataAccess() {
		super(AddressEntity.class);
	}

}
