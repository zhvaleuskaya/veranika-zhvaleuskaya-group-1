/**
 * @author Unic
 * "hw16" project, Feb 23, 2015, 6:50:50 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns.messaging;

public interface MessageEndpointListener
{
	void messageReceived(Message message);
}