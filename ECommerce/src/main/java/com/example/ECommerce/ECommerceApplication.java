package com.example.ECommerce;

import com.example.ECommerce.model.Category;
import com.example.ECommerce.model.Price;
import com.example.ECommerce.model.Product;
import com.example.ECommerce.repositories.CategoryRepository;
import com.example.ECommerce.repositories.PriceRepository;
import com.example.ECommerce.repositories.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;


@SpringBootApplication
public class ECommerceApplication implements CommandLineRunner {


	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;

	private PriceRepository priceRepository;

	public ECommerceApplication(ProductRepository productRepository,
									 CategoryRepository categoryRepository,
									 PriceRepository priceRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.priceRepository = priceRepository;
	}

	public static void main(String[] args) {

		SpringApplication.run(ECommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		Category category = new Category();
		category.setCategory("jewelery");

		Category savedCategory = categoryRepository.save(category);

		Price price = new Price("Doller", 1000.0);
		// Price savedPrice = priceRepository.save(price); // In product the price is a cascade type so if we are not saving it in db then also JPA at first save the price in db and then save product

//		Product product = new Product();
//		product.setTitle("iPhone4");
//		product.setImage("image url");
//		product.setDescription("Best phone ever");
//		product.setCategory(savedCategory);
//		product.setPrice(price);
//
//
//		productRepository.save(product);
//
//		Product product1 = productRepository.findByTitle("iPhone4");
//		System.out.println(product1);

//		Optional<Category> categoryOptional = categoryRepository.
//				findById(UUID.fromString("02600b88-43f3-4341-8bff-ec665a17c21c"));
//
//
//		if(!categoryOptional.isEmpty()) {
//			Category category = categoryOptional.get();
//			System.out.println(category.getProduct());
//		}

//		productRepository.deleteById(UUID.fromString("8ffdd9b4-acd1-48a3-9c44-ec92c2b4e5b1"));

	}

}
