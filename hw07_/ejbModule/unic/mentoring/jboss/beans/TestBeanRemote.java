package unic.mentoring.jboss.beans;

import javax.ejb.Remote;

@Remote
public interface TestBeanRemote
{
	String hello(String name);
}