package ua.lviv.iot.model.entity;

import java.sql.Date;

import ua.lviv.iot.annotation.Autoincremented;
import ua.lviv.iot.annotation.Column;
import ua.lviv.iot.annotation.PrimaryKey;
import ua.lviv.iot.annotation.Table;
import ua.lviv.iot.model.entity.formatter.Formatter;

@Table(name= "account_owner")
public class AccountOwnerEntity {
	@PrimaryKey
	@Autoincremented
	@Column(name = "id")
	private Integer id = 0;

	@Column(name = "personal_identification_number", length = 10)
	private String personalIdentificationNumber;

	@Column(name = "name", length = 45)
	private String name;

	@Column(name = "surname", length = 45)
	private String surname;

	@Column(name = "patronym", length = 45)
	private String patronym;

	@Column(name = "mobile_number", length = 13)
	private String mobileNumber;

	@Column(name = "email", length = 45)
	private String email;

	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "adress_id")
	private Integer addressId;

	public AccountOwnerEntity() {

	}

	public AccountOwnerEntity(Integer id, String personalIdentificationNumber, String name, String surname,
			String patronym, String mobileNumber, String email, Date birthDate, Integer addressId) {
		super();
		this.id = id;
		this.personalIdentificationNumber = personalIdentificationNumber;
		this.name = name;
		this.surname = surname;
		this.patronym = patronym;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.birthDate = birthDate;
		this.addressId = addressId;
	}

	@Override
	public String toString() {
		String[] columnsNames = { "account_owner_id", "personal_identification_number", "name", "surname", "patronym",
				"mobile_number", "e_mail", "birth_date", "adress_id" };
		String[] columnValues = { id.toString(), personalIdentificationNumber, name, surname, patronym, mobileNumber,
				email, birthDate.toString(), addressId.toString() };
		return Formatter.formatRow(columnsNames, columnValues);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPersonalIdentificationNumber() {
		return personalIdentificationNumber;
	}

	public void setPersonalIdentificationNumber(String personalIdentificationNumber) {
		this.personalIdentificationNumber = personalIdentificationNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPatronym() {
		return patronym;
	}

	public void setPatronym(String patronym) {
		this.patronym = patronym;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressId == null) ? 0 : addressId.hashCode());
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((patronym == null) ? 0 : patronym.hashCode());
		result = prime * result
				+ ((personalIdentificationNumber == null) ? 0 : personalIdentificationNumber.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		AccountOwnerEntity other = (AccountOwnerEntity) obj;
		if (addressId == null) {
			if (other.addressId != null)
				return false;
		} else if (!addressId.equals(other.addressId))
			return false;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mobileNumber == null) {
			if (other.mobileNumber != null)
				return false;
		} else if (!mobileNumber.equals(other.mobileNumber))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (patronym == null) {
			if (other.patronym != null)
				return false;
		} else if (!patronym.equals(other.patronym))
			return false;
		if (personalIdentificationNumber == null) {
			if (other.personalIdentificationNumber != null)
				return false;
		} else if (!personalIdentificationNumber.equals(other.personalIdentificationNumber))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

}
