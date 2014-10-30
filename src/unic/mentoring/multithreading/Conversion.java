/**
 * @author Unic
 * "hw01_classLoading" project, 2014.
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading;

import java.io.Serializable;

public class Conversion implements Serializable
{
	private static final long serialVersionUID = 0L;
	
	private Currency source;
	private Currency destination;
	private double rate;
	
	public Conversion(Currency source, Currency destination, double rate)
	{
		this.source = source;
		this.destination = destination;
		this.rate = rate;
	}
	
	public Currency getSource()
	{
		return source;
	}
	
	public Currency getDestination()
	{
		return destination;
	}
	
	public double getRate()
	{
		return rate;
	}
}