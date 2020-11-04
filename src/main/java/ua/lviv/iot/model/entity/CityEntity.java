package ua.lviv.iot.model.entity;

import ua.lviv.iot.annotation.Autoincremented;
import ua.lviv.iot.annotation.Column;
import ua.lviv.iot.annotation.PrimaryKey;
import ua.lviv.iot.annotation.Table;
import ua.lviv.iot.model.entity.formatter.Formatter;

@Table(name = "city")
public class CityEntity {
	@PrimaryKey
	@Autoincremented
	@Column(name = "id")
	private Integer id = 0;

	@Column(name = "name", length = 45)
	private String name;

	@Column(name = "zip_code")
	private Integer zipCode;

	@Column(name = "phone_code")
	private String phoneCode;

	public CityEntity() {

	}

	public CityEntity(Integer id, String name, Integer zipCode, String phoneCode) {
		super();
		this.id = id;
		this.name = name;
		this.zipCode = zipCode;
		this.phoneCode = phoneCode;
	}

	@Override
	public String toString() {
		String[] columnsNames = { "city_id", "name", "zip_code", "phone_code" };
		String[] columnValues = { id.toString(), name, zipCode.toString(), phoneCode.toString() };
		return Formatter.formatRow(columnsNames, columnValues);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phoneCode == null) ? 0 : phoneCode.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
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
		CityEntity other = (CityEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phoneCode == null) {
			if (other.phoneCode != null)
				return false;
		} else if (!phoneCode.equals(other.phoneCode))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}
}
