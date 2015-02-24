/**
 * @author Unic
 * "hw16" project, Feb 23, 2015, 6:35:35 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns.messaging;

public interface MessageTranslator<T>
{
	Message translate(T t);
}