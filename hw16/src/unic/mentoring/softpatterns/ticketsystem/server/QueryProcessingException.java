/**
 * @author Unic
 * "hw16" project, Feb 19, 2015, 7:43:39 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns.ticketsystem.server;

public class QueryProcessingException extends Exception
{
	private static final long serialVersionUID = 0;
	
	public QueryProcessingException(String message)
	{
		super(message);
	}
}