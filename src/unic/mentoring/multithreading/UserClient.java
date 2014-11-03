/**
 * @author Unic
 * "hw01_classLoading" project, Nov 3, 2014, 12:27:03 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserClient extends Client
{
	private static final String SYSNAME = "system";
	
	private Account account;
	
	public UserClient(Bank bank)
	{
		super(bank);
	}

	@Override
	public void run()
	{
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		
		for (;;)
		{
			try
			{
				System.out.print("system% ");
				String s = br.readLine();
				
				if ("exit".equals(s))
					break;
				
				if ("login".equals(s))
				{
					if (account != null)
					{
						System.out.println(SYSNAME + "% Logout first.");
					}
					else
					{
						System.out.print("Login name: ");
						s = br.readLine();
						
						try
						{
							account = bank.getAccount(s);
							System.out.println("Loggen in.");
						}
						catch (BankException e)
						{
							System.out.println("No such account: \"" + s + "\".");
						}
					}
				}
				
				if ("logout".equals(s))
				{
					if (account == null)
					{
						System.out.println("Login first.");
					}
					else
					{
						account = null;
						System.out.println("Logged out.");
					}
				}
				
				if ("register".equals(s))
				{
					System.out.println("Login: ");
					s = br.readLine();
					
					try
					{
						bank.getAccount(s);
						System.out.println("Account with such login \"" + s + "\" already exists.");
					}
					catch (BankException e)
					{
						//XXX Distinct account inexistence and another error
					}
				}
			}
			catch (IOException e){}
		}
		
		System.out.println("Bye.");
	}
}