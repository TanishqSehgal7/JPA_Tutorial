package com.example.jpatutorial.jpaTutorial;

import com.example.jpatutorial.jpaTutorial.entities.Product;
import com.example.jpatutorial.jpaTutorial.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpaTutorialApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testRepository() {

		Product product = Product.builder()
				.sku("NestleChocolate5050")
				.title("Nestle Milk Chocolate")
				.price(BigDecimal.valueOf(50.00))
				.quantity(5).build();

		Product savedProduct = productRepository.save(product);
		System.out.println(savedProduct);
	}

	@Test
	void getRepository() {

		List<Product> productList = productRepository.findAll();

		for(Product product: productList) {
			System.out.println(product);
		}

//		productList = productRepository.findByTitle("Pepsi");
//		System.out.println(productList);

		productList = productRepository.findByCreatedAtAfter(LocalDateTime.of(2024,1,1,0, 0,0));
		System.out.println(productList);

		productList = productRepository.findByQuantityAndPrice(5, BigDecimal.valueOf(120));
		System.out.println(productList);

		productList = productRepository.findByPriceLessThan(BigDecimal.valueOf(50));
		System.out.println(productList);

		productList = productRepository.findByTitleLike("%Choco%");
		System.out.println(productList);

//		productList = productRepository.findByTitleContainingIgnoreCase("ChoCo");
//		System.out.println(productList);

	}

	@Test
	void getSingleProduct() {

		Optional<Product> singleUniqueProduct = productRepository
				.findByTitleAndPrice("RedBull Energy Drink", BigDecimal.valueOf(100));
		singleUniqueProduct.ifPresent(System.out::println);
	}

}
