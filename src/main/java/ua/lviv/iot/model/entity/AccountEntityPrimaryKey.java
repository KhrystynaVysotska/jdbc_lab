package ua.lviv.iot.model.entity;

import ua.lviv.iot.annotation.Autoincremented;
import ua.lviv.iot.annotation.Column;
import ua.lviv.iot.annotation.PrimaryKey;

public class AccountEntityPrimaryKey {
	@PrimaryKey
	@Autoincremented
	@Column(name = "id")
	private Integer id = 0;
	@Column(name = "pin_code_id")
	private Integer pinCodeId;

	public AccountEntityPrimaryKey() {

	}

	public AccountEntityPrimaryKey(Integer pinCodeId) {
		this.pinCodeId = pinCodeId;
	}

	public AccountEntityPrimaryKey(Integer id, Integer pinCodeId) {
		this.id = id;
		this.pinCodeId = pinCodeId;
	}

	@Override
	public String toString() {
		return "AccountEntityPrimaryKey [id=" + id + ", pinCodeId=" + pinCodeId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pinCodeId == null) ? 0 : pinCodeId.hashCode());
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
		AccountEntityPrimaryKey other = (AccountEntityPrimaryKey) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pinCodeId == null) {
			if (other.pinCodeId != null)
				return false;
		} else if (!pinCodeId.equals(other.pinCodeId))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPinCodeId() {
		return pinCodeId;
	}

	public void setPinCodeId(Integer pinCodeId) {
		this.pinCodeId = pinCodeId;
	}

}
