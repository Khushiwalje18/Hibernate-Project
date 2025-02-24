package com.tka.SRMSdao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.tka.SRMSentity.Address;
import com.tka.SRMSentity.Student;
import com.tka.SRMSentity.StudentOrder;

public class StudentDao {

	public List<Student> getAllStudents() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Student.class);
		cfg.addAnnotatedClass(Address.class);
		cfg.addAnnotatedClass(StudentOrder.class);
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria cri = session.createCriteria(Student.class);
		List<Student> studentdb = cri.list();
		transaction.commit();
		session.close();
		return studentdb;
	}

	public void addNewStudent(String name, int marks, Address add) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Student.class);
		cfg.addAnnotatedClass(Address.class);
		cfg.addAnnotatedClass(StudentOrder.class);
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Student s = new Student(name, marks, add);
		session.save(s);
		transaction.commit();
		session.close();
	}

	public List<StudentOrder> getAllOrders() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Student.class);
		cfg.addAnnotatedClass(Address.class);
		cfg.addAnnotatedClass(StudentOrder.class);
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria cri = session.createCriteria(StudentOrder.class);
		List<StudentOrder> studentOrderdb = cri.list();
		transaction.commit();
		session.close();
		return studentOrderdb;
	}

	public void deleteStudent(int deleteId) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Student.class);
		cfg.addAnnotatedClass(Address.class);
		cfg.addAnnotatedClass(StudentOrder.class);
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria cri = session.createCriteria(Student.class).add(Restrictions.eq("roll", deleteId));
		Student s = (Student) cri.uniqueResult();
		if (s != null) {
			session.delete(s);
			System.out.println("Student record deleted successfully");

		} else {
			System.out.println("student not found");
		}
		transaction.commit();
		session.close();
	}

	public void updateStudentAddress(int updateId, Address newAddress) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Student.class);
		cfg.addAnnotatedClass(Address.class);
		cfg.addAnnotatedClass(StudentOrder.class);
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Student s = session.get(Student.class, updateId);
		if (s != null) {
			s.setAddr(newAddress);
			System.out.println("Address updated successfully");
		} else {
			System.out.println("Student not found");
		}
		transaction.commit();
		session.close();
	}

	public void placeOrder(int studentId, List<StudentOrder> solist) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Student.class);
		cfg.addAnnotatedClass(Address.class);
		cfg.addAnnotatedClass(StudentOrder.class);
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Student s = session.get(Student.class, studentId);
		if (s != null) {

			for (StudentOrder order : solist) {
				order.setStudent(s);
				session.save(order);

			}
			session.save(s);
			transaction.commit();

			System.out.println("Order placed successfully");
		} else {
			System.out.println("Student not found with ID: " + studentId);
		}
	}
}
