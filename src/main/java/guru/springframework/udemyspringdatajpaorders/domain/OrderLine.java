package guru.springframework.udemyspringdatajpaorders.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Entity
public class OrderLine extends BaseEntity {

	private Integer quantityOrdered;

	@ManyToOne
	private OrderHeader orderHeader;

	public Integer getQuantityOrdered() {

		return quantityOrdered;
	}

	public void setQuantityOrdered(final Integer quantityOrdered) {

		this.quantityOrdered = quantityOrdered;
	}

	public OrderHeader getOrderHeader() {

		return orderHeader;
	}

	public void setOrderHeader(final OrderHeader orderHeader) {

		this.orderHeader = orderHeader;
	}

	@Override
	public boolean equals(final Object o) {

		if (this == o)
			return true;
		if (!(o instanceof final OrderLine orderLine))
			return false;
		if (!super.equals(o))
			return false;

		if (!Objects.equals(quantityOrdered, orderLine.quantityOrdered))
			return false;
		return Objects.equals(orderHeader, orderLine.orderHeader);
	}

	@Override
	public int hashCode() {

		int result = super.hashCode();
		result = 31 * result + (quantityOrdered != null ? quantityOrdered.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {

		return "OrderLine{" +
				"quantityOrdered=" + quantityOrdered +
				", orderHeader=" + orderHeader +
				'}';
	}
}
