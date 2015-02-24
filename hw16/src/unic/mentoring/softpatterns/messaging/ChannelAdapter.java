/**
 * @author Unic
 * "hw16" project, Feb 23, 2015, 6:34:57 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns.messaging;

import java.io.IOException;

public class ChannelAdapter<T>
{
	private Channel channel;
	private MessageTranslator<T> translator;
	
	public ChannelAdapter(Channel channel, MessageTranslator<T> translator)
	{
		this.channel = channel;
		this.translator = translator;
	}
	
	public void send(T t) throws IOException
	{
		channel.send( translator.translate(t) );
	}
}