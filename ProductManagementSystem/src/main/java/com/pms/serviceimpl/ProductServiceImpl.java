package com.pms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.entity.Product;
import com.pms.exception.ProductNotFoundException;
import com.pms.repository.ProductRepository;
import com.pms.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository pr;

	@Override
	public Product saveProduct(Product product) {
		
		return pr.save(product);
	}

	@Override
	public List<Product> getAllProducts() {

		return pr.findAll();
	}

	@Override
	public Product getProductById(int pid) {

		return pr.findById(pid).orElseThrow(() -> new ProductNotFoundException("No product exist in table"));
		
	}

	@Override
	public Product updateProductById(Product product, int pid) {

		Product newproduct = pr.findById(pid).orElseThrow(() -> new ProductNotFoundException("No product exist in table"));
		
		// updated value
		newproduct.setPrice(product.getPrice());
		pr.save(newproduct);
		return newproduct;
		
	}

	@Override
	public void deleteProductById(int pid) {

		pr.findById(pid).orElseThrow(() -> new ProductNotFoundException("No product exist in table"));
		pr.deleteById(pid);
		
	}

}
