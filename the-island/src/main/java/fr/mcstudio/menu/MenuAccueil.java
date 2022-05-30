package fr.mcstudio.menu;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;


public class MenuAccueil {

	private JFrame frame;
	private JLayeredPane layeredPane ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAccueil window = new MenuAccueil();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuAccueil() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// Frame Settings :
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
		ImageIcon img = new ImageIcon(MenuAccueil.class.getResource("/Menu/Menu/background.jpg"));
	    Background background = new Background(img,80,frame);
	    layeredPane.add(background);
        layeredPane.setLayer(background, 0);
        
        
        // Titre :
        JLabel titre = new JLabel(new ImageIcon(MenuAccueil.class.getResource("/Menu/Menu/titre_jouer.png")));
	    titre.setBounds(517,100,600 , 145);
	    layeredPane.add(titre);
        layeredPane.setLayer(titre, 1);
        
        
        // Menu Choix Nombre Joueurs :
        Choix test = new Choix(layeredPane);
        test.setBounds(450, 300 , 800,200);
        test.setBackground(new Color(0,0,0,0));
        layeredPane.add(test);
        layeredPane.setLayer(test, 1);
  
        Musique musique = new Musique("Son/sample1.wav");
    	musique.jouerMusique();
        //------------------------------------------------------------------ A voir encore
       
        JLabel mute = new JLabel(new ImageIcon(MenuAccueil.class.getResource("/Menu/Menu/mute_marron.png")));
	    mute.setBounds(50,35 , 70 , 70);
	    mute.addMouseListener(new MouseAdapter()
        {
	    	
	    	boolean son = true;
	    	public void mousePressed(MouseEvent e)
            {
            	if(son == true)
            	{
            		musique.arreterMusique();
            		mute.setIcon(new ImageIcon(MenuAccueil.class.getResource("/Menu/Menu/mute_red.png")));
            		son = false ; 
            	}
            	else if (son == false)
            	{
            		musique.jouerMusique();
            		mute.setIcon(new ImageIcon(MenuAccueil.class.getResource("/Menu/Menu/mute_marron.png")));
                	son = true ; 
            	}
            	// son.augVolMusique();
            }
	    	
            public void mouseEntered(MouseEvent e)
            {
            	
            	if(son == true)
            	{
            		mute.setIcon(new ImageIcon(MenuAccueil.class.getResource("/Menu/Menu/mute_red.png")));
            	}
            	else if (son == false)
            	{
            		mute.setIcon(new ImageIcon(MenuAccueil.class.getResource("/Menu/Menu/mute_marron.png")));
            	}
            }
            
            
            
            public void mouseExited(MouseEvent e)
            {
            	if(son == true)
            	{
            		mute.setIcon(new ImageIcon(MenuAccueil.class.getResource("/Menu/Menu/mute_marron.png")));
            	}
            	else if (son == false)
            	{
            		mute.setIcon(new ImageIcon(MenuAccueil.class.getResource("/Menu/Menu/mute_red.png")));
            	}
            }
            
            
        });
        
	 // Bouton UP :
	    JLabel up = new JLabel(new ImageIcon(MenuAccueil.class.getResource("/Menu/Menu/images_80/volume_up.png")));
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
	    JLabel down = new JLabel(new ImageIcon(MenuAccueil.class.getResource("/Menu/Menu/images_80/volume_down.png")));
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
        
        layeredPane.setVisible(true);


        
	}

}
