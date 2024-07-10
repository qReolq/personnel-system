package qreol.projects.personnel_system.service;

import org.springframework.stereotype.Service;
import qreol.projects.personnel_system.domain.Department;
import qreol.projects.personnel_system.domain.DepartmentAssignment;
import qreol.projects.personnel_system.domain.Employee;

import java.util.Date;
import java.util.List;

@Service
public interface DepartmentAssignmentService {

    List<DepartmentAssignment> getDepartmentAssignmentByDepartmentAndPeriod(Long departmentId, Date startDate, Date endDate);

    void hireEmployee(Employee employee, Department department);

    void fireEmployee(Employee employee, Department department);

}
