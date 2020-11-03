package ua.lviv.iot.model.service.implementation;

import java.sql.SQLException;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.dao.implementation.BankDataAccess;
import ua.lviv.iot.model.entity.BankEntity;
import ua.lviv.iot.model.service.AbstractService;

public class BankService extends AbstractService<BankEntity, Integer> {

	private BankDataAccess bankDataAccess;

	public BankService() {
		bankDataAccess = new BankDataAccess();
	}

	@Override
	protected AbstractDataAccess<BankEntity, Integer> getDAO() {
		return bankDataAccess;
	}

	@Override
	public BankEntity findById(Integer identificationCode) throws SQLException {
		return bankDataAccess.findById(identificationCode);
	}
	
	@Override
	public boolean delete(Integer identificationCode) throws SQLException {
		return bankDataAccess.delete(identificationCode) != 0;
	}
	@Override
	public BankEntity update(Integer identificationCode, BankEntity entity) throws SQLException {
		BankEntity oldEntity = findById(identificationCode);
		bankDataAccess.update(entity);
		return oldEntity;
	}
}
