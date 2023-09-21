package guru.springframework.udemyspringdatajpaorders.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@AttributeOverrides({
		@AttributeOverride(
				name = "shippingAddress.address",
				column = @Column(name = "shipping_address")
		),
		@AttributeOverride(
				name = "shippingAddress.city",
				column = @Column(name = "shipping_city")
		),
		@AttributeOverride(
				name = "shippingAddress.state",
				column = @Column(name = "shipping_state")
		),
		@AttributeOverride(
				name = "shippingAddress.zipCode",
				column = @Column(name = "shipping_zip_code")
		),
		@AttributeOverride(
				name = "billingAddress.address",
				column = @Column(name = "billing_address")
		),
		@AttributeOverride(
				name = "billingAddress.city",
				column = @Column(name = "billing_city")
		),
		@AttributeOverride(
				name = "billingAddress.state",
				column = @Column(name = "billing_state")
		),
		@AttributeOverride(
				name = "billingAddress.zipCode",
				column = @Column(name = "billing_zip_code")
		)
})
public class OrderHeader extends BaseEntity {

	@Embedded
	private Address shippingAddress;

	@Embedded
	private Address billingAddress;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@OneToMany(mappedBy = "orderHeader", cascade = CascadeType.PERSIST)
	private Set<OrderLine> orderLines;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Customer customer;

	public void addOrderLine(final OrderLine orderLine) {

		if (orderLines == null) {
			orderLines = new HashSet<>();
		}

		orderLine.setOrderHeader(this);
		orderLines.add(orderLine);
	}

	public Address getShippingAddress() {

		return shippingAddress;
	}

	public void setShippingAddress(final Address shippingAddress) {

		this.shippingAddress = shippingAddress;
	}

	public Address getBillingAddress() {

		return billingAddress;
	}

	public void setBillingAddress(final Address billingAddress) {

		this.billingAddress = billingAddress;
	}

	public OrderStatus getOrderStatus() {

		return orderStatus;
	}

	public void setOrderStatus(final OrderStatus orderStatus) {

		this.orderStatus = orderStatus;
	}

	public Set<OrderLine> getOrderLines() {

		return orderLines;
	}

	public void setOrderLines(final Set<OrderLine> orderLines) {

		this.orderLines = orderLines;
	}

	public Customer getCustomer() {

		return customer;
	}

	public void setCustomer(final Customer customer) {

		this.customer = customer;
	}

	@Override
	public boolean equals(final Object o) {

		if (this == o)
			return true;
		if (!(o instanceof final OrderHeader that))
			return false;
		if (!super.equals(o))
			return false;

		if (!Objects.equals(shippingAddress, that.shippingAddress))
			return false;
		if (!Objects.equals(billingAddress, that.billingAddress))
			return false;
		if (orderStatus != that.orderStatus)
			return false;
		if (!Objects.equals(orderLines, that.orderLines))
			return false;
		return Objects.equals(customer, that.customer);
	}

	@Override
	public int hashCode() {

		int result = super.hashCode();
		result = 31 * result + (shippingAddress != null ? shippingAddress.hashCode() : 0);
		result = 31 * result + (billingAddress != null ? billingAddress.hashCode() : 0);
		result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
		result = 31 * result + (orderLines != null ? orderLines.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {

		return "OrderHeader{" +
				", shippingAddress=" + shippingAddress +
				", billingAddress=" + billingAddress +
				", orderStatus=" + orderStatus +
				", orderLines=" + orderLines +
				'}';
	}
}
