package fr.mcstudio.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Joueur {

	private JTextField textField;
	private boolean clic = false ;
	public String []tabJoueurs ; 
	public boolean retour_bool = false ;
	public int nbJr;

	public Joueur(int nbJr) {
		this.nbJr = nbJr;
	}

	public void displayPlayer(JPanel panel, JFrame frame, int resolution) {
		int a = 650 * resolution / 80;
    	int b = 500 * resolution / 80;
		JLabel jlabels[] = new JLabel[this.nbJr] ;
		JTextField textfield[] = new JTextField[this.nbJr];
		
		for(int i=0 ; i<this.nbJr ; i++)
		{
			jlabels[i] = new JLabel("Joueur "+(i+1));
			jlabels[i].setBounds(a, b - 50 * resolution / 80, 200 * resolution / 80, 60 * resolution / 80);
//			jlabels[i].setFont(new Font("Tahoma", Font.PLAIN, 15));
//			jlabels[i].setForeground(Color.WHITE);
			
			jlabels[i].setFont(new Font("Showcard Gothic", Font.PLAIN, 30 * resolution / 80));
			//--------------------------------------------------------------------------- Font
			jlabels[i].setForeground(Color.WHITE);
			
			textfield[i] = new JTextField();
			textfield[i].setBounds(a+(200 * resolution / 80), b - 40 * resolution / 80, 200 * resolution / 80, 30 * resolution / 80);
			textfield[i].setFont(new Font("Showcard Gothic", Font.PLAIN, 30 * resolution / 80));
			//--------------------------------------------------------------------------- Font
    		b = b + 60;
    		panel.add(jlabels[i]);
    		panel.add(textfield[i]);
		}
		
		JButton btnNewButton = new JButton("Commencer !");
		btnNewButton.setFont(new Font("Showcard Gothic", Font.BOLD, 30 * resolution / 80));
		//-------------------------------------------------------------------------------- Font
		btnNewButton.setBounds(710 * resolution / 80, panel.getHeight() - 100 * resolution / 80 - 60, 250 * resolution / 80, 50 * resolution / 80);
		panel.add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
        		System.out.println(textfield[0].getText());
            }
        });

		SwingUtilities.updateComponentTreeUI(frame);
	}
	
	public String[] recupJoueur() {
		String[] tabJ = new String[this.nbJr];
		for(int i=0 ; i<this.nbJr ; i++)
		{
			tabJ[i] = tabJoueurs[i] ;
		} 
		return tabJ ; 
	}
	

}
