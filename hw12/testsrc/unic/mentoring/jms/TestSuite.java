/**
 * @author Unic
 * "hw12" project, Jan 24, 2015, 6:35:23 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.jms;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import unic.mentoring.jms.client.MessageDataTest;
import unic.mentoring.jms.ctrl.IndexCtrlTest;

@RunWith(Suite.class)
@SuiteClasses({ MessageDataTest.class, IndexCtrlTest.class })
public class TestSuite{}