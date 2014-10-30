/**
 * @author Unic
 * "hw01_classLoading" project, 2014.
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading;

public class BankException extends Exception
{
	private static final long serialVersionUID = 0;

	public BankException(String message)
	{
		super(message);
	}
}