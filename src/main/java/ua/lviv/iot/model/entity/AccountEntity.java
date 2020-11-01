package ua.lviv.iot.model.entity;

import ua.lviv.iot.annotation.Column;
import ua.lviv.iot.annotation.PrimaryKeyComposite;
import ua.lviv.iot.annotation.Table;

@Table(name = "account")
public class AccountEntity {
	@PrimaryKeyComposite
	private AccountEntityPrimaryKey accountEntityPrimaryKey;
	@Column(name = "current_account_number")
	private Long currentAccountNumber;
	@Column(name = "amount")
	private Integer amount;
	@Column(name = "account_owner_id")
	private Integer accountOwnerId;
	@Column(name = "bank_identification_code")
	private Integer bankIdentificationCode;
	@Column(name = "currency_id")
	private Integer currencyId;
	@Column(name = "account_type_id")
	private Integer accountTypeId;

	public AccountEntity(AccountEntityPrimaryKey accountEntityPrimaryKey, Long currentAccountNumber, Integer amount,
			Integer accountOwnerId, Integer bankIdentificationCode, Integer currencyId, Integer accountTypeId) {
		super();
		this.accountEntityPrimaryKey = accountEntityPrimaryKey;
		this.currentAccountNumber = currentAccountNumber;
		this.amount = amount;
		this.accountOwnerId = accountOwnerId;
		this.bankIdentificationCode = bankIdentificationCode;
		this.currencyId = currencyId;
		this.accountTypeId = accountTypeId;
	}
	
	public AccountEntityPrimaryKey getAccountPrimaryKey() {
		return accountEntityPrimaryKey;
	}

	public void setAccountPrimaryKey(AccountEntityPrimaryKey accountPrimaryKey) {
		this.accountEntityPrimaryKey = accountPrimaryKey;
	}

	public Long getCurrentAccountNumber() {
		return currentAccountNumber;
	}

	public void setCurrentAccountNumber(long currentAccountNumber) {
		this.currentAccountNumber = currentAccountNumber;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Integer getAccountOwnerId() {
		return accountOwnerId;
	}

	public void setAccountOwnerId(int accountOwnerId) {
		this.accountOwnerId = accountOwnerId;
	}

	public Integer getBankIdentificationCode() {
		return bankIdentificationCode;
	}

	public void setBankIdentificationCode(int bankIdentificationCode) {
		this.bankIdentificationCode = bankIdentificationCode;
	}

	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	public Integer getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(int accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	@Override
	public String toString() {
		return "AccountEntity [accountEntityPrimaryKey=" + accountEntityPrimaryKey + ", currentAccountNumber="
				+ currentAccountNumber + ", amount=" + amount + ", accountOwnerId=" + accountOwnerId
				+ ", bankIdentificationCode=" + bankIdentificationCode + ", currencyId=" + currencyId
				+ ", accountTypeId=" + accountTypeId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountEntityPrimaryKey == null) ? 0 : accountEntityPrimaryKey.hashCode());
		result = prime * result + ((accountOwnerId == null) ? 0 : accountOwnerId.hashCode());
		result = prime * result + ((accountTypeId == null) ? 0 : accountTypeId.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((bankIdentificationCode == null) ? 0 : bankIdentificationCode.hashCode());
		result = prime * result + ((currencyId == null) ? 0 : currencyId.hashCode());
		result = prime * result + ((currentAccountNumber == null) ? 0 : currentAccountNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountEntity other = (AccountEntity) obj;
		if (accountEntityPrimaryKey == null) {
			if (other.accountEntityPrimaryKey != null)
				return false;
		} else if (!accountEntityPrimaryKey.equals(other.accountEntityPrimaryKey))
			return false;
		if (accountOwnerId == null) {
			if (other.accountOwnerId != null)
				return false;
		} else if (!accountOwnerId.equals(other.accountOwnerId))
			return false;
		if (accountTypeId == null) {
			if (other.accountTypeId != null)
				return false;
		} else if (!accountTypeId.equals(other.accountTypeId))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (bankIdentificationCode == null) {
			if (other.bankIdentificationCode != null)
				return false;
		} else if (!bankIdentificationCode.equals(other.bankIdentificationCode))
			return false;
		if (currencyId == null) {
			if (other.currencyId != null)
				return false;
		} else if (!currencyId.equals(other.currencyId))
			return false;
		if (currentAccountNumber == null) {
			if (other.currentAccountNumber != null)
				return false;
		} else if (!currentAccountNumber.equals(other.currentAccountNumber))
			return false;
		return true;
	}
}