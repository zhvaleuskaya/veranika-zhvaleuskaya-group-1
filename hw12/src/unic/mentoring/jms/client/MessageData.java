/**
 * @author Unic
 * "hw12" project, Jan 18, 2015, 2:20:15 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.jms.client;

public class MessageData
{
	private static final int TOPIC_LENGTH_LIMIT = 40;
	private static final int MESSAGE_LENGTH_LIMIT = 60;
	private static final String ENDING = "...";
	
	private long id;
	private String message;
	private String topic;

	public MessageData(long id, String message, String topic)
	{
		this.id = id;
		this.message = message;
		this.topic = topic;
	}

	public long getId()
	{
		return id;
	}

	public String getMessage()
	{
		return message;
	}

	public String getTopic()
	{
		return topic;
	}
	
	@Override
	public String toString()
	{
		String messagePart = message.length() > TOPIC_LENGTH_LIMIT ? message.substring(0, TOPIC_LENGTH_LIMIT - ENDING.length()) + ENDING : message;
		String topicPart = topic.length() > MESSAGE_LENGTH_LIMIT ? topic.substring(0, MESSAGE_LENGTH_LIMIT - ENDING.length()) + ENDING : topic;
		
		return "[" + id + "][" + topicPart + "] " + messagePart;
	}
}