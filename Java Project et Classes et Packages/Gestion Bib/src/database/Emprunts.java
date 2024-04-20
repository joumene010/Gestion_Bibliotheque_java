package database;

import java.awt.Image;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import net.proteanit.sql.DbUtils;

public class Emprunts {
	Image img1 = new ImageIcon(this.getClass().getResource("/check.png")).getImage();
	Image img2 = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();

	ImageIcon icon = new ImageIcon(img1);
	ImageIcon icon1 = new ImageIcon(img2);

	public Emprunts() {
	}

	DataBase conn = new DataBase();

	public void consulterHistoriqueEmprunts(JTable table) {
		try {
			Statement stmt = (Statement) conn.connecion().createStatement();
			String sql = "select p.idexemplaire,l.titre , p.date_emprunt, p.date_retour, p.num_ab from emprunts p ,exemplaire e , livre l  where (p.idexemplaire = e.id_exp )and (e.id_livre = l.idlivre)";
					ResultSet rs = stmt.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e1) {
		}
	}

	public void consulterEmpruntsEnCours(JTable table) {
		try {
			DataBase conn = new DataBase();
			Statement stmt = (Statement) conn.connecion().createStatement();
			String sql = "select p.idexemplaire, l.titre , p.date_emprunt, p.date_retour, p.num_ab from emprunts p ,exemplaire e , livre l  where (p.idexemplaire = e.id_exp )and (e.id_livre = l.idlivre) and livre_retourne=1";
			ResultSet rs = stmt.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e1) {
		}
	}

	public void AfficherLivre(JTable table) {
		try {
			String sql = "select Idlivre,nisbn,titre,auteur from livre";
			PreparedStatement stmt = (PreparedStatement) conn.connecion().prepareStatement(sql);
			ResultSet rs;
			rs = stmt.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e1) {
			System.out.println(e1);
		}
	}

	public void AfficherLivreEmprunte(JTable table,JTextField email) {
		
		try {
			
			String sql4 = "select num_abonne from client where Email='" + email.getText() + "'";
			Statement stmt4 = (Statement) conn.connecion().createStatement();
			ResultSet rs2 = stmt4.executeQuery(sql4);
			rs2.next();
			int num = rs2.getInt(1);
			System.out.println(num);
			
				
					DataBase conn = new DataBase();
					Statement stmt = (Statement) conn.connecion().createStatement();
					String sql = "select Idlivre,nisbn,titre,auteur from livre where Idlivre in (select id_livre from exemplaire where id_exp in (select idexemplaire from emprunts where num_ab="+ num + " and livre_retourne=1))";
					ResultSet rs = stmt.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e1) {
			System.out.println(e1);
			JOptionPane.showMessageDialog(null, "Impossible de rendre le livre", "erreur", JOptionPane.PLAIN_MESSAGE,
					icon1);

		}
	}
	
	public void Emprunterlivre(JTable table, JTextField email) {
		try {
			if (table.getSelectedRowCount() == 1) {
				int row = table.getSelectedRow();
				String cell = table.getValueAt(row, 0).toString();
				String sql = "select id_exp from exemplaire where id_livre=" + cell
						+ " and id_exp not in(select idexemplaire from emprunts where livre_retourne=1)";
				Statement stmt = (Statement) conn.connecion().createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				if (!rs.next()) {
					JOptionPane.showMessageDialog(null, "Tous les exemplaires ont été empruntés", "erreur",
							JOptionPane.PLAIN_MESSAGE, icon1);
				} else {
					String sql1 = "select min(id_exp) from exemplaire where id_livre=" + cell
							+ " and id_exp not in(select idexemplaire from emprunts where livre_retourne=1) and id_exp<>0";
					System.out.println(sql1);
					PreparedStatement stmt1 = (PreparedStatement) conn.connecion().prepareStatement(sql1);
					ResultSet rs1 = stmt1.executeQuery(sql1);
					rs1.next();
					int min = rs1.getInt(1);
					System.out.println(min);
					String sql4 = "select num_abonne from client where Email='" + email.getText() + "'";
					Statement stmt4 = (Statement) conn.connecion().createStatement();
					ResultSet rs2 = stmt4.executeQuery(sql4);
					rs2.next();
					int num = rs2.getInt(1);
					System.out.println(num);
					String sql2 = "insert into emprunts values(" + min + ",sysdate(),null,1," + num + ")";
					System.out.println(sql2);
					PreparedStatement stmt2 = (PreparedStatement) conn.connecion().prepareStatement(sql2);
					stmt2.executeUpdate(sql2);
					JOptionPane.showMessageDialog(null, "Emprunt Realisée avec succés", "Succés",
							JOptionPane.PLAIN_MESSAGE, icon);
				}
			} else if (table.getSelectedRowCount() == 0) {

				JOptionPane.showMessageDialog(null, "Vous devez séléctionner une ligne", "erreur",
						JOptionPane.PLAIN_MESSAGE, icon1);
			} else {

				JOptionPane.showMessageDialog(null, "Emprunt multiple impossible", "erreur", JOptionPane.PLAIN_MESSAGE,
						icon1);
			}
		} catch (Exception e2) {
			System.out.println(e2);
			JOptionPane.showMessageDialog(null, "Emprunt impossible", "erreur", JOptionPane.PLAIN_MESSAGE, icon1);
		}
	}

	public void rendreLivre(JTable table) {
		try {
			if (table.getSelectedRowCount() == 1) {
				int row = table.getSelectedRow();
				String idexp = table.getValueAt(row, 0).toString();
				String date_emprunt = table.getValueAt(row, 2).toString();
				String numabonne = table.getValueAt(row, 4).toString();
				String sql = "update emprunts set date_retour=sysdate(),livre_retourne=0 where idexemplaire=" + idexp
						+ " and num_ab=" + numabonne + " and livre_retourne=1";
				Statement stmt = (Statement) conn.connecion().createStatement();
				int x = stmt.executeUpdate(sql);
				System.out.println();
				JOptionPane.showMessageDialog(null, "Livre avec succés", "Succés", JOptionPane.PLAIN_MESSAGE, icon);
			} else if (table.getSelectedRowCount() == 0) {

				JOptionPane.showMessageDialog(null, "Vous devez séléctionner un livre é rendre", "erreur",
						JOptionPane.PLAIN_MESSAGE, icon1);
			} else {

				JOptionPane.showMessageDialog(null, "Erreur,Impossible de rendre plusieurs livre simultanément",
						"erreur", JOptionPane.PLAIN_MESSAGE, icon1);
			}
		} catch (Exception e1) {
			System.out.println(e1);
			JOptionPane.showMessageDialog(null, "Impossible de rendre le livre", "erreur", JOptionPane.PLAIN_MESSAGE,
					icon1);

		}
	}
}
