package unic.mentoring.jboss.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class TestBean implements TestBeanRemote
{
	public TestBean(){}

	@Override
	public String hello(String name)
	{
		return "Hello, " + name + "!";
	}
}