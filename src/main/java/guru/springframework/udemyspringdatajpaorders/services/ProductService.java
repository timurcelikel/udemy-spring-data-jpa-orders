package guru.springframework.udemyspringdatajpaorders.services;

import guru.springframework.udemyspringdatajpaorders.domain.Product;

public interface ProductService {

	Product saveProduct(Product product);

	Product updateQuantityOnHand(Long id, Integer quantity);
}
