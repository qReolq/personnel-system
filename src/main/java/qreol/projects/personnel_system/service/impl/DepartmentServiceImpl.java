package qreol.projects.personnel_system.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qreol.projects.personnel_system.domain.Department;
import qreol.projects.personnel_system.domain.exceptions.ResourceNotFoundException;
import qreol.projects.personnel_system.repository.DepartmentRepository;
import qreol.projects.personnel_system.service.DepartmentService;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public Department getById(Long id) {
        return departmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Department by id:" + id + " is not found")
        );
    }

    @Override
    public Department save(Department department, Long parentDepartmentId) {
        if (parentDepartmentId != null) {
            Department parentDepartment = getById(parentDepartmentId);
            department.setParentDepartment(parentDepartment);
        }

        department.setCreateAt(new Date());

        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartmentsByDate(Date date) {
        return departmentRepository.findAllByCreateAt(date);
    }

    @Override
    public List<Department> getSubDepartments(Long id) {
        return getById(id).getSubDepartments();
    }

}
