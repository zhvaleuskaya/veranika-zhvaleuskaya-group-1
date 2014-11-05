/**
 * @author Unic
 * "hw01_classLoading" project, Nov 3, 2014, 10:21:05 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import unic.mentoring.multithreading.core.Account;
import unic.mentoring.multithreading.core.Conversion;
import unic.mentoring.multithreading.core.Currency;
import unic.mentoring.multithreading.dao.Dao;
import unic.mentoring.multithreading.exception.ServiceException;
import unic.mentoring.multithreading.service.impl.ServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest
{
	@Mock
	private Dao dao;
	
	private Conversion conversionUsd2Eur;
	private Currency currencyUsd;
	private Currency currencyEur;
	private double delta;
	
	@Before
	public void before()
	{
		dao = Mockito.mock(Dao.class);
		
		currencyUsd = new Currency("usd", 0.01);
		currencyEur = new Currency("eur", 0.01);
		conversionUsd2Eur = new Conversion(currencyUsd, currencyEur, 0.75);
		delta = 0.0001;
	}
	
	@Test
	public void createAndGetAccount() throws ServiceException
	{
		Service service = new ServiceImpl(dao);
		
		String accountId1 = service.createAccount("theName1");
		String accountId2 = service.createAccount("theName2");
		Assert.assertEquals("0", accountId1);
		Assert.assertEquals("1", accountId2);
		
		Account account1 = service.getAccount(accountId1);
		Assert.assertNotNull(account1);
		Assert.assertEquals("0", account1.getId());
		Assert.assertEquals("theName1", account1.getName());
		
		Account account2 = service.getAccount(accountId2);
		Assert.assertNotNull(account2);
		Assert.assertEquals("1", account2.getId());
		Assert.assertEquals("theName2", account2.getName());
	}
	
	@Test
	public void saveAndGetAccount() throws ServiceException
	{
		Account account = new Account("theId", "theName");
		Service service = new ServiceImpl(dao);
		
		service.saveAccount(account);
		Account accountActual = service.getAccount("theId");
		Assert.assertEquals(account, accountActual);
	}
	
	@Test
	public void findAccountsByName() throws ServiceException
	{
		Account account1 = new Account("0", "fertility");
		Account account2 = new Account("1", "offering");
		Account account3 = new Account("2", "proficiency");
		
		Service service = new ServiceImpl(dao);
		service.createAccount( account1.getName() );
		service.createAccount( account2.getName() );
		service.createAccount( account3.getName() );
		List<Account> accounts = null;
		
		accounts = service.findAccountsByName("fer");
		Assert.assertEquals( 2, accounts.size() );
		Assert.assertTrue( accounts.contains(account1) );
		Assert.assertTrue( accounts.contains(account2) );
		
		accounts = service.findAccountsByName("of");
		Assert.assertEquals( 2, accounts.size() );
		Assert.assertTrue( accounts.contains(account2) );
		Assert.assertTrue( accounts.contains(account3) );
		
		accounts = service.findAccountsByName("i");
		Assert.assertEquals( 3, accounts.size() );
		Assert.assertTrue( accounts.contains(account1) );
		Assert.assertTrue( accounts.contains(account2) );
		Assert.assertTrue( accounts.contains(account3) );
		
		accounts = service.findAccountsByName("ency");
		Assert.assertEquals( 1, accounts.size() );
		Assert.assertTrue( accounts.contains(account3) );
		
		accounts = service.findAccountsByName("abrahadabra");
		Assert.assertEquals( 0, accounts.size() );
	}
	
	@Test
	public void convertSuccess() throws ServiceException
	{
		Service service = new ServiceImpl(dao);
		service.createAccount("theName");
		
		Account account = service.getAccount("0");
		account.addCurrency(currencyUsd, 10);
		account.addCurrency(currencyEur, 5);
		service.saveAccount(account);
		
		service.convert("0", conversionUsd2Eur, 10);
		account = service.getAccount("0");
		Assert.assertEquals(12.5, account.getAmount(currencyEur), delta);
	}
	
	@Test(expected = ServiceException.class)
	public void convertFail() throws ServiceException
	{
		Service service = new ServiceImpl(dao);
		service.createAccount("theName");
		
		Account account = service.getAccount("0");
		account.addCurrency(currencyUsd, 10);
		account.addCurrency(currencyEur, 5);
		service.saveAccount(account);
		
		service.convert("0", conversionUsd2Eur, 20);
	}
}