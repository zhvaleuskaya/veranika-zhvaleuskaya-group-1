/**
 * @author Unic
 * "hw01_classLoading" project, 2014.
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading;

import java.util.HashMap;
import java.util.Map;

public class Bank
{
	private ServiceImpl service;
	private Map<String, Currency> currencies;
	private Map<String, Conversion> conversions;
	
	public Bank()
	{
		this.service = new ServiceImpl();
		this.currencies = new HashMap<String, Currency>();
		this.conversions = new HashMap<String, Conversion>();
	}
	
	public void addCurrency(String code, double valuette) throws BankException
	{
		if (currencies.containsKey(code))
			throw new BankException("Currency already exists: " + code);
		
		Currency currency = new Currency(code, valuette);
		currencies.put(code, currency);
	}
	
	public Currency getCurrency(String code) throws BankException
	{
		Currency currency = currencies.get(code);
		
		if (currency == null)
			throw new BankException("No such currency: " + code);
		
		return currency;
	}
	
	public void addConversion(String source, String destination, double rate) throws BankException
	{
		Currency sourceCurrency = getCurrency(source);
		Currency destinationCurrency = getCurrency(destination);
		
		addConversion(sourceCurrency, destinationCurrency, rate);
	}
	
	protected void addConversion(Currency source, Currency destination, double rate)
	{
		String code = Util.makeConversionCode(source, destination);
		Conversion conversion = conversions.get(code);
		
		if (conversion == null)
			conversions.put(code, new Conversion(source, destination, rate));
	}
	
	public Conversion getConversion(String source, String destination) throws BankException
	{
		Currency sourceCurrency = getCurrency(source);
		Currency destinationCurrency = getCurrency(destination);
		
		return getConversion(sourceCurrency, destinationCurrency);
	}
	
	protected Conversion getConversion(Currency source, Currency destination)
	{
		return conversions.get( Util.makeConversionCode(source, destination) );
	}
	
	public String addAccount(String name) throws BankException
	{
		try
		{
			return service.createAccount(name);
		}
		catch (ServiceException e)
		{
			throw new BankException("Can't create account: " + e.getMessage());
		}
	}
	
	protected Account getAccount(String id) throws BankException
	{
		try
		{
			return service.getAccount(id);
		}
		catch (ServiceException e)
		{
			throw new BankException("Can't retrieve account with id \"" + id + "\": " + e.getMessage());
		}
	}
	
	protected void saveAccount(Account account) throws BankException
	{
		try
		{
			service.saveAccount(account);
		}
		catch (ServiceException e)
		{
			throw new BankException("Can't save account: " + e.getMessage());
		}
	}
	
	public void addAccountCurrency(String accountId, String currencyCode) throws BankException
	{
		addAccountCurrency(accountId, currencyCode, 0);
	}
	
	public void addAccountCurrency(String accountId, String currencyCode, double initialAmount) throws BankException
	{
		Currency currency = getCurrency(currencyCode);
		Account account = getAccount(accountId);
		account.addCurrency(currency, initialAmount);
		saveAccount(account);
	}
	
	public void convert(String accountId, String sourceCurrencyCode, String destinationCurrencyCode, double amount) throws BankException
	{
		Currency sourceCurrency = getCurrency(sourceCurrencyCode);
		Currency destinationCurrency = getCurrency(destinationCurrencyCode);
		
		Conversion conversion = conversions.get( Util.makeConversionCode(sourceCurrency, destinationCurrency) );
		
		if (conversion == null)
			throw new BankException("Have no conversion from " + sourceCurrency.getCode() + " to " + destinationCurrency.getCode());
		
		try
		{
			service.convert(accountId, conversion, amount);
		}
		catch (ServiceException e)
		{
			throw new BankException("Can't convert currencies: " + e.getMessage());
		}
	}
	
	public void saveAll() throws BankException
	{
		try
		{
			service.saveAll();
		}
		catch (ServiceException e)
		{
			throw new BankException("Can't save all data: " + e.getMessage());
		}
	}
	
	public void loadAll() throws BankException
	{
		try
		{
			service.loadAll();
		}
		catch (ServiceException e)
		{
			throw new BankException("Can't load all data: " + e.getMessage());
		}
	}
}