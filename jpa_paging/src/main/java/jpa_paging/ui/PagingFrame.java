package jpa_paging.ui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import jpa_paging.model.Student;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PagingFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistenceUnitName");

	private EntityManager manager = factory.createEntityManager();
	
	private long count = 0;
	private int pageIndex = 0;
	private int maxResult= 3;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PagingFrame frame = new PagingFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	List<Student> ogrenciListesi;
	
	private void ogrenciListele() {
		
		Query countStudents = manager.createQuery("SELECT count(s) FROM Student s");
		
		count = (Long)countStudents.getSingleResult();
		
		Query query = manager.createQuery("SELECT s FROM Student s"); // JPQL
		
		query.setMaxResults(maxResult);
		query.setFirstResult(pageIndex);
		
		ogrenciListesi = query.getResultList();

		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("Id");
		model.addColumn("Ad");
		model.addColumn("Soyad");
		model.addColumn("OkulNo");
		model.addColumn("Cinsiyet");
		model.addColumn("Mezun");

		Object[] row = new Object[6];

		int size = ogrenciListesi.size();

		for (int i = 0; i < size; i++) {
			row[0] = ogrenciListesi.get(i).getId();
			row[1] = ogrenciListesi.get(i).getAd();
			row[2] = ogrenciListesi.get(i).getSoyad();
			row[3] = ogrenciListesi.get(i).getOkulNo();
			row[4] = ogrenciListesi.get(i).getCinsiyet();
			row[5] = ogrenciListesi.get(i).isMezun();
			

			model.addRow(row);
		}

		table.setModel(model);
	}
	/**
	 * Create the frame.
	 */
	public PagingFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 170);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setBounds(0, 0, 1, 1);
		scrollPane.setViewportView(table);
		
		JButton btnGeri = new JButton("< Geri");
		btnGeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageIndex -= maxResult;
				
				if(pageIndex < 0) {
					JOptionPane.showMessageDialog(btnGeri,
							"En başa geldiniz.");
					pageIndex += maxResult;
					
					return;
				}
				ogrenciListele();
			}
		});
		btnGeri.setBounds(109, 204, 89, 23);
		contentPane.add(btnGeri);
		
		JButton btnIleri = new JButton("İleri >");
		btnIleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageIndex += maxResult;
				
				if(pageIndex >= count) {
					JOptionPane.showMessageDialog(btnGeri,
							"En sona geldiniz.");
					pageIndex-= maxResult;
					return;
				}
				
				ogrenciListele();
			}
		});
		btnIleri.setBounds(234, 204, 89, 23);
		contentPane.add(btnIleri);
		
		ogrenciListele();
	}
}
