package com.example.SpringMVCDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.SpringMVCDemo.model.Employee;
import com.example.SpringMVCDemo.service.EmployeeService;

@Controller
public class HomeController {

	@Autowired
	EmployeeService empService;

	@RequestMapping("/list_employees")
	public String listEmployees(Model model) {
		List<Employee> list = empService.getEmployeeList();
		model.addAttribute("employees", list);// key value pair
		return "employee"; // here its a view name, thymlefe, employee.html in templates
							// display employee list in browser
	}

	@RequestMapping("/")
	public String welcome() {
		return "index";
	}

	@RequestMapping("/delete/{id}") // id is path variable
	public String deleteEmployee(@PathVariable(name = "id") int id) {// not return any data back, so not use model
		empService.delete(id);
		return "redirect:/list_employees";
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditEmployeePage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_employee");// add a view
		Employee emp = empService.get(id);

		mav.addObject("employee", emp);// fetch the "emp" details from the database
		return mav; // key-value pairs into key "employee", the mav("edit_employee" view) now can
					// get it
					// through "employee"
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveEmployee(@ModelAttribute("employee") Employee emp) {// receive "employee" as "emp"
		empService.saveEmp(emp);
		return "redirect:/list_employees";
	}

	@RequestMapping("/new")
	public String newEmployee(Model model) {
		Employee e = new Employee();
		model.addAttribute("employee_new", e);
		return "newemployee";
	}

	@RequestMapping("/test")
	public String testMethod(Model model) {
		model.addAttribute("message", "welcome to spring MVC");
		return "welcome";
	}
}
