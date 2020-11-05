package ua.lviv.iot.model.service.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import ua.lviv.iot.model.dao.AbstractDataAccess;
import ua.lviv.iot.model.dao.implementation.AccountDataAccess;
import ua.lviv.iot.model.dao.implementation.TransferDataAccess;
import ua.lviv.iot.model.entity.AccountEntity;
import ua.lviv.iot.model.entity.TransferEntity;
import ua.lviv.iot.model.service.AbstractService;
import ua.lviv.iot.persistant.ConnectionManager;

public class TransferService extends AbstractService<TransferEntity, Integer> {

	public TransferService() {

	}

	@Override
	protected AbstractDataAccess<TransferEntity, Integer> getDao() {
		return new TransferDataAccess();
	}

	public void makeTransaction(TransferEntity transfer) throws SQLException {
		Connection connection = ConnectionManager.getConnection();
		AccountDataAccess accountDataAccess = new AccountDataAccess();
		TransferDataAccess transferDataAccess = new TransferDataAccess();
		Integer senderId = transfer.getSenderAccountId();
		Integer recipientId = transfer.getRecipientAccountId();
		Integer amountToTransfer = transfer.getAmount();
		if (senderId.equals(recipientId)) {
			System.out.println("You cannot send money to your own account!\n");
		} else {
			connection.setAutoCommit(false);
			AccountEntity sender = accountDataAccess.findById(senderId);
			AccountEntity recipient = accountDataAccess.findById(recipientId);
			if (sender.getAmount() >= amountToTransfer) {
				Integer senderAccountAmount = sender.getAmount();
				Integer recipientAccountAmount = recipient.getAmount();
				sender.setAmount(senderAccountAmount - amountToTransfer);
				recipient.setAmount(recipientAccountAmount + amountToTransfer);
				int resultOfFirstModification = accountDataAccess.update(sender);
				int resultOfSecondModification = accountDataAccess.update(recipient);
				if (resultOfFirstModification != 0 && resultOfSecondModification != 0) {
					int resultOfTransaction = transferDataAccess.create(transfer);
					if (resultOfTransaction != 0) {
						System.out.println("Transaction completed successfully!\n ");
						connection.commit();
					}
				} else {
					System.out.println("Transaction failed!\n");
					connection.rollback();
				}
			} else {
				System.out.println("You don't have enough money to transfer!\n");
				connection.rollback();
			}
		}
	}
}
