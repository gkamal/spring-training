package tenx.store.rest;

import java.math.BigDecimal;
import java.net.URI;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import tenx.store.model.Product;

public class ProductServiceTest {
	
	private static final String BASE_URL = "http://localhost:8080/store-ui/rest/";

	@Test
	public void testProductsApi() {
		RestTemplate restTemplate = new RestTemplate();
		
		Product[] products = restTemplate.getForObject(
				BASE_URL + "products",
				Product[].class);
		
		for(Product p:products) {
			System.out.println(p.getName());
		}
		
		Product p= new Product();
		p.setName("Pro Spring");
		p.setPrice(new BigDecimal("600"));
		p.setAvailableQuantity(10);
		
	 	URI location = restTemplate.postForLocation(BASE_URL + "products", p);
	 	System.out.println(location);
		Product p1 = restTemplate.getForObject(location, Product.class);
		System.out.println(p.getId());
		
		p1.setAvailableQuantity(100);
		
		restTemplate.put(location, p1);
		
		restTemplate.delete(location);
	}

}
