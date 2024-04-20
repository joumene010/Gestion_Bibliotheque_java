package database;

import java.awt.Image;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mysql.jdbc.Statement;

public class Exemplaire {
	int id_exemplaire;
	Image img1 = new ImageIcon(this.getClass().getResource("/check.png")).getImage();
	Image img2 = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
	ImageIcon icon = new ImageIcon(img1);
	ImageIcon icon1 = new ImageIcon(img2);

	public Exemplaire() {
	}

	DataBase cn = new DataBase();

	public void AjouterExemplaire(JTextField textField, JTable table) {
		try {
			int row1 = table.getSelectedRow();
			String cell = table.getValueAt(row1, 0).toString();
			String cell1 = table.getValueAt(row1, 4).toString();
			Statement st = (Statement) cn.connecion().createStatement();
			int x = Integer.parseInt(textField.getText());
			int y = Integer.parseInt(cell1);
			int z = Integer.parseInt(cell);
			String sql = "insert into exemplaire(ID_Livre) values(" + z + ")";
			for (int i = 0; i < x; i++)
				st.executeUpdate(sql);
			sql = "update livre set quantite =" + (y + x) + " where idlivre=" + z;
			Statement stamt = (Statement) cn.connecion().createStatement();
			stamt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "exemplaires ajoutés avec succés", "Succés", JOptionPane.PLAIN_MESSAGE,
					icon);
		} catch (Exception e1) {
			{
				JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout", "Erreur", JOptionPane.PLAIN_MESSAGE,
						icon1);
			}
		}
	}

	public void SupprimerExemplaire(JTextField textField, JTable table) {
		try {
			int row1 = table.getSelectedRow();
			String cell = table.getValueAt(row1, 0).toString();
			String cell1 = table.getValueAt(row1, 4).toString();
			Statement st = (Statement) cn.connecion().createStatement();
			int x = Integer.parseInt(textField.getText());
			int y = Integer.parseInt(cell1);
			int z = Integer.parseInt(cell);
			System.out.println(x);
			System.out.println(y);
			String sql = "select id_exp from exemplaire";
			ResultSet rs = st.executeQuery(sql);
			rs.last();
			String d = rs.getString("id_exp");
			if (x > y) {
				JOptionPane.showMessageDialog(null, "Suppression de ce nombre d'exemplaires impossible", "Echec",
						JOptionPane.PLAIN_MESSAGE, icon1);
			} else {
				int k = Integer.parseInt(d);
				int i = 0;
				int j = -1;
				while (i < x) {
					j++;
					String sql2 = "delete from exemplaire where id_livre=" + z + " and id_exp=" + (k - j);
					int h = st.executeUpdate(sql2);
					if (h == 1)
						System.out.println(h);
					i++;
				}

				String sql1 = "update livre set quantite = " + (y - x) + " where idlivre='" + z + "'";
				Statement stamt = (Statement) cn.connecion().createStatement();
				stamt.executeUpdate(sql1);
				JOptionPane.showMessageDialog(null, "exemplaires supprimées avec succés", "Succés",
						JOptionPane.PLAIN_MESSAGE, icon);
			}
		} catch (Exception e1) {
			System.out.println(e1);
			JOptionPane.showMessageDialog(null, "Erreur lors de la suppression", "Erreur", JOptionPane.PLAIN_MESSAGE,
					icon1);
		}
	}
}
