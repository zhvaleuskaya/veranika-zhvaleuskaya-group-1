/**
 * @author Unic
 * "hw01_classLoading" project, Oct 28, 2014, 1:47:38 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading.exception;

public class ServiceException	extends Exception
{
	private static final long serialVersionUID = 0L;

	public ServiceException(String message)
	{
		super(message);
	}
}