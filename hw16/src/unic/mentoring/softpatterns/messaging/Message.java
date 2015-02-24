/**
 * @author Unic
 * "hw16" project, Feb 23, 2015, 5:53:56 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns.messaging;

public class Message
{
	private long timeSent;
	private long timeReceived;
	private Object data;
	
	public Message(Object data)
	{
		this.data = data;
	}

	public long getTimeSent()
	{
		return timeSent;
	}

	public void setTimeSent(long timeSent)
	{
		this.timeSent = timeSent;
	}

	public long getTimeReceived()
	{
		return timeReceived;
	}

	public void setTimeReceived(long timeReceived)
	{
		this.timeReceived = timeReceived;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}
}