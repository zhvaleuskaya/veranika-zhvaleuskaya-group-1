/**
 * @author Unic
 * "hw15" project, Feb 13, 2015, 6:16:11 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.entpatterns;

import java.io.*;
import java.net.*;

public class Client implements Runnable
{
	private static Socket clientSocket;
	private static PrintStream os;
	private static DataInputStream is;
	private static BufferedReader reader;
	private static boolean closed = false;

	public static void main(String[] args)
	{
		try
		{
			clientSocket = new Socket(C.HOST, C.PORT);
			reader = new BufferedReader( new InputStreamReader(System.in) );
			os = new PrintStream( clientSocket.getOutputStream() );
			is = new DataInputStream( clientSocket.getInputStream() );
		}
		catch (Exception e)
		{
			System.out.println("Exception: " + e.getMessage());
		}

		if (clientSocket != null && os != null && is != null)
		{
			try
			{
				new Thread( new Client() ).start();

				while (!closed)
				{
					os.println( reader.readLine() );
				}

				os.close();
				is.close();
				clientSocket.close();
			}
			catch (IOException e)
			{
				System.err.println("IOException: " + e);
			}
		}
	}

	public void run()
	{
		String responseLine;
		
		try
		{
			while ( (responseLine = is.readLine()) != null )
			{
				System.out.println("\n" + responseLine);
				
				if ( C.GLOBAL_COMMIT.equalsIgnoreCase(responseLine) || C.GLOBAL_ABORT.equalsIgnoreCase(responseLine) )
				{
					break;
				}
			}
			
			closed = true;
		}
		catch (IOException e)
		{
			System.err.println("IOException: " + e);
		}
	}
}