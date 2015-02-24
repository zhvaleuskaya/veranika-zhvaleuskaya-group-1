/**
 * @author Unic
 * "hw16" project, Feb 23, 2015, 8:10:08 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns.messaging.translator;

import unic.mentoring.softpatterns.messaging.Message;
import unic.mentoring.softpatterns.messaging.MessageTranslator;
import unic.mentoring.softpatterns.messaging.data.RouteData;


public class RouteMessageTranslator implements MessageTranslator<RouteData>
{
	@Override
	public Message translate(RouteData t)
	{
		return new Message(t);
	}
}