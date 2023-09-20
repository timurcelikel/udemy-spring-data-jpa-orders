package guru.springframework.udemyspringdatajpaorders.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Entity
public class OrderLine extends BaseEntity {

	private Integer quantityOrdered;

	@ManyToOne
	private OrderHeader orderHeader;

	@ManyToOne
	private Product product;

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

	public Product getProduct() {

		return product;
	}

	public void setProduct(final Product product) {

		this.product = product;
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
		if (!Objects.equals(orderHeader, orderLine.orderHeader))
			return false;
		return Objects.equals(product, orderLine.product);
	}

	@Override
	public int hashCode() {

		int result = super.hashCode();
		result = 31 * result + (quantityOrdered != null ? quantityOrdered.hashCode() : 0);
		result = 31 * result + (product != null ? product.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {

		return "OrderLine{" +
				"quantityOrdered=" + quantityOrdered +
				", product=" + product +
				'}';
	}
}
