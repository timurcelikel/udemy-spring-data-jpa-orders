package guru.springframework.udemyspringdatajpaorders.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
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
}
