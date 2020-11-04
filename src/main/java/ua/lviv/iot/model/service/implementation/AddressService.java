package ua.lviv.iot.model.service.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.dao.implementation.AddressDataAccess;
import ua.lviv.iot.model.entity.AddressEntity;
import ua.lviv.iot.model.service.AbstractService;

public class AddressService extends AbstractService<AddressEntity, Integer> {

	public AddressService() {

	}

	@Override
	protected AbstractDataAccess<AddressEntity, Integer> getDAO() {
		return new AddressDataAccess();
	}

}
