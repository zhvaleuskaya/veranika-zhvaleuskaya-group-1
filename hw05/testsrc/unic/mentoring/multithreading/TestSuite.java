/**
 * @author Unic
 * "hw01_classLoading" project, Nov 3, 2014, 12:01:44 AM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import unic.mentoring.multithreading.client.ClientsTest;
import unic.mentoring.multithreading.dao.DaoTest;
import unic.mentoring.multithreading.service.ServiceTest;
import unic.mentoring.multithreading.util.UtilTest;

@RunWith(Suite.class)
@SuiteClasses({ UtilTest.class, DaoTest.class, ServiceTest.class, ClientsTest.class })
public class TestSuite{}