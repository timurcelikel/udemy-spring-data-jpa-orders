package guru.springframework.udemyspringdatajpaorders.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class OrderHeader {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String customerName;

	public Long getId() {

		return id;
	}

	public void setId(final Long id) {

		this.id = id;
	}

	public String getCustomerName() {

		return customerName;
	}

	public void setCustomerName(final String customerName) {

		this.customerName = customerName;
	}

	@Override
	public boolean equals(final Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		final OrderHeader that = (OrderHeader) o;

		if (!Objects.equals(id, that.id))
			return false;
		return Objects.equals(customerName, that.customerName);
	}

	@Override
	public int hashCode() {

		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {

		return "OrderHeader{" +
				"id=" + id +
				", customerName='" + customerName + '\'' +
				'}';
	}
}
