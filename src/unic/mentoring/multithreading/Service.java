/**
 * @author Unic
 * "hw01_classLoading" project, Nov 3, 2014, 2:13:48 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading;

import java.util.List;

public interface Service
{
	public String createAccount(String name) throws ServiceException;
	public void saveAccount(Account account) throws ServiceException;
	public Account getAccount(String id) throws ServiceException;
	public List<Account> findAccountsByName(String query);
	void saveAll() throws ServiceException;
	void loadAll() throws ServiceException;
	public void convert(String accountId, Conversion conversion, double amount) throws ServiceException;
}