package admin;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import firstInterface.FenetreLogin;
import firstInterface.Test;

public class Admin extends JFrame implements ActionListener {
	Image img = new ImageIcon(this.getClass().getResource("/fenetre.jpg")).getImage();
	private static JFrame frame1;
	private static JTextField textField;
	private static JTextField textField1;
	JButton b3, retour1;
	private static JFrame frame;

	public static JFrame getFrame() {
		return frame;
	}

	public static void setFrame(JFrame frame) {
		Admin.frame = frame;
	}

	public static JTextField getTextField() {
		return textField;
	}

	public static JTextField getTextField1() {
		return textField1;
	}

	public static JFrame getFrame1() {
		return frame1;

	}

	public Admin() {
		setTitle("Connexion Administrateur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		this.setSize(960, 686);
		setLocation(350, 150);

		setContentPane(new JLabel(new ImageIcon(img)));
		setLayout(null);
		JLabel label = new JLabel("Username");
		label.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 20));
		JLabel label1 = new JLabel("Password");
		label1.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 20));
		textField = new JTextField();
		textField1 = new JPasswordField();
		Color c = new Color(6, 141, 169);
		label1.setForeground(c);
		label.setForeground(c);
		label.setBounds(200, 130, 300, 25);
		add(label);
		retour1 = new JButton("Précédent");
		Image img = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		initButton(retour1, img);
		retour1.setForeground(Color.BLACK);
		retour1.setFont(new Font("Tahoma", Font.BOLD, 14));
		retour1.setBounds(10, 550, 180, 64);
		add(retour1);
		textField.setBounds(200, 170, 200, 25);
		add(textField);
		b3 = new JButton("Valider");
		Image img2 = new ImageIcon(this.getClass().getResource("/val.png")).getImage();
		initButton(b3, img2);

		add(label1);
		label1.setBounds(200, 200, 300, 30);
		b3.setForeground(Color.BLACK);

		add(textField1);
		textField1.setBounds(200, 240, 200, 25);
		add(b3);
		b3.setBounds(150, 300, 300, 60);

		b3.addActionListener(this);
		retour1.addActionListener(v -> {
			dispose();
		});
		retour1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				FenetreLogin f1 = new FenetreLogin();
				f1.setVisible(true);
			}
		});

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
		{
			JButton retour1 = new JButton("Déconnexion");
			Image img4 = new ImageIcon(this.getClass().getResource("/Deconnexion.png")).getImage();
			initButton(retour1, img4);
			if (e.getSource() == b3) {
				String a = "admin";
				if ((textField.getText().equals(a)) && (textField1.getText().equals(a))) {
					frame = new JFrame("Choisir une option");
					frame.setResizable(false);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setLocation(350, 150);
					frame.setLayout(null);
					frame.setContentPane(new JLabel(new ImageIcon(img)));
					frame.setSize(960, 686);
					dispose();

					retour1.setForeground(Color.black);
					retour1.setFont(new Font("Tahoma", Font.BOLD, 15));
					retour1.setBounds(10, 30, 180, 64);
					frame.add(retour1);
					JButton b1 = new JButton("Livres");
					Image img1 = new ImageIcon(this.getClass().getResource("/livre.png")).getImage();
					initButton(b1, img1);
					JButton b2 = new JButton("Usagers");
					Image img2 = new ImageIcon(this.getClass().getResource("/client.png")).getImage();
					initButton(b2, img2);
					JButton b3 = new JButton("Emprunts");
					Image img3 = new ImageIcon(this.getClass().getResource("/emprunt.png")).getImage();
					initButton(b3, img3);
					b1.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 20));
					b2.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 20));
					b3.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 20));
					Color c = new Color(6, 141, 169);
					b1.setForeground(c);
					b2.setForeground(c);
					b3.setForeground(c);
					b1.setBounds(300, 56, 250, 128);
					b2.setBounds(300, 200, 250, 128);
					b3.setBounds(300, 344, 250, 128);
					retour1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e1) {
							new Admin().setVisible(true);
						}
					});
					retour1.addActionListener(z -> {
						frame.dispose();
					});
					frame.add(b1);
					frame.add(b2);
					frame.add(b3);
					frame.setVisible(true);
					b1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e1) {
							new admin.GestionLivre();
						}
					});
					b1.addActionListener(v -> {
						frame.dispose();
					});
					b2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e1) {
							new admin.GestionUsagers();
						}
					});
					b2.addActionListener(v -> {
						frame.dispose();
					});
					b3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e1) {
							new admin.GestionEmprunt();
						}
					});
					b3.addActionListener(v -> {
						frame.dispose();
					});

				} else {
					JOptionPane.showMessageDialog(null, "Invalid Username Or Password !", "Error", 0);
				}
			}
		}

	}
}