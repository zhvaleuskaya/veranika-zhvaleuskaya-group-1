/**
 * @author Unic
 * "hw16" project, Feb 19, 2015, 6:39:15 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.softpatterns;

public interface Criteria<T>
{
	boolean matches(T t);
}