/**
 * @author Unic
 * "hw01_classLoading" project, Nov 3, 2014, 2:12:29 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading;

import java.io.File;

public interface Dao
{
	Object readItem(File file) throws DaoException;
	void writeItem(File file, Object o) throws DaoException;
}