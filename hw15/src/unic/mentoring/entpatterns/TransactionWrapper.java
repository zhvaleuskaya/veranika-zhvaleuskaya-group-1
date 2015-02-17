/**
 * @author Unic
 * "hw15" project, Feb 17, 2015, 7:38:28 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.entpatterns;

public interface TransactionWrapper
{
	void startTransaction(Query query);
	void commit();
	void abort();
	void synchronize();
	Status getStatus();
}