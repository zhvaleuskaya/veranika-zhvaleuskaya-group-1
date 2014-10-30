/**
 * @author Unic
 * "hw01_classLoading" project, 2014.
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading;

import java.io.Serializable;

public class Currency implements Serializable
{
	private static final long serialVersionUID = 0L;
	
	private String code;
	private double valuette;

	public Currency(String code, double valuette)
	{
		this.code = code;
		this.valuette = valuette;
	}
	
	public String getCode()
	{
		return code;
	}

	public double getValuette()
	{
		return valuette;
	}
}