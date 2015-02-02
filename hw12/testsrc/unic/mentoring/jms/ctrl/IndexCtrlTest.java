/**
 * @author Unic
 * "hw12" project, Jan 24, 2015, 6:40:30 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.jms.ctrl;

import org.junit.Assert;
import org.junit.Test;

public class IndexCtrlTest
{
	@Test
	public void indexTest()
	{
		IndexCtrl ctrl = new IndexCtrl();
		Assert.assertEquals("index", ctrl.index(null));
	}
}