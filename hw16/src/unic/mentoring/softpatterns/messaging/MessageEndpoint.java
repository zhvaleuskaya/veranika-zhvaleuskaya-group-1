/**
 * @author Unic
 * "hw16" project, Feb 23, 2015, 6:15:45 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns.messaging;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class MessageEndpoint implements Runnable
{
	private static final Logger LOG = Logger.getLogger(MessageEndpoint.class);
	
	private int port;
	private List<MessageEndpointListener> listeners;
	private boolean alive = true;
	private ServerSocket serverSocket;
	
	public MessageEndpoint(int port)
	{
		listeners = new ArrayList<>(1);
	}
	
	public void addListener(MessageEndpointListener listener)
	{
		listeners.add(listener);
	}
	
	public void removeListener(MessageEndpointListener listener)
	{
		listeners.remove(listener);
	}

	@Override
	public void run()
	{
		try
		{
			serverSocket = new ServerSocket(port);
		}
		catch (IOException e)
		{
			LOG.error("Can't start server on message endpoint: " + e.getMessage());
			return;
		}
		
		while (alive)
		{
			Socket clientSocket;
			
			try
			{
				clientSocket = serverSocket.accept();
			}
			catch (IOException e)
			{
				LOG.info("Closing message endpoint: " + e.getMessage());
				return;
			}
			
			Object object = null;
			ObjectInputStream ois = null;
			
			try
			{
				ois = new ObjectInputStream( clientSocket.getInputStream() );
				object = ois.readObject();
				
				if (object instanceof Message)
				{
					onMessage( (Message)object );
				}
			}
			catch (ClassNotFoundException | IOException e){}
			finally
			{
				try
				{
					if (ois != null)
					{
						ois.close();
					}
				}
				catch (IOException e1){}
				
				try
				{
					clientSocket.close();
				}
				catch (IOException e){}
			}
		}
	}
	
	private void onMessage(Message message)
	{
		for (MessageEndpointListener listener : listeners)
		{
			listener.messageReceived(message);
		}
	}
	
	public void die()
	{
		alive = false;
		
		if (serverSocket != null)
		{
			try
			{
				serverSocket.close();
			}
			catch (IOException e){}
		}
	}
}