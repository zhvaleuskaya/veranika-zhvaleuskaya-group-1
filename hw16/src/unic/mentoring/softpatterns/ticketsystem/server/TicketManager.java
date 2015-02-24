/**
 * @author Unic
 * "hw16" project, Feb 19, 2015, 6:58:18 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns.ticketsystem.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import unic.mentoring.softpatterns.data.Ticket;
import unic.mentoring.softpatterns.ticketsystem.service.Service;

/** Pattern: Anti-Corruption Layer
 * Adapter between user text queries interface and Service API.
 */
public class TicketManager
{
	private Service service;
	
	public TicketManager(Service service)
	{
		this.service = service;
	}
	
	private String processQuery(String command, Map<String, String> paramsMap) throws QueryProcessingException
	{
		if ("find".equals(command))
		{
			Criteria<Ticket> criteria = parseCriteria(paramsMap);
			List<Ticket> tickets = service.findTickets(criteria);
			stringify(tickets);
		}
		
		throw new QueryProcessingException("Unknown command \"" + command + "\"");
	}
	
	public String processQuery(String query)
	{
		String response = "Malformed query.";
		String[] arguments = query.split(" ");
		
		if (arguments.length > 0)
		{
			String command = arguments[0];
			Map<String, String> paramsMap = null;
			
			if (arguments.length > 1)
			{
				paramsMap = new HashMap<>();
				
				for (int iParam = 1; iParam < arguments.length; ++iParam)
				{
					String param = arguments[iParam];
					String[] keyValue = param.split("=");
					
					if (keyValue.length == 2)
					{
						paramsMap.put(keyValue[0], keyValue[1]);
					}
				}
			}
			
			try
			{
				response = processQuery(command, paramsMap);
			}
			catch (QueryProcessingException e)
			{
				response = "Error: " + e.getMessage();
			}
		}
		
		return response;
	}
	
	private Criteria<Ticket> parseCriteria(Map<String, String> paramsMap)
	{
		TicketCriteria criteria = new TicketCriteria();
		criteria.setSource( paramsMap.get("from") );
		criteria.setSource( paramsMap.get("to") );
		criteria.setSource( paramsMap.get("on") );
		
		return criteria;
	}
	
	private String stringify(List<Ticket> tickets)
	{
		StringBuilder sb = new StringBuilder( tickets.isEmpty() ? "No tickets found." : "Tickets found: " );
		boolean more = false;
		
		for (Ticket ticket : tickets)
		{
			if (more)
			{
				sb.append(", ");
			}
			else
			{
				more = true;
			}
			
			sb.append("[").append(ticket.toString()).append("]");
		}
		
		return sb.toString();
	}
}