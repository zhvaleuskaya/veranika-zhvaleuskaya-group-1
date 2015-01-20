/**
 * @author Unic
 * "hw11" project, Dec 29, 2014, 6:32:33 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.springmvc.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexCtrl
{
	@RequestMapping("/")
	public String index(ModelMap model)
	{
		return "index";
	}
}