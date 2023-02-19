package jpa_paging.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class StudentIdentity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate verilmeTarihi;
	private LocalDate sonKullanmaTarihi;
	
	@OneToOne(mappedBy="identity")
	private Student student;
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public StudentIdentity() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getVerilmeTarihi() {
		return verilmeTarihi;
	}

	public void setVerilmeTarihi(LocalDate verilmeTarihi) {
		this.verilmeTarihi = verilmeTarihi;
	}

	public LocalDate getSonKullanmaTarihi() {
		return sonKullanmaTarihi;
	}

	public void setSonKullanmaTarihi(LocalDate sonKullanmaTarihi) {
		this.sonKullanmaTarihi = sonKullanmaTarihi;
	}

	public StudentIdentity( LocalDate verilmeTarihi, LocalDate sonKullanmaTarihi) {
		this.verilmeTarihi = verilmeTarihi;
		this.sonKullanmaTarihi = sonKullanmaTarihi;
	}
	
	
	
	
}
