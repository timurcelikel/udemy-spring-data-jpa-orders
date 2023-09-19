package guru.springframework.udemyspringdatajpaorders.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Product extends BaseEntity{

	private String description;
	@Enumerated(EnumType.STRING)
	private ProductStatus productStatus;

	public String getDescription() {

		return description;
	}

	public void setDescription(final String description) {

		this.description = description;
	}

	public ProductStatus getProductStatus() {

		return productStatus;
	}

	public void setProductStatus(final ProductStatus productStatus) {

		this.productStatus = productStatus;
	}

	@Override
	public boolean equals(final Object o) {

		if (this == o)
			return true;
		if (!(o instanceof final Product product))
			return false;
		if (!super.equals(o))
			return false;

		if (!Objects.equals(description, product.description))
			return false;
		return productStatus == product.productStatus;
	}

	@Override
	public int hashCode() {

		int result = super.hashCode();
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (productStatus != null ? productStatus.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {

		return "Product{" +
				"description='" + description + '\'' +
				", productStatus=" + productStatus +
				'}';
	}
}
