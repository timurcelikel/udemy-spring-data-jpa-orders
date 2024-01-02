package guru.springframework.udemyspringdatajpaorders.bootstrap;

import guru.springframework.udemyspringdatajpaorders.domain.Customer;
import guru.springframework.udemyspringdatajpaorders.domain.OrderHeader;
import guru.springframework.udemyspringdatajpaorders.repository.CustomerRepository;
import guru.springframework.udemyspringdatajpaorders.repository.OrderHeaderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class Bootstrap implements CommandLineRunner {

	@Autowired
	OrderHeaderRepository orderHeaderRepository;

	@Autowired
	CustomerRepository customerRepository;

	/* Note: Remember that DataLoadTest would need to be ran first to populate our test db
		@Transactional        // this is what was added to fix the Lazy Init Error. Commented out after annotating
		categories with FetchType.EAGER (see notes)
	*/
	@Override
	public void run(String... args) throws Exception {
		log.info("Starting Bootstrap run method...");
		Optional<OrderHeader> orderHeader = orderHeaderRepository.findById(1L);
		log.info("OrderHeader present: " + orderHeader.isPresent());
		orderHeader.ifPresent(header -> header.getOrderLines().forEach(ol -> {
			log.info(ol.getProduct().getDescription());
			ol.getProduct().getCategories().forEach(cat -> log.info(cat.getDescription()));
		}));
		
		Customer customer = new Customer();
		customer.setCustomerName("Testing Version");
		Customer savedCustomer = customerRepository.save(customer);
		log.info("Version1: " + savedCustomer.getVersion());

		savedCustomer.setCustomerName("Testing Version 2");
		Customer savedCustomer2 = customerRepository.save(savedCustomer);
		log.info("Version2: " + savedCustomer2.getVersion());

		savedCustomer2.setCustomerName("Testing Version 3");
		Customer savedCustomer3 = customerRepository.save(savedCustomer2);
		log.info("Version3: " + savedCustomer3.getVersion());

		//customerRepository.deleteById(savedCustomer.getId());  // commenting this out to demonstrate stale update
		// exception:
		// customerRepository.delete(savedCustomer); // Throws StaleObjectStateException
		customerRepository.delete(savedCustomer3);
	}
}
