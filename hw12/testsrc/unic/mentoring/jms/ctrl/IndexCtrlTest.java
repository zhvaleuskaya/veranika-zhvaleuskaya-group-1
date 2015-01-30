/**
 * @author Unic
 * "hw12" project, Jan 24, 2015, 6:40:30 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.jms.ctrl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class IndexCtrlTest
{
	@Autowired
	private IndexCtrl ctrl;
	
	@Test
	public void indexTest()
	{
		Assert.assertEquals("index", ctrl.index(null));
	}
}