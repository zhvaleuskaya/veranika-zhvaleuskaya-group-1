package unic.mentoring.hibernate.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Unit implements java.io.Serializable
{
	private static final long serialVersionUID = 0;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(targetEntity = Employee.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "unit_id")
	private Collection<Employee> employees;
	
	private String name;

	public Unit()
	{
	}

	public Unit(String name)
	{
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

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public Collection<Employee> getEmployees()
	{
		return employees;
	}
	
	public void setEmployees(Collection<Employee> employees)
	{
		this.employees = employees;
	}
}