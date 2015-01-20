/**
 * @author Unic
 * "hw11" project, Jan 2, 2015, 4:30:01 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.springmvc.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import unic.mentoring.springmvc.dao.CatalogDao;
import unic.mentoring.springmvc.model.CategoryModel;
import unic.mentoring.springmvc.model.ProductModel;
import unic.mentoring.springmvc.service.CatalogService;

public class CatalogServiceImpl implements CatalogService
{
	@Autowired
	private CatalogDao catalogDao;
	
	@Override
	public Collection<CategoryModel> getRootCategories()
	{
		return catalogDao.getRootCategories();
	}

	@Override
	public CategoryModel getCategoryById(Integer id)
	{
		return catalogDao.getCategoryById(id);
	}

	@Override
	public ProductModel getProductById(Integer id)
	{
		return catalogDao.getProductById(id);
	}
}