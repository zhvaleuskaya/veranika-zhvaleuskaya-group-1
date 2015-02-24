/**
 * @author Unic
 * "hw16" project, Feb 18, 2015, 7:04:55 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns.data;

import java.util.Date;

/** Pattern: Aggregator
 * Ticket aggregates Route that contains part of ticket related information.
 */
public class Ticket
{
	private Route route;
	private int cost;
	private Date time;

	public Ticket(Route route, int cost, Date time)
	{
		this.route = route;
		this.cost = cost;
		this.time = time;
	}
	
	public Route getRoute()
	{
		return route;
	}

	public void setRoute(Route route)
	{
		this.route = route;
	}

	public int getCost()
	{
		return cost;
	}

	public void setCost(int cost)
	{
		this.cost = cost;
	}

	public Date getTime()
	{
		return time;
	}

	public void setTime(Date time)
	{
		this.time = time;
	}
	
	@Override
	public String toString()
	{
		return "From \"" + route.getSource() + "\" to \"" + route.getDestination() + "\" on " + time.toString() + " for " + cost;
	}
}