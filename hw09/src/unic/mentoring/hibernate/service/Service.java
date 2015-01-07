/**
 * @author Unic
 * "hw08" project, Dec 9, 2014, 10:09:33 PM
 * GPL v3: http://gnu.org/licenses
 */

package unic.mentoring.hibernate.service;

import java.util.Collection;

import unic.mentoring.hibernate.model.Employee;
import unic.mentoring.hibernate.model.EmployeeStatus;
import unic.mentoring.hibernate.model.Project;
import unic.mentoring.hibernate.model.Unit;

public interface Service
{
	Integer createUnit(String name);
	Integer createProject(String name);
	Integer createEmployee(EmployeeStatus status, String country, String town, String name, Unit unit, Collection<Project> projects);
	
	Unit getUnit(Integer id);
	Project getProject(Integer id);
	Employee getEmployee(Integer id);
	
	void removeUnit(Integer id);
	void removeProject(Integer id);
	void removeEmployee(Integer id);
	
	void updateUnit(Unit model);
	void updateProject(Project model);
	void updateEmployee(Employee model);
	
	void addEmployeeToUnit(Integer employeeId, Integer unitId);
	void addEmployeeToProject(Integer employeeId, Integer projectId);
}