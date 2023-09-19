package guru.springframework.udemyspringdatajpaorders.repository;

import guru.springframework.udemyspringdatajpaorders.domain.OrderHeader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderHeaderRepositoryTest {

	@Autowired
	OrderHeaderRepository orderHeaderRepository;

	@Test
	void testOrderHeaderPersists() {

		OrderHeader orderHeader = new OrderHeader();
		orderHeader.setCustomer("John Steinbeck");
		orderHeaderRepository.save(orderHeader);
		List<OrderHeader> orders = orderHeaderRepository.findAll();

		assertThat(orders.size()).isGreaterThan(0);
	}

}
