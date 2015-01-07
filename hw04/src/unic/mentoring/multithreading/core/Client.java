/**
 * @author Unic
 * "hw01_classLoading" project, 2014.
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading.core;

import java.util.concurrent.Callable;

public abstract class Client implements Callable<Long>
{
	protected Bank bank;
	
	public Client(Bank bank)
	{
		this.bank = bank;
	}
}