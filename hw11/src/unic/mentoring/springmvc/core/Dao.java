/**
 * @author Unic
 * "hw08" project, Dec 9, 2014, 10:11:35 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.springmvc.core;

import java.io.Serializable;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class Dao
{
	private static Logger LOG = Logger.getLogger(Dao.class);
	
	private SessionFactory sessionFactory;
	
	protected Dao()
	{
		this.sessionFactory = DbUtil.getSessionFactory();
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
	
	@SuppressWarnings("unchecked")
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
	
	protected Collection<?> query(String query)
	{
		Session session = sessionFactory.openSession();
		Collection<?> result = null;
		
		try
		{
			Query q = session.createQuery(query);
			result = q.list();
		}
		catch (HibernateException e)
		{
			LOG.error(e.getMessage());
		}
		finally
		{
			session.close();
		}
		
		return result;
	}
}