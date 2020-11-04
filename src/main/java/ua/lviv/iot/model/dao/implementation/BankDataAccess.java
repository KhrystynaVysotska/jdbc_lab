package ua.lviv.iot.model.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.entity.BankEntity;
import ua.lviv.iot.persistant.ConnectionManager;
import ua.lviv.iot.transformer.Transformer;

public class BankDataAccess<ID> extends AbstractDataAccess<BankEntity, ID> {
	
	private static final String FIND_BANK_BY_IDENTIFICATION_CODE = "SELECT * FROM bank WHERE identification_code = ?;";;
	private static final String DELETE_BANK_BY_IDENTIFICATION_CODE = "DELETE FROM bank WHERE identification_code = ?;";
	public BankDataAccess() {
		super(BankEntity.class);
	}

	@Override
	public BankEntity findById(ID identificationCode) throws SQLException {
		BankEntity bank = null;
		Connection connection = ConnectionManager.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BANK_BY_IDENTIFICATION_CODE)) {
			preparedStatement.setObject(1, identificationCode);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					bank = (BankEntity) new Transformer<BankEntity>(BankEntity.class).convertResultSetToEntity(resultSet);
					break;
				}
			}

		}
		return bank;
	}
	
	@Override
	public int delete(ID identificationCode) throws SQLException {
		Connection connection = ConnectionManager.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BANK_BY_IDENTIFICATION_CODE)) {
			preparedStatement.setObject(1, identificationCode);
			return preparedStatement.executeUpdate();
		}
	}
}
