/**
 * @author Unic
 * "hw11" project, Jan 2, 2015, 2:54:37 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.springmvc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductModel implements Serializable
{
	private static final long serialVersionUID = 0;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = true)
	private CategoryModel category;
	
	private String description;

	public ProductModel(){}
	
	public ProductModel(String name, String description, CategoryModel category)
	{
		this.name = name;
		this.description = description;
		this.category = category;
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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
}