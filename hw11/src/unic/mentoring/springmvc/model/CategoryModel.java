/**
 * @author Unic
 * "hw11" project, Jan 2, 2015, 2:54:09 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.springmvc.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryModel implements Serializable
{
	private static final long serialVersionUID = 0;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = true)
	private CategoryModel category;
	
	@OneToMany(targetEntity = CategoryModel.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Collection<CategoryModel> subcategories;
	
	@OneToMany(targetEntity = ProductModel.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Collection<ProductModel> products;

	public CategoryModel(){}
	
	public CategoryModel(String name)
	{
		this.name = name;
	}
	
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

	public CategoryModel getCategory()
	{
		return category;
	}

	public void setCategory(CategoryModel category)
	{
		this.category = category;
	}
	
	public Collection<CategoryModel> getSubcategories()
	{
		return subcategories;
	}
	
	public void setSubcategories(Collection<CategoryModel> subcategories)
	{
		this.subcategories = subcategories;
	}
	
	public Collection<ProductModel> getProducts()
	{
		return products;
	}

	public void setProducts(Collection<ProductModel> products)
	{
		this.products = products;
	}
}