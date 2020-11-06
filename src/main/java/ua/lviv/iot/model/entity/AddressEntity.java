package ua.lviv.iot.model.entity;

import ua.lviv.iot.annotation.Autoincremented;
import ua.lviv.iot.annotation.Column;
import ua.lviv.iot.annotation.PrimaryKey;
import ua.lviv.iot.annotation.Table;
import ua.lviv.iot.model.entity.formatter.Formatter;

@Table(name = "adress")
public class AddressEntity {
	@PrimaryKey
	@Autoincremented
	@Column(name = "id")
	private Integer id = 0;

	@Column(name = "city_id")
	private Integer cityId;
	@Column(name = "street_id")
	private Integer streetId;
	@Column(name = "building_id")
	private Integer buildingId;

	public AddressEntity() {

	}

	public AddressEntity(Integer id, Integer cityId, Integer streetId, Integer buildingId) {
		super();
		this.id = id;
		this.cityId = cityId;
		this.streetId = streetId;
		this.buildingId = buildingId;
	}

	@Override
	public String toString() {
		String[] columnsNames = { "address_id", "city_id", "street_id", "building_id" };
		String[] columnValues = { id.toString(), cityId.toString(), streetId.toString(), buildingId.toString() };
		return Formatter.formatRow(columnsNames, columnValues);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getStreetId() {
		return streetId;
	}

	public void setStreetId(Integer streetId) {
		this.streetId = streetId;
	}

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buildingId == null) ? 0 : buildingId.hashCode());
		result = prime * result + ((cityId == null) ? 0 : cityId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((streetId == null) ? 0 : streetId.hashCode());
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
		AddressEntity other = (AddressEntity) obj;
		if (buildingId == null) {
			if (other.buildingId != null) {
				return false;
			}
		} else if (!buildingId.equals(other.buildingId)) {
			return false;
		}
		if (cityId == null) {
			if (other.cityId != null) {
				return false;
			}
		} else if (!cityId.equals(other.cityId)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (streetId == null) {
			if (other.streetId != null) {
				return false;
			}
		} else if (!streetId.equals(other.streetId)) {
			return false;
		}
		return true;
	}
}
