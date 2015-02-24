/**
 * @author Unic
 * "hw16" project, Feb 19, 2015, 6:39:58 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns.ticketsystem.server;

import java.util.Date;

import unic.mentoring.softpatterns.data.Ticket;

public class TicketCriteria implements Criteria<Ticket>
{
	private String source;
	private String destination;
	private Date date;
	
	public TicketCriteria(){}

	public TicketCriteria(String source, String destination, Date date)
	{
		this.source = source;
		this.destination = destination;
		this.date = date;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean matches(Ticket ticket)
	{
		if (source != null && !ticket.getRoute().getSource().equals(source))
		{
			return false;
		}
		
		if (destination != null && !ticket.getRoute().getDestination().equals(destination))
		{
			return false;
		}
		
		if (date != null)
		{
			Date ticketDate = ticket.getTime();
			
			if (ticketDate.getYear() != date.getYear())
			{
				return false;
			}
			
			if (ticketDate.getMonth() != date.getMonth())
			{
				return false;
			}
			
			if (ticketDate.getDate() != date.getDate())
			{
				return false;
			}
		}
		
		return true;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public String getDestination()
	{
		return destination;
	}

	public void setDestination(String destination)
	{
		this.destination = destination;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}
}