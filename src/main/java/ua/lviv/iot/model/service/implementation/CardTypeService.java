package ua.lviv.iot.model.service.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.dao.implementation.CardTypeDataAccess;
import ua.lviv.iot.model.entity.CardTypeEntity;
import ua.lviv.iot.model.service.AbstractService;

public class CardTypeService extends AbstractService<CardTypeEntity, Integer> {

	public CardTypeService() {

	}

	@Override
	protected AbstractDataAccess<CardTypeEntity, Integer> getDAO() {
		return new CardTypeDataAccess();
	}

}
