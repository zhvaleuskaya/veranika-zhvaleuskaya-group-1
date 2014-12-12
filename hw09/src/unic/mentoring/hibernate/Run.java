/**
 * @author Unic
 * "hw08" project, Dec 9, 2014, 9:10:37 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.hibernate;

import org.hibernate.SessionFactory;

import unic.mentoring.hibernate.model.Employee;
import unic.mentoring.hibernate.model.EmployeeStatus;
import unic.mentoring.hibernate.model.Project;
import unic.mentoring.hibernate.model.Unit;
import unic.mentoring.hibernate.service.Service;
import unic.mentoring.hibernate.service.impl.ServiceImpl;
import unic.mentoring.hibernate.util.DbUtil;

public class Run
{
	public static void main(String[] args)
	{
		SessionFactory sessionFactory = DbUtil.getSessionFactory();
		Service service = new ServiceImpl(sessionFactory);
		Employee employee;
		Project project;
		Unit unit;
		
		Integer idEmployee = service.createEmployee(EmployeeStatus.ACTIVE, "by", "The Town", "The Name", null, null);
		
		employee = service.getEmployee(idEmployee);
		employee.setStatus(EmployeeStatus.INACTIVE);
		employee.getProfile().setName("The Name New");
		employee.getAddress().setCountry("ch");
		employee.getAddress().setTown("Bern");
		service.updateEmployee(employee);
		
		Integer idUnit = service.createUnit("Unit One");
		service.addEmployeeToUnit(idEmployee, idUnit);
		
		Integer idProject1 = service.createProject("Project One");
		Integer idProject2 = service.createProject("Project Two");
		service.addEmployeeToProject(idEmployee, idProject1);
		service.addEmployeeToProject(idEmployee, idProject2);
		
		unit = service.getUnit(idUnit);
		unit.setName("Unit One New");
		service.updateUnit(unit);
		
		project = service.getProject(idProject1);
		project.setName("Project One New");
		service.updateProject(project);
		
		service.removeUnit(idUnit);
		service.removeProject(idProject1);
		service.removeProject(idProject2);
		service.removeEmployee(idEmployee);
		
		DbUtil.closeSessionFactory();
	}
}