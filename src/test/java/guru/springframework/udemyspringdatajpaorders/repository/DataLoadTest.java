package guru.springframework.udemyspringdatajpaorders.repository;

import guru.springframework.udemyspringdatajpaorders.domain.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;
import java.util.stream.Collectors;

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

		int ordersToCreate = 1000;

		for (int i = 0; i < ordersToCreate; i++) {
			log.info("Creating order #: " + i);
			saveOrder(customer, products);
		}

		orderHeaderRepository.flush();
	}

	@Test
	@Transactional
	void testDatabaseLock() {
		Long id = 1L;

		Optional<OrderHeader> orderHeader = orderHeaderRepository.findById(id);

		if (orderHeader.isPresent()) {
			OrderHeader oh = orderHeader.get();
			Address billTo = new Address();
			billTo.setAddress("Bill me");
			oh.setBillingAddress(billTo);
			oh.setTestRow("test");
			orderHeaderRepository.saveAndFlush(oh);

			log.info("I updated the order");
		}
	}

	@Test
	void testLazyVsEager() {
		// With the default Eager fetch for Customer (ManyToOne) we get a single query with a join
		//  When changing to a Lazy fetch type we get a separate query for customer from the getCustomer() below
		OrderHeader orderHeader = orderHeaderRepository.getReferenceById(5L);
		log.info("Order Id Is: " + orderHeader.getId());
		log.info("Customer name is: " + orderHeader.getCustomer().getCustomerName());
	}

	@Test
	void testN_PlusOneProblem() {

		Customer customer =
			customerRepository.findCustomerByCustomerNameIgnoreCase(TEST_CUSTOMER).orElse(null);

		IntSummaryStatistics totalOrdered =
			orderHeaderRepository.findAllByCustomer(customer).stream().flatMap(
				orderHeader -> orderHeader.getOrderLines().stream()).collect(
				Collectors.summarizingInt(OrderLine::getQuantityOrdered));

		log.info("total ordered: " + totalOrdered.getSum());
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
