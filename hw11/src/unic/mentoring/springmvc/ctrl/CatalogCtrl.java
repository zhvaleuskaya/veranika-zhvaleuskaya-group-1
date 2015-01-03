/**
 * @author Unic
 * "hw11" project, Dec 29, 2014, 6:32:33 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.springmvc.ctrl;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import unic.mentoring.springmvc.facade.CatalogFacade;

@Controller
public class CatalogCtrl
{
	@Resource(name = "catalogFacade")
	private CatalogFacade catalogFacade;
	
	@RequestMapping("/catalog")
	public String catalog(ModelMap model)
	{
		model.addAttribute("categories", catalogFacade.getRootCategories());
		return "catalog";
	}
	
	@RequestMapping("/category/{id}")
	public String category(@PathVariable("id") Integer id, ModelMap model)
	{
		model.addAttribute("category", catalogFacade.getCategoryById(id));
		return "category";
	}
	
	@RequestMapping("/product/{id}")
	public String product(@PathVariable("id") Integer id, ModelMap model)
	{
		model.addAttribute("product", catalogFacade.getProductById(id));
		return "product";
	}
}