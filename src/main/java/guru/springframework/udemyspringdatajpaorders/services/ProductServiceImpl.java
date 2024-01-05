package guru.springframework.udemyspringdatajpaorders.services;

import guru.springframework.udemyspringdatajpaorders.domain.Product;
import guru.springframework.udemyspringdatajpaorders.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Product saveProduct(final Product product) {
		return productRepository.saveAndFlush(product);
	}

	@Override
	public Product updateQuantityOnHand(final Long id, final Integer quantity) {
		Product product = productRepository.findById(id).orElseThrow();
		product.setQuantityOnHand(quantity);
		return productRepository.saveAndFlush(product);
	}
}
