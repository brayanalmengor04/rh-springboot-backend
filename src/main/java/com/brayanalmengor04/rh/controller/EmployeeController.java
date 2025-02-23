package com.brayanalmengor04.rh.controller;

import com.brayanalmengor04.rh.entity.Employee;
import com.brayanalmengor04.rh.service.EmployeeService;
import com.brayanalmengor04.rh.service.IEmployeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rh-app")
// Puerto del frontend react
@CrossOrigin(value ="http://localhost:5173" )
public class EmployeeController {
    // Esto es para consola
    private static final Logger logger = LoggerFactory.
            getLogger(EmployeeController.class);

    @Autowired
    private IEmployeService employeService;

    // Solicitar Toda la lita de empleados
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        var employees = employeService.listEmployees();
        employees.forEach(employee ->
                logger.info(employee.toString()));
        return employees;
    }

    // Ahora para el frontend agregar empleados
    @PostMapping("/employee-add")
    public Employee addEmployee(@RequestBody Employee employee) {
        logger.info(employee.toString());
        return employeService.saveEmployee(employee);
    }


}
