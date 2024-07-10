package qreol.projects.personnel_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qreol.projects.personnel_system.domain.Department;

import java.util.Date;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findAllByCreateAt(Date date);

}
