/**
 * @author Unic
 * "hw11" project, Jan 2, 2015, 4:38:53 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.springmvc.converter;

import unic.mentoring.springmvc.core.Converter;
import unic.mentoring.springmvc.data.CategoryData;
import unic.mentoring.springmvc.data.ProductData;
import unic.mentoring.springmvc.model.ProductModel;

public class ProductConverter extends Converter<ProductModel, ProductData>
{
	@Override
	protected void populate(ProductModel source, ProductData target)
	{
		target.setId( source.getId() );
		target.setName( source.getName() );
		target.setDescription( source.getDescription() );
		
		if (source.getCategory() != null)
		{
			CategoryData category = new CategoryData();
			category.setId( source.getCategory().getId() );
			category.setName( source.getCategory().getName() );
			target.setCategory(category);
		}
	}

	@Override
	public ProductData createTarget()
	{
		return new ProductData();
	}
}