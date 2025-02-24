package com.brayanalmengor04.rh.controller;

import com.brayanalmengor04.rh.entity.Employee;
import com.brayanalmengor04.rh.exception.ResourceNotFound;
import com.brayanalmengor04.rh.service.EmployeeService;
import com.brayanalmengor04.rh.service.IEmployeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/employee")
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

    // Editar empleados para frontend esto es para mostrar los datos que vamos editar

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        Employee employee = employeService.searchEmployeeById(id);
        if (employee == null) throw new ResourceNotFound("Employee not found");
        return ResponseEntity.ok(employee);
    }

    // Metodo para editar (formulario)
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id
            , @RequestBody Employee employee) {

        Employee updatedEmployee = employeService.searchEmployeeById(id);
        if (updatedEmployee == null) throw new ResourceNotFound("Employee not found");
        updatedEmployee.setName(employee.getName());
        updatedEmployee.setSalary(employee.getSalary());
        updatedEmployee.setDepartment(employee.getDepartment());
        employeService.saveEmployee(updatedEmployee);
        return ResponseEntity.ok(updatedEmployee);
    }

    // Metodo para eliminar

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee
            (@PathVariable Integer id) {
        Employee employee = employeService.searchEmployeeById(id);
        if (employee == null) throw new ResourceNotFound("Employee not found");
        employeService.deleteEmployee(employee);
        // Json {"eliminado":"true"}
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
