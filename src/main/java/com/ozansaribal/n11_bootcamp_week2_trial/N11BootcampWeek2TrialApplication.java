package com.ozansaribal.n11_bootcamp_week2_trial;

import com.ozansaribal.n11_bootcamp_week2_trial.Converter.JsonResponseConverter;
import com.ozansaribal.n11_bootcamp_week2_trial.Converter.XmlResponseConverter;
import com.ozansaribal.n11_bootcamp_week2_trial.Entity.Category;
import com.ozansaribal.n11_bootcamp_week2_trial.Entity.Product;
import com.ozansaribal.n11_bootcamp_week2_trial.Service.EntityService.CategoryEntityService;
import com.ozansaribal.n11_bootcamp_week2_trial.Service.EntityService.ProductEntityService;
import com.ozansaribal.n11_bootcamp_week2_trial.Service.WebService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class N11BootcampWeek2TrialApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = SpringApplication.run(N11BootcampWeek2TrialApplication.class, args);

		CategoryEntityService categoryEntityService = applicationContext.getBean(CategoryEntityService.class);

		ProductEntityService productEntityService = applicationContext.getBean(ProductEntityService.class);

		List<Product> productList = productEntityService.findAll();

		for (Product product : productList){
			System.out.println(product.getName());
		}

	}

	private static void saveBook(CategoryEntityService categoryEntityService) {
		Category book = new Category();
		book.setName("Book");
		book.setRefraction(1L);

		book = categoryEntityService.save(book);

		Category bookBottom = new Category();
		bookBottom.setRefraction(2L);
		bookBottom.setName("Book");
		bookBottom.setTopCategory(book);

		categoryEntityService.save(bookBottom);
	}

	private static void findAllProductList(ProductEntityService productEntityService) {
		List<Product> productList = productEntityService.findAll();

		for (Product product : productList) {
			System.out.println(product.getName());
		}
	}

	private static void findAllProductList(CategoryEntityService categoryEntityService) {
		List<Category> categoryList = categoryEntityService.findAll();

		for (Category category : categoryList) {
			System.out.println(category.getName());
		}
	}

	private static void deleteProductList(ProductEntityService productEntityService) {
		List<Long> toBeDeleteProductIdlist = Arrays.asList(102L, 152L, 202L, 252L, 302L);

		for (Long productId : toBeDeleteProductIdlist) {
			productEntityService.deleteById(productId);
		}
	}

	private static void getSamsungM31(CategoryEntityService categoryEntityService, ProductEntityService productEntityService) {
		Category category = categoryEntityService.findById(502L);

		Product product = new Product();
		product.setName("Samsung M31");
		product.setPrice(new BigDecimal("3000"));
		product.setRegisterDate(new Date());
		product.setCategory(category);

		product = productEntityService.save(product);

		System.out.println(product);
	}

	private static Category getTelephoneCategory(CategoryEntityService service) {
		Category topCategory = service.findById(2L);

		System.out.println(topCategory);

		Category category = new Category();
		category.setName("Telephone");
		category.setRefraction(2L);
		category.setTopCategory(topCategory);

		category = service.save(category);

		System.out.println(category);

		return category;
	}

}
