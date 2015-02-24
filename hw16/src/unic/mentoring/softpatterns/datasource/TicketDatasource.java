/**
 * @author Unic
 * "hw16" project, Feb 23, 2015, 6:33:33 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns.datasource;

import unic.mentoring.softpatterns.C;
import unic.mentoring.softpatterns.C.Remote;
import unic.mentoring.softpatterns.messaging.Channel;
import unic.mentoring.softpatterns.messaging.ChannelAdapter;
import unic.mentoring.softpatterns.messaging.MessageTranslator;
import unic.mentoring.softpatterns.messaging.channel.TCPChannel;
import unic.mentoring.softpatterns.messaging.data.TicketData;
import unic.mentoring.softpatterns.messaging.translator.TicketMessageTranslator;

public class TicketDatasource
{
	private ChannelAdapter<TicketData> channelAdapter;
	
	public TicketDatasource(ChannelAdapter<TicketData> channelAdapter)
	{
		this.channelAdapter = channelAdapter;
		provide();
	}
	
	private void provide()
	{
		//TODO provide data to channel
	}
	
	public static void main()
	{
		Channel channel = new TCPChannel(C.Remote.ROUTE_EXTERNAL_DATASOURCE_HOST, C.Remote.ROUTE_EXTERNAL_DATASOURCE_PORT);
		MessageTranslator<TicketData> messageTranslator = new TicketMessageTranslator();
		ChannelAdapter<TicketData> channelAdapter = new ChannelAdapter<TicketData>(channel, messageTranslator);
		new TicketDatasource(channelAdapter);
	}
}