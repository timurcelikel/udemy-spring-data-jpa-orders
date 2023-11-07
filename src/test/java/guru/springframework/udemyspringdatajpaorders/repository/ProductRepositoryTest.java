package guru.springframework.udemyspringdatajpaorders.repository;

import guru.springframework.udemyspringdatajpaorders.domain.Product;
import guru.springframework.udemyspringdatajpaorders.domain.ProductStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

	@Autowired
	ProductRepository productRepository;

	@Test
	void testGetCategory() {

		Optional<Product> product = productRepository.findByDescription("PRODUCT1");
		assertTrue(product.isPresent());
		assertNotNull(product.get().getCategories());
	}

	@Test
	void tesProductPersists() {

		Product product = new Product();
		product.setDescription("Towel Rack");
		product.setProductStatus(ProductStatus.NEW);
		Product savedProduct = productRepository.save(product);
		assertNotNull(savedProduct);

		Product fetcheProduct = productRepository.getReferenceById(savedProduct.getId());
		assertNotNull(fetcheProduct);
		assertNotNull(fetcheProduct.getDescription());
		assertNotNull(fetcheProduct.getProductStatus());
		assertNotNull(fetcheProduct.getCreatedDate());
		assertNotNull(fetcheProduct.getLastModifiedDate());
	}

}
