/**
 * @author Unic
 * "hw01_classLoading" project, Nov 4, 2014, 12:15:44 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading.util;

import java.util.Comparator;

import unic.mentoring.multithreading.core.Currency;

public class CurrencyComparator implements Comparator<Currency>
{
	public static CurrencyComparator INSTANCE = new CurrencyComparator();
	
	@Override
	public int compare(Currency o1, Currency o2)
	{
		return o1.getCode().compareTo( o2.getCode() );
	}
}