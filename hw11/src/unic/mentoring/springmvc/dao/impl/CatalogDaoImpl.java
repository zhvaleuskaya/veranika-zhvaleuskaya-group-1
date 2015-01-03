/**
 * @author Unic
 * "hw11" project, Jan 2, 2015, 4:27:18 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.springmvc.dao.impl;

import java.util.Collection;

import unic.mentoring.springmvc.core.Dao;
import unic.mentoring.springmvc.dao.CatalogDao;
import unic.mentoring.springmvc.model.CategoryModel;
import unic.mentoring.springmvc.model.ProductModel;

public class CatalogDaoImpl extends Dao implements CatalogDao
{
	private static final String QUERY_ROOT_CATEGORIES = "FROM CategoryModel c WHERE c.category IS null";
	
	@Override
	public Collection<CategoryModel> getRootCategories()
	{
		return (Collection<CategoryModel>)query(QUERY_ROOT_CATEGORIES);
	}

	@Override
	public CategoryModel getCategoryById(Integer id)
	{
		return get(id, CategoryModel.class);
	}

	@Override
	public ProductModel getProductById(Integer id)
	{
		return get(id, ProductModel.class);
	}
}