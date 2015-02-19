/**
 * @author Unic
 * "hw16" project, Feb 19, 2015, 7:58:10 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/** Pattern: Open Host Service
 * Listens for queries in specific format and plays role of network service.
 */
public class TicketServer
{
	private TicketManager manager;
	private boolean alive = true;
	
	public TicketServer(TicketManager manager)
	{
		this.manager = manager;
	}
	
	public void listen() throws IOException
	{
		ServerSocket serverSocket = new ServerSocket(C.Remote.PORT);
		
		while (alive)
		{
			Socket clientSocket = serverSocket.accept();
			BufferedReader br = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()) );
			String request = br.readLine();
			String response = manager.processQuery(request);
			clientSocket.getOutputStream().write(response.getBytes(), 0, response.length());
			clientSocket.close();
		}
		
		serverSocket.close();
	}
	
	public void die()
	{
		alive = false;
	}
	
	public static void main(String args[]) throws IOException
	{
		Service service = new ServiceImpl();
		TicketManager manager = new TicketManager(service);
		TicketServer server = new TicketServer(manager);
		server.listen();
	}
}