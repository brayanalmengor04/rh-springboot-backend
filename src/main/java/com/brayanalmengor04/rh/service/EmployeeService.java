package com.brayanalmengor04.rh.service;

import com.brayanalmengor04.rh.entity.Employee;
import com.brayanalmengor04.rh.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> listEmployees() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee searchEmployeeById(Integer id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        this.employeeRepository.delete(employee);
    }
}
