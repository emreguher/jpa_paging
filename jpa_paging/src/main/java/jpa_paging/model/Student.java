package jpa_paging.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Student implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int okulNo;
	private String ad;
	private String soyad;
	private Cinsiyet cinsiyet;
	private boolean mezun;
	public StudentIdentity getIdentity() {
		return identity;
	}

	public void setIdentity(StudentIdentity identity) {
		this.identity = identity;
	}

	@OneToOne(cascade = CascadeType.ALL)
	private StudentIdentity identity;
	
	@OneToMany(mappedBy="student", cascade = CascadeType.ALL)
	private List<TelefonBilgisi> telefonlari;
	
	public List<TelefonBilgisi> getTelefonlari() {
		return telefonlari;
	}

	public void setTelefonlari(List<TelefonBilgisi> telefonlari) {
		this.telefonlari = telefonlari;
	}

	public Student() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOkulNo() {
		return okulNo;
	}

	public void setOkulNo(int okulNo) {
		this.okulNo = okulNo;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public Student( int okulNo, String ad, String soyad, Cinsiyet cinsiyet, boolean mezun) {
		this.okulNo = okulNo;
		this.ad = ad;
		this.soyad = soyad;
		this.cinsiyet = cinsiyet;
		this.mezun = mezun;
	}

	public Cinsiyet getCinsiyet() {
		return cinsiyet;
	}

	public void setCinsiyet(Cinsiyet cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

	public boolean isMezun() {
		return mezun;
	}

	public void setMezun(boolean mezun) {
		this.mezun = mezun;
	}

}
