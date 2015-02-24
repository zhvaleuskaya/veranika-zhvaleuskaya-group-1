/**
 * @author Unic
 * "hw16" project, Feb 23, 2015, 7:42:25 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns.messaging.processor;

import unic.mentoring.softpatterns.messaging.Message;
import unic.mentoring.softpatterns.messaging.MessageProcessor;
import unic.mentoring.softpatterns.messaging.data.RouteData;
import unic.mentoring.softpatterns.messaging.data.TicketData;
import unic.mentoring.softpatterns.ticketsystem.service.Service;


public class TicketSystemMessageProcessor	implements MessageProcessor
{
	private Service service;
	
	public TicketSystemMessageProcessor(Service service)
	{
		this.service = service;
	}
	
	@Override
	public void process(Message message)
	{
		if (message.getData() instanceof RouteData)
		{
			/* TODO route data proces logic:
			 * convert to Route
			 * process with service
			 */
		}
		else if (message.getData() instanceof TicketData)
		{
			/* TODO ticket data proces logic:
			 * convert to Ticket
			 * process with service
			 */
		}
	}
}