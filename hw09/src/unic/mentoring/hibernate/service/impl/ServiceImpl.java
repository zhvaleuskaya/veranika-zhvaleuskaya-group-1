/**
 * @author Unic
 * "hw08" project, Dec 9, 2014, 10:11:35 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.hibernate.service.impl;

import java.io.Serializable;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import unic.mentoring.hibernate.model.Employee;
import unic.mentoring.hibernate.model.EmployeeStatus;
import unic.mentoring.hibernate.model.Profile;
import unic.mentoring.hibernate.model.Project;
import unic.mentoring.hibernate.model.Unit;
import unic.mentoring.hibernate.service.Service;

public class ServiceImpl implements Service
{
	private static Logger LOG = Logger.getLogger(ServiceImpl.class);
	
	private SessionFactory sessionFactory;
	
	public ServiceImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	protected Integer create(Serializable model)
	{
		if (model == null)
		{
			LOG.error("Model is null.");
			return null;
		}
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer id = null;
		
		try
		{
			tx = session.beginTransaction();
			id = (Integer) session.save(model);
			tx.commit();
		}
		catch (HibernateException e)
		{
			if (tx != null) tx.rollback();
			LOG.error(e.getMessage());
		}
		finally
		{
			session.close();
		}
		
		return id;
	}
	
	protected <T> T get(Integer id, Class<T> type)
	{
		if (id == null)
		{
			LOG.error("Id is null.");
			return null;
		}
		
		Session session = sessionFactory.openSession();
		T model = null;
		
		try
		{
			model = (T)session.get(type, id);
		}
		catch (HibernateException e)
		{
			LOG.error(e.getMessage());
		}
		finally
		{
			session.close();
		}
		
		return model;
	}
	
	protected <T> void remove(T model)
	{
		if (model == null)
		{
			LOG.error("Model is null.");
			return;
		}
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try
		{
			tx = session.beginTransaction();
			session.delete(model);
			tx.commit();
		}
		catch (HibernateException e)
		{
			if (tx != null) tx.rollback();
			LOG.error(e.getMessage());
		}
		finally
		{
			session.close();
		}
	}
	
	protected <T> void remove(Integer id, Class<T> type)
	{
		T model = get(id, type);
		remove(model);
	}
	
	protected <T> void update(T model)
	{
		if (model == null)
		{
			LOG.error("Model is null.");
			return;
		}
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try
		{
			tx = session.beginTransaction();
			session.update(model);
			tx.commit();
		}
		catch (HibernateException e)
		{
			if (tx != null) tx.rollback();
			LOG.error(e.getMessage());
		}
		finally
		{
			session.close();
		}
	}
	
	protected <T> void update(Integer id, Class<T> type)
	{
		T model = get(id, type);
		update(model);
	}
	
	protected Integer createProfile(String name)
	{
		Profile model = new Profile(name);
		return create(model);
	}
	
	@Override
	public Integer createUnit(String name)
	{
		Unit model = new Unit(name);
		return create(model);
	}
	
	@Override
	public Integer createProject(String name)
	{
		Project model = new Project(name);
		return create(model);
	}
	
	@Override
	public Integer createEmployee(EmployeeStatus status, String country, String town, String name, Unit unit, Collection<Project> projects)
	{
		Integer profileId = createProfile(name);
		
		if (profileId == null)
		{
			LOG.error("Can't create profile for employee.");
		}
		else
		{
			LOG.debug("Profile with id=" + profileId + " created for employee.");
			Profile profile = getProfile(profileId);
			
			if (profile == null)
			{
				LOG.error("Can't get profile created for employee.");
			}
			else
			{
				Employee model = new Employee(status, country, town, profile, unit);
				return create(model);
			}
		}
		
		return null;
	}

	public Profile getProfile(Integer id)
	{
		return get(id, Profile.class);
	}
	
	@Override
	public Unit getUnit(Integer id)
	{
		return get(id, Unit.class);
	}

	@Override
	public Project getProject(Integer id)
	{
		return get(id, Project.class);
	}

	@Override
	public Employee getEmployee(Integer id)
	{
		return get(id, Employee.class);
	}
	
	@Override
	public void removeUnit(Integer id)
	{
		Unit unit = getUnit(id);
		
		if (unit == null)
		{
			LOG.error("Can't retreive unit for removing: id=" + id);
			return;
		}
		
		unit.getEmployees().clear();
		update(unit);
		
		remove(id, Unit.class);
	}

	@Override
	public void removeProject(Integer id)
	{
		Project project = getProject(id);
		
		if (project == null)
		{
			LOG.error("Can't retreive project for removing: id=" + id);
			return;
		}
		
		project.getEmployees().clear();
		update(project);
		
		remove(id, Project.class);
	}

	@Override
	public void removeEmployee(Integer id)
	{
		Employee employee = getEmployee(id);
		
		if (employee == null)
		{
			LOG.error("Can't retreive employee for removing: id=" + id);
			return;
		}
		
		employee.getProjects().clear();
		update(employee);
		
		remove(id, Employee.class);
	}
	
	@Override
	public void updateUnit(Unit model)
	{
		update(model);
	}

	@Override
	public void updateProject(Project model)
	{
		update(model);
	}

	@Override
	public void updateEmployee(Employee model)
	{
		update(model.getProfile());
		update(model);
	}

	@Override
	public void addEmployeeToUnit(Integer employeeId, Integer unitId)
	{
		Employee employee = getEmployee(employeeId);
		Unit unit = getUnit(unitId);
		
		employee.setUnit(unit);
		update(employee);
	}

	@Override
	public void addEmployeeToProject(Integer employeeId, Integer projectId)
	{
		Employee employee = getEmployee(employeeId);
		Project project = getProject(projectId);
		
		employee.getProjects().add(project);
		update(employee);
	}
}