package guru.springframework.udemyspringdatajpaorders.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.Objects;
import java.util.Set;

@Entity
public class Category extends BaseEntity {

	private String description;

	@ManyToMany
	@JoinTable(name = "product_category",
			joinColumns = @JoinColumn(name = "category_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id"))
	private Set<Product> products;

	public String getDescription() {

		return description;
	}

	public void setDescription(final String description) {

		this.description = description;
	}

	public Set<Product> getProducts() {

		return products;
	}

	public void setProducts(final Set<Product> products) {

		this.products = products;
	}

	@Override
	public boolean equals(final Object o) {

		if (this == o)
			return true;
		if (!(o instanceof final Category category))
			return false;
		if (!super.equals(o))
			return false;

		return Objects.equals(description, category.description);
	}

	@Override
	public int hashCode() {

		int result = super.hashCode();
		result = 31 * result + (description != null ? description.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {

		return "Category{" +
				"description='" + description + '\'' +
				'}';
	}
}
