/**
 * @author Unic
 * "hw11" project, Jan 2, 2015, 3:06:32 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.springmvc.data;

import java.util.Collection;

public class CategoryData
{
	private Integer id;
	private String name;
	private CategoryData category;
	private Collection<CategoryData> subcategories;
	private Collection<ProductData> products;

	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public CategoryData getCategory()
	{
		return category;
	}

	public void setCategory(CategoryData category)
	{
		this.category = category;
	}
	
	public Collection<CategoryData> getSubcategories()
	{
		return subcategories;
	}
	
	public void setSubcategories(Collection<CategoryData> subcategories)
	{
		this.subcategories = subcategories;
	}
	
	public Collection<ProductData> getProducts()
	{
		return products;
	}
	
	public void setProducts(Collection<ProductData> products)
	{
		this.products = products;
	}
}