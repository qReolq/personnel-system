package qreol.projects.personnel_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qreol.projects.personnel_system.domain.Department;
import qreol.projects.personnel_system.domain.DepartmentAssignment;
import qreol.projects.personnel_system.domain.Employee;

import java.util.Date;
import java.util.List;

@Repository
public interface DepartmentAssignmentRepository extends JpaRepository<DepartmentAssignment, Long> {

    List<DepartmentAssignment> findAllByDepartmentAndHiredDateBetween(Department department, Date start, Date end);

    DepartmentAssignment getDepartmentAssignmentByDepartmentAndEmployee(Department department, Employee employee);

}
