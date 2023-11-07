package guru.springframework.udemyspringdatajpaorders.repository;

import guru.springframework.udemyspringdatajpaorders.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderHeaderRepositoryTest {

	@Autowired
	OrderHeaderRepository orderHeaderRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CustomerRepository customerRepository;

	Product product;

	@BeforeEach
	void setUp() {

		Product newProduct = new Product();
		newProduct.setProductStatus(ProductStatus.NEW);
		newProduct.setDescription("Test Product");
		product = productRepository.saveAndFlush(newProduct);
	}

	@Test
	void testSaveCustomerWithOrders() {

		Customer customer = new Customer();
		customer.setCustomerName("Customer Contact");
		OrderHeader oh = new OrderHeader();
		oh.setCustomer(customer);
		customer.addOrderHeader(oh);
		Customer savedCustomer = customerRepository.save(customer);
		assertNotNull(savedCustomer);
	}

	@Test
	void testSaveOrderWithLine() {

		OrderLine orderLine = new OrderLine();
		orderLine.setQuantityOrdered(5);
		orderLine.setProduct(product);

		// We no longer do this but instead use our addOrderLine() helper method
		//orderHeader.setOrderLines(Set.of(orderLine));
		//orderLine.setOrderHeader(orderHeader);

		OrderHeader orderHeader = new OrderHeader();
		Customer customer = new Customer();
		customer.setCustomerName("New Customer");
		orderHeader.setCustomer(customer);
		orderHeader.addOrderLine(orderLine);

		OrderApproval orderApproval = new OrderApproval();
		orderApproval.setApprovedBy("me");
		orderHeader.setOrderApproval(orderApproval);

		OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

		assertNotNull(savedOrder);
		assertNotNull(savedOrder.getId());
		assertEquals(savedOrder.getOrderLines().size(), 1);

		OrderHeader fetchedOrder = orderHeaderRepository.getReferenceById(savedOrder.getId());
		assertNotNull(fetchedOrder);
	}

	@Test
	void testOrderHeaderPersists() {

		OrderHeader orderHeader = new OrderHeader();
		Customer customer = new Customer();
		customer.setCustomerName("John Steinbeck");
		orderHeader.setCustomer(customer);
		OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);
		List<OrderHeader> orders = orderHeaderRepository.findAll();

		assertThat(orders.size()).isGreaterThan(0);

		OrderHeader fetchedOrder = orderHeaderRepository.getReferenceById(savedOrder.getId());
		assertNotNull(fetchedOrder);
		assertNotNull(fetchedOrder.getCreatedDate());
		assertNotNull(fetchedOrder.getLastModifiedDate());
	}

}
