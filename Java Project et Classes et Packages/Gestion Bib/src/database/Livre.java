package database;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import admin.GestionLivre;
import client.SignInClient;
import com.mysql.jdbc.Statement;
import net.proteanit.sql.DbUtils;

public class Livre {
	JButton retour;
	DataBase cn = new DataBase();

	public Livre() {
	}

	private boolean verifier(JTextField textField) {
		int count = 0;
		try {
			DataBase cn = new DataBase();
			Statement st = (Statement) cn.connecion().createStatement();
			String sql = "select count(*) from Livre where nisbn=" + textField.getText();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			count = rs.getInt(1);
		} catch (Exception e1) {
		}
		if (count == 0)
			return true;
		else
			return false;
	}

	Image img1 = new ImageIcon(this.getClass().getResource("/check.png")).getImage();
	Image img2 = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
	ImageIcon icon = new ImageIcon(img1);
	ImageIcon icon1 = new ImageIcon(img2);

	public void AjouterLivre(JTextField textField, JTextField textField_1, JTextField textField_2,
			JTextField textField_3, JTextField textField_4, JTable table) {
		try {
			int i = 0;
			int j = 0;
			int k = 0;
			int q = 0;
			int k1 = 0;
			int v = 0;
			while ((k < textField_3.getText().length() && textField_3.getText().charAt(k) >= 'A'
					&& textField_3.getText().charAt(k) <= 'z')
					|| (k < textField_3.getText().length() && textField_3.getText().charAt(k) == ' ')) {
				k = k + 1;
			}
			while (v < textField_3.getText().length()) {
				if (textField_3.getText().charAt(v) == ' ')
					k1 = k1 + 1;
				v = v + 1;
			}
			while (i < textField.getText().length() && textField.getText().charAt(i) >= '0'
					&& textField.getText().charAt(i) <= '9') {
				i = i + 1;
			}
			while (j < textField_2.getText().length() && textField_2.getText().charAt(j) >= '0'
					&& textField_2.getText().charAt(j) <= '9') {
				j = j + 1;
			}
			while (q < textField_4.getText().length() && textField_4.getText().charAt(q) >= '0'
					&& textField_4.getText().charAt(q) <= '9') {
				q = q + 1;
			}
			if (i != 4) {
				JOptionPane.showMessageDialog(null, "L'ID du livre doit contenir 4 chiffres !", "erreur",
						JOptionPane.PLAIN_MESSAGE, icon1);
			} else if (j != 10) {
				System.out.println(j);
				JOptionPane.showMessageDialog(null, "L'ISBN doit contenir 10 chiffres !", "erreur",
						JOptionPane.PLAIN_MESSAGE, icon1);
			} else if (k != textField_3.getText().length() || k1 > 2) {
				JOptionPane.showMessageDialog(null,
						"Le nom de l'auteur ne doit contenir que des lettres et deux espaces au maximum!", "erreur",
						JOptionPane.PLAIN_MESSAGE, icon1);
			} else if (q != textField_4.getText().length()) {
				JOptionPane.showMessageDialog(null, "La quantité ne peut pas contenir des caractéres alphabétiques!",
						"erreur", JOptionPane.PLAIN_MESSAGE, icon1);
			} else if (!verifier(textField_2)) {
				JOptionPane.showMessageDialog(null, "L'isbn existe déja!", "erreur", JOptionPane.PLAIN_MESSAGE, icon1);
			} else {
				PreparedStatement ps = cn.connecion()
						.prepareStatement("insert into livre (IDlivre,nisbn,titre,auteur,quantite)values (?,?,?,?,?)");
				ps.setLong(1, Integer.parseInt(textField.getText()));
				ps.setString(3, textField_1.getText());
				ps.setLong(2, Integer.parseInt(textField_2.getText()));
				ps.setString(4, textField_3.getText());
				ps.setLong(5, Integer.parseInt(textField_4.getText()));
				ps.executeUpdate();

				JOptionPane.showMessageDialog(null, "insertion Realisée avec Succés ", "succes",
						JOptionPane.PLAIN_MESSAGE, icon);
				String sql = "select * from livre";
				PreparedStatement stmt = cn.connecion().prepareStatement(sql);
				ResultSet rs;
				rs = stmt.executeQuery(sql);
				table.setModel(DbUtils.resultSetToTableModel(rs));
				Statement stmt1 = (Statement) cn.connecion().createStatement();
				String sql1 = "insert into exemplaire(id_livre) values(" + textField.getText() + ")";
				System.out.println(sql1);
				for (int z = 0; z < Integer.parseInt(textField_4.getText()); z++) {
					stmt.executeUpdate(sql1);
				}
			}
		} catch (Exception e1) {
			System.out.println(e1);
			JOptionPane.showMessageDialog(null, "L'ID existe déja", "erreur", JOptionPane.PLAIN_MESSAGE, icon1);
		}
	}

	public void AfficherLivre(JTable table) {
		try {
			String sql = "select * from livre";
			PreparedStatement stmt = cn.connecion().prepareStatement(sql);
			ResultSet rs;
			rs = stmt.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e1) {
			System.out.println(e1);
		}
	}

	public void SupprimerLivre(JTable table) {
		try {

			if (table.getSelectedRowCount() == 1) {
				int row = table.getSelectedRow();
				System.out.println(row);

				String cell = table.getValueAt(row, 0).toString();
				String sql = "delete from livre where idlivre=" + cell;
				Statement stmt = (Statement) cn.connecion().createStatement();
				stmt.executeUpdate(sql);
				sql = "select * from livre";
				PreparedStatement stmt1 = cn.connecion().prepareStatement(sql);
				ResultSet rs;
				rs = stmt1.executeQuery(sql);
				table.setModel(DbUtils.resultSetToTableModel(rs));
				Statement stmt2 = (Statement) cn.connecion().createStatement();
				String sql1 = "delete from exemplaire where id_livre=" + cell;
				stmt2.executeUpdate(sql1);
				JOptionPane.showMessageDialog(null, "Suppression Realisée avec succés", "Succés",
						JOptionPane.PLAIN_MESSAGE, icon);
			} else if (table.getSelectedRowCount() == 0) {

				JOptionPane.showMessageDialog(null, "Vous devez séléctionner une ligne é supprimer", "erreur",
						JOptionPane.PLAIN_MESSAGE, icon1);
			} else {

				JOptionPane.showMessageDialog(null, "Suppression multiple impossible", "erreur",
						JOptionPane.PLAIN_MESSAGE, icon1);
			}
		} catch (Exception e1) {

			JOptionPane.showMessageDialog(null, "Suppression impossible", "erreur", JOptionPane.PLAIN_MESSAGE, icon1);
		}

	}

	public void ModifierLivre(JTextField textField, JTextField textField_1, JTextField textField_2,
			JTextField textField_3, JTextField textField_4, JTable table) {
		String a = null;
		String b = "";
		int row1 = table.getSelectedRow();
		a = table.getValueAt(row1, 0).toString();
		b = table.getValueAt(row1, 4).toString();
		try {
			if (!(GestionLivre.getTextField().getText().equals(a)))
				JOptionPane.showMessageDialog(null,
						"Erreur,l'ID doit rester le méme/Tous les champs doivent étre renseignés", "erreur",
						JOptionPane.PLAIN_MESSAGE, icon1);
			else if (!(GestionLivre.getTextField_4().getText().equals(b))) {
				JOptionPane.showMessageDialog(null, "Erreur,la quantité doit rester la méme", "erreur",
						JOptionPane.PLAIN_MESSAGE, icon1);
			} else if (table.getSelectedRowCount() == 1) {
				int row = table.getSelectedRow();
				System.out.println(row);

				String cell = table.getValueAt(row, 0).toString();
				String sql = "delete from livre where idlivre=" + cell;
				Statement stmt = (Statement) cn.connecion().createStatement();
				stmt.executeUpdate(sql);
				PreparedStatement ps = cn.connecion()
						.prepareStatement("insert into livre (IDlivre,nisbn,titre,auteur,quantite)values (?,?,?,?,?)");
				ps.setLong(1, Integer.parseInt(textField.getText()));
				ps.setString(3, textField_1.getText());
				ps.setLong(2, Integer.parseInt(textField_2.getText()));
				ps.setString(4, textField_3.getText());
				ps.setLong(5, Integer.parseInt(textField_4.getText()));
				ps.executeUpdate();
				Image img8 = new ImageIcon(this.getClass().getResource("/check.png")).getImage();
				ImageIcon icon = new ImageIcon(img8);
				String req = "select * from livre";
				PreparedStatement stamt = cn.connecion().prepareStatement(req);
				ResultSet rs = stmt.executeQuery(req);
				table.setModel(DbUtils.resultSetToTableModel(rs));
				JOptionPane.showMessageDialog(null, "Modification realisée avec succés", "Succés",
						JOptionPane.PLAIN_MESSAGE, icon);
			} else if (table.getSelectedRowCount() == 0)
				JOptionPane.showMessageDialog(null, "Vous devez séléctionner une ligne é Modifier", "erreur",
						JOptionPane.PLAIN_MESSAGE, icon1);
			else
				JOptionPane.showMessageDialog(null, "Modification multiple impossible", "erreur",
						JOptionPane.PLAIN_MESSAGE, icon1);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "L'ID doit étre unique / Tous les champs doivent étre renseignés",
					"Modification Impossible", JOptionPane.PLAIN_MESSAGE, icon1);
		}

	}

	private void initButton(JButton bt, Image image) {
		ImageIcon h;
		bt.setIcon(h = new ImageIcon(image));
		bt.setFocusPainted(false);
		bt.setMargin(null);
		bt.setBorder(BorderFactory.createEmptyBorder());
		bt.setContentAreaFilled(false);
	}

	public void RechercherLivre(JTable table) {
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

		JButton retour = new JButton("Précédent");
		Image img7 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		initButton(retour, img7);
		retour.setForeground(Color.BLACK);
		retour.setFont(new Font("Tahoma", Font.BOLD, 14));
		retour.setBounds(10, 550, 180, 64);
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				if (e1.getSource() == retour) {
					frame.dispose();
				}
			}
		});

		frame.add(retour);

		comboBox = new JComboBox();
		String ID = "select idlivre from livre";

		try {

			Statement st = (Statement) cn.connecion().createStatement();
			ResultSet rst = st.executeQuery(ID);
			while (rst.next()) {
				comboBox.addItem(rst.getString("IDLivre"));
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
		String titre = "select titre from livre";

		try {

			Statement st = (Statement) cn.connecion().createStatement();
			ResultSet rst = st.executeQuery(titre);
			while (rst.next()) {
				comboBox_1.addItem(rst.getString("Titre"));
			}
		} catch (Exception ex) {
		}

		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setEnabled(false);
				comboBox_2.setEnabled(false);
				comboBox_3.setEnabled(false);
			}
		});
		comboBox_1.setBounds(214, 213, 133, 33);
		contentPane.add(comboBox_1);

		comboBox_2.setBounds(417, 213, 133, 33);
		contentPane.add(comboBox_2);
		String Auteur = "select auteur from livre";
		try {
			Statement st = (Statement) cn.connecion().createStatement();
			ResultSet rst = st.executeQuery(Auteur);
			while (rst.next()) {
				comboBox_2.addItem(rst.getString("Auteur"));
			}
		} catch (Exception ex) {
		}
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setEnabled(false);
				comboBox_1.setEnabled(false);
				comboBox_3.setEnabled(false);
			}
		});

		JLabel lblNewLabel = new JLabel("Recherche");
		Color c = new Color(42, 110, 143);
		lblNewLabel.setForeground(c);
		lblNewLabel.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 50));
		lblNewLabel.setBounds(344, 32, 300, 50);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Par ID");
		lblNewLabel_1.setForeground(new Color(0, 156, 109));
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(21, 153, 200, 33);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Par Titre");
		lblNewLabel_2.setForeground(new Color(0, 156, 109));
		lblNewLabel_2.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(214, 153, 150, 33);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Par Auteur");
		lblNewLabel_3.setForeground(new Color(0, 156, 109));
		lblNewLabel_3.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(417, 153, 150, 33);
		contentPane.add(lblNewLabel_3);
		contentPane.setBackground(new Color(217, 237, 253));
		comboBox_3.setBounds(634, 213, 133, 33);
		contentPane.add(comboBox_3);
		String isbn = "select nisbn from livre";
		try {

			Statement st = (Statement) cn.connecion().createStatement();
			ResultSet rst = st.executeQuery(isbn);
			while (rst.next()) {
				comboBox_3.addItem(rst.getString("nisbn"));
			}
		}

		catch (Exception ex) {
		}
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
				comboBox_1.setEnabled(false);
				comboBox_3.setEnabled(false);
			}
		});

		JLabel lblNewLabel_3_1 = new JLabel("Par n\u00B0isbn");
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
						String sql = "select * from livre where idlivre=" + comboBox.getSelectedItem();

						Statement st = (Statement) cn.connecion().createStatement();
						ResultSet rst = st.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rst));
					} catch (Exception ex) {
					}

				}
				if (comboBox_1.isEnabled() == true) {
					try {
						String sql = "select * from livre where titre='" + comboBox_1.getSelectedItem() + "'";
						Statement st = (Statement) cn.connecion().createStatement();
						ResultSet rst = st.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rst));
					} catch (Exception ex) {
					}

				}
				if (comboBox_2.isEnabled() == true) {
					try {
						String sql = "select * from livre where auteur='" + comboBox_2.getSelectedItem() + "'";

						Statement st = (Statement) cn.connecion().createStatement();
						ResultSet rst = st.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rst));
					} catch (Exception ex) {
					}

				}
				if (comboBox_3.isEnabled() == true) {
					try {
						String sql = "select * from livre where nisbn=" + comboBox_3.getSelectedItem();

						Statement st = (Statement) cn.connecion().createStatement();
						ResultSet rst = st.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rst));
					} catch (Exception ex) {
					}
				}
				frame.dispose();

				JOptionPane.showMessageDialog(null, "Recherche réalisée avec succés", "", JOptionPane.PLAIN_MESSAGE,
						icon);
				table.setVisible(true);

			}
		});

	}

}
