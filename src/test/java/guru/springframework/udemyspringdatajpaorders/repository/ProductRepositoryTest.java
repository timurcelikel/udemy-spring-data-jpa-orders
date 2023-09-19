package guru.springframework.udemyspringdatajpaorders.repository;

import guru.springframework.udemyspringdatajpaorders.domain.OrderHeader;
import guru.springframework.udemyspringdatajpaorders.domain.Product;
import guru.springframework.udemyspringdatajpaorders.domain.ProductStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

	@Autowired
	ProductRespository productRespository;

	@Test
	void tesProductPersists() {

		Product product = new Product();
		product.setDescription("Towel Rack");
		product.setProductStatus(ProductStatus.NEW);
		Product savedProduct = productRespository.save(product);
		assertNotNull(savedProduct);

		Product fetcheProduct = productRespository.getReferenceById(savedProduct.getId());
		assertNotNull(fetcheProduct);
		assertNotNull(fetcheProduct.getDescription());
		assertNotNull(fetcheProduct.getProductStatus());
		assertNotNull(fetcheProduct.getCreatedDate());
		assertNotNull(fetcheProduct.getLastModifiedDate());
	}

}
