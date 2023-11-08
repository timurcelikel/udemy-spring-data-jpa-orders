package guru.springframework.udemyspringdatajpaorders.repository;

import guru.springframework.udemyspringdatajpaorders.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class DataLoadTest {

	final String PRODUCT_D1 = "Product 1";
	final String PRODUCT_D2 = "Product 2";
	final String PRODUCT_D3 = "Product 3";

	final String TEST_CUSTOMER = "TEST CUSTOMER";

	@Autowired
	OrderHeaderRepository orderHeaderRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ProductRepository productRepository;

	@Rollback(value = false)
	@Test
	void testDataLoader() {
		List<Product> products = loadProducts();
		Customer customer = loadCustomers();

		int ordersToCreate = 100;

		for (int i = 0; i < ordersToCreate; i++) {
			log.info("Creating order #: " + i);
			saveOrder(customer, products);
		}

		orderHeaderRepository.flush();
	}

	@Test
	void testLazyVsEager() {
		// With the default Eager fetch for Customer (ManyToOne) we get a single query with a join
		//  When changing to a Lazy fetch type we get a separate query for customer from the getCustomer() below
		OrderHeader orderHeader = orderHeaderRepository.getReferenceById(5L);
		log.info("Order Id Is: " + orderHeader.getId());
		log.info("Customer name is: " + orderHeader.getCustomer().getCustomerName());
	}

	private void saveOrder(Customer customer, List<Product> products) {
		Random random = new Random();

		OrderHeader orderHeader = new OrderHeader();
		orderHeader.setCustomer(customer);

		products.forEach(product -> {
			OrderLine orderLine = new OrderLine();
			orderLine.setProduct(product);
			orderLine.setQuantityOrdered(random.nextInt(20));
			orderHeader.addOrderLine(orderLine);
		});

		orderHeaderRepository.save(orderHeader);
	}

	private Customer loadCustomers() {
		return getOrSaveCustomer(TEST_CUSTOMER);
	}

	private Customer getOrSaveCustomer(String customerName) {
		return customerRepository.findCustomerByCustomerNameIgnoreCase(customerName)
			.orElseGet(() -> {
				Customer c1 = new Customer();
				c1.setCustomerName(customerName);
				c1.setEmail("test@example.com");
				Address address = new Address();
				address.setAddress("123 Main");
				address.setCity("New Orleans");
				address.setState("LA");
				c1.setAddress(address);
				return customerRepository.save(c1);
			});
	}

	private List<Product> loadProducts() {
		List<Product> products = new ArrayList<>();

		products.add(getOrSaveProduct(PRODUCT_D1));
		products.add(getOrSaveProduct(PRODUCT_D2));
		products.add(getOrSaveProduct(PRODUCT_D3));

		return products;
	}

	private Product getOrSaveProduct(String description) {
		return productRepository.findByDescription(description)
			.orElseGet(() -> {
				Product p1 = new Product();
				p1.setDescription(description);
				p1.setProductStatus(ProductStatus.NEW);
				return productRepository.save(p1);
			});
	}
}
