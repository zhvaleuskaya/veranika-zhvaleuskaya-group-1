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
import unic.mentoring.softpatterns.messaging.data.RouteData;
import unic.mentoring.softpatterns.messaging.translator.RouteMessageTranslator;


public class RouteDatasource
{
	private ChannelAdapter<RouteData> channelAdapter;
	
	public RouteDatasource(ChannelAdapter<RouteData> channelAdapter)
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
		MessageTranslator<RouteData> messageTranslator = new RouteMessageTranslator();
		ChannelAdapter<RouteData> channelAdapter = new ChannelAdapter<RouteData>(channel, messageTranslator);
		new RouteDatasource(channelAdapter);
	}
}