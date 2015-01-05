/**
 * @author Unic
 * "hw11" project, Jan 2, 2015, 4:31:52 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.springmvc.facade.impl;

import java.util.ArrayList;
import java.util.Collection;

import unic.mentoring.springmvc.data.CategoryData;
import unic.mentoring.springmvc.data.ProductData;
import unic.mentoring.springmvc.facade.CatalogFacade;

public class CatalogFacadeMock implements CatalogFacade
{
	@Override
	public Collection<CategoryData> getRootCategories()
	{
		CategoryData category;
		ProductData product;
		Collection<ProductData> products;
		Collection<CategoryData> categories = new ArrayList<CategoryData>(2);
		products = new ArrayList<ProductData>(3);
		product = new ProductData();
		product.setId(101);
		product.setName("p1");
		product.setDescription("p1d");
		products.add(product);
		product = new ProductData();
		product.setId(102);
		product.setName("p2");
		product.setDescription("p2d");
		products.add(product);
		product = new ProductData();
		product.setId(103);
		product.setName("p3");
		product.setDescription("p3d");
		products.add(product);
		category = new CategoryData();
		category.setId(501);
		category.setName("c1");
		category.setProducts(products);
		categories.add(category);
		products = new ArrayList<ProductData>(2);
		product = new ProductData();
		product.setId(104);
		product.setName("p4");
		product.setDescription("p4d");
		products.add(product);
		product = new ProductData();
		product.setId(105);
		product.setName("p5");
		product.setDescription("p5d");
		products.add(product);
		category = new CategoryData();
		category.setId(502);
		category.setName("c2");
		category.setProducts(products);
		categories.add(category);
		return categories;
	}

	@Override
	public CategoryData getCategoryById(Integer id)
	{
		ProductData product;
		Collection<ProductData> products = new ArrayList<>(2);
		CategoryData category = new CategoryData();
		category.setId(id);
		category.setName("Category " + id);
		product = new ProductData();
		product.setId(201);
		product.setName("pp1");
		product.setDescription("pp1d");
		products.add(product);
		product = new ProductData();
		product.setId(202);
		product.setName("pp2");
		product.setDescription("pp2d");
		products.add(product);
		category.setProducts(products);
		return category;
	}

	@Override
	public ProductData getProductById(Integer id)
	{
		ProductData product = new ProductData();
		product.setId(id);
		product.setName("The product " + id);
		product.setDescription("The description for " + product.getName() + ".");
		CategoryData category = new CategoryData();
		category.setId(707);
		category.setName("The category " + category.getId());
		product.setCategory(category);
		return product;
	}
}