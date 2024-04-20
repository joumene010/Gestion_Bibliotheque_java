package admin;

import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
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
import database.Emprunts;

public class GestionEmprunt extends JFrame implements ActionListener {
	JButton b1, b, b2, retour, d;
	private static JTable table;

	public GestionEmprunt() {
		JPanel contentPane = new JPanel();
		setTitle("Emprunts");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 836, 447);
		setSize(1000, 700);
		setLocation(350, 150);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		b = new JButton("Consulter l'historique des emprunts");
		Image img = new ImageIcon(this.getClass().getResource("/hist.png")).getImage();
		initButton(b, img);
		b.setForeground(Color.black);
		b.setFont(new Font("Tahoma", Font.BOLD, 14));
		b.setBounds(640, 140, 350, 80);
		contentPane.add(b);
		retour = new JButton("Précédent");
		Image img1 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		initButton(retour, img1);
		retour.setForeground(Color.BLACK);
		retour.setFont(new Font("Tahoma", Font.BOLD, 14));
		retour.setBounds(10, 590, 180, 64);
		add(retour);

		b1 = new JButton("Consulter les emprunts en cours");
		Image img2 = new ImageIcon(this.getClass().getResource("/encours.png")).getImage();
		initButton(b1, img2);
		b1.setForeground(Color.black);
		b1.setFont(new Font("Tahoma", Font.BOLD, 14));
		b1.setBounds(640, 480, 350, 80);
		contentPane.add(b1);
		b2 = new JButton("Rendre Livre");
		Image img3 = new ImageIcon(this.getClass().getResource("/rendre.png")).getImage();
		initButton(b2, img3);
		b2.setForeground(Color.black);
		b2.setFont(new Font("Tahoma", Font.BOLD, 14));
		b2.setBounds(40, 400, 350, 80);
		contentPane.add(b2);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 10, 591, 323);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID Livre", "ID exemplaire", "Date Emprunt", "Date Retour", "Num_Abonne" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(108);
		table.getColumnModel().getColumn(2).setPreferredWidth(96);
		table.getColumnModel().getColumn(3).setPreferredWidth(96);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		setVisible(true);
		b.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		retour.addActionListener(this);
		d = new JButton("Déconnexion");
		Image img4 = new ImageIcon(this.getClass().getResource("/Deconnexion.png")).getImage();
		initButton(d, img4);
		d.setForeground(Color.BLACK);
		d.setFont(new Font("Tahoma", Font.BOLD, 10));
		d.setBounds(850, 10, 140, 64);
		add(d);
		d.addActionListener(this);

	}

	private void initButton(JButton bt, String image) {
		ImageIcon h;
		bt.setIcon(h = new ImageIcon(image));
		bt.setFocusPainted(false);
		bt.setMargin(null);
		bt.setBorder(BorderFactory.createEmptyBorder());
		bt.setContentAreaFilled(false);
	}

	private void initButton(JButton bt, Image image) {
		ImageIcon h;
		bt.setIcon(h = new ImageIcon(image));
		bt.setFocusPainted(false);
		bt.setMargin(null);
		bt.setBorder(BorderFactory.createEmptyBorder());
		bt.setContentAreaFilled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			Emprunts em = new Emprunts();
			em.consulterEmpruntsEnCours(table);
		}
		if (e.getSource() == b) {
			Emprunts em = new Emprunts();
			em.consulterHistoriqueEmprunts(table);
		}
		if (e.getSource() == b2) {
			Emprunts em = new Emprunts();
			em.rendreLivre(table);
		}
		if (e.getSource() == retour) {
			dispose();
			Admin.getFrame().setVisible(true);
		}
		if (e.getSource() == d) {
			dispose();
			Admin a = new Admin();
			a.setVisible(true);
		}
	}
}
