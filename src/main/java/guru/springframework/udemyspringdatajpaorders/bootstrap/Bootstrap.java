package guru.springframework.udemyspringdatajpaorders.bootstrap;

import guru.springframework.udemyspringdatajpaorders.domain.OrderHeader;
import guru.springframework.udemyspringdatajpaorders.repository.OrderHeaderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Slf4j
public class Bootstrap implements CommandLineRunner {

	@Autowired
	OrderHeaderRepository orderHeaderRepository;

	// Note: Remember that DataLoadTest would need to be ran first to populate our test db
	@Transactional        // this is what was added to fix the Lazy Init Error
	@Override
	public void run(String... args) throws Exception {
		log.info("Starting Bootstrap run method...");
		Optional<OrderHeader> orderHeader = orderHeaderRepository.findById(1L);
		log.info("OrderHeader present: " + orderHeader.isPresent());
		orderHeader.ifPresent(header -> header.getOrderLines().forEach(ol -> {
			log.info(ol.getProduct().getDescription());
			ol.getProduct().getCategories().forEach(cat -> log.info(cat.getDescription()));
		}));
	}
}
