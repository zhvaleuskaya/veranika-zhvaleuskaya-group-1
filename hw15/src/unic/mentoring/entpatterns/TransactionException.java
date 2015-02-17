/**
 * @author Unic
 * "hw15" project, Feb 17, 2015, 8:06:35 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.entpatterns;

public class TransactionException extends Exception
{
	private static final long serialVersionUID = 0;
	
	public TransactionException(String message)
	{
		super(message);
	}
}