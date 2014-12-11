/**
 * @author Unic
 * "hw08" project, Dec 9, 2014, 9:10:37 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import unic.mentoring.hibernate.model.EmployeeStatus;
import unic.mentoring.hibernate.model.Project;
import unic.mentoring.hibernate.model.Unit;
import unic.mentoring.hibernate.service.Service;
import unic.mentoring.hibernate.service.impl.ServiceImpl;

public class Run
{
	private static SessionFactory sessionFactory;

	public static void main(String[] args)
	{
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
		serviceRegistryBuilder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		Service service = new ServiceImpl(sessionFactory);
		
		Integer id = null;
		
		id = service.createUnit("testUnit");
		System.out.println("  q=" + id);
		Unit unit = service.getUnit(id);
		System.out.println("  w=" + unit.getId());
		
		id = service.createProject("testProject");
		System.out.println("  q=" + id);
		Project project = service.getProject(id);
		System.out.println("  w=" + project.getId());
		List<Project> projects = new ArrayList<Project>(1);
		projects.add(project);
		
		id = service.createEmployee(EmployeeStatus.ACTIVE, "qe", "theTown", "theName", unit, projects);
		System.out.println("  q=" + id);
		
		//TODO test m-m rel
		
		sessionFactory.close();
	}
}