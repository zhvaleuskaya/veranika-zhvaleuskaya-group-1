package unic.mentoring.gc;

/**
 * @author aleh_lazouski
 */
public class Run extends Thread
{
	public static void main(String[] args)
	{
		System.out.println("Hello, garbage collector!");
		new Run().start();
	}
	
	@Override
	public void run()
	{
		Object o;
		
		for (;;)
		{
			for (int i = 0; i < 10000; ++i)
			{
				o = new Object();
				o.toString();
				o = null;
			}
			
			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException e){}
		}
	}
}