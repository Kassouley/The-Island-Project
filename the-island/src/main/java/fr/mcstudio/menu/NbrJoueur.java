package fr.mcstudio.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class NbrJoueur extends JLayeredPane {
	public static int nbJr;
	private JSlider slider;
	private JTextField field;
	private JButton button;
	private JLabel text;
	Joueur menuJ;

	public NbrJoueur(JLayeredPane layeredPane) {
		slider = new JSlider();
		field = new JTextField(5);
		field.setEditable(false);
		button = new JButton("OK");
		text = new JLabel("Choisissez le nombre de joueurs de cette partie :");
		text.setFont(new Font("Showcard Gothic", Font.PLAIN, 25));
        //------------------------------------------------------------------------------ Changer le truc de font
		text.setForeground(Color.WHITE);
		text.setBounds(297, 150, 400, 46);

		add(text);
		add(slider);
		add(field);
		add(button);

		slider.setMaximum(4);
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				nbJr = slider.getValue();
				field.setText(Integer.toString(nbJr));
			}
		});
		slider.setValue(0);
		slider.setBounds(300, 50, 507, 67);
		slider.setBackground(new Color(0, 0, 0, 0));
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("nJr = " + nbJr);
				if (nbJr == 0) {
					JOptionPane.showMessageDialog(null, "Vueillez entrer le nombre de joueurs !", "Attention",
							JOptionPane.WARNING_MESSAGE);
				} else {
					Joueur menuJ = new Joueur(nbJr);
					menuJ.setBounds(568, 400, 500, 200);
					menuJ.setBackground(new Color(0, 0, 0, 0));
//                    menuJ.setBounds(540, 350 , 500,(200/nbJr)+50);
					layeredPane.add(menuJ);
					layeredPane.setLayer(menuJ, 2);

					// String[] tabJ = menuJ.recupJoueur(nbJr);
					// System.out.println(tabJ[10]);
				}

			}
		});
		slider.setValue(0);

	}
}

