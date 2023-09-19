package guru.springframework.udemyspringdatajpaorders.repository;

import guru.springframework.udemyspringdatajpaorders.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRespository extends JpaRepository<Product, Long> {

}
