package unic.mentoring.hibernate.model;

import java.util.Collection;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Employee implements java.io.Serializable
{
	private static final long serialVersionUID = 0;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "unit_id", nullable = true)
	private Unit unit;
	
	@OneToOne
	@JoinColumn(name = "profile_id")
	private Profile profile;
	
	@Enumerated(EnumType.STRING)
	private EmployeeStatus status;
	
	@Embedded()
	private Address address;
	//cascade = CascadeType.ALL
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "employee2project",
			joinColumns = @JoinColumn(name = "employee_id"),
			inverseJoinColumns = @JoinColumn(name = "project_id"))
	private Collection<Project> projects;

	public Employee()
	{
	}

	public Employee(EmployeeStatus status, String country, String town, Profile profile, Unit unit)
	{
		this.status = status;
		this.address = new Address(country, town);
		this.profile = profile;
		this.unit = unit;
	}

	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public EmployeeStatus getStatus()
	{
		return this.status;
	}

	public void setStatus(EmployeeStatus status)
	{
		this.status = status;
	}
	
	public Address getAddress()
	{
		return address;
	}
	
	public void setAddress(Address address)
	{
		this.address = address;
	}
	
	public Profile getProfile()
	{
		return profile;
	}
	
	public void setProfile(Profile profile)
	{
		this.profile = profile;
	}
	
	public Unit getUnit()
	{
		return unit;
	}
	
	public void setUnit(Unit unit)
	{
		this.unit = unit;
	}
	
	public Collection<Project> getProjects()
	{
		return projects;
	}
	
	public void setProjects(Collection<Project> projects)
	{
		this.projects = projects;
	}
}