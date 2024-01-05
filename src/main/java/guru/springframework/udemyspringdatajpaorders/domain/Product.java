package guru.springframework.udemyspringdatajpaorders.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Product extends BaseEntity {

	private String description;

	@Enumerated(EnumType.STRING)
	private ProductStatus productStatus;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "product_category",
		joinColumns = @JoinColumn(name = "product_id"),
		inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories;

	private Integer quantityOnHand = 0;
}
