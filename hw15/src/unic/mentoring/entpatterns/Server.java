/**
 * @author Unic
 * "hw15" project, Feb 13, 2015, 6:14:37 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.entpatterns;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server
{
	boolean closed = false, inputFromAll = false;
	List<ClientThread> t;
	List<String> data;
	private static int port = 60006;

	Server()
	{
		t = new ArrayList<ClientThread>();
		data = new ArrayList<String>();
	}

	public static void main(String args[])
	{
		Socket clientSocket = null;
		ServerSocket serverSocket = null;

		Server ser = new Server();

		try
		{
			serverSocket = new ServerSocket(port);
		}
		catch (IOException e)
		{
			System.out.println(e);
		}

		while (!ser.closed)
		{
			try
			{
				clientSocket = serverSocket.accept();
				ClientThread th = new ClientThread(ser, clientSocket);
				ser.t.add(th);
				System.out.println("\nNow Total clients are: " + (ser.t).size());
				ser.data.add("NOT_SENT");
				th.start();
			}
			catch (IOException e){}
		}

		try
		{
			serverSocket.close();
		}
		catch (Exception e1){}
	}
}