package guru.springframework.udemyspringdatajpaorders.repository;

import guru.springframework.udemyspringdatajpaorders.domain.OrderHeader;
import guru.springframework.udemyspringdatajpaorders.domain.OrderLine;
import guru.springframework.udemyspringdatajpaorders.domain.Product;
import guru.springframework.udemyspringdatajpaorders.domain.ProductStatus;
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
	ProductRespository productRespository;

	Product product;

	@BeforeEach
	void setUp() {

		Product newProduct = new Product();
		newProduct.setProductStatus(ProductStatus.NEW);
		newProduct.setDescription("Test Product");
		product = productRespository.saveAndFlush(newProduct);
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
		orderHeader.setCustomer("New Customer");
		orderHeader.addOrderLine(orderLine);

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
		orderHeader.setCustomer("John Steinbeck");
		OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);
		List<OrderHeader> orders = orderHeaderRepository.findAll();

		assertThat(orders.size()).isGreaterThan(0);

		OrderHeader fetchedOrder = orderHeaderRepository.getReferenceById(savedOrder.getId());
		assertNotNull(fetchedOrder);
		assertNotNull(fetchedOrder.getCreatedDate());
		assertNotNull(fetchedOrder.getLastModifiedDate());
	}
}
