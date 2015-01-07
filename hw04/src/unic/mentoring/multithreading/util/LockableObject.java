/**
 * @author Unic
 * "hw01_classLoading" project, Oct 28, 2014, 12:48:15 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading.util;

public class LockableObject<T>
{
	private final Object lock = new Object();
	private T object;
	
	public LockableObject(T object)
	{
		this.object = object;
	}
	
	public T getObject()
	{
		return object;
	}
	
	public void setObject(T object)
	{
		this.object = object;
	}
	
	public Object getLock()
	{
		return lock;
	}
}