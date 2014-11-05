/**
 * @author Unic
 * "hw01_classLoading" project, Nov 2, 2014, 11:42:45 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.multithreading.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import unic.mentoring.multithreading.core.Currency;
import unic.mentoring.multithreading.util.Util;

public class UtilTest
{
	private double delta;
	
	@Before
	public void before()
	{
		delta = 0.000001;
	}
	
	@Test
	public void makeConversionCode()
	{
		Currency srcCurrency = new Currency("currOne", 1);
		Currency dstCurrency = new Currency("currTwo", 1);
		Assert.assertEquals( "currOne->currTwo", Util.makeConversionCode(srcCurrency, dstCurrency) );
		
		srcCurrency = new Currency("currThree", 1);
		dstCurrency = new Currency("currFour", 1);
		Assert.assertEquals( "currThree->currFour", Util.makeConversionCode(srcCurrency, dstCurrency) );
	}
	
	@Test
	public void round()
	{
		Assert.assertEquals( 5.89, Util.round(5.88888, 0.01), delta );
		Assert.assertEquals( 0.1, Util.round(0.09999, 0.1), delta );
		Assert.assertEquals( 1.001, Util.round(1.001, 0.001), delta );
		Assert.assertEquals( 1.002, Util.round(1.00199, 0.001), delta );
	}
	
	@Test
	public void convertCurrency()
	{
		Assert.assertEquals( 15, Util.convertCurrency(10, 1.5, 0.1), delta );
		Assert.assertEquals( 2.6, Util.convertCurrency(2, 1.299, 0.1), delta );
		Assert.assertEquals( 5, Util.convertCurrency(5, 0.999, 1), delta );
		Assert.assertEquals( 10.15, Util.convertCurrency(7, 1.45, 0.01), delta );
	}
	
	@Test
	public void getFileExtension()
	{
		Assert.assertEquals( "ext2", Util.getFileExtension("name.ext1.ext2") );
		Assert.assertEquals( "", Util.getFileExtension("name.ext1.") );
		Assert.assertEquals( "ext", Util.getFileExtension("name.ext") );
		Assert.assertEquals( "e", Util.getFileExtension("name.e") );
		Assert.assertEquals( "", Util.getFileExtension("name.") );
		Assert.assertEquals( "", Util.getFileExtension("name") );
		Assert.assertEquals( "ext", Util.getFileExtension("e.ext") );
		Assert.assertEquals( "e", Util.getFileExtension("e.e") );
		Assert.assertEquals( "ext", Util.getFileExtension(".ext") );
		Assert.assertEquals( "", Util.getFileExtension(".") );
		Assert.assertEquals( "", Util.getFileExtension("") );
	}
	
	@Test
	public void getFileName()
	{
		Assert.assertEquals( "name.ext1", Util.getFileName("name.ext1.ext2") );
		Assert.assertEquals( "name.ext1", Util.getFileName("name.ext1.") );
		Assert.assertEquals( "name", Util.getFileName("name.ext") );
		Assert.assertEquals( "name", Util.getFileName("name.e") );
		Assert.assertEquals( "name", Util.getFileName("name.") );
		Assert.assertEquals( "name", Util.getFileName("name") );
		Assert.assertEquals( "e", Util.getFileName("e.ext") );
		Assert.assertEquals( "e", Util.getFileName("e.e") );
		Assert.assertEquals( "", Util.getFileName(".ext") );
		Assert.assertEquals( "", Util.getFileName(".") );
		Assert.assertEquals( "", Util.getFileName("") );
	}
}