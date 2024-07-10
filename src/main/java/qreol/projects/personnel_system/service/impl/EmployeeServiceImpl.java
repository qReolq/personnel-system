package qreol.projects.personnel_system.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qreol.projects.personnel_system.domain.Department;
import qreol.projects.personnel_system.domain.Employee;
import qreol.projects.personnel_system.domain.exceptions.ResourceNotFoundException;
import qreol.projects.personnel_system.domain.exceptions.ResourceNotValidException;
import qreol.projects.personnel_system.repository.EmployeeRepository;
import qreol.projects.personnel_system.service.DepartmentAssignmentService;
import qreol.projects.personnel_system.service.DepartmentService;
import qreol.projects.personnel_system.service.EmployeeService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;
    private final DepartmentAssignmentService DAService;

    @Override
    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee by id:" + id + " is not found")
        );
    }

    @Override
    @Transactional
    public Employee hireEmployee(Employee employee, Long departmentId) {
        Department department = departmentService.getById(departmentId);
        employee.setDepartment(department);

        DAService.hireEmployee(employee, department);

        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Employee fireEmployee(Long id) {
        Employee employee = getById(id);

        if (employee.getDepartment() == null)
            throw new ResourceNotValidException("The employee is no longer employed");

        DAService.fireEmployee(employee, employee.getDepartment());
        employee.setDepartment(null);

        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Employee transferEmployee(Long employeeId, Long newDepartmentId) {
        Employee employee = getById(employeeId);
        Department newDepartment = departmentService.getById(newDepartmentId);

        if (employee.getDepartment() != null && employee.getDepartment().getId().equals(newDepartmentId))
            throw new ResourceNotValidException("The employee is already working here");

        DAService.fireEmployee(employee, employee.getDepartment());
        DAService.fireEmployee(employee, newDepartment);
        employee.setDepartment(newDepartment);

        return employeeRepository.save(employee);
    }

}
