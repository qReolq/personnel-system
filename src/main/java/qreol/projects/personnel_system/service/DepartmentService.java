package qreol.projects.personnel_system.service;

import qreol.projects.personnel_system.domain.Department;

import java.util.Date;
import java.util.List;

public interface DepartmentService {

    Department getById(Long id);

    Department save(Department department, Long parentDepartmentId);

    List<Department> getAllDepartmentsByDate(Date date);

    List<Department> getSubDepartments(Long id);

    List<Department> getAll();

}
