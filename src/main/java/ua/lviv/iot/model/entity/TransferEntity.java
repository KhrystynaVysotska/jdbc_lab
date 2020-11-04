package ua.lviv.iot.model.entity;

import java.sql.Date;
import java.sql.Time;

import ua.lviv.iot.annotation.Autoincremented;
import ua.lviv.iot.annotation.Column;
import ua.lviv.iot.annotation.PrimaryKey;
import ua.lviv.iot.annotation.Table;
import ua.lviv.iot.model.entity.formatter.Formatter;

@Table(name = "transfer")
public class TransferEntity {

	@PrimaryKey
	@Autoincremented
	@Column(name = "id")
	private Integer id = 0;
	@Column(name = "sender_account_id")
	private Integer senderAccountId;
	@Column(name = "recipient_account_id")
	private Integer recipientAccountId;
	@Column(name = "amount")
	private Integer amount;
	@Column(name = "currency_id")
	private Integer currencyId;
	@Column(name = "date")
	private Date date;
	@Column(name = "time")
	private Time time;
	@Column(name = "purpose_of_payment")
	private String purposeOfPayment = "not specified";

	public TransferEntity() {

	}

	public TransferEntity(Integer id, Integer senderAccountId, Integer recipientAccountId, Integer amount,
			Integer currencyId, Date date, Time time, String purposeOfPayment) {
		super();
		this.id = id;
		this.senderAccountId = senderAccountId;
		this.recipientAccountId = recipientAccountId;
		this.amount = amount;
		this.currencyId = currencyId;
		this.date = date;
		this.time = time;
		this.purposeOfPayment = purposeOfPayment != null ? purposeOfPayment : "not specified";
	}

	@Override
	public String toString() {
		String[] columnsNames = { "transfer_id", "sender_account_id", "recipient_account_id", "amount", "currency_id",
				"date", "time", "purpose_of_payment" };
		String[] columnValues = { id.toString(), senderAccountId.toString(), recipientAccountId.toString(),
				amount.toString(), currencyId.toString(), date.toString(), time.toString(),
				purposeOfPayment != null ? purposeOfPayment : "not specified" };
		return Formatter.formatRow(columnsNames, columnValues);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSenderAccountId() {
		return senderAccountId;
	}

	public void setSenderAccountId(Integer senderAccountId) {
		this.senderAccountId = senderAccountId;
	}

	public Integer getRecipientAccountId() {
		return recipientAccountId;
	}

	public void setRecipientAccountId(Integer recipientAccountId) {
		this.recipientAccountId = recipientAccountId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getPurposeOfPayment() {
		return purposeOfPayment;
	}

	public void setPurposeOfPayment(String purposeOfPayment) {
		this.purposeOfPayment = purposeOfPayment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((currencyId == null) ? 0 : currencyId.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((purposeOfPayment == null) ? 0 : purposeOfPayment.hashCode());
		result = prime * result + ((recipientAccountId == null) ? 0 : recipientAccountId.hashCode());
		result = prime * result + ((senderAccountId == null) ? 0 : senderAccountId.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		TransferEntity other = (TransferEntity) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (currencyId == null) {
			if (other.currencyId != null)
				return false;
		} else if (!currencyId.equals(other.currencyId))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (purposeOfPayment == null) {
			if (other.purposeOfPayment != null)
				return false;
		} else if (!purposeOfPayment.equals(other.purposeOfPayment))
			return false;
		if (recipientAccountId == null) {
			if (other.recipientAccountId != null)
				return false;
		} else if (!recipientAccountId.equals(other.recipientAccountId))
			return false;
		if (senderAccountId == null) {
			if (other.senderAccountId != null)
				return false;
		} else if (!senderAccountId.equals(other.senderAccountId))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
}
