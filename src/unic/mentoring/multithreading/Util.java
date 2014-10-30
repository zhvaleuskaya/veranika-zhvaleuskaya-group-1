/**
 * @author Unic
 * "hw01_classLoading" project, 2014.
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading;

public class Util
{
	public static String makeConversionCode(Currency source, Currency destination)
	{
		return source.getCode() + "->" + destination.getCode();
	}
	
	public static double round(double value, double valuette)
	{
		return ((int)(value / valuette)) * valuette;
	}
	
	public static double convertCurrency(double amount, double rate, double valuette)
	{
		return round(amount * rate, valuette);
	}
	
	public static String getFileExtension(String filename)
	{
		int p = filename.lastIndexOf('.');
		return p == -1 ? "" : filename.substring(p + 1);
	}
	
	public static String getFileName(String filename)
	{
		int p = filename.indexOf('.');
		return p == -1 ? filename : filename.substring(0, p);
	}
}