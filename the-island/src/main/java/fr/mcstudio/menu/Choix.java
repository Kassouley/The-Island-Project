package fr.mcstudio.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Choix {
	
	public static int nbJr ;
	public JSlider slider;
	public JTextField field;
    public JButton button;
    public JLabel text ;
    public Joueur menuJ ;
    
    public Choix() {
    }

    public void displayChoice(JPanel panel, JFrame frame, int resolution) {
        field = new JTextField(5);
        field.setEditable(false);
        field.setFont(new Font("Showcard Gothic", Font.BOLD, 40 * resolution / 80));
        field.setHorizontalAlignment(SwingConstants.CENTER);
        //--------------------------------------------------------------------------------------- Font
        field.setBounds(750 * resolution / 80, 350 * resolution / 80, 60 * resolution / 80, 60 * resolution / 80);
        
        //	Text :
        text = new JLabel("Choisissez le nombre de joueurs de cette partie :");
        text.setFont(new Font("Showcard Gothic", Font.PLAIN, 30 * resolution / 80));
        //--------------------------------------------------------------------------------------- Font
        text.setForeground(Color.WHITE);
        text.setBounds(420 * resolution / 80, 260 * resolution / 80, panel.getWidth()-(text.getX()*2), 40 * resolution / 80);
        
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
        slider.setPreferredSize(new Dimension(panel.getWidth()-(text.getX()*2), 40 * resolution / 80));
        slider.setBounds(420 * resolution / 80, 300 * resolution / 80, panel.getWidth()-(text.getX()*2), 40 * resolution / 80);
        
        
        //	Bouton OK :
        button = new JButton("OK");
        button.setFont(new Font("Showcard Gothic", Font.BOLD, 40 * resolution / 80));
        //------------------------------------------------------------------------------ Font
        button.setBounds(830 * resolution / 80, 350 * resolution / 80, slider.getWidth()-(410 * resolution / 80), 60 * resolution / 80);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		System.out.println("nJr = "+nbJr); 
        		if (nbJr <= 1) {
        			JOptionPane.showMessageDialog(null, "Veuillez entrer le nombre de joueurs !" , "Attention",JOptionPane.WARNING_MESSAGE);
        		} else {
        			Joueur menuJ = new Joueur(nbJr);
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