package guru.springframework.udemyspringdatajpaorders.domain;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Address {

	private String address;

	private String city;

	private String state;

	private String zipCode;

	public String getAddress() {

		return address;
	}

	public void setAddress(final String address) {

		this.address = address;
	}

	public String getCity() {

		return city;
	}

	public void setCity(final String city) {

		this.city = city;
	}

	public String getState() {

		return state;
	}

	public void setState(final String state) {

		this.state = state;
	}

	public String getZipCode() {

		return zipCode;
	}

	public void setZipCode(final String zipCode) {

		this.zipCode = zipCode;
	}

	@Override
	public boolean equals(final Object o) {

		if (this == o)
			return true;
		if (!(o instanceof final Address address1))
			return false;

		if (!Objects.equals(address, address1.address))
			return false;
		if (!Objects.equals(city, address1.city))
			return false;
		if (!Objects.equals(state, address1.state))
			return false;
		return Objects.equals(zipCode, address1.zipCode);
	}

	@Override
	public int hashCode() {

		int result = address != null ? address.hashCode() : 0;
		result = 31 * result + (city != null ? city.hashCode() : 0);
		result = 31 * result + (state != null ? state.hashCode() : 0);
		result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {

		return "Address{" +
				"address='" + address + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				", zipCode='" + zipCode + '\'' +
				'}';
	}
}
