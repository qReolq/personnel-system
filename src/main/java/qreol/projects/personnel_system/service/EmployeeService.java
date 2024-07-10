package qreol.projects.personnel_system.service;

import qreol.projects.personnel_system.domain.Employee;

public interface EmployeeService {

    Employee getById(Long id);

    Employee hireEmployee(Employee employee, Long departmentId);

    Employee fireEmployee(Long id);

    Employee transferEmployee(Long employeeId, Long newDepartmentId);

}
