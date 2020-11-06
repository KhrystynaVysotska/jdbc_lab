package ua.lviv.iot.model.service.implementation;

import java.sql.SQLException;
import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.dao.implementation.BankDataAccess;
import ua.lviv.iot.model.entity.BankEntity;
import ua.lviv.iot.model.service.AbstractService;

public class BankService extends AbstractService<BankEntity, Integer> {

	private BankDataAccess<Integer> bankDataAccess;

	public BankService() {
		bankDataAccess = new BankDataAccess<>();
	}

	@Override
	protected AbstractDataAccess<BankEntity, Integer> getDao() {
		return bankDataAccess;
	}

	@Override
	public BankEntity create(BankEntity entity) throws SQLException {
		int result = bankDataAccess.create(entity);
		if (result != 0) {
			BankEntity createdBank = findById(entity.getIdentificationCode());
			return createdBank;
		} else {
			return null;
		}
	}
}
