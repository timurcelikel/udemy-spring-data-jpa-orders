package guru.springframework.udemyspringdatajpaorders.repository;

import guru.springframework.udemyspringdatajpaorders.domain.Product;
import guru.springframework.udemyspringdatajpaorders.domain.ProductStatus;
import guru.springframework.udemyspringdatajpaorders.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Slf4j
@ComponentScan(basePackageClasses = ProductService.class)
class ProductRepositoryTest {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductService productService;

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

	@Test
	void testSaveProduct() {
		Product product = new Product();
		product.setDescription("My Product");
		product.setProductStatus(ProductStatus.NEW);

		Product savedProduct = productRepository.save(product);

		Product fetchedProduct = productRepository.getReferenceById(savedProduct.getId());

		assertNotNull(fetchedProduct);
		assertNotNull(fetchedProduct.getDescription());
		assertNotNull(fetchedProduct.getCreatedDate());
		assertNotNull(fetchedProduct.getLastModifiedDate());
	}

	@Test
	void addAndUpdateProduct() {
		Product product = new Product();
		product.setDescription("My Product");
		product.setProductStatus(ProductStatus.NEW);

		Product savedProduct = productService.saveProduct(product);

		Product savedProduct2 = productService.updateQuantityOnHand(savedProduct.getId(), 25);
		//log.info(String.valueOf(savedProduct2.getQuantityOnHand()));
		System.out.println(savedProduct2.getQuantityOnHand());
	}
}
