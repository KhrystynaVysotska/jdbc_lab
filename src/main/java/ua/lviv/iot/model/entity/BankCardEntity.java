package ua.lviv.iot.model.entity;

import java.sql.Date;
import ua.lviv.iot.annotation.Autoincremented;
import ua.lviv.iot.annotation.Column;
import ua.lviv.iot.annotation.PrimaryKey;
import ua.lviv.iot.annotation.Table;
import ua.lviv.iot.model.entity.formatter.Formatter;

@Table(name = "bank_card")
public class BankCardEntity {

	@PrimaryKey
	@Autoincremented
	@Column(name = "id")
	private Integer id = 0;

	@Column(name = "account_id")
	private Integer accountId;

	@Column(name = "card_type_id")
	private Integer cardTypeId;

	@Column(name = "cvc2")
	private Integer cvc2 = 0;

	@Column(name = "date_of_expire")
	private Date dateOfExpire;

	public BankCardEntity() {

	}

	public BankCardEntity(Integer id, Integer accountId, Integer cardTypeId, Integer cvc2, Date dateOfExpire) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.cardTypeId = cardTypeId;
		this.cvc2 = cvc2;
		this.dateOfExpire = (Date) dateOfExpire.clone();
	}

	@Override
	public String toString() {
		String[] columnsNames = { "bank_card_id", "account_id", "card_type_id", "cvc2", "date_of_expire" };
		String[] columnValues = { id.toString(), accountId.toString(), cardTypeId.toString(), cvc2.toString(),
				dateOfExpire.toString() };
		return Formatter.formatRow(columnsNames, columnValues);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(Integer cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public Integer getCvc2() {
		return cvc2;
	}

	public void setCvc2(Integer cvc2) {
		this.cvc2 = cvc2;
	}

	public Date getDateOfExpire() {
		return (Date) dateOfExpire.clone();
	}

	public void setDateOfExpire(Date dateOfExpire) {
		this.dateOfExpire = (Date) dateOfExpire.clone();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + ((cardTypeId == null) ? 0 : cardTypeId.hashCode());
		result = prime * result + ((cvc2 == null) ? 0 : cvc2.hashCode());
		result = prime * result + ((dateOfExpire == null) ? 0 : dateOfExpire.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BankCardEntity other = (BankCardEntity) obj;
		if (accountId == null) {
			if (other.accountId != null) {
				return false;
			}
		} else if (!accountId.equals(other.accountId)) {
			return false;
		}
		if (cardTypeId == null) {
			if (other.cardTypeId != null) {
				return false;
			}
		} else if (!cardTypeId.equals(other.cardTypeId)) {
			return false;
		}
		if (cvc2 == null) {
			if (other.cvc2 != null) {
				return false;
			}
		} else if (!cvc2.equals(other.cvc2)) {
			return false;
		}
		if (dateOfExpire == null) {
			if (other.dateOfExpire != null) {
				return false;
			}
		} else if (!dateOfExpire.equals(other.dateOfExpire)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
