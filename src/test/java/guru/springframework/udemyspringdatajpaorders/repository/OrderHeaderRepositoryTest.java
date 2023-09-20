package guru.springframework.udemyspringdatajpaorders.repository;

import guru.springframework.udemyspringdatajpaorders.domain.OrderHeader;
import guru.springframework.udemyspringdatajpaorders.domain.OrderLine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderHeaderRepositoryTest {

	@Autowired
	OrderHeaderRepository orderHeaderRepository;

	@Test
	void testSaveOrderWithLine() {

		OrderHeader orderHeader = new OrderHeader();
		orderHeader.setCustomer("New Customer");

		OrderLine orderLine = new OrderLine();
		orderLine.setQuantityOrdered(5);
		orderHeader.setOrderLines(Set.of(orderLine));
		orderLine.setOrderHeader(orderHeader);
		OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

		assertNotNull(savedOrder);
		assertNotNull(savedOrder.getId());
		assertEquals(savedOrder.getOrderLines().size(), 1);
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
