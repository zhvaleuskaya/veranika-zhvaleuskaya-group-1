/**
 * @author Unic
 * "hw16" project, Feb 18, 2015, 7:04:55 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns;

import java.util.Date;

/** Pattern: Aggregator
 * Ticket aggregates Route that contains part of ticket related information.
 */
public class Ticket
{
	private Route route;
	private double cost;
	private Date time;

	public Route getRoute()
	{
		return route;
	}

	public void setRoute(Route route)
	{
		this.route = route;
	}

	public double getCost()
	{
		return cost;
	}

	public void setCost(double cost)
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
}