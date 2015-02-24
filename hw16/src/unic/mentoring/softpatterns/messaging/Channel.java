/**
 * @author Unic
 * "hw16" project, Feb 23, 2015, 5:52:52 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns.messaging;

import java.io.IOException;

public interface Channel
{
	void send(Message message) throws IOException;
}