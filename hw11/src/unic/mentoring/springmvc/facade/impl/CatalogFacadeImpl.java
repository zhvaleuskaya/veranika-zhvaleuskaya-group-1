/**
 * @author Unic
 * "hw11" project, Jan 2, 2015, 4:31:52 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.springmvc.facade.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import unic.mentoring.springmvc.converter.CategoryConverter;
import unic.mentoring.springmvc.converter.ProductConverter;
import unic.mentoring.springmvc.data.CategoryData;
import unic.mentoring.springmvc.data.ProductData;
import unic.mentoring.springmvc.facade.CatalogFacade;
import unic.mentoring.springmvc.service.CatalogService;

public class CatalogFacadeImpl implements CatalogFacade
{
	@Autowired
	private CatalogService catalogService;
	
	@Autowired
	private CategoryConverter categoryConverter;
	
	@Autowired
	private ProductConverter productConverter;
	
	@Override
	public Collection<CategoryData> getRootCategories()
	{
		return categoryConverter.convertAll( catalogService.getRootCategories() );
	}

	@Override
	public CategoryData getCategoryById(Integer id)
	{
		return categoryConverter.convert( catalogService.getCategoryById(id) );
	}

	@Override
	public ProductData getProductById(Integer id)
	{
		return productConverter.convert( catalogService.getProductById(id) );
	}
}