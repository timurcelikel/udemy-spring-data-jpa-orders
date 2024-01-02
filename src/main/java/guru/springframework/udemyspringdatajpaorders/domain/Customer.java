package guru.springframework.udemyspringdatajpaorders.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Customer extends BaseEntity {

	private String customerName;

	@Embedded
	private Address address;

	private String phone;

	private String email;

	@Version
	private Integer version;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
	private Set<OrderHeader> orders = new HashSet<>();

	public void addOrderHeader(final OrderHeader orderHeader) {

		if (orders == null) {
			orders = new HashSet<>();
		}

		orderHeader.setCustomer(this);
		orders.add(orderHeader);
	}
}
