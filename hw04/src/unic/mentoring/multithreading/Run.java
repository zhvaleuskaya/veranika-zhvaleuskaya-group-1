/**
 * @author Unic
 * "hw01_classLoading" project, 2014.
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading;

import unic.mentoring.multithreading.client.UserClient;
import unic.mentoring.multithreading.core.Bank;
import unic.mentoring.multithreading.exception.BankException;

@Deprecated
public class Run
{
	public static void main(String[] args)
	{
		try
		{
			String id = null;
			Bank bank = new Bank();
			bank.addCurrency("eur", 0.01);
			bank.addCurrency("usd", 0.01);
			bank.addCurrency("jpy", 1);
			bank.addConversion("eur", "usd", 1.27363);
			bank.addConversion("usd", "eur", 0.78515);
			bank.addConversion("eur", "jpy", 137.683);
			bank.addConversion("jpy", "eur", 0.007263061);
			bank.addConversion("usd", "jpy", 108.102);
			bank.addConversion("jpy", "usd", 0.009250523);
			id = bank.addAccount("One");
			bank.addAccountCurrency(id, "eur", 100);
			bank.addAccountCurrency(id, "usd", 144);
			bank.addAccountCurrency(id, "jpy", 800);
			id = bank.addAccount("Two");
			bank.addAccountCurrency(id, "eur", 100);
			bank.addAccountCurrency(id, "usd", 1088);
			id = bank.addAccount("Three");
			bank.addAccountCurrency(id, "eur", 100);
			bank.addAccountCurrency(id, "usd", 8448);
			bank.saveAll();
			bank.loadAll();
			
			new UserClient(bank);
		}
		catch (BankException e)
		{
			e.printStackTrace();
		}
	}
}