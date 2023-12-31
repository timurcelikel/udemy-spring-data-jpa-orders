package guru.springframework.udemyspringdatajpaorders.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
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

	private String testRow;

	//@OneToMany(mappedBy = "orderHeader", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@OneToMany(mappedBy = "orderHeader", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch =
		FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private Set<OrderLine> orderLines;

	//@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Customer customer;

	//@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, mappedBy = "orderHeader")
	// Somehow removing mappedBy increased our query performance by a ton
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@Fetch(FetchMode.SELECT)
	private OrderApproval orderApproval;

	public void addOrderLine(final OrderLine orderLine) {

		if (orderLines == null) {
			orderLines = new HashSet<>();
		}

		orderLines.add(orderLine);
		orderLine.setOrderHeader(this);
	}
}
