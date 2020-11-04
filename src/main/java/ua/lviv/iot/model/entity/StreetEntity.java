package ua.lviv.iot.model.entity;

import ua.lviv.iot.annotation.Autoincremented;
import ua.lviv.iot.annotation.Column;
import ua.lviv.iot.annotation.PrimaryKey;
import ua.lviv.iot.annotation.Table;
import ua.lviv.iot.model.entity.formatter.Formatter;

@Table(name = "street")
public class StreetEntity {

	@PrimaryKey
	@Autoincremented
	@Column(name = "id")
	private Integer id = 0;

	@Column(name = "name", length = 45)
	private String name;

	public StreetEntity() {

	}

	public StreetEntity(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		String[] columnsNames = { "street_id", "name" };
		String[] columnValues = { id.toString(), name };
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		StreetEntity other = (StreetEntity) obj;
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
		return true;
	}

}
