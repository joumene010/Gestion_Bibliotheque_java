package firstInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import admin.Admin;
import client.Client;

public class FenetreLogin extends JFrame implements ActionListener {
	JButton b1, b;

	public FenetreLogin() {
		this.setTitle("Session");
		setLayout(new BorderLayout());
		Image img = new ImageIcon(this.getClass().getResource("/fenetre.jpg")).getImage();
		setContentPane(new JLabel(new ImageIcon(img)));
		setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel l1 = new JLabel("Choisissez une session");
		l1.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 30));

		l1.setBounds(200,80, 500, 200);
		Color c = new Color(6, 141, 169);
		l1.setForeground(c);
		this.add(l1);
		this.setLocation(350, 150);
		Color x = new Color(126, 23, 23);
		b = new JButton("Administrateur");
		b.setBackground(x);
		b.setForeground(Color.WHITE);
		b.setBounds(300, 300, 200, 50);
		this.add(b);
		b.addActionListener(this);
		this.setSize(960, 686);
		this.setLayout(null);
		this.setVisible(true);
		b1 = new JButton("Client");
		b1.setBounds(300, 370, 200, 50);
		
		b1.setBackground(x);
		b1.setForeground(Color.WHITE);
		this.add(b1);
		b1.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b) {
			Admin a = new Admin();
			a.setVisible(true);
			
		}
		if (e.getSource() == b1) {
			dispose();
			Client c = new Client();
			c.setVisible(true);
		}
	}

}