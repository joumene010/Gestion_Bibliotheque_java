package admin;

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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import client.Client;

public class GestionUsagers extends JFrame implements ActionListener {
	private static JTable table_1;
	private static JTextField textField;
	private static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextField textField_3;
	private static JTextField textField_4;

	public static JTable getTable_1() {
		return table_1;
	}

	public static JTextField getTextField() {
		return textField;
	}

	public static JTextField getTextField_1() {
		return textField_1;
	}

	public static JTextField getTextField_2() {
		return textField_2;
	}

	public static JTextField getTextField_3() {
		return textField_3;
	}

	public static JTextField getTextField_4() {
		return textField_4;
	}

	JButton b1, b2, b3, b4, b5, b6, b7, retour, d;

	public GestionUsagers() {
		setLocation(350, 150);
		setTitle("Usagers");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 942, 527);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setVisible(true);
		setSize(1000, 700);
		setLocation(350, 150);
		contentPane.setBackground(new Color(224, 255, 255));
		b1 = new JButton("Afficher Client");
		Image img = new ImageIcon(this.getClass().getResource("/aff.png")).getImage();
		initButton(b1, img);

		b1.setForeground(Color.BLACK);
		b1.setFont(new Font("Tahoma", Font.BOLD, 14));

		b1.setBounds(324, 435, 210, 50);
		contentPane.add(b1);

		retour = new JButton("Précédent");
		Image img1 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		initButton(retour, img1);
		retour.setForeground(Color.BLACK);
		retour.setFont(new Font("Tahoma", Font.BOLD, 14));
		retour.setBounds(10, 550, 180, 64);
		add(retour);
		// Create the "supprimer Client" button
		b2 = new JButton("Supprimer Client");
		Image img2 = new ImageIcon(this.getClass().getResource("/supp.png")).getImage();
		initButton(b2, img2);
		b2.setForeground(Color.BLACK);
		b2.setBackground(Color.RED);
		b2.setFont(new Font("Tahoma", Font.BOLD, 14));
		b2.setBounds(603, 435, 250, 50);
		contentPane.add(b2);
		// Create the "rechercher Client" button
		b3 = new JButton("Rechercher Client");
		Image img3 = new ImageIcon(this.getClass().getResource("/rech.png")).getImage();
		initButton(b3, img3);
		b3.setForeground(Color.black);
		b3.setFont(new Font("Tahoma", Font.BOLD, 14));
		b3.setBounds(10, 183, 250, 50);
		contentPane.add(b3);
		JTable table = new javax.swing.JTable();
		table.setBounds(789, 74, -432, 326);
		contentPane.add(table);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(269, 27, 591, 334);
		contentPane.add(scrollPane);

		table_1 = new javax.swing.JTable();
		scrollPane.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Num_abonne", "Nom", "Prenom", "mode", "Email", "Statut" }));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(126);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(110);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(144);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(200);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(120);
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(843, 82, 17, 318);
		contentPane.add(scrollBar);
		d = new JButton("Déconnexion");
		Image img4 = new ImageIcon(this.getClass().getResource("/Deconnexion.png")).getImage();
		initButton(d, img4);
		d.setForeground(Color.BLACK);
		d.setFont(new Font("Tahoma", Font.BOLD, 10));
		d.setBounds(850, 10, 140, 64);
		add(d);
		d.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		retour.addActionListener(this);

	}

	private void initButton(JButton bt, Image image) {
		ImageIcon h;
		bt.setIcon(h = new ImageIcon(image));
		bt.setFocusPainted(false);
//	   		    bt.setMargin(null);           
		bt.setBorder(BorderFactory.createEmptyBorder());
		bt.setContentAreaFilled(false);

	}

	public void actionPerformed(ActionEvent e) {
		int num_ab = 0;
		if (e.getSource() == b1) {
			Client c = new Client(num_ab);
			c.AfficherClient(table_1);
		}
		if (e.getSource() == b2) {
			Client c = new Client(num_ab);
			c.SupprimerClient(table_1);
		}
		if (e.getSource() == b3) {

			Client c = new Client(num_ab);
			c.rechercherClient(table_1);
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
