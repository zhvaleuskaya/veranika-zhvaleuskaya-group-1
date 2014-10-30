/**
 * @author Unic
 * "hw01_classLoading" project, 2014.
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading;

import java.io.Serializable;
import java.util.HashMap;
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
}