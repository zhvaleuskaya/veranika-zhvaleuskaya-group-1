/**
 * @author Unic
 * "hw11" project, Jan 2, 2015, 4:29:11 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.springmvc.service;

import java.util.Collection;

import unic.mentoring.springmvc.model.CategoryModel;
import unic.mentoring.springmvc.model.ProductModel;

public interface CatalogService
{
	Collection<CategoryModel> getRootCategories();
	CategoryModel getCategoryById(Integer id);
	ProductModel getProductById(Integer id);
}