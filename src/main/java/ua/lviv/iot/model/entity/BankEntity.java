package ua.lviv.iot.model.entity;

import java.sql.Date;

import ua.lviv.iot.annotation.Column;
import ua.lviv.iot.annotation.PrimaryKey;
import ua.lviv.iot.annotation.Table;
import ua.lviv.iot.model.entity.formatter.Formatter;

@Table(name = "bank")
public class BankEntity {
	@PrimaryKey
	@Column(name = "identification_code", length = 6)
	private Integer identificationCode;
	@Column(name = "state_registration_code")
	private Integer stateRegistrationCode;
	@Column(name = "full_bank_name")
	private String fullBankName;
	@Column(name = "short_bank_name")
	private String shortBankName;
	@Column(name = "bank_license_number")
	private Integer bankLicenseNumber;
	@Column(name = "bank_license_date")
	private Date bankLicenseDate;

	public BankEntity() {

	}

	public BankEntity(int identificationCode, int stateRegistrationCode, String fullBankName, String shortBankName,
			int bankLicenseNumber, Date bankLicenseDate) {
		super();
		this.identificationCode = identificationCode;
		this.stateRegistrationCode = stateRegistrationCode;
		this.fullBankName = fullBankName;
		this.shortBankName = shortBankName;
		this.bankLicenseNumber = bankLicenseNumber;
		this.bankLicenseDate = bankLicenseDate;
	}

	@Override
	public String toString() {
		String[] columnsNames = { "identification_code", "state_registration_code", "full_bank_name", "short_bank_name",
				"bank_license_number", "bank_license_date" };
		String[] columnValues = { identificationCode.toString(), stateRegistrationCode.toString(), fullBankName,
				shortBankName, bankLicenseNumber.toString(), bankLicenseDate.toString() };
		return Formatter.formatRow(columnsNames, columnValues);
	}

	public int getIdentificationCode() {
		return identificationCode;
	}

	public void setIdentificationCode(int identificationCode) {
		this.identificationCode = identificationCode;
	}

	public int getStateRegistrationCode() {
		return stateRegistrationCode;
	}

	public void setStateRegistrationCode(int stateRegistrationCode) {
		this.stateRegistrationCode = stateRegistrationCode;
	}

	public String getFullBankName() {
		return fullBankName;
	}

	public void setFullBankName(String fullBankName) {
		this.fullBankName = fullBankName;
	}

	public String getShortBankName() {
		return shortBankName;
	}

	public void setShortBankName(String shortBankName) {
		this.shortBankName = shortBankName;
	}

	public int getBankLicenseNumber() {
		return bankLicenseNumber;
	}

	public void setBankLicenseNumber(int bankLicenseNumber) {
		this.bankLicenseNumber = bankLicenseNumber;
	}

	public Date getBankLicenseDate() {
		return bankLicenseDate;
	}

	public void setBankLicenseDate(Date bankLicenseDate) {
		this.bankLicenseDate = bankLicenseDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bankLicenseDate == null) ? 0 : bankLicenseDate.hashCode());
		result = prime * result + bankLicenseNumber;
		result = prime * result + ((fullBankName == null) ? 0 : fullBankName.hashCode());
		result = prime * result + identificationCode;
		result = prime * result + ((shortBankName == null) ? 0 : shortBankName.hashCode());
		result = prime * result + stateRegistrationCode;
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
		BankEntity other = (BankEntity) obj;
		if (bankLicenseDate == null) {
			if (other.bankLicenseDate != null)
				return false;
		} else if (!bankLicenseDate.equals(other.bankLicenseDate))
			return false;
		if (bankLicenseNumber != other.bankLicenseNumber)
			return false;
		if (fullBankName == null) {
			if (other.fullBankName != null)
				return false;
		} else if (!fullBankName.equals(other.fullBankName))
			return false;
		if (identificationCode != other.identificationCode)
			return false;
		if (shortBankName == null) {
			if (other.shortBankName != null)
				return false;
		} else if (!shortBankName.equals(other.shortBankName))
			return false;
		if (stateRegistrationCode != other.stateRegistrationCode)
			return false;
		return true;
	}
}
