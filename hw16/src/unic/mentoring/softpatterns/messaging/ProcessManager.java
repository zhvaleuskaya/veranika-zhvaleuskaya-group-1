/**
 * @author Unic
 * "hw16" project, Feb 23, 2015, 7:23:04 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns.messaging;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class ProcessManager implements MessageEndpointListener
{
	private static final Logger LOG = Logger.getLogger(ProcessManager.class);
	
	private ControlBus controlBus;
	private MessageProcessor messageProcessor;
	private Map<MessageEndpoint, Thread> endpointThreadMap;
	
	public ProcessManager(ControlBus controlBus, MessageProcessor messageProcessor)
	{
		this.controlBus = controlBus;
		this.messageProcessor = messageProcessor;
		endpointThreadMap = new HashMap<MessageEndpoint, Thread>();
	}

	@Override
	public void messageReceived(Message message)
	{
		message.setTimeReceived( System.currentTimeMillis() );
		controlBus.processMessage(message);
		messageProcessor.process(message);
	}
	
	public void registerEndpoint(MessageEndpoint endpoint)
	{
		endpoint.addListener(this);
		Thread thread = new Thread(endpoint);
		endpointThreadMap.put(endpoint, thread);
		thread.start();
	}
	
	public void unregisterEndpoint(MessageEndpoint endpoint)
	{
		endpoint.removeListener(this);
		Thread thread = endpointThreadMap.get(endpoint);
		
		if (thread != null)
		{
			endpointThreadMap.remove(endpoint);
			thread.interrupt();
		}
		
		endpoint.die();
	}
	
	protected void printData()
	{
		Map<String, Object> data = controlBus.getData();
		
		StringBuilder sb = new StringBuilder( data.isEmpty() ? "No statistics info." : "Statistics:\\n");
		
		for (String key : data.keySet())
		{
			sb.append(key).append(": ").append( data.get(key) ).append("\\n");
		}
		
		LOG.info( sb.toString() );
	}
}