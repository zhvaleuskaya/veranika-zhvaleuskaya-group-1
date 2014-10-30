/**
 * @author Unic
 * "hw01_classLoading" project, 2014.
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading;

public abstract class Client implements Runnable
{
	protected Bank bank;
	
	public Client(Bank bank)
	{
		this.bank = bank;
	}
}