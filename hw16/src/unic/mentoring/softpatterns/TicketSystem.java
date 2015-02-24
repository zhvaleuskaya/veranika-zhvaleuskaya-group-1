/**
 * @author Unic
 * "hw16" project, Feb 23, 2015, 8:29:56 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns;

import java.io.IOException;

import unic.mentoring.softpatterns.messaging.ControlBus;
import unic.mentoring.softpatterns.messaging.MessageEndpoint;
import unic.mentoring.softpatterns.messaging.MessageProcessor;
import unic.mentoring.softpatterns.messaging.ProcessManager;
import unic.mentoring.softpatterns.messaging.processor.TicketSystemMessageProcessor;
import unic.mentoring.softpatterns.ticketsystem.server.TicketManager;
import unic.mentoring.softpatterns.ticketsystem.server.TicketServer;
import unic.mentoring.softpatterns.ticketsystem.service.Service;
import unic.mentoring.softpatterns.ticketsystem.service.impl.SampleServiceImpl;

public class TicketSystem
{
	public static void main(String args[]) throws IOException
	{
		/** Common objects
		 */
		Service service = new SampleServiceImpl();
		
		/** Ticket system server
		 */
		TicketManager manager = new TicketManager(service);
		TicketServer server = new TicketServer(manager);
		server.listen();
		
		/** Data import system
		 */
		ControlBus controlBus = new ControlBus();
		MessageProcessor messageProcessor = new TicketSystemMessageProcessor(service);
		ProcessManager processManager = new ProcessManager(controlBus, messageProcessor);
		processManager.registerEndpoint( new MessageEndpoint(C.Remote.ROUTE_EXTERNAL_DATASOURCE_PORT) );
		processManager.registerEndpoint( new MessageEndpoint(C.Remote.TICKET_EXTERNAL_DATASOURCE_PORT) );
	}
}