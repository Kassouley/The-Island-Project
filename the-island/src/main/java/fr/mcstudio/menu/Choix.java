package fr.mcstudio.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Choix extends JPanel {
	
	public static int nbJr ;
	public JSlider slider;
	public JTextField field;
    public JButton button;
    public JLabel text ;
    Joueur menuJ ;
    
    public Choix(JLayeredPane layeredPane) {

        field = new JTextField(5);
        field.setEditable(false);
        field.setFont(new Font("Showcard Gothic", Font.BOLD, 40));
        //--------------------------------------------------------------------------------------- Font
        field.setBounds(300, 50, 507, 67);
        
        //	Text :
        text = new JLabel("Choisissez le nombre de joueurs de cette partie :");
        text.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
        //----------------------------------------------------------------------------------------- Font
        text.setForeground(Color.WHITE);

        
        //	Slider :
        slider = new JSlider();
        slider.setMaximum(4);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            	nbJr = slider.getValue() ;
                field.setText(Integer.toString(nbJr));
            }
        });
        slider.setValue(0);
        slider.setPreferredSize(new Dimension(650, 40));
        
        
        //	Bouton OK :
        button = new JButton("OK");
        button.setFont(new Font("Showcard Gothic", Font.BOLD, 40));
        //------------------------------------------------------------------------------ Font
        button.setBounds(300,300,500,60);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
        		System.out.println("nJr = "+nbJr); 
        		if (nbJr==0) 
        		{
        			JOptionPane.showMessageDialog(null, "Vueillez entrer le nombre de joueurs !" , "Attention",JOptionPane.WARNING_MESSAGE);
        		}
        		
        		else 
        		{
        			Joueur menuJ = new Joueur(nbJr);
                    menuJ.setBounds(450,400,800,500);
                    menuJ.setBackground(new Color(0,0,0,0));
                    layeredPane.add(menuJ);
                    layeredPane.setLayer(menuJ, 2);  
                    slider.setEnabled(false);
        		}
        	}
        });
        slider.setValue(0);
        
        
        //	Ajout des elements sur le JPanel :    	
        add(text);
        add(slider);
        add(field);
        add(button);
        
     
    	
    }
}
