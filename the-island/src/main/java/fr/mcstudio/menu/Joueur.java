package fr.mcstudio.menu;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import fr.mcstudio.Main;
import fr.mcstudio.enums.Color;
import fr.mcstudio.game.Game;
import fr.mcstudio.game.Player;

public class Joueur {

	private Accueil accueil;
	public String []tabJoueurs ; 
	public boolean retour_bool = false ;
	public int nbJr;
	private ArrayList<Player> players= new ArrayList<Player>();

	public Joueur(int nbJr, Accueil accueil) {
		this.nbJr = nbJr;
		this.accueil = accueil;
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
			jlabels[i].setForeground(java.awt.Color.WHITE);
			
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
            	accueil.getLayeredPane().removeAll();
            	System.out.println(textfield[0].getText());
            	for (int i = 0; i < nbJr; i++) {
            		Player P = new Player(textfield[i].getText(), Color.values()[i], false, resolution);
            		ImageIcon avatar = new ImageIcon(Main.class.getResource("/SideBar/avatar" + (i+1) + ".png"));
            		Image scaleImage = avatar.getImage().getScaledInstance(avatar.getIconWidth() * resolution / 90,
            				avatar.getIconHeight() * resolution / 90, Image.SCALE_SMOOTH);
            		avatar.setImage(scaleImage);
            		P.setAvatar(avatar);
            		players.add(P);
				}
            	
            	@SuppressWarnings("unused")
				Game game = new Game(resolution, accueil, players);
        		
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
