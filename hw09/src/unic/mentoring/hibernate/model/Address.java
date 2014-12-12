package unic.mentoring.hibernate.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;


@Embeddable
@Access(AccessType.FIELD)
public class Address implements java.io.Serializable
{
	private static final long serialVersionUID = 0;
	
	private String country;
	private String town;

	public Address()
	{
	}

	public Address(String country, String town)
	{
		this.country = country;
		this.town = town;
	}

	public String getCountry()
	{
		return this.country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getTown()
	{
		return this.town;
	}

	public void setTown(String town)
	{
		this.town = town;
	}
}