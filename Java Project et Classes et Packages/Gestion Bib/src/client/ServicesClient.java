package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import admin.Admin;
import database.Emprunts;
import database.Livre;

public class ServicesClient extends JFrame implements ActionListener {
	private static JTable table;
	JButton b1, b2, b3, b4, d;

	public ServicesClient() {
		setTitle("Choisir une option");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(1000, 700);
		setLocation(350, 150);
		setResizable(false);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(244, 27, 688, 286);
		contentPane.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "IDLivre", "N\u00B0isbn", "Titre", "Auteur" }));
		b1 = new JButton("Rechercher Livre");
		Image img = new ImageIcon(this.getClass().getResource("/rechercher.png")).getImage();
		initButton(b1, img);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		b1.setBounds(400, 343, 210, 50);
		b1.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(b1);

		b2 = new JButton("Afficher Livre");
		Image img1 = new ImageIcon(this.getClass().getResource("/afficher.png")).getImage();
		initButton(b2, img1);
		b2.setBounds(400, 443, 200, 50);
		b2.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(b2);
		
		
		b3 = new JButton("Emprunter Livre");
		Image img2 = new ImageIcon(this.getClass().getResource("/emprunterlivre.png")).getImage();
		initButton(b3, img2);
		b3.setBounds(400, 543, 210, 50);
		b3.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(b3);
	
		
		b4 = new JButton("Afficher Livres Empruntés");
		Image img4 = new ImageIcon(this.getClass().getResource("/empliv.png")).getImage();
		initButton(b4, img4);
		b4.setBounds(650, 443, 300, 50);
		b4.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(b4);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
		d = new JButton("Deconnexion");
		Image img3 = new ImageIcon(this.getClass().getResource("/Deconnexion.png")).getImage();
		initButton(d, img3);
		d.setBounds(5, 5, 140, 100);
		contentPane.add(d);
		d.addActionListener(this);
	}

	private void initButton(JButton bt, Image image) {
		ImageIcon h;
		bt.setIcon(h = new ImageIcon(image));
		bt.setFocusPainted(false);
		bt.setMargin(null);
		bt.setBorder(BorderFactory.createEmptyBorder());
		bt.setContentAreaFilled(false);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			Livre l = new Livre();
			l.RechercherLivre(table);
		}
		if (e.getSource() == b2) {
			Emprunts em = new Emprunts();
			em.AfficherLivre(table);
		}
		
		if (e.getSource() == b3) {
			Emprunts em1 = new Emprunts();
			em1.Emprunterlivre(table, Client.getTextField());
		}
		if (e.getSource() == b4) {
			Emprunts em = new Emprunts();
			em.AfficherLivreEmprunte(table,Client.getTextField());
		}
		if (e.getSource() == d) {
			dispose();
			Client c = new Client();
			c.setVisible(true);
		}
	}
}
