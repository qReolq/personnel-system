package qreol.projects.personnel_system.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UiController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/add-employee")
    public String addEmployee() {
        return "add-employee";
    }

    @GetMapping("/departments-on-date")
    public String departmentsOnDate() {
        return "departments-on-date";
    }

    @GetMapping("/employees-in-department")
    public String employeesInDepartment() {
        return "employees-in-department";
    }

    @GetMapping("/add-department")
    public String addDepartment() {
        return "add-department";
    }


}
