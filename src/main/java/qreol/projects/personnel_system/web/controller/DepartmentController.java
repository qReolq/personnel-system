package qreol.projects.personnel_system.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qreol.projects.personnel_system.domain.Department;
import qreol.projects.personnel_system.service.DepartmentService;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Department> createDepartment(
            @RequestBody Department department,
            @RequestParam(required = false) Long parentDepartmentId
    ) {
        return ResponseEntity.ok(departmentService.save(department, parentDepartmentId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAllDepartmentsByDate(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return ResponseEntity.ok(departmentService.getAllDepartmentsByDate(date));
    }

    @GetMapping("/sub")
    public ResponseEntity<List<Department>> getSubDepartments(
            @RequestParam Long parentDepartmentId
    ) {
        return ResponseEntity.ok(departmentService.getSubDepartments(parentDepartmentId));
    }

}
