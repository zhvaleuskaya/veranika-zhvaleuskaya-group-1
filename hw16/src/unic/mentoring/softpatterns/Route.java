/**
 * @author Unic
 * "hw16" project, Feb 18, 2015, 7:21:43 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns;

public class Route
{
	private String source;
	private String destination;
	
	public Route(String source, String destination)
	{
		this.source = source;
		this.destination = destination;
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
}