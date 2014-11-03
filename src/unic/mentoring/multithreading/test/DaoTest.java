/**
 * @author Unic
 * "hw01_classLoading" project, Nov 3, 2014, 2:18:32 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import unic.mentoring.multithreading.Account;
import unic.mentoring.multithreading.Currency;
import unic.mentoring.multithreading.Dao;
import unic.mentoring.multithreading.DaoException;
import unic.mentoring.multithreading.DaoImpl;

public class DaoTest
{
	private static final String TEST_ACCOUNT_ID = "_testAccountId";
	
	private double delta;
	private File file;
	
	@Before
	public void before()
	{
		delta = 0.000001;
		file = new File("data/test/" + TEST_ACCOUNT_ID + ".ext");
	}
	
	@Test
	public void readWriteItem() throws DaoException, FileNotFoundException
	{
		Currency currency1 = new Currency("currOne", 0.1);
		Currency currency2 = new Currency("currTwo", 0.01);
		Account account = new Account(TEST_ACCOUNT_ID, "theName");
		account.addCurrency(currency1);
		account.addCurrency(currency2, 125.45);
		
		Dao dao = new DaoImpl();
		dao.writeItem(file, account);
		
		Object o = dao.readItem(file);
		Assert.assertTrue(o instanceof Account);
		
		Account accountActual = (Account)o;
		Assert.assertEquals(TEST_ACCOUNT_ID, accountActual.getId());
		Assert.assertEquals("theName", accountActual.getName());
		Assert.assertEquals( accountActual.getAmount(currency1), 0, delta );
		Assert.assertEquals( accountActual.getAmount(currency2), 125.45, delta );
	}
	
	@After
	public void after() throws IOException
	{
		Files.delete( Paths.get(file.getAbsolutePath()) );
	}
}