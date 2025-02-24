package com.tka.SRMScontroller;

import java.util.List;

import com.tka.SRMSentity.Address;
import com.tka.SRMSentity.Student;
import com.tka.SRMSentity.StudentOrder;
import com.tka.SRMSservice.StudentService;

public class StudentController {
	StudentService service=null;
	
	public List<Student> getAllStudents() {
		service= new StudentService();
		List<Student> studentdb=service.getAllStudents();
		return studentdb;
	}

	public void addNewStudent(String name, int marks, Address add) {
		service=new StudentService();
		service.addNewStudent(name,marks,add);
	}

	public List<StudentOrder> getAllOrders() {
		service= new StudentService();
		List<StudentOrder> studentOrderdb=service.getAllOrders();
		return studentOrderdb;
	}

	public List<Address> getStudentAddress(int roll) {
		service= new StudentService();
		List<Address>  studentAddress = service.getStudentAddress(roll);
		return studentAddress;
		
	}

	public List<Student> getStudentsWithMarksGreaterThan(int marks1) {
		service= new StudentService();
		List<Student> studentdb=service.getStudentsWithMarksGreaterThan(marks1);
		return studentdb;		
	}

	public List<Address> getStudentsFromCity(String searchCity) {
		service= new StudentService();
		List<Address> studentdb=service.getStudentsFromCity(searchCity);
		return studentdb;
	}

	public List<Student> getStudentsWhoOrderedPoha() {
		service= new StudentService();
		List<Student> studentdb=service.getStudentsWhoOrderedPoha();
		return studentdb;
	}

	public void deleteStudent(int deleteId) {
		service= new StudentService();
		service.deleteStudent(deleteId);
	}

	public void updateStudentAddress(int updateId, Address newAddress) {
		service=new StudentService();
		service.updateStudentAddress(updateId, newAddress);
		
	}

	

	public void placeOrder(int studentId,List<StudentOrder> solist) {
		service= new StudentService();
		service.placeOrder(studentId, solist);
	}

	
	}




