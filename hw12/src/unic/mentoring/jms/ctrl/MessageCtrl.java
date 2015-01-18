/**
 * @author Unic
 * "hw12" project, Jan 17, 2015, 8:17:36 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.jms.ctrl;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import unic.mentoring.jms.Constants;

@Controller
public class MessageCtrl
{
	private static final String ATTR_MESSAGE = "message";
	
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public String message(Model model, @RequestParam("message") String message, @RequestParam("topic") String topic)
	{
		if (!StringUtils.isEmpty(message))
		{
			try
			{
				sendMessage(message, topic);
				model.addAttribute(ATTR_MESSAGE, "Message (length: " + message.length() + ") has been sent into topic \"" + topic + "\".");
			}
			catch (JMSException e)
			{
				model.addAttribute(ATTR_MESSAGE, "Can't send message: " + e.getMessage());
			}
		}
		else
		{
			model.addAttribute(ATTR_MESSAGE, "No message has been specified to send.");
		}
		
		return Constants.Page.INDEX;
	}
	
	protected void sendMessage(String message, String topicName) throws JMSException
	{
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		Connection connection = connectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic(topicName);
		MessageProducer producer = session.createProducer(topic);
		TextMessage jmsMessage = session.createTextMessage();
		jmsMessage.setText(message);
		
		producer.send(jmsMessage);
		connection.close();
	}
}