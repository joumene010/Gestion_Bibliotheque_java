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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.Exemplaire;
import database.Livre;

public class GestionLivre extends JFrame implements ActionListener {
	private static JTable table_1;
	private static JTextField textField;
	private static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextField textField_3;
	private static JTextField textField_4;
	private static JTextField textField_5;

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

	JButton retour, b1, b2, b3, b4, b5, b6, b7, d;

	public GestionLivre() {
		setTitle("Livre");
		setLocation(350, 150);
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
		b1 = new JButton("Afficher Livre");
		Image img = new ImageIcon(this.getClass().getResource("/aff.png")).getImage();
		initButton(b1, img);
		b1.setForeground(Color.BLACK);
		b1.setFont(new Font("Tahoma", Font.BOLD, 16));
		b1.setBounds(480, 371, 250, 50);
		contentPane.add(b1);
		b2 = new JButton("Rechercher Livre");
		Image img1 = new ImageIcon(this.getClass().getResource("/rech.png")).getImage();
		initButton(b2, img1);
		b2.setForeground(Color.black);
		b2.setFont(new Font("Tahoma", Font.BOLD, 16));

		b2.setBounds(324, 435, 250, 50);
		contentPane.add(b2);
		retour = new JButton("Précédent");
		Image img3 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		initButton(retour, img3);
		retour.setForeground(Color.BLACK);
		retour.setFont(new Font("Tahoma", Font.BOLD, 16));
		retour.setBounds(10, 590, 250, 64);
		add(retour);
		b3 = new JButton("Modifier Livre");
		Image img4 = new ImageIcon(this.getClass().getResource("/mod.png")).getImage();
		initButton(b3, img4);
		b3.setForeground(Color.BLACK);
		b3.setFont(new Font("Tahoma", Font.BOLD, 16));
		b3.setBounds(10, 500, 250, 50);
		contentPane.add(b3);

		b4 = new JButton("Supprimer Livre");
		Image img5 = new ImageIcon(this.getClass().getResource("/supp.png")).getImage();
		initButton(b4, img5);
		b4.setForeground(Color.BLACK);
		b4.setBackground(Color.RED);
		b4.setFont(new Font("Tahoma", Font.BOLD, 16));
		b4.setBounds(603, 435, 250, 50);
		contentPane.add(b4);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(269, 27, 591, 334);
		contentPane.add(scrollPane);

		table_1 = new javax.swing.JTable();
		scrollPane.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID Livre", "Titre", "Code isbn", "Auteur", "Quantite" }));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(123);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(126);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(110);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(144);

		JLabel lblNewLabel = new JLabel("ID Livre");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 10, 99, 13);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(10, 46, 200, 35);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Titre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setBounds(10, 98, 45, 13);
		contentPane.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 130, 200, 35);
		contentPane.add(textField_1);

		JLabel lblNewLabel_1_1 = new JLabel("Code isbn");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setForeground(Color.BLUE);
		lblNewLabel_1_1.setBounds(10, 183, 82, 13);
		contentPane.add(lblNewLabel_1_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(10, 218, 200, 35);
		contentPane.add(textField_2);

		JLabel lblNewLabel_2 = new JLabel("Auteur");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 270, 82, 13);
		contentPane.add(lblNewLabel_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(10, 307, 200, 35);
		contentPane.add(textField_3);

		JLabel lblNewLabel_2_1 = new JLabel("Quantite");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1.setForeground(Color.BLUE);
		lblNewLabel_2_1.setBounds(10, 359, 104, 13);
		contentPane.add(lblNewLabel_2_1);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(10, 385, 200, 35);
		contentPane.add(textField_4);
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(450, 550, 200, 35);
		contentPane.add(textField_5);
		b5 = new JButton("Ajouter Livre");
		Image img6 = new ImageIcon(this.getClass().getResource("/ajouter.png")).getImage();
		initButton(b5, img6);
		b5.setForeground(Color.BLACK);
		b5.setFont(new Font("Tahoma", Font.BOLD, 14));
		b5.setBackground(Color.RED);
		b5.setBounds(10, 435, 193, 50);
		contentPane.add(b5);
		b6 = new JButton("Ajouter Exemplaire");
		Image img7 = new ImageIcon(this.getClass().getResource("/plus.png")).getImage();
		initButton(b6, img7);
		b6.setForeground(Color.BLACK);
		b6.setFont(new Font("Tahoma", Font.BOLD, 16));
		b6.setBackground(Color.RED);
		b6.setBounds(634, 515, 300, 50);
		contentPane.add(b6);
		d = new JButton("Déconnexion");
		Image img8 = new ImageIcon(this.getClass().getResource("/Deconnexion.png")).getImage();
		initButton(d, img8);
		d.setForeground(Color.BLACK);
		d.setFont(new Font("Tahoma", Font.BOLD, 10));
		d.setBounds(850, 10, 140, 64);
		contentPane.add(d);
		b7 = new JButton("Supprimer Exemplaire");
		Image img9 = new ImageIcon(this.getClass().getResource("/moins.png")).getImage();
		initButton(b7, img9);
		b7.setForeground(Color.BLACK);
		b7.setFont(new Font("Tahoma", Font.BOLD, 16));
		b7.setBackground(Color.RED);
		b7.setBounds(646, 570, 300, 50);
		contentPane.add(b7);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		retour.addActionListener(this);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			Livre l = new Livre();
			l.AfficherLivre(table_1);
		}
		if (e.getSource() == b2) {
			Livre l = new Livre();
			l.RechercherLivre(table_1);
		}
		if (e.getSource() == b3) {
			Livre l = new Livre();
			l.ModifierLivre(textField, textField_1, textField_2, textField_3, textField_4, table_1);
		}
		if (e.getSource() == b4) {
			Livre l = new Livre();
			l.SupprimerLivre(table_1);
		}
		if (e.getSource() == b5) {
			Livre l = new Livre();
			l.AjouterLivre(textField, textField_1, textField_2, textField_3, textField_4, table_1);
		}
		if (e.getSource() == b6) {
			Exemplaire ex = new Exemplaire();
			ex.AjouterExemplaire(textField_5, table_1);
		}
		if (e.getSource() == b7) {
			Exemplaire ex = new Exemplaire();
			ex.SupprimerExemplaire(textField_5, table_1);
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
