/**
 * @author Unic
 * "hw11" project, Jan 2, 2015, 4:21:22 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.springmvc.dao;

import java.util.Collection;

import unic.mentoring.springmvc.model.CategoryModel;
import unic.mentoring.springmvc.model.ProductModel;

public interface CatalogDao
{
	Collection<CategoryModel> getRootCategories();
	CategoryModel getCategoryById(Integer id);
	ProductModel getProductById(Integer id);
}