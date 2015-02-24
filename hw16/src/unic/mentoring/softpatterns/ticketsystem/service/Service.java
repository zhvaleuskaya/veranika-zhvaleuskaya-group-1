/**
 * @author Unic
 * "hw16" project, Feb 19, 2015, 6:35:05 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns.ticketsystem.service;

import java.util.List;

import unic.mentoring.softpatterns.data.Ticket;
import unic.mentoring.softpatterns.ticketsystem.server.Criteria;

public interface Service
{
	List<Ticket> findTickets(Criteria<Ticket> criteria);
}