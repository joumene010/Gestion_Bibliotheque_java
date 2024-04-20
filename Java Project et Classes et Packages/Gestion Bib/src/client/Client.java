package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Statement;

import admin.GestionUsagers;
import database.DataBase;
import firstInterface.FenetreLogin;
import net.proteanit.sql.DbUtils;

import java.sql.*;

public class Client extends JFrame implements ActionListener {
	private static JTextField textField;
	private static JTextField textField1;
	JButton b3, b4, retour;
	private static JFrame frame;

	public static JTextField getTextField() {
		return textField;
	}

	public static JTextField getTextField1() {
		return textField1;
	}

	public static JFrame getframe() {
		return frame;
	}

	Image img = new ImageIcon(this.getClass().getResource("/check.png")).getImage();
	Image img1 = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
	ImageIcon icon = new ImageIcon(img);
	ImageIcon icon1 = new ImageIcon(img1);
	private int id_ab;

	public Client(int id_ab) {
	}

	public Client() {
		setTitle("Authentification");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);
		Image img = new ImageIcon(this.getClass().getResource("/fenetre.jpg")).getImage();
		setContentPane(new JLabel(new ImageIcon(img)));
		this.setSize(960, 686);
		setLayout(new FlowLayout());
		JLabel label = new JLabel("E-mail");
		JLabel label1 = new JLabel("Password");
		textField = new JTextField();
		textField1 = new JPasswordField();
		retour = new JButton("Précédent");
		Image img10 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		initButton(retour, img10);
		retour.setForeground(Color.black);
		retour.setFont(new Font("Tahoma", Font.BOLD, 14));
		retour.setBounds(10, 30, 180, 64);
		add(retour);
		b3 = new JButton("Valider");
		b4 = new JButton("S'inscrire");
		JLabel l2 = new JLabel("Vous n'avez pas de compte ?");
		JLabel l3 = new JLabel("Créez en-un !");
		l2.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 20));
		l3.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 20));
		setLayout(null);
		add(label);
		label.setBounds(320, 130, 300, 30);
		add(textField);
		Color c = new Color(6, 141, 169);
		label.setForeground(c);
		label.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 20));
		label1.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 20));

		b3.setBackground(Color.PINK);
		b3.setForeground(Color.BLACK);
		textField.setBounds(320, 170, 150, 25);
		add(label1);
		label1.setBounds(320, 200, 300, 30);
		label1.setForeground(c);
		add(textField1);
		textField1.setBounds(320, 240, 150, 25);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(b3);
		b3.setBounds(325, 280, 200, 80);
		setVisible(true);
		Color M = new Color(126, 23, 23);
		b4.setBackground(M);
		b4.setForeground(Color.white);
		add(b4);
		add(l2);
		setLocationRelativeTo(null);
		add(l3);
		Color Y = new Color(229, 88, 7);
		
		l2.setBounds(150, 520, 500, 20);
		l3.setBounds(150, 540, 300, 20);
		Color x = new Color(213, 189, 61);
		l3.setForeground(Y);
		l2.setForeground(Y);
		setLocation(350, 150);
		b4.setBounds(400, 570, 100, 40);
		Image img1 = new ImageIcon(this.getClass().getResource("/val.png")).getImage();
		initButton(b3, img1);
		b4.addActionListener(this);
		b3.addActionListener(this);
		retour.addActionListener(this);
	}

	DataBase cn = new DataBase();

	private void initButton(JButton bt, Image image) {
		ImageIcon h;
		bt.setIcon(h = new ImageIcon(image));
		bt.setFocusPainted(false);
		bt.setMargin(null);
		bt.setBorder(BorderFactory.createEmptyBorder());
		bt.setContentAreaFilled(false);

	}

	public void AfficherClient(JTable table) {
		DataBase cn = new DataBase();
		try {
			String sql = "select * from client";
			PreparedStatement stmt = cn.connecion().prepareStatement(sql);
			ResultSet rs;
			rs = stmt.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e1) {
			System.out.println(e1);
		}
	}

	public void SupprimerClient(JTable table) {
		try {

			if (table.getSelectedRowCount() == 1) {
				int row = table.getSelectedRow();
				System.out.println(row);
				String cell = table.getValueAt(row, 0).toString();
				String sql = "delete from client where Num_Abonne=" + cell;
				Statement stmt = (Statement) cn.connecion().createStatement();
				stmt.executeUpdate(sql);
				sql = "select * from client";
				PreparedStatement stmt1 = cn.connecion().prepareStatement(sql);
				ResultSet rs;
				rs = stmt1.executeQuery(sql);
				table.setModel(DbUtils.resultSetToTableModel(rs));

				JOptionPane.showMessageDialog(null, "Suppression Realisée avec succés", "Succés",
						JOptionPane.PLAIN_MESSAGE, icon);
			} else if (table.getSelectedRowCount() == 0) {

				JOptionPane.showMessageDialog(null, "Vous devez séléctionner une ligne a supprimer", "erreur",
						JOptionPane.PLAIN_MESSAGE, icon1);
			} else {

				JOptionPane.showMessageDialog(null, "Suppression multiple impossible", "erreur",
						JOptionPane.PLAIN_MESSAGE, icon1);
			}
		} catch (Exception e1) {

			JOptionPane.showMessageDialog(null, "Suppression impossible", "erreur", JOptionPane.PLAIN_MESSAGE, icon1);
		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b3) {
			DataBase cn = new DataBase();
			String sql = "select * from client where (Email='" + textField.getText() + "' and motdepasse='"
					+ textField1.getText() + "');";
			try {
				Statement stmt = (Statement) cn.connecion().createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {
					Image img = new ImageIcon(this.getClass().getResource("/check.png")).getImage();
					ImageIcon icon = new ImageIcon(img);
					JOptionPane.showMessageDialog(null, "connection Réalisée avec succés", "succes",
							JOptionPane.PLAIN_MESSAGE, icon);
					this.dispose();
					ServicesClient s = new ServicesClient();
				} else {
					Image img = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
					ImageIcon icon = new ImageIcon(img);
					JOptionPane.showMessageDialog(null, "email ou mot de passe invalide ", "error",
							JOptionPane.PLAIN_MESSAGE, icon);
				}
			} catch (Exception e1) {
				System.out.println(e1);
			}
		}
		if (e.getSource() == b4) {
			this.dispose();
			SignInClient s1 = new SignInClient();
		}
		if (e.getSource() == retour) {
			this.dispose();
			FenetreLogin f1 = new FenetreLogin();
			f1.setVisible(true);
		}
	}

	public void rechercherClient(JTable table) {
		JFrame frame = new JFrame("Recherche");

		frame.setVisible(true);
		JComboBox comboBox, comboBox_1, comboBox_2, comboBox_3;
		comboBox_1 = new JComboBox();
		comboBox_2 = new JComboBox();
		comboBox_3 = new JComboBox();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 862, 498);
		frame.setLocation(350, 150);
		frame.setSize(960, 686);
		frame.setResizable(false);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		frame.setContentPane(contentPane);
		retour = new JButton("Précédent");
		Image img = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		initButton(retour, img);
		retour.setForeground(Color.BLACK);
		retour.setFont(new Font("Tahoma", Font.BOLD, 14));
		retour.setBounds(10, 550, 180, 64);
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				if (e1.getSource() == retour) {
					dispose();
					GestionUsagers b = new GestionUsagers();
				}
			}
		});

		frame.add(retour);

		comboBox = new JComboBox();
		String ID = "select num_abonne from client";

		try {

			Statement st = (Statement) cn.connecion().createStatement();
			ResultSet rst = st.executeQuery(ID);
			while (rst.next()) {
				comboBox.addItem(rst.getString("Num_Abonne"));
			}

		} catch (Exception ex) {
		}

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_1.setEnabled(false);
				comboBox_2.setEnabled(false);
				comboBox_3.setEnabled(false);

			}
		});
		comboBox.setBounds(21, 213, 133, 33);
		contentPane.add(comboBox);
		String titre = "select Nom from client";

		try {

			Statement st = (Statement) cn.connecion().createStatement();
			ResultSet rst = st.executeQuery(titre);
			while (rst.next()) {
				comboBox_1.addItem(rst.getString("Nom"));
			}
		} catch (Exception ex) {
		}

		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setEnabled(false);
				comboBox_3.setEnabled(false);
			}
		});

		comboBox_1.setBounds(214, 213, 133, 33);
		contentPane.add(comboBox_1);
		contentPane.setBackground(new Color(217, 237, 253));
		comboBox_2.setBounds(417, 213, 133, 33);
		contentPane.add(comboBox_2);
		String Prenom = "select Prenom from client";
		try {
			Statement st = (Statement) cn.connecion().createStatement();
			ResultSet rst = st.executeQuery(Prenom);
			while (rst.next()) {
				comboBox_2.addItem(rst.getString("Prenom"));
			}
		} catch (Exception ex) {
		}
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setEnabled(false);
				comboBox_3.setEnabled(false);
			}
		});

		JLabel lblNewLabel = new JLabel("Recherche");
		Color c = new Color(42, 110, 143);
		lblNewLabel.setForeground(c);
		lblNewLabel.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 50));
		lblNewLabel.setBounds(344, 32, 300, 50);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Par Num_Abonne");
		lblNewLabel_1.setForeground(new Color(0, 156, 109));
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(21, 153, 200, 33);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Par Nom");
		lblNewLabel_2.setForeground(new Color(0, 156, 109));
		lblNewLabel_2.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(214, 153, 150, 33);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Par Prenom");
		lblNewLabel_3.setForeground(new Color(0, 156, 109));
		lblNewLabel_3.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(417, 153, 150, 33);
		contentPane.add(lblNewLabel_3);

		comboBox_3.setBounds(634, 213, 133, 33);
		contentPane.add(comboBox_3);
		comboBox_3.addItem("Etudiant");
		comboBox_3.addItem("Enseignant");
		comboBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_1.setEnabled(false);
				comboBox_2.setEnabled(false);
				comboBox.setEnabled(false);
			}
		});

		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setEnabled(false);
				comboBox_3.setEnabled(false);
			}
		});

		JLabel lblNewLabel_3_1 = new JLabel("Par Statut");
		lblNewLabel_3_1.setForeground(new Color(0, 156, 109));
		lblNewLabel_3_1.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNewLabel_3_1.setBounds(634, 153, 100, 33);
		contentPane.add(lblNewLabel_3_1);

		JButton btnNewButton = new JButton("Rechercher");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBackground(new Color(243, 213, 81));
		btnNewButton.setForeground(Color.black);
		btnNewButton.setBounds(345, 344, 118, 41);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.isEnabled() == true) {
					try {
						String sql = "select * from client where Num_Abonne=" + comboBox.getSelectedItem();

						Statement st = (Statement) cn.connecion().createStatement();
						ResultSet rst = st.executeQuery(sql);
						frame.dispose();
						table.setModel(DbUtils.resultSetToTableModel(rst));
						JOptionPane.showMessageDialog(null, "Recherche réalisée avec succés", "Succes",
								JOptionPane.PLAIN_MESSAGE, icon);
						table.setVisible(true);
					} catch (Exception ex) {
					}

				}
				if (comboBox_1.isEnabled() == true) {
					try {
						String sql = "select * from client where nom='" + comboBox_1.getSelectedItem()
								+ "' and prenom='" + comboBox_2.getSelectedItem() + "'";
						Statement st = (Statement) cn.connecion().createStatement();
						ResultSet rst = st.executeQuery(sql);
						if (rst.next()) {
							rst.first();
							rst = st.executeQuery(sql);
							table.setModel(DbUtils.resultSetToTableModel(rst));
							frame.dispose();
							JOptionPane.showMessageDialog(null, "Recherche réalisée avec succés", "Succes",
									JOptionPane.PLAIN_MESSAGE, icon);
							table.setVisible(true);
						} else
							JOptionPane.showMessageDialog(null, "Echec de la recherche", "Succes",
									JOptionPane.PLAIN_MESSAGE, icon1);
					} catch (Exception ex) {
						System.out.println(ex);
					}

				}
				if (comboBox_3.isEnabled() == true) {
					try {
						String sql = "select * from client where statut='" + comboBox_3.getSelectedItem() + "'";

						Statement st = (Statement) cn.connecion().createStatement();
						ResultSet rst = st.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rst));
						frame.dispose();
						JOptionPane.showMessageDialog(null, "Recherche réalisée avec succés", "erreur",
								JOptionPane.PLAIN_MESSAGE, icon);
						table.setVisible(true);
					} catch (Exception ex) {
					}
				}
			}

		});

	}
}