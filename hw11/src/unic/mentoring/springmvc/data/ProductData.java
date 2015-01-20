/**
 * @author Unic
 * "hw11" project, Jan 2, 2015, 3:07:56 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.springmvc.data;

public class ProductData
{
	private Integer id;
	private String name;
	private String description;
	private CategoryData category;

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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public CategoryData getCategory()
	{
		return category;
	}
	
	public void setCategory(CategoryData category)
	{
		this.category = category;
	}
}