/**
 * @author Unic
 * "hw11" project, Jan 2, 2015, 4:34:07 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.springmvc.converter;

import org.springframework.beans.factory.annotation.Autowired;

import unic.mentoring.springmvc.core.Converter;
import unic.mentoring.springmvc.data.CategoryData;
import unic.mentoring.springmvc.model.CategoryModel;

public class CategoryConverter extends Converter<CategoryModel, CategoryData>
{
	@Autowired
	private ProductConverter productConverter;
	
	@Override
	protected void populate(CategoryModel source, CategoryData target)
	{
		target.setId( source.getId() );
		target.setName( source.getName() );
		target.setSubcategories( convertAll(source.getSubcategories()) );
		target.setProducts( productConverter.convertAll(source.getProducts()) );
		
		if (source.getCategory() != null)
		{
			CategoryData category = new CategoryData();
			category.setId( source.getCategory().getId() );
			category.setName( source.getCategory().getName() );
			target.setCategory(category);
		}
	}

	@Override
	public CategoryData createTarget()
	{
		return new CategoryData();
	}
}