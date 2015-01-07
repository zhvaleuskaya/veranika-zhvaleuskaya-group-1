/**
 * @author Unic
 * "hw01_classLoading" project, 2014.
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.memman;

public class Run
{
	public static void main(String[] args)
	{
		new Run();
	}
	
	public Run()
	{
		Object o1 = null, o2 = null, o3 = null;
		
		for (;;)
		{
			o3 = o2;
			o2 = o1;
			o1 = invoke( new Object() );
		}
	}
	
	protected Object invoke(Object o)
	{
		return o = new int[786432];
	}
}