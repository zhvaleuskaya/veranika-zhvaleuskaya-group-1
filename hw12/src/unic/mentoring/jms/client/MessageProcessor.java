/**
 * @author Unic
 * "hw12" project, Jan 18, 2015, 2:47:27 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.jms.client;

import javax.jms.Message;

public interface MessageProcessor
{
	void processMessage(Message message, TopicSubscriber subscriber);
}