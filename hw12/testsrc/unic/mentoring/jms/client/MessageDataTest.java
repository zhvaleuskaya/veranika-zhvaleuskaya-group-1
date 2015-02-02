/**
 * @author Unic
 * "hw12" project, Jan 24, 2015, 6:32:30 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.jms.client;

import org.junit.Assert;
import org.junit.Test;

public class MessageDataTest
{
	@Test
	public void toStringTest()
	{
		MessageData data = new MessageData(5, "qwe", "asd");
		Assert.assertEquals("[5][asd] qwe", data.toString());
	}
}