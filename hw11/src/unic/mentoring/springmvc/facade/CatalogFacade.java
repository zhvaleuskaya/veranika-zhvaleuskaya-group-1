/**
 * @author Unic
 * "hw11" project, Jan 2, 2015, 4:30:49 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.springmvc.facade;

import java.util.Collection;

import unic.mentoring.springmvc.data.CategoryData;
import unic.mentoring.springmvc.data.ProductData;

public interface CatalogFacade
{
	Collection<CategoryData> getRootCategories();
	CategoryData getCategoryById(Integer id);
	ProductData getProductById(Integer id);
}