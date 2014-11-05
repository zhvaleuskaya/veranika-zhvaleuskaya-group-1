/**
 * @author Unic
 * "hw01_classLoading" project, Nov 3, 2014, 2:13:48 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading.service;

import java.util.List;

import unic.mentoring.multithreading.core.Account;
import unic.mentoring.multithreading.core.Conversion;
import unic.mentoring.multithreading.exception.ServiceException;

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