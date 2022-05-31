package fr.mcstudio.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * It's a class that represents a choice of number of players.
 */
public class Choix {
	
    // A constructor.
    public Choix(Accueil accueil) {
    	this.accueil = accueil;
    }

    // A reference to the parent object.
    private Accueil accueil;

	// A variable that is used to store the number of players.
    private static int nbJr;

	// Declaring a slider.
    private JSlider slider;

	// A variable that is used to store the JTextField object.
    private JTextField field;

	// Declaring a button.
    private JButton button;

	// A variable that is used to store the JLabel object.
    private JLabel text ;

    /**
     * It displays a JSlider, a JTextField and a JButton on a JPanel
     * 
     * @param panel the JPanel that the elements are added to
     * @param frame the JFrame
     * @param resolution the resolution of the screen
     */
    public void displayChoice(JPanel panel, JFrame frame, int resolution) {
        field = new JTextField(5);
        field.setEditable(false);
        field.setFont(new Font("Showcard Gothic", 
        		Font.BOLD, 40 * resolution / 80));
        field.setHorizontalAlignment(SwingConstants.CENTER);
        //--------------------------------------------------------------------------------------- Font
        field.setBounds(750 * resolution / 80, 350 * resolution / 80, 
        		60 * resolution / 80, 60 * resolution / 80);
        
        //	Text :
        text = new JLabel("Choisissez le nombre de joueurs de cette partie :");
        text.setFont(new Font("Showcard Gothic", 
        		Font.PLAIN, 30 * resolution / 80));
        //--------------------------------------------------------------------------------------- Font
        text.setForeground(Color.WHITE);
        text.setBounds(420 * resolution / 80, 260 * resolution / 80, 
        		panel.getWidth()-(text.getX()*2), 40 * resolution / 80);
        
        //	Slider :
        slider = new JSlider();
        slider.setMaximum(4);
        slider.setMinimum(2);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            	nbJr = slider.getValue() ;
                field.setText(Integer.toString(nbJr));
            }
        });
        slider.setValue(2);
        slider.setPreferredSize(new Dimension(panel.getWidth()-
        		(text.getX()*2), 40 * resolution / 80));
        slider.setBounds(420 * resolution / 80, 300 * resolution / 80, 
        		panel.getWidth()-(text.getX()*2), 40 * resolution / 80);
        
        
        //	Bouton OK :
        button = new JButton("OK");
        button.setFont(new Font("Showcard Gothic", 
        		Font.BOLD, 40 * resolution / 80));
        //------------------------------------------------------------------------------ Font
        button.setBounds(830 * resolution / 80, 350 * resolution / 80, 
        		slider.getWidth()-(410 * resolution / 80), 
        		60 * resolution / 80);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		System.out.println("nJr = "+nbJr); 
        		if (nbJr <= 1) {
        			JOptionPane.showMessageDialog(null, 
        					"Veuillez entrer le nombre de joueurs !", 
        					"Attention",JOptionPane.WARNING_MESSAGE);
        		} else {
        			Joueur menuJ = new Joueur(nbJr, accueil);
                    menuJ.displayPlayer(panel, frame, resolution);
                    slider.setEnabled(false);
        		}
        	}
        });
        slider.setValue(2);
        
        //	Ajout des elements sur le JPanel :    	
        panel.add(text);
        panel.add(slider);
        panel.add(field);
        panel.add(button);
    }
}