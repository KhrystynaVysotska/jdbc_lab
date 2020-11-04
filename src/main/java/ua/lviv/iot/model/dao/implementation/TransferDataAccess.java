package ua.lviv.iot.model.dao.implementation;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.entity.TransferEntity;

public class TransferDataAccess extends AbstractDataAccess<TransferEntity, Integer> {

	public TransferDataAccess() {
		super(TransferEntity.class);
	}
}
