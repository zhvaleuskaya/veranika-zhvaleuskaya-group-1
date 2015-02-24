/**
 * @author Unic
 * "hw16" project, Feb 23, 2015, 5:56:40 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns.messaging.channel;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import unic.mentoring.softpatterns.messaging.Channel;
import unic.mentoring.softpatterns.messaging.Message;

public class TCPChannel implements Channel
{
	private String host;
	private int port;
	
	public TCPChannel(String host, int port)
	{
		this.host = host;
		this.port = port;
	}
	
	@Override
	public void send(Message message) throws IOException
	{
		Socket socket = new Socket(host, port);
		ObjectOutputStream oos = new ObjectOutputStream( socket.getOutputStream() );
		message.setTimeSent( System.currentTimeMillis() );
		oos.writeObject(message);
		oos.close();
		socket.close();
	}
}