/**
 * @author Unic
 * "hw01_classLoading" project, 2014.
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Account implements Serializable
{
	private static final long serialVersionUID = 0L;
	
	private String id;
	private String name;
	private Map<Currency, Double> amounts;
	
	public Account(String id, String name)
	{
		this.id = id;
		this.name = name;
		this.amounts = new HashMap<Currency, Double>();
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Double getAmount(Currency currency)
	{
		return amounts.get(currency);
	}
	
	public void setAmount(Currency currency, double amount)
	{
		amounts.put(currency, amount);
	}
	
	public void addCurrency(Currency currency, double initialAmount)
	{
		amounts.put(currency, initialAmount);
	}
	
	public void addCurrency(Currency currency)
	{
		amounts.put(currency, 0d);
	}
	
	public void removeCurrency(Currency currency)
	{
		amounts.remove(currency);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Account)
		{
			Account account = (Account)o;
			
			if (!id.equals(account.getId()))
				return false;
			if (!name.equals(account.getName()))
				return false;
			
			for (Currency currency : amounts.keySet())
			{
				Double value = account.getAmount(currency);
				
				if (value == null || !value.equals( amounts.get(currency) ))
					return false;
			}
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public String toString()
	{
		List<Currency> keys = new ArrayList<>();
		keys.addAll( amounts.keySet() );
		Collections.sort(keys, CurrencyComparator.INSTANCE);
		
		StringBuilder amountsHash = new StringBuilder();
		
		for (Currency currency : keys)
			amountsHash.append("|").append(currency.getCode()).append(":").append(amounts.get(currency));
		
		return (id + "|" + name + "@" + amountsHash.toString());
	}
	
	@Override
	public int hashCode()
	{
		return toString().hashCode();
	}
}