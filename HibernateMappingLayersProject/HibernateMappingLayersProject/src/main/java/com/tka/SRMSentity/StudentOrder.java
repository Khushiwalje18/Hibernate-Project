package com.tka.SRMSentity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "studentOrder", schema = "advjava202ProjectDb")
public class StudentOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	private String orderName;
	private int price;

	@ManyToOne
	@JoinColumn(name = "roll", referencedColumnName = "roll", nullable = false)
	private Student student;

	public StudentOrder() {
		super();
	}

	public StudentOrder(String orderName, int price) {
		super();
		this.orderName = orderName;
		this.price = price;
	}

	public StudentOrder(String orderName, int price, Student student) {
		super();
		this.orderName = orderName;
		this.price = price;
		this.setStudent(student);
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrdername() {
		return orderName;
	}

	public void setOrdername(String orderName) {
		this.orderName = orderName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
		if (student != null && !student.getOrderList().contains(this)) {
			student.getOrderList().add(this);
		}
	}

	@Override
	public String toString() {
		return "StudentOrder [orderId=" + orderId + ", Ordername=" + orderName + ", student roll no =" + student + "]";
	}

}
