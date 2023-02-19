package jpa_paging.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class TelefonBilgisi implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length=4)
	private String alanKodu;
	@Column(length=7)
	private String numara;
	@ManyToOne
	private Student student;
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public TelefonBilgisi() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlanKodu() {
		return alanKodu;
	}

	public void setAlanKodu(String alanKodu) {
		this.alanKodu = alanKodu;
	}

	public String getNumara() {
		return numara;
	}

	public void setNumara(String numara) {
		this.numara = numara;
	}

	public TelefonBilgisi(String alanKodu, String numara) {


		this.alanKodu = alanKodu;
		this.numara = numara;
	}
	
	

}
