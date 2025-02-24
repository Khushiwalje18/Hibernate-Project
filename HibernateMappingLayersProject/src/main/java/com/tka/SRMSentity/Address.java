package com.tka.SRMSentity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address", schema = "advjava202ProjectDb")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int a_id;
	private String city;
	private String email;

	@OneToOne(mappedBy = "addr", cascade= CascadeType.ALL)
	@JoinColumn(name="student_roll")
	private Student student;

	public Address() {
		super();
	}

	public Address(String city, String email) {
		super();
		this.city = city;
		this.email = email;
	}

	public int getA_id() {
		return a_id;
	}

	public void setA_id(int a_id) {
		this.a_id = a_id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Address [a_id=" + a_id + ", city=" + city + ", email=" + email + "]";
	}

}
