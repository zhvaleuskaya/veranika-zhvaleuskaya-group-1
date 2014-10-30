package unic.mentoring.multithreading;

/**
 * @author aleh_lazouski
 */
public class Counter
{
	private int count;
	
	public int getCount()
	{
		return count;
	}
	
	public void inc()
	{
		synchronized (this)
		{
			++count;
		}
	}
}