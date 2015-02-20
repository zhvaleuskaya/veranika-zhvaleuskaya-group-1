/**
 * @author Unic
 * "hw16" project, Feb 19, 2015, 6:28:18 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SampleServiceImpl implements Service
{
	private List<Ticket> tickets;

	public SampleServiceImpl()
	{
		tickets = new ArrayList<Ticket>();
		tickets.add( new Ticket( new Route("A", "B"), 12, new Date() ) );
		tickets.add( new Ticket( new Route("B", "C"), 25, new Date() ) );
		tickets.add( new Ticket( new Route("A", "C"), 34, new Date() ) );
	}
	
	@Override
	public List<Ticket> findTickets(Criteria<Ticket> criteria)
	{
		List<Ticket> ticketsFound = new ArrayList<Ticket>();
		
		for (Ticket ticket : tickets)
		{
			if (criteria.matches(ticket))
			{
				ticketsFound.add(ticket);
			}
		}
		
		return ticketsFound;
	}
}