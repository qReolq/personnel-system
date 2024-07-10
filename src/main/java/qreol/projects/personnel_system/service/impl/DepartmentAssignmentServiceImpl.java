package qreol.projects.personnel_system.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qreol.projects.personnel_system.domain.Department;
import qreol.projects.personnel_system.domain.DepartmentAssignment;
import qreol.projects.personnel_system.domain.Employee;
import qreol.projects.personnel_system.repository.DepartmentAssignmentRepository;
import qreol.projects.personnel_system.service.DepartmentAssignmentService;
import qreol.projects.personnel_system.service.DepartmentService;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DepartmentAssignmentServiceImpl implements DepartmentAssignmentService {

    private final DepartmentService departmentService;
    private final DepartmentAssignmentRepository DARepository;

    @Override
    public List<DepartmentAssignment> getDepartmentAssignmentByDepartmentAndPeriod(
            Long departmentId,
            Date startDate,
            Date endDate
    ) {
        Department department = departmentService.getById(departmentId);
        return DARepository.findAllByDepartmentAndHiredDateBetween(
                department,
                startDate,
                endDate
        );
    }

    @Override
    @Transactional
    public void hireEmployee(Employee employee, Department department) {
        DepartmentAssignment departmentAssignment = new DepartmentAssignment();
        departmentAssignment.setEmployee(employee);
        departmentAssignment.setDepartment(department);
        departmentAssignment.setHiredDate(new Date());

        DARepository.save(departmentAssignment);
    }

    @Override
    @Transactional
    public void fireEmployee(Employee employee, Department department) {
        DepartmentAssignment departmentAssignment = DARepository
                .getDepartmentAssignmentByDepartmentAndEmployee(department, employee);

        departmentAssignment.setFiredDate(new Date());
    }

}
