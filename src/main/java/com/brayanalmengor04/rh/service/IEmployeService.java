package com.brayanalmengor04.rh.service;

import com.brayanalmengor04.rh.entity.Employee;

import java.util.List;

public interface IEmployeService {
    public List<Employee> listEmployees();
    public Employee searchEmployeeById(Integer id);
    public Employee saveEmployee(Employee employee);
    public void deleteEmployee(Employee employee);


}
