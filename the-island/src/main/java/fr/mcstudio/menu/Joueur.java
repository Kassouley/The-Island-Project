package fr.mcstudio.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Joueur extends JPanel {

	private JTextField textField;
	private boolean clic = false ;
	public String []tabJoueurs ; 
	public boolean retour_bool = false ;
	public Joueur(int nbJr) {
		
		setLayout(null);
			
		int a = 200 ;
    	int b = 100 ;
		JLabel jlabels[] = new JLabel[nbJr] ;
		JTextField textfield[] = new JTextField[nbJr];
		
		for(int i=0 ; i<nbJr ; i++)
		{
			jlabels[i] = new JLabel("Joueur "+(i+1));
			jlabels[i].setBounds(a, b, 200,60);
//			jlabels[i].setFont(new Font("Tahoma", Font.PLAIN, 15));
//			jlabels[i].setForeground(Color.WHITE);
			
			jlabels[i].setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
			//--------------------------------------------------------------------------- Font
			jlabels[i].setForeground(Color.WHITE);
			
			textfield[i] = new JTextField();
			textfield[i].setBounds(a+200, b, 200, 30);
			textfield[i].setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
			//--------------------------------------------------------------------------- Font
    		b = b + 60;
    		add(jlabels[i]);
    		add(textfield[i]);
		}
		
		JButton btnNewButton = new JButton("Commencer !");
		btnNewButton.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		//-------------------------------------------------------------------------------- Font
		btnNewButton.setBounds(260, 350 , 250,50);
		add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
        		System.out.println(textfield[0].getText());

            }
        });
		
		JButton retour = new JButton("Retour");
		retour.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		//----------------------------------------------------------------------------------- Font
		retour.setBounds(260, 410 , 250,50);
		add(retour);
		retour.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
        		System.out.println("RETOUR");
            }
        });
		
		
	}
	
	String[] recupJoueur(int nbJr) {
		String[] tabJ = null ;
		for(int i=0 ; i<nbJr ; i++)
		{
			tabJ[i] = tabJoueurs[i] ;
		} 
		return tabJ ; 
		
	}
	

}
