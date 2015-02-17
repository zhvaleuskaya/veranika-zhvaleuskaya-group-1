/**
 * @author Unic
 * "hw15" project, Feb 17, 2015, 7:39:18 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.entpatterns;

public class Query
{
	private String query;
	
	public Query(String query)
	{
		this.query = query;
	}
	
	public String getQuery()
	{
		return query;
	}
	
	public void setQuery(String query)
	{
		this.query = query;
	}
}