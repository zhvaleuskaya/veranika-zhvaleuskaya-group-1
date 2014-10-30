/**
 * @author Unic
 * "hw01_classLoading" project, 2014.
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading;


public class Run
{
	protected static final String PARAM_GENERATE_SAMPLE = "-makeSamples";
	
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
			
			Counter counter = new Counter();
			Thread[][] threads = new Thread[3][];
			threads[0] = new Thread[2];
			// 2 threads * 8 conversions = 16 conversions
			for (int i = 0; i < 2; ++i)
				threads[0][i] = new Thread( new UserClientBot(bank, "0", 8, counter) );
			threads[1] = new Thread[4];
			// 4 threads * 16 conversions = 64 conversions
			for (int i = 0; i < 4; ++i)
				threads[1][i] = new Thread( new UserClientBot(bank, "1", 16, counter) );
			threads[2] = new Thread[8];
			// 8 threads * 32 conversions = 256 conversions
			for (int i = 0; i < 8; ++i)
				threads[2][i] = new Thread( new UserClientBot(bank, "2", 32, counter) );
			
			for (int t = 0; t < 3; ++t)
			{
				for (int i = 0; i < threads[t].length; ++i)
				{
					threads[t][i].start();
					
					// It can be used to execute conversions one by one
					//threads[t][i].join();
				}
			}
			
			// Total conversions: 256 + 64 + 16 = 336
			while (counter.getCount() < 336)
			{
				Thread.sleep(10);
			}
			
			Currency currencyEur = bank.getCurrency("eur");
			Currency currencyUsd = bank.getCurrency("usd");
			String[] accountIds = {"0", "1", "2"};
			for (String accountId : accountIds)
			{
				Account account = bank.getAccount(accountId);
				System.out.println( account.getId() + " " + account.getName() + " " + account.getAmount(currencyEur) + " " + account.getAmount(currencyUsd) );
			}
		}
		catch (BankException | InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}