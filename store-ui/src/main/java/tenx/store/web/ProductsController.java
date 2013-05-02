package tenx.store.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tenx.store.internal.ProductDao;
import tenx.store.model.Product;

@Controller
public class ProductsController {
	
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping("/products")
	public String listProducts( @RequestParam(value="name", defaultValue="")String name, Model model ) {
		System.out.println("fetching... products");
		List<Product> products = productDao.search(name);
		
		model.addAttribute("products", products);
		
		return "/WEB-INF/views/products.jsp";
	}
	
	@RequestMapping("/products/special")
	public String specialProducts() {
		return "redirect:../products?name=special";
	}

}







