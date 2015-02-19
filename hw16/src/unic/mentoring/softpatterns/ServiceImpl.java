/**
 * @author Unic
 * "hw16" project, Feb 19, 2015, 6:28:18 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns;

import java.util.ArrayList;
import java.util.List;

public class ServiceImpl implements Service
{
	private List<Ticket> tickets;

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