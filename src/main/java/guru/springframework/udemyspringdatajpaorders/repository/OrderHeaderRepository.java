package guru.springframework.udemyspringdatajpaorders.repository;

import guru.springframework.udemyspringdatajpaorders.domain.Customer;
import guru.springframework.udemyspringdatajpaorders.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {

	List<OrderHeader> findAllByCustomer(Customer customer);
}
