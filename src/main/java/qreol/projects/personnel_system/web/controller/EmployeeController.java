package qreol.projects.personnel_system.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qreol.projects.personnel_system.domain.DepartmentAssignment;
import qreol.projects.personnel_system.domain.Employee;
import qreol.projects.personnel_system.service.DepartmentAssignmentService;
import qreol.projects.personnel_system.service.EmployeeService;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentAssignmentService DAService;

    @PostMapping("/hire")
    public ResponseEntity<Employee> hireEmployee(
            @RequestBody Employee employee,
            @RequestParam Long departmentId
    ) {
        return ResponseEntity.ok(employeeService.hireEmployee(employee, departmentId));
    }

    @PostMapping("/fire")
    public ResponseEntity<Employee> fireEmployee(
            @RequestParam Long employeeId
    ) {
        return ResponseEntity.ok(employeeService.fireEmployee(employeeId));
    }

    @PostMapping("/transfer")
    public ResponseEntity<Employee> transferEmployee(
            @RequestParam Long employeeId,
            @RequestParam Long newDepartmentId
    ) {
        return ResponseEntity.ok(employeeService.transferEmployee(employeeId, newDepartmentId));
    }

    @GetMapping("/by-department")
    public ResponseEntity<List<DepartmentAssignment>> getEmployeesByDepartmentAndPeriod(
            @RequestParam Long departmentId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) {
        return ResponseEntity.ok(
                DAService.getDepartmentAssignmentByDepartmentAndPeriod(
                        departmentId,
                        startDate,
                        endDate
                )
        );
    }

}
