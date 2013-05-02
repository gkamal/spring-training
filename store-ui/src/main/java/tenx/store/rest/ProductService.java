package tenx.store.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import tenx.store.internal.ProductDao;
import tenx.store.model.Product;

@Controller
public class ProductService {

	@Autowired
	ProductDao productDao;
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public @ResponseBody List<Product> allProducts() {
		return productDao.search("");
	}
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.CREATED)
	public void createProducts(@RequestBody Product product, 
			HttpServletRequest request,
			HttpServletResponse response) {
		productDao.create(product);
		String location = request.getRequestURL() + "/" + product.getId();
		response.addHeader("Location", location);
	}
	
	@RequestMapping(value="/products/{id}", method=RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
		//productDao.update(product);
		Product p = productDao.findById(id);
		p.setAvailableQuantity(product.getAvailableQuantity());
		productDao.update(p);
	}
	
	@RequestMapping(value="/products/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable("id") Long id) {
		productDao.delete(id);
	}
	
	@RequestMapping("/products/{id}")
	public @ResponseBody Product getProducts(@PathVariable("id") Long id) {
		return productDao.findById(id);
	}
	
	
	
	
}
