/**
 * @author Unic
 * "hw01_classLoading" project, Nov 4, 2014, 12:56:00 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import unic.mentoring.multithreading.core.Account;
import unic.mentoring.multithreading.core.Bank;
import unic.mentoring.multithreading.core.Currency;
import unic.mentoring.multithreading.dao.Dao;
import unic.mentoring.multithreading.exception.BankException;
import unic.mentoring.multithreading.service.Service;
import unic.mentoring.multithreading.service.impl.ServiceImpl;
import unic.mentoring.multithreading.util.Counter;

@RunWith(MockitoJUnitRunner.class)
public class ClientsTest
{
	private static final Logger LOG = Logger.getLogger(ClientsTest.class);
	private static final int THREADS_COUNT = 2;
	private static final int CONVESRIONS_COUNT = 10;
	
	private Bank bank;
	private Currency currencyUsd;
	private Currency currencyEur;
	private String accountId;
	private double delta = 0.0001;
	
	@Mock
	private Dao dao;
	
	@Before
	public void before() throws BankException
	{
		Service service = new ServiceImpl(dao);
		bank = new Bank(service);
		bank.addCurrency("eur", 0.01);
		bank.addCurrency("usd", 0.01);
		bank.addConversion("eur", "usd", 1.27363);
		bank.addConversion("usd", "eur", 0.78515);
		accountId = bank.addAccount("The torturing account");
		bank.addAccountCurrency(accountId, "eur", 100);
		bank.addAccountCurrency(accountId, "usd", 144);
		currencyUsd = bank.getCurrency("usd");
		currencyEur = bank.getCurrency("eur");
	}
	
	@Test
	public void testParallerConversions() throws BankException, InterruptedException, ExecutionException
	{
		Counter counter = new Counter();
		List<UserClientBot> threads = new ArrayList<>(THREADS_COUNT);
		
		for (int i = 0; i < THREADS_COUNT; ++i)
		{
			threads.add( new UserClientBot(bank, accountId, CONVESRIONS_COUNT, counter) );
		}
		
		ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNT);
		List<Future<Long>> results = executorService.invokeAll(threads);
		
		StringBuilder info = new StringBuilder("Execution results:");
		for (Future<Long> future : results)
		{
			info.append(" ").append( future.get() );
		}
		LOG.info( info.toString() );
		
		Account account = bank.getAccount(accountId);
		LOG.info("Conversion result for \"" + account.getId() + "\": " + currencyEur.getCode() + ":" + account.getAmount(currencyEur) + ", " +
				currencyUsd.getCode() + ":" + account.getAmount(currencyUsd) );
		
		Assert.assertEquals(115.8, account.getAmount(currencyEur), delta);
		Assert.assertEquals(124.0, account.getAmount(currencyUsd), delta);
	}
}