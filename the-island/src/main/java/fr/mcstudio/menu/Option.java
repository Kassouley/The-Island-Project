package fr.mcstudio.menu;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Option {

	private JFrame frame;
	private JLayeredPane layeredPane ;
	private int dimension ; 
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Option window = new Option();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Option() {
		initialize();
	}

	private void initialize() {
	
		frame = new JFrame();
		frame.setBounds(0, 0, 1635, 919);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().setLayout(null);

		frame.setTitle("The Island - Projet POO 2022");
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    
	    // Layered Pane Settings :
	 		layeredPane = new JLayeredPane();
	 		frame.setContentPane(layeredPane);
	 		
	 		
	 	// Background :
			ImageIcon img = new ImageIcon(Option.class.getResource("/Menu/Menu/background.jpg"));
		    Background background = new Background(img,80,frame);
		    layeredPane.add(background);
		    layeredPane.setLayer(background, 0);
     
		    Musique musique = new Musique("Son/sample1.wav");
	    	musique.jouerMusique();
            //----------------------------------------------------------------------- A voir encore
	       
	        JLabel mute = new JLabel(new ImageIcon(Option.class.getResource("/Menu/Menu/mute_marron.png")));
		    mute.setBounds(50,35 , 70 , 70);
		    mute.addMouseListener(new MouseAdapter()
	        {
		    	
		    	boolean son = true;
		    	public void mousePressed(MouseEvent e)
	            {
	            	if(son == true)
	            	{
	            		musique.arreterMusique();
	            		mute.setIcon(new ImageIcon(Option.class.getResource("/Menu/Menu/mute_red.png")));
	            		son = false ; 
	            	}
	            	else if (son == false)
	            	{
	            		musique.jouerMusique();
	            		mute.setIcon(new ImageIcon(Option.class.getResource("/Menu/Menu/mute_marron.png")));
	                	son = true ; 
	            	}
	            	// son.augVolMusique();
	            }
		    	
	            public void mouseEntered(MouseEvent e)
	            {
	            	
	            	if(son == true)
	            	{
	            		mute.setIcon(new ImageIcon(Option.class.getResource("/Menu/Menu/mute_red.png")));
	            	}
	            	else if (son == false)
	            	{
	            		mute.setIcon(new ImageIcon(Option.class.getResource("/Menu/Menu/mute_marron.png")));
	            	}
	            }
	            
	            
	            
	            public void mouseExited(MouseEvent e)
	            {
	            	if(son == true)
	            	{
	            		mute.setIcon(new ImageIcon(Option.class.getResource("/Menu/Menu/mute_marron.png")));
	            	}
	            	else if (son == false)
	            	{
	            		mute.setIcon(new ImageIcon(Option.class.getResource("/Menu/Menu/mute_red.png")));
	            	}
	            }
	            
	            
	        });
	        
		 // Bouton UP :
		    JLabel up = new JLabel(new ImageIcon(Option.class.getResource("/Menu/Menu/images_80/volume_up.png")));
		    up.setBounds(65,170 , 45 , 45);
		    layeredPane.add(up);
	        layeredPane.setLayer(up, 1);
	        up.addMouseListener(new MouseAdapter()
	        {
		    	
		    	boolean son = true;
		    	public void mousePressed(MouseEvent e)
	            {
	            	if(son == true)
	            	{
	            		musique.augVolMusique();
	            		System.out.println("volume up");
	            	}
	            }
		    	
	        });
	        
	     // Bouton down :
		    JLabel down = new JLabel(new ImageIcon(Option.class.getResource("/Menu/Menu/images_80/volume_down.png")));
		    down.setBounds(65,120 , 45 , 45);
		    layeredPane.add(down);
	        layeredPane.setLayer(down, 1);
	        down.addMouseListener(new MouseAdapter()
	        {
		    	
		    	boolean son = true;
		    	public void mousePressed(MouseEvent e)
	            {
	            	if(son == true)
	            	{
	            		musique.dimVolMusique();
	            		System.out.println("volume down");
	            	}
	            }
		    	
	        });
		    
		    
		    layeredPane.add(mute);
	        layeredPane.setLayer(mute, 1);
		    
		    
		    
		    
		// Titre :
		    JLabel titre = new JLabel(new ImageIcon(Option.class.getResource("/Menu/Menu/titre_jouer.png")));
		    titre.setBounds(517,100,600 , 145);
		    layeredPane.add(titre);
		    layeredPane.setLayer(titre, 1);
		    
		    
		// OPTION :   
		JLabel lblNewLabel = new JLabel("OPTIONS");
		lblNewLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 60));
        //------------------------------------------------------------------------------------ Font
	    lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(550, 300, 553, 110);
		layeredPane.add(lblNewLabel);
		layeredPane.setLayer(lblNewLabel, 1);
		
		// Bouton AIDE :
		JLabel aide = new JLabel(new ImageIcon(Option.class.getResource("/Menu/Menu/images_80/aide_1.png")));
		aide.setBounds(550, 430 , 130 , 149);
	    layeredPane.add(aide);
        layeredPane.setLayer(aide,2);
        aide.addMouseListener(new MouseAdapter()
        {
	    	public void mousePressed(MouseEvent e)
            {
	    		System.out.println("AIDE");
        		
        		//JOptionPane.showMessageDialog( null, "Vous allez �tre redirig� vers une page WEB ! ", "R�gles du Jeu", JOptionPane.PLAIN_MESSAGE);
        		JOptionPane jop = new JOptionPane();		
				int option = jop.showConfirmDialog(null, "Vous allez �tre redirig� vers une page WEB ! ", "R�gles du Jeu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(option == JOptionPane.OK_OPTION)
				{
					try {
						Desktop.getDesktop().browse(new URI("http://carnetdesgeekeries.com/j2s-the-island-asmodee/"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
            }
	    	
        });
		
				
		// Bouton DIMENSIONS :	
		JLabel dimensions = new JLabel(new ImageIcon(Option.class.getResource("/Menu/Menu/images_80/dim_1.png")));
		dimensions.setBounds(950, 430 , 130 , 149);
	    layeredPane.add(dimensions);
        layeredPane.setLayer(dimensions,1);
        dimensions.addMouseListener(new MouseAdapter()
        {
	    	public void mousePressed(MouseEvent e)
            {
	    		System.out.println("DIMENSIONS");
        		
        		JList list = new JList(new String[] {"Petit : 1432 x 809", "Moyen : 1635 x 919" , "Grand : 1850 x 1029" });
        		JOptionPane.showMessageDialog(null, list, "Multi-Select Example", JOptionPane.PLAIN_MESSAGE);
        		
        		if(list.getSelectedValue() == "Petit : 1432 x 809")
        		{
        			System.out.println("PETIT");
        			dimension = 70 ;        			
        		}
        		else if (list.getSelectedValue() == "Moyen : 1635 x 919")
        		{
        			System.out.println("MOYEN");
        			dimension = 80 ;
        		}
        		else if (list.getSelectedValue() == "Grand : 1850 x 1029")
        		{
        			System.out.println("GRAND");
        			dimension = 90 ;
        		}
            	
            }
	    	
        });
        
        
        // Bouton RETOUR :		
		JLabel retour = new JLabel(new ImageIcon(Option.class.getResource("/Menu/Menu/images_80/retour_1.png")));
		retour.setBounds(750, 600 , 130 , 149);
	    layeredPane.add(retour);
        layeredPane.setLayer(retour,1);
        retour.addMouseListener(new MouseAdapter()
        {
	    	public void mousePressed(MouseEvent e)
            {
	    		System.out.println("RETOUR");
	    		System.out.println(dimension);
            	
            }
	    	
        });
		
    		
		
	}
}
