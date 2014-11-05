/**
 * @author Unic
 * "hw01_classLoading" project, 2014.
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import unic.mentoring.multithreading.core.Account;
import unic.mentoring.multithreading.core.Conversion;
import unic.mentoring.multithreading.core.Currency;
import unic.mentoring.multithreading.dao.Dao;
import unic.mentoring.multithreading.dao.impl.DaoImpl;
import unic.mentoring.multithreading.exception.DaoException;
import unic.mentoring.multithreading.exception.ServiceException;
import unic.mentoring.multithreading.service.Service;
import unic.mentoring.multithreading.util.LockableObject;
import unic.mentoring.multithreading.util.Util;

public class ServiceImpl implements Service
{
	private static final Logger LOG = Logger.getLogger(ServiceImpl.class);
	
	protected final String DIR_DATA = "data/";
	protected final String DIR_ACCOUNTS = "accounts/";
	protected final String FEXT_JAVA_SERIALIZED = ".jsrl";
	protected long newAccountId = 0;
	protected Dao dao;
	
	private Map<String, LockableObject<Account>> accountsCache;
	
	public ServiceImpl()
	{
		this( new DaoImpl() );
	}
	
	public ServiceImpl(Dao dao)
	{
		this.accountsCache = new ConcurrentHashMap<>();
		this.dao = dao;
	}
	
	public String createAccount(String name) throws ServiceException
	{
		Account account = new Account("" + newAccountId++, name);
		saveAccount(account);
		
		LOG.debug("Account created: id=\"" + account.getId() + "\", name=\"" + account.getName() + "\"");
		
		return account.getId();
	}
	
	public void saveAccount(Account account) throws ServiceException
	{
		try
		{
			writeAccount(account);
		}
		catch (IOException e)
		{
			throw new ServiceException("Can't save account: " + e.getMessage());
		}
	}
	
	protected void writeAccount(Account account) throws IOException
	{
		String filePath = DIR_DATA + DIR_ACCOUNTS + account.getId() + FEXT_JAVA_SERIALIZED;
		File file = new File(filePath);
		
		if (!file.exists())
		{
			Files.createFile( Paths.get(filePath) );
		}
		
		ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( new File(filePath) ) );
		oos.writeObject(account);
		oos.close();
		
		LockableObject<Account> loAccount = accountsCache.get( account.getId() );
		
		if (loAccount == null)
		{
			accountsCache.put( account.getId(), new LockableObject<Account>(account) );
		}
		else
		{
			synchronized (loAccount.getLock())
			{
				loAccount.setObject(account);
			}
		}
	}
	
	public Account getAccount(String id) throws ServiceException
	{
		LockableObject<Account> loAccount = accountsCache.get(id);
		Account account = loAccount == null ? null : loAccount.getObject();
		
		if (account == null)
		{
			try
			{
				return readAccount(id);
			}
			catch (ClassNotFoundException | ServiceException | IOException e)
			{
				throw new ServiceException("Can't read account: " + e.getMessage());
			}
		}
		
		return account;
	}
	
	protected Account readAccount(String id) throws ServiceException, ClassNotFoundException, IOException
	{
		File file = new File(DIR_DATA + DIR_ACCOUNTS + id + FEXT_JAVA_SERIALIZED);
		Object o;
		
		try
		{
			o = dao.readItem(file);
		}
		catch (DaoException e)
		{
			throw new ServiceException("Can't read account: " + e.getMessage());
		}
		
		if (!(o instanceof Account))
		{
			throw new ServiceException("Object isn't of class " + Account.class.getName());
		}
		
		Account account = (Account)o;
		LockableObject<Account> loAccount = accountsCache.get(id);
		
		if (loAccount == null)
		{
			accountsCache.put( account.getId(), new LockableObject<Account>(account) );
		}
		else
		{
			synchronized (loAccount.getLock())
			{
				loAccount.setObject(account);
			}
		}
		
		return account;
	}
	
	public List<Account> findAccountsByName(String query)
	{
		List<Account> accounts = new ArrayList<>();
		
		for (LockableObject<Account> loAccount : accountsCache.values())
		{
			synchronized (loAccount.getLock())
			{
				if (loAccount.getObject().getName().contains(query))
					accounts.add( loAccount.getObject() );
			}
		}
		
		return accounts;
	}
	
	public void saveAll() throws ServiceException
	{
		for (LockableObject<Account> loAccount : accountsCache.values())
		{
			synchronized (loAccount.getLock())
			{
				saveAccount( loAccount.getObject() );
			}
		}
	}
	
	public void loadAll() throws ServiceException
	{
		File fileDir = new File(DIR_DATA + DIR_ACCOUNTS);
		
		if (fileDir.exists() && fileDir.isDirectory())
		{
			String[] filenames = fileDir.list();
			
			for (String filename : filenames)
			{
				getAccount( Util.getFileName(filename) );
			}
		}
	}
	
	public void convert(String accountId, Conversion conversion, double amount) throws ServiceException
	{
		LockableObject<Account> loAccount = accountsCache.get(accountId);
		
		synchronized (loAccount.getLock())
		{
			Account account = loAccount.getObject();
			Currency sourceCurrency = conversion.getSource();
			Currency destinationCurrency = conversion.getDestination();
			Double sourceCurrencyAmount = account.getAmount(sourceCurrency);
			Double destinationCurrencyAmount = account.getAmount(destinationCurrency);
			
			if (sourceCurrencyAmount == null)
			{
				throw new ServiceException("Account \"" + accountId + "\" has no source currency \"" + sourceCurrency.getCode());
			}
			
			if (destinationCurrencyAmount == null)
			{
				throw new ServiceException("Account \"" + accountId + "\" has no destination currency \"" + destinationCurrencyAmount);
			}
			
			if (sourceCurrencyAmount < amount)
			{
				throw new ServiceException("Insufficient source amount: " + sourceCurrencyAmount + "/" + amount);
			}
			
			if (amount < sourceCurrency.getValuette())
			{
				throw new ServiceException("Too small amount to convert (smaller than source currency valuette): " + amount + "/" + sourceCurrency.getValuette());
			}
			
			double convertedAmount = Util.convertCurrency(amount, conversion.getRate(), destinationCurrency.getValuette());
			
			if (convertedAmount < destinationCurrency.getValuette())
			{
				throw new ServiceException("Too small amount to convert (conversion result less that destination currency valuette): " + convertedAmount + "/" + destinationCurrency.getValuette());
			}
			
			account.setAmount(sourceCurrency, sourceCurrencyAmount - amount);
			account.setAmount(destinationCurrency, destinationCurrencyAmount + convertedAmount);
			
			saveAccount(account);
			
			LOG.info("Conversion performed for account \"" + accountId + "\" with conversion \"" + conversion.toString() + "\": " + amount + "->" + convertedAmount);
		}
	}
}