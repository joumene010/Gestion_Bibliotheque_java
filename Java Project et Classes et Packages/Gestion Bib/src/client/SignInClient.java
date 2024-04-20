package client;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.sql.PreparedStatement;

import database.DataBase;

public class SignInClient extends JFrame implements ActionListener {
	private static JTextField textField1;
	private static JTextField textField2;
	private static JTextField textField3;
	private static JTextField textField4;
	private static JTextField textField5;
	JButton retour, Button;

	public static JTextField getTextField1() {
		return textField1;
	}

	public static JTextField getTextField2() {
		return textField2;
	}

	public static JTextField getTextField3() {
		return textField3;
	}

	public static JTextField getTextField4() {
		return textField4;
	}

	public static JTextField getTextField5() {
		return textField5;
	}

	public SignInClient() {
		setTitle("Inscription");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		Image img = new ImageIcon(this.getClass().getResource("/fenetre.jpg")).getImage();
		setContentPane(new JLabel(new ImageIcon(img)));
		setLayout(new FlowLayout());
		setVisible(true);
		setResizable(false);
		setLocation(350, 150);
		setSize(960, 686);
		JLabel label1 = new JLabel("Nom");
		JLabel label2 = new JLabel("Prenom");
		JLabel label3 = new JLabel("E-mail");
		JLabel label4 = new JLabel("Mot De Passe");
		JLabel label5 = new JLabel("Statut");
		label1.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 20));
		label2.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 20));
		label3.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 20));
		label4.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 20));
		label5.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 20));
		Color c = new Color(6, 141, 169);
		label1.setForeground(c);
		label2.setForeground(c);
		label3.setForeground(c);
		label4.setForeground(c);
		label5.setForeground(c);

		textField1 = new JTextField();
		textField2 = new JTextField();
		textField3 = new JTextField();
		textField5 = new JTextField();
		textField4 = new JPasswordField();
		Button = new JButton("S'inscrire");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(label1);
		add(label2);
		add(textField1);
		add(label3);
		add(textField3);
		add(label4);
		add(textField4);
		add(textField2);
		add(textField5);
		add(Button);
		add(label5);
		label1.setBounds(300, 100, 200, 30);
		textField1.setBounds(300, 140, 200, 30);
		label2.setBounds(300, 180, 200, 30);
		textField2.setBounds(300, 220, 200, 30);
		label3.setBounds(300, 260, 200, 30);
		textField3.setBounds(300, 300, 200, 30);
		label4.setBounds(300, 340, 200, 30);
		textField4.setBounds(300, 380, 200, 30);
		label5.setBounds(300, 420, 200, 30);
		textField5.setBounds(300, 460, 200, 30);
		Button.setBounds(420, 500, 100, 40);
		Color M = new Color(126, 23, 23);

		Button.setBackground(M);
		Button.setForeground(Color.white);
		Button.addActionListener(this);
		retour = new JButton("Précédent");
		Image img1 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		initButton(retour, img1);
		retour.setForeground(Color.black);
		retour.setFont(new Font("Tahoma", Font.BOLD, 14));
		retour.setBounds(10, 550, 180, 64);
		add(retour);
		retour.addActionListener(this);
	}

	private void initButton(JButton bt, Image image) {
		ImageIcon h;
		bt.setIcon(h = new ImageIcon(image));
		bt.setFocusPainted(false);
		bt.setMargin(null);
		bt.setBorder(BorderFactory.createEmptyBorder());
		bt.setContentAreaFilled(false);
	}

	private boolean verifier() {
		int count = 0;
		try {
			DataBase cn = new DataBase();
			Statement st = cn.connecion().createStatement();
			String sql = "select count(*) from Client where Email='" + SignInClient.getTextField3().getText() + "'";
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			count = rs.getInt(1);
		} catch (Exception e1) {
			System.out.println(e1);
		}
		if (count == 0)
			return true;
		else
			return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == Button) {
				String a = SignInClient.getTextField1().getText();
				String b = SignInClient.getTextField2().getText();
				String c = SignInClient.getTextField4().getText();
				String d = SignInClient.getTextField3().getText();
				String k = SignInClient.getTextField5().getText();
				String v = k.toUpperCase();
				String q = "ETUDIANT";
				String h = "ENSEIGNANT";
				Image icon2 = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
				ImageIcon icon1 = new ImageIcon(icon2);
				int i = 0;
				int i1 = 0;
				int p1 = 0, p2 = 0;
				int i2 = 0, i3 = 0;
				while ((i < a.length() && a.charAt(i) >= 'A' && a.charAt(i) <= 'z')
						|| (i < a.length() && a.charAt(i) == ' ')) {
					i = i + 1;
				}
				while (i2 < a.length()) {
					if (a.charAt(i2) == ' ')
						p1 = p1 + 1;
					i2 = i2 + 1;
				}
				while (i3 < b.length()) {
					if (b.charAt(i3) == ' ')
						p2 = p2 + 1;
					i3 = i3 + 1;
				}
				while ((i1 < b.length() && b.charAt(i1) >= 'A' && b.charAt(i1) <= 'z')
						|| (i1 < b.length() && b.charAt(i1) == ' ')) {
					i1 = i1 + 1;
				}
				if (c.length() == 0 || a.length() == 0 || b.length() == 0 || d.length() == 0 || k.length() == 0)
					JOptionPane.showMessageDialog(null, "Champ Vide !", "error", JOptionPane.PLAIN_MESSAGE, icon1);
				else if (((!v.equals(q)) && (!v.equals(h))))
					JOptionPane.showMessageDialog(null, "Statut Invalide !", "error", JOptionPane.PLAIN_MESSAGE, icon1);
				else if ((d.indexOf('@') == -1 && d.indexOf('.') == -1) || (d.indexOf('@') > d.indexOf('.')))
					JOptionPane.showMessageDialog(null, "Email Invalide !", "error", JOptionPane.PLAIN_MESSAGE, icon1);
				else if (i != a.length() || i1 != b.length() || p1 > 1 || p2 > 1) {
					JOptionPane.showMessageDialog(null, "Nom ou Prenom Invalide !", "error", JOptionPane.PLAIN_MESSAGE,
							icon1);
				} else if (!verifier())
					JOptionPane.showMessageDialog(null, "l'Email doit étre unique !", "error",
							JOptionPane.PLAIN_MESSAGE, icon1);
				else {
					DataBase cn = new DataBase();
					PreparedStatement ps = cn.connecion().prepareStatement(
							"insert into client(Nom,Prenom,motdepasse,Email,statut) values (?,?,?,?,?)");
					ps.setString(1, SignInClient.getTextField1().getText());
					ps.setString(2, SignInClient.getTextField2().getText());
					ps.setString(3, SignInClient.getTextField4().getText());
					ps.setString(4, SignInClient.getTextField3().getText());
					ps.setString(5, SignInClient.getTextField5().getText());
					ps.executeUpdate();
					Image img = new ImageIcon(this.getClass().getResource("/check.png")).getImage();
					ImageIcon icon = new ImageIcon(img);
					JOptionPane.showMessageDialog(null, "inscription Realisée avec Succés ", "succes",
							JOptionPane.PLAIN_MESSAGE, icon);
					dispose();
					Client c1 = new Client();
				}
			}
			if (e.getSource() == retour) {
				dispose();
				Client c2 = new Client();
				c2.setVisible(true);
			}
		} catch (Exception e1) {
			System.out.println(e1);
			Image img = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
			ImageIcon icon = new ImageIcon(img);
			JOptionPane.showMessageDialog(null, "insertion invalide!", "error", JOptionPane.PLAIN_MESSAGE, icon);
		}

	}

}