import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.service.spi.Manageable;

import jpa_paging.model.Cinsiyet;
import jpa_paging.model.Student;
import jpa_paging.model.StudentIdentity;
import jpa_paging.model.TelefonBilgisi;

public class Test {

	public static void main(String[] args) {
		
		EntityManagerFactory f = 
				Persistence.createEntityManagerFactory("persistenceUnitName");
		
		EntityManager m = f.createEntityManager();
		
		EntityTransaction t = m.getTransaction();
		
		Student s = new Student(111,"SSS", "AAAA", Cinsiyet.ERKEK, false);
		StudentIdentity i = new StudentIdentity(LocalDate.now(), LocalDate.now());
		
		s.setIdentity(i);
		
		TelefonBilgisi t1 = new TelefonBilgisi("0532","1111111");
		TelefonBilgisi t2 = new TelefonBilgisi("0535","5555555");
		
		List<TelefonBilgisi> telefonlar = new 
				ArrayList<>();
		telefonlar.add(t1);
		telefonlar.add(t2);
		
		s.setTelefonlari(telefonlar);
		
		//Student s = m.find(Student.class, 13);
		
		t.begin();
		//m.remove(s);
		m.persist(s);
		t.commit();
		
		
	}

}
