/**
 * @author Unic
 * "hw12" project, Jan 18, 2015, 12:13:05 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.jms.client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JmsClient extends JFrame implements ActionListener, MessageProcessor, ListSelectionListener
{
	private static final long serialVersionUID = 0;
	private static final int CTRL_HEIGHT = 24;
	private static final int CTRL_WIDTH = 128;
	private static final int CTRL_INTEROFFSET = 4;
	
	private long messageId = 0;
	private JPanel pMain;
	private JTextField tfTopic;
	private JButton bSubscribe, bUnsubscribe, bRemoveMessage;
	private JList<MessageData> listMessages;
	private DefaultListModel<MessageData> listMessagesModel;
	private JList<TopicSubscriber> listSubscribers;
	private DefaultListModel<TopicSubscriber> listSubscribersModel;
	private JTextArea taMessage, taConsole;
	
	private Map<String, TopicSubscriber> subscribers;
	
	public JmsClient() throws JMSException
	{
		listMessagesModel = new DefaultListModel<MessageData>();
		listSubscribersModel = new DefaultListModel<TopicSubscriber>();
		subscribers = new HashMap<>();
		
		// Create GUI
		
		setTitle("JMS Client");
		setBounds(64, 64, 640, 580);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		pMain = new JPanel();
		pMain.setBounds(4, 4, getWidth() - 4, getHeight() - 4);
		pMain.setLayout(null);
		add(pMain);
		
		tfTopic = new JTextField();
		tfTopic.setBounds(0, 0, CTRL_WIDTH, CTRL_HEIGHT);
		pMain.add(tfTopic);
		
		bSubscribe = new JButton("Subscribe");
		bSubscribe.setBounds(tfTopic.getX() + tfTopic.getWidth() + CTRL_INTEROFFSET, 0, CTRL_WIDTH, CTRL_HEIGHT);
		bSubscribe.addActionListener(this);
		pMain.add(bSubscribe);
		
		bUnsubscribe = new JButton("Unsubscribe");
		bUnsubscribe.setBounds(bSubscribe.getX() + bSubscribe.getWidth() + CTRL_INTEROFFSET, 0, CTRL_WIDTH, CTRL_HEIGHT);
		bUnsubscribe.addActionListener(this);
		pMain.add(bUnsubscribe);
		
		bRemoveMessage = new JButton("Remove message");
		bRemoveMessage.setBounds(bUnsubscribe.getX() + bUnsubscribe.getWidth() + CTRL_INTEROFFSET, 0, CTRL_WIDTH * 3 / 2, CTRL_HEIGHT);
		bRemoveMessage.addActionListener(this);
		pMain.add(bRemoveMessage);
		
		listSubscribers = new JList<>(listSubscribersModel);
		listSubscribers.setBounds(0, tfTopic.getY() + tfTopic.getHeight() + CTRL_INTEROFFSET, CTRL_WIDTH * 2, 200);
		pMain.add(listSubscribers);
		
		listMessages = new JList<>(listMessagesModel);
		listMessages.setBounds(listSubscribers.getX() + listSubscribers.getWidth() + CTRL_INTEROFFSET, listSubscribers.getY(), CTRL_WIDTH * 2, 200);
		listMessages.addListSelectionListener(this);
		pMain.add(listMessages);
		
		taConsole = new JTextArea("This is the log.");
		taConsole.setBounds(listSubscribers.getX(), listSubscribers.getY() + listSubscribers.getHeight() + CTRL_INTEROFFSET, listSubscribers.getWidth(), 200);
		taConsole.setWrapStyleWord(true);
		taConsole.setEditable(false);
		taConsole.setBackground(Color.decode("0xFFFFDD"));
		pMain.add(taConsole);
		
		taMessage = new JTextArea("This is the place for message contents.");
		taMessage.setBounds(listMessages.getX(), listMessages.getY() + listMessages.getHeight() + CTRL_INTEROFFSET, listMessages.getWidth(), 200);
		taMessage.setWrapStyleWord(true);
		taMessage.setEditable(false);
		taMessage.setBackground(Color.decode("0xDDEEFF"));
		pMain.add(taMessage);
		
		// Finalize GUI initialization
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == bSubscribe)
		{
			String topicName = tfTopic.getText();
			
			if (topicName.length() > 0 && !subscribers.containsKey(topicName))
			{
				TopicSubscriber subscriber = new TopicSubscriber(topicName, this);
				
				try
				{
					subscriber.subscribe();
					subscribers.put(topicName, subscriber);
					listSubscribersModel.addElement(subscriber);
					log("Subscribed to topic \"" + topicName + "\".");
				}
				catch (JMSException e1)
				{
					log("Can't start subscriber for topic \"" + topicName + "\": " + e1.getMessage());
					
					try
					{
						subscriber.unsubscribe();
					}
					catch (JMSException e2)
					{
						log("Unable to unsubscribe properly: " + e2.getMessage());
					}
				}
			}
		}
		
		if (e.getSource() == bUnsubscribe)
		{
			int index = listSubscribers.getSelectedIndex();
			
			if (index >= 0)
			{
				TopicSubscriber subscriber = listSubscribers.getSelectedValue();
				
				try
				{
					subscriber.unsubscribe();
				}
				catch (JMSException e1)
				{
					log("Unable to unsubscribe properly: " + e1.getMessage());
				}
				
				listSubscribersModel.removeElementAt(index);
				subscribers.remove( subscriber.getTopicName() );
				log("Unsubscribed from topic \"" + subscriber.getTopicName() + "\".");
			}
		}
		
		if (e.getSource() == bRemoveMessage)
		{
			int index = listMessages.getSelectedIndex();
			
			if (index >= 0)
			{
				listMessagesModel.removeElementAt(index);
				taMessage.setText("");
			}
		}
	}
	
	@Override
	public void processMessage(Message message, TopicSubscriber subscriber)
	{
		if (message instanceof TextMessage)
		{
			TextMessage textMessage = (TextMessage) message;
			
			try
			{
				MessageData messageData = new MessageData(messageId++, textMessage.getText(), subscriber.getTopicName());
				listMessagesModel.addElement(messageData);
			}
			catch (JMSException e)
			{
				log("Can't process message: " + e.getMessage());
			}
			
			return;
		}
		
		log("Message of unknown format has been received: " + message.getClass().getName());
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		if (e.getSource() == listMessages)
		{
			int index = listMessages.getSelectedIndex();
			
			if (index >= 0)
			{
				MessageData messageData = listMessages.getSelectedValue();
				taMessage.setText( messageData.getMessage() );
			}
		}
	}
	
	protected void log(String message)
	{
		taConsole.setText( taConsole.getText() + "\r\n" + message );
	}
	
	public static void main(String[] args) throws JMSException
	{
		new JmsClient();
	}
}