package unic.mentoring.springcore.repository;

import java.util.List;

import unic.mentoring.springcore.data.Product;

public interface ProductRepository
{
	Product getProductById(Long productId);
	List<Product> getProducts();
	List<Product> getProductsByName(String name);
	Long createProduct(Product product);
	void updateProduct(Product product);
	void deleteProduct(Long productId);
}