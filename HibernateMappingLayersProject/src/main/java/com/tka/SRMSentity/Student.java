package com.tka.SRMSentity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student", schema = "advjava202ProjectDb")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roll;
	private String name;
	private int marks;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "a_id")
	private Address addr;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<StudentOrder> orderList;

	public Student() {
		super();
	}

	public Student(String name, int marks) {
		super();
		this.name = name;
		this.marks = marks;
	}

	public Student(String name, int marks, Address addr) {
		super();
		this.name = name;
		this.marks = marks;
		this.setAddr(addr);
	}

	public Student(String name, int marks, Address addr, List<StudentOrder> orderList) {
		super();
		this.name = name;
		this.marks = marks;
		this.addr = addr;
		this.orderList = orderList;
	}

	public int getRoll() {
		return roll;
	}

	public void setRoll(int roll) {
		this.roll = roll;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public Address getAddr() {
		return addr;
	}

	public void setAddr(Address addr) {
		this.addr = addr;
		if (addr != null) {
			addr.setStudent(this);
		}
	}

	public List<StudentOrder> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<StudentOrder> orderList) {
		this.orderList = orderList;
	}

	public void addOrder(StudentOrder order) {
		orderList.add(order);
		order.setStudent(this);
	}

	@Override
	public String toString() {
		return "Student [roll=" + roll + ", name=" + name + "]";
	}

}
