/**
 * @author Unic
 * "hw16" project, Feb 19, 2015, 6:58:18 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns;

import java.util.List;

/** Pattern: Layers Architecture
 * 0ro layer: TicketServer
 * 1st layer: TicketManager
 * 2nd layer: Service
 */
public class TicketManager
{
	private Service service;
	
	public TicketManager(Service service)
	{
		this.service = service;
	}
	
	private Criteria<Ticket> parseCriteria(String query) throws ParsingException
	{
		//TODO criteria parsing logic
		
		throw new ParsingException("Can't parse ticket criteria from \"" + query + "\"");
	}
	
	public String processQuery(String query)
	{
		//TODO process query logic
		
		return "";
	}
	
	public List<Ticket> findTickets(String query) throws ParsingException
	{
		return service.findTickets( parseCriteria(query) );
	}
}