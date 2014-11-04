/**
 * @author Unic
 * "hw01_classLoading" project, Nov 3, 2014, 2:13:48 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading;

import java.util.List;

public interface Service
{
	String createAccount(String name) throws ServiceException;
	void saveAccount(Account account) throws ServiceException;
	Account getAccount(String id) throws ServiceException;
	List<Account> findAccountsByName(String query);
	void saveAll() throws ServiceException;
	void loadAll() throws ServiceException;
	void convert(String accountId, Conversion conversion, double amount) throws ServiceException;
}