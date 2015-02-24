/**
 * @author Unic
 * "hw16" project, Feb 19, 2015, 6:44:24 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns.ticketsystem.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import unic.mentoring.softpatterns.C;

/** Pattern: Layers Architecture
 * Layer 1: ConsoleTicketClient
 * Layer 2: TicketServer
 * Layer 3: TicketManager
 * Layer 4: Service
 */
public class ConsoleTicketClient
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		String cmd = null;
		
		while (true)
		{
			System.out.print("> ");
			cmd = br.readLine();
			
			if ("x".equals(cmd))
			{
				break;
			}
			
			if (cmd.startsWith("q "))
			{
				String query = cmd.substring(2);
				
				if (query.length() > 0)
				{
					try
					{
						query(query);
					}
					catch (IOException e)
					{
						System.out.println("Can't perform query: " + e.getMessage());
					}
				}
				else
				{
					System.out.println("Query is empty.");
				}
				
				continue;
			}
			
			System.out.println("Unknown command.");
		}
		
		System.out.println("Adiaŭ!");
	}
	
	public static String query(String request) throws UnknownHostException, IOException
	{
		Socket socket = new Socket(C.Remote.TICKET_SYSTEM_HOST, C.Remote.TICKET_SYSTEM_PORT);
		socket.getOutputStream().write( (request + "\\n").getBytes() );
		BufferedReader br = new BufferedReader( new InputStreamReader(socket.getInputStream()) );
		String response = br.readLine();
		socket.close();
		
		return response;
	}
}