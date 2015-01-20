/**
 * @author Unic
 * "hw12" project, Jan 18, 2015, 12:51:47 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.jms.client;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import unic.mentoring.jms.Constants;

public class TopicSubscriber implements MessageListener
{
	private String topicName;
	private Connection connection;
	private MessageProcessor processor;
	
	public TopicSubscriber(String topicName, MessageProcessor processor)
	{
		this.topicName = topicName;
		this.processor = processor;
	}
	
	public void subscribe() throws JMSException
	{
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connection = connectionFactory.createConnection(Constants.JMS_BROKER_URL, topicName);
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic(topicName);
		MessageConsumer consumer = session.createConsumer(topic);
		consumer.setMessageListener(this);
	}
	
	public void unsubscribe() throws JMSException
	{
		if (connection != null)
		{
			connection.close();
		}
	}

	@Override
	public void onMessage(Message message)
	{
		processor.processMessage(message, this);
	}
	
	@Override
	public String toString()
	{
		return topicName;
	}
	
	public String getTopicName()
	{
		return topicName;
	}
}