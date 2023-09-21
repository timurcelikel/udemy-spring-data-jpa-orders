package guru.springframework.udemyspringdatajpaorders.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Customer extends BaseEntity {

	private String customerName;

	@Embedded
	private Address address;

	private String phone;

	private String email;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
	private Set<OrderHeader> orders = new HashSet<>();

	public void addOrderHeader(final OrderHeader orderHeader) {

		if (orders == null) {
			orders = new HashSet<>();
		}

		orderHeader.setCustomer(this);
		orders.add(orderHeader);
	}

	public String getCustomerName() {

		return customerName;
	}

	public void setCustomerName(final String customerName) {

		this.customerName = customerName;
	}

	public Address getAddress() {

		return address;
	}

	public void setAddress(final Address address) {

		this.address = address;
	}

	public String getPhone() {

		return phone;
	}

	public void setPhone(final String phone) {

		this.phone = phone;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(final String email) {

		this.email = email;
	}

	public Set<OrderHeader> getOrders() {

		return orders;
	}

	public void setOrders(final Set<OrderHeader> orders) {

		this.orders = orders;
	}

	@Override
	public boolean equals(final Object o) {

		if (this == o)
			return true;
		if (!(o instanceof final Customer customer))
			return false;
		if (!super.equals(o))
			return false;

		if (!Objects.equals(customerName, customer.customerName))
			return false;
		if (!Objects.equals(address, customer.address))
			return false;
		if (!Objects.equals(phone, customer.phone))
			return false;
		if (!Objects.equals(email, customer.email))
			return false;
		return Objects.equals(orders, customer.orders);
	}

	@Override
	public int hashCode() {

		int result = super.hashCode();
		result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
		result = 31 * result + (address != null ? address.hashCode() : 0);
		result = 31 * result + (phone != null ? phone.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (orders != null ? orders.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {

		return "Customer{" +
				"customerName='" + customerName + '\'' +
				", address=" + address +
				", phone='" + phone + '\'' +
				", email='" + email + '\'' +
				", orders=" + orders +
				'}';
	}
}
