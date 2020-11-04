package ua.lviv.iot.model.dao.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.entity.CardTypeEntity;

public class CardTypeDataAccess extends AbstractDataAccess<CardTypeEntity, Integer> {

	public CardTypeDataAccess() {
		super(CardTypeEntity.class);
	}

}
