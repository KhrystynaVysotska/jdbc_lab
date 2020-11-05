package ua.lviv.iot.model.entity;

import ua.lviv.iot.annotation.Autoincremented;
import ua.lviv.iot.annotation.Column;
import ua.lviv.iot.annotation.PrimaryKey;
import ua.lviv.iot.annotation.Table;
import ua.lviv.iot.model.entity.formatter.Formatter;

@Table(name = "pin_code")
public class PinCodeEntity {
	@PrimaryKey
	@Autoincremented
	@Column(name = "id")
	private Integer id = 0;

	@Column(name = "pin", length = 45)
	private String pin;

	public PinCodeEntity() {

	}

	public PinCodeEntity(Integer id, String pin) {
		super();
		this.id = id;
		this.pin = pin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pin == null) ? 0 : pin.hashCode());
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
		PinCodeEntity other = (PinCodeEntity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (pin == null) {
			if (other.pin != null) {
				return false;
			}
		} else if (!pin.equals(other.pin)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		String[] columnsNames = { "pin_code_id", "pin" };
		String[] columnValues = { id.toString(), pin };
		return Formatter.formatRow(columnsNames, columnValues);
	}

}
