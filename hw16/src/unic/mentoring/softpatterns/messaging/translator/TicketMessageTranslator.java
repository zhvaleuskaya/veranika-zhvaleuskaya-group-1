/**
 * @author Unic
 * "hw16" project, Feb 23, 2015, 8:11:29 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns.messaging.translator;

import unic.mentoring.softpatterns.messaging.Message;
import unic.mentoring.softpatterns.messaging.MessageTranslator;
import unic.mentoring.softpatterns.messaging.data.TicketData;

public class TicketMessageTranslator implements MessageTranslator<TicketData>
{
	@Override
	public Message translate(TicketData t)
	{
		return new Message(t);
	}
}