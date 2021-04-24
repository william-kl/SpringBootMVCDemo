package com.example.SpringMVCDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringMVCDemo.model.Employee;
import com.example.SpringMVCDemo.repo.EmployeeRepo;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepo empRepo;

	public List<Employee> getEmployeeList() {
		/*
		 * List<Employee> empList = new ArrayList<>(); empList.add(new Employee(1,
		 * "william1", "will1@gmail.com", "CA1")); empList.add(new Employee(2,
		 * "william2", "will2@gmail.com", "CA2")); empList.add(new Employee(3,
		 * "william3", "will3@gmail.com", "CA3")); empList.add(new Employee(4,
		 * "william4", "will4@gmail.com", "CA4"));
		 * 
		 * return empList;
		 */
		return empRepo.findAll();
	}

	public void delete(int id) {
		empRepo.deleteById(id);
	}

	public Employee get(int i) {
		return empRepo.findById(i).get();// empRepo.findById(i) is optional object, need convert to Employee
	}

	public void saveEmp(Employee e) {
		empRepo.save(e);
	}

}
