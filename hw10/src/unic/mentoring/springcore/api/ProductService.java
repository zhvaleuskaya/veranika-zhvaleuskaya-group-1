package unic.mentoring.springcore.api;

import java.util.List;

import unic.mentoring.springcore.data.Product;

/**
 * Provides API for manipulating products.
 */
public interface ProductService {
    
    /**
     * Gets the product by id.
     *
     * @param productId the product id
     * @return the product by id
     */
    Product getProductById(Long productId);

    /**
     * Gets the all products.
     *
     * @return the products
     */
    List<Product> getProducts();
    
    /**
     * Gets the products by name.
     *
     * @param name the name
     * @return the products by name
     */
    List<Product> getProductsByName(String name);
    
    /**
     * Creates the product.
     *
     * @param product the product
     * @return the product id
     */
    Long createProduct(Product product);
    
    /**
     * Update product.
     *
     * @param product the product
     */
    void updateProduct(Product product);
    
    /**
     * Delete product.
     *
     * @param productId the product id
     */
    void deleteProduct(Long productId);
}
