package unic.mentoring.jboss.entities;

// Generated Dec 1, 2014 11:46:37 PM by Hibernate Tools 3.4.0.CR1

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable
{
	private static final long serialVersionUID = 0L;
	
	private Integer id;
	private String login;
	private String password;
	private String name;

	public User(){}

	public User(String login, String password, String name)
	{
		this.login = login;
		this.password = password;
		this.name = name;
	}

	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getLogin()
	{
		return this.login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}