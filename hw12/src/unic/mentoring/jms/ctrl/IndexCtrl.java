/**
 * @author Unic
 * "hw11" project, Dec 29, 2014, 6:32:33 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.jms.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import unic.mentoring.jms.Constants;

@Controller
public class IndexCtrl
{
	@RequestMapping("/")
	public String index(Model model)
	{
		return Constants.Page.INDEX;
	}
}