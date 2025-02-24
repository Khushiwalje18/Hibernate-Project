package com.tka.SRMSclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tka.SRMScontroller.StudentController;
import com.tka.SRMSentity.Address;
import com.tka.SRMSentity.Student;
import com.tka.SRMSentity.StudentOrder;

public class StudentClient {

	public static void main(String[] args) {

		StudentController controller = null;
		int choice;
		List<Student> studentdb = null;
		List<StudentOrder> studentOrderdb = null;
		List<Address> studentAddress = null;

		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("\n===== Student Management System =====");
			System.out.println("1. Add Student");
			System.out.println("2. View All Students");
			System.out.println("3. View All Orders");
			System.out.println("4. Get address of specific student");
			System.out.println("5. Get all students with max greater than....");
			System.out.println("6. Get all students from specific city");
			System.out.println("7. Get student who ordered Poha");
			System.out.println("8. Delete a student");
			System.out.println("9: Update address of a student");
			System.out.println("10. Place order for a student");
			System.out.println("11. Exit");
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				if (controller == null)
					controller = new StudentController();
				System.out.print("Enter Name: ");
				String name = sc.next();
				System.out.print("Enter marks: ");
				int marks = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter city: ");
				String city = sc.nextLine();
				System.out.println("Enter email id: ");
				String email = sc.nextLine();
				Address add = new Address(city, email);
				controller.addNewStudent(name, marks, add);
				break;

			case 2:
				if (controller == null)
					controller = new StudentController();
				studentdb = controller.getAllStudents();
				for (Student s : studentdb) {
					System.out.println("Name: " + s.getName());
				}
				break;

			case 3:
				if (controller == null)
					controller = new StudentController();
				studentOrderdb = controller.getAllOrders();
				for (StudentOrder o : studentOrderdb) {
					System.out.println("Ordered By: " + o.getStudent() + " Order name: " + o.getOrdername() + " Price: "
							+ o.getPrice());
				}
				break;

			case 4:
				if (controller == null)
					controller = new StudentController();
				System.out.print("Enter Student roll: ");
				int roll = sc.nextInt();
				studentAddress = controller.getStudentAddress(roll);
				System.out.println("Address of student with roll no: " + roll);
				for (Address add1 : studentAddress) {
					System.out.println("Address : city: " + add1.getCity() + "email: " + add1.getEmail());
				}
				break;

			case 5:
				if (controller == null)
					controller = new StudentController();

				System.out.print("Enter minimum marks: ");
				int marks1 = sc.nextInt();
				studentdb = controller.getStudentsWithMarksGreaterThan(marks1);
				System.out.println("Students with marks greater than " + marks1);
				for (Student s : studentdb) {
					System.out.println(s.getName() + " " + s.getMarks());
				}
				break;

			case 6:
				if (controller == null)
					controller = new StudentController();

				System.out.print("Enter city name: ");
				String searchCity = sc.next();
				studentAddress = controller.getStudentsFromCity(searchCity);
				System.out.println("Student from " + searchCity);
				for(Address a: studentAddress) {
					System.out.println(a.getStudent().getName());
				}
				break;

			case 7:
				if (controller == null)
					controller = new StudentController();

				studentdb = controller.getStudentsWhoOrderedPoha();
				System.out.println("Students who ordered poha: ");
				for (Student s : studentdb) {
					System.out.println(s.getRoll() + " " + s.getName());
				}
				break;

			case 8:
				if (controller == null)
					controller = new StudentController();

				System.out.print("Enter Student roll to delete: ");
				int deleteId = sc.nextInt();
				controller.deleteStudent(deleteId);
				break;

			case 9:
				if (controller == null)
					controller = new StudentController();
				System.out.print("Enter Student ID: ");
				int updateId = sc.nextInt();
				sc.nextLine();
				System.out.print("Enter New City: ");
				String newCity = sc.nextLine();
				System.out.print("Enter New Email: ");
				String newEmail = sc.nextLine();
				Address newAddress = new Address(newCity, newEmail);
				controller.updateStudentAddress(updateId, newAddress);
				break;

			case 10:
				if (controller == null)
					controller = new StudentController();
				System.out.print("Enter Student ID: ");
				int studentId = sc.nextInt();
				sc.nextLine();
				System.out.println("How many orders you want to place: ");
				int count = sc.nextInt();
				sc.nextLine();
				studentOrderdb = new ArrayList<StudentOrder>();
				for (int i = 1; i <= count; i++) {
					System.out.print("Enter Order " + i + " name: ");
					String orderName = sc.nextLine();
					System.out.print("Enter Order price: ");
					int price = sc.nextInt();
					sc.nextLine();
					StudentOrder so = new StudentOrder(orderName, price);
					studentOrderdb.add(so);
				}

				controller.placeOrder(studentId, studentOrderdb);
				break;

			case 11:
				System.out.println("Exiting... Thank you!");
				break;

			default:
				System.out.println("Invalid choice! Please try again.");
			}
		} while (choice != 11);
	}

}
