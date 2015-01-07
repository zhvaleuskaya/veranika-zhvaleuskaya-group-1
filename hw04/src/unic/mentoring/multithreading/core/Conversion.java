/**
 * @author Unic
 * "hw01_classLoading" project, 2014.
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading.core;

import java.io.Serializable;

import unic.mentoring.multithreading.util.Util;

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
	
	@Override
	public String toString()
	{
		return Util.makeConversionCode(source, destination) + ":" + rate;
	}
}