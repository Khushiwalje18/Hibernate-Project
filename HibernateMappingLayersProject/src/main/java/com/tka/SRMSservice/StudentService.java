package com.tka.SRMSservice;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tka.SRMSdao.StudentDao;
import com.tka.SRMSentity.Address;
import com.tka.SRMSentity.Student;
import com.tka.SRMSentity.StudentOrder;

public class StudentService {
	StudentDao dao = null;

	public List<Student> getAllStudents() {
		dao = new StudentDao();
		List<Student> studentdb = dao.getAllStudents();
		return studentdb;
	}

	public void addNewStudent(String name, int marks, Address add) {
		dao = new StudentDao();
		dao.addNewStudent(name, marks, add);

	}

	public List<StudentOrder> getAllOrders() {
		dao = new StudentDao();
		List<StudentOrder> studentOrderdb = dao.getAllOrders();
		return studentOrderdb;
	}

	public List<Address> getStudentAddress(int roll) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Student.class);
		cfg.addAnnotatedClass(Address.class);
		cfg.addAnnotatedClass(StudentOrder.class);
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria cri = session.createCriteria(Address.class).add(Restrictions.eq("student.roll", roll));
		List<Address> studentAddress = cri.list();
		transaction.commit();
		session.close();
		return studentAddress;
	}

	public List<Student> getStudentsWithMarksGreaterThan(int marks1) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Student.class);
		cfg.addAnnotatedClass(Address.class);
		cfg.addAnnotatedClass(StudentOrder.class);
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria cri = session.createCriteria(Student.class).add(Restrictions.gt("marks", marks1));
		List<Student> studentdb = cri.list();
		transaction.commit();
		session.close();
		return studentdb;
	}

	public List<Address> getStudentsFromCity(String searchCity) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Student.class);
		cfg.addAnnotatedClass(Address.class);
		cfg.addAnnotatedClass(StudentOrder.class);
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria cri = session.createCriteria(Student.class).add(Restrictions.eq("city", searchCity));
		List<Address> studentdb = cri.list();
		transaction.commit();
		session.close();
		return studentdb;
	}

	public List<Student> getStudentsWhoOrderedPoha() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Student.class);
		cfg.addAnnotatedClass(Address.class);
		cfg.addAnnotatedClass(StudentOrder.class);
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();

		Criteria cri = session.createCriteria(StudentOrder.class).add(Restrictions.eq("orderName", "Poha"))
				.setProjection(Projections.property("student"));

		List<Student> studentdb = cri.list();
		transaction.commit();
		session.close();
		return studentdb;
	}

	public void deleteStudent(int deleteId) {
		dao = new StudentDao();
		dao.deleteStudent(deleteId);
	}

	public void updateStudentAddress(int updateId, Address newAddress) {
		dao = new StudentDao();
		dao.updateStudentAddress(updateId, newAddress);
	}

	public void placeOrder(int studentId, List<StudentOrder> solist) {
		dao = new StudentDao();
		dao.placeOrder(studentId, solist);
	}

}
