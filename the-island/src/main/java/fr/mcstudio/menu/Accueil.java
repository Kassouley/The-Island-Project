package fr.mcstudio.menu;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

public class Accueil {

	private JFrame frame;
	private JLayeredPane layeredPane ;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accueil window = new Accueil();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Accueil() {
		initialize(70);
	}

	private void initialize(int a) {
		frame = new JFrame();
		frame.setBounds(0, 0, 1635, 919);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setTitle("The Island - Projet POO 2022");
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    frame.getContentPane().setLayout(null);
	    
	    
	    // Layered Pane Settings :    
 		layeredPane = new JLayeredPane();
 		frame.setContentPane(layeredPane);
	    
	    
	    // Background : 
	    ImageIcon img = new ImageIcon(Accueil.class.getResource("/Menu/Menu/background.jpg"));
	    Background background = new Background(img,80,frame);
	    layeredPane.add(background);
        layeredPane.setLayer(background, 0);
 			    
	    
        // Ajout du son : 
	    Musique musique = new Musique("Son/sample1.wav");
    	musique.jouerMusique();
		// -------------------------------------------------------------------- Ça c'est à voir, pck je sais pas comment ça marche
	    
    	
    	// Bouton MUTE :
	    JLabel mute = new JLabel(new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/mute_marron.png")));
	    mute.setBounds(50,35 , 70 , 70);
	    layeredPane.add(mute);
        layeredPane.setLayer(mute, 1);
	    mute.addMouseListener(new MouseAdapter()
        {
	    	
	    	boolean son = true;
	    	public void mousePressed(MouseEvent e)
            {
            	if(son == true)
            	{
            		musique.arreterMusique();
            		mute.setIcon(new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/mute_red.png")));
            		son = false ; 
            	}
            	else if (son == false)
            	{
            		musique.jouerMusique();
            		mute.setIcon(new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/mute_marron.png")));
                	son = true ; 
            	}
            	// son.augVolMusique();
            }
	    	
            public void mouseEntered(MouseEvent e)
            {
            	
            	if(son == true)
            	{
            		mute.setIcon(new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/mute_red.png")));
            	}
            	else if (son == false)
            	{
            		mute.setIcon(new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/mute_marron.png")));
            	}
            }
            
            
            
            public void mouseExited(MouseEvent e)
            {
            	if(son == true)
            	{
            		mute.setIcon(new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/mute_marron.png")));
            	}
            	else if (son == false)
            	{
            		mute.setIcon(new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/mute_red.png")));
            	}
            }
            
            
        });
	    
	    
	    // Bouton UP :
	    JLabel up = new JLabel(new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/volume_up.png")));
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
            		System.out.println("up up up");
            	}
            }
	    	
        });
        
     // Bouton down :
	    JLabel down = new JLabel(new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/volume_down.png")));
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
	    
	    // Titre :
	    JLabel titre = new JLabel(new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/titre.png")));
	    titre.setBounds(592,150,450 , 100);
	    layeredPane.add(titre);
        layeredPane.setLayer(titre, 1);
	    
	    
        // Bouton JOUER :
	    JLabel jouer = new JLabel(new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/jouer1.png")));
	    jouer.setBounds(692,400,252,88);
	    layeredPane.add(jouer);
        layeredPane.setLayer(jouer, 1);		    
	    jouer.addMouseListener(new MouseAdapter()
        {
	    	
            public void mouseEntered(MouseEvent e)
            {
            	jouer.setIcon(new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/jouer2.png")));
            }
            
            public void mousePressed(MouseEvent e)
            {
            	// TODO: Implementer animation bouton 
            	System.out.println("JOUER");
                MenuAccueil choix = new MenuAccueil();
                musique.arreterMusique();
                //choix.setVisible(true);
               // choix.setBounds(-7, 0, 1635, 919);
				frame.setVisible(false);
            }
            
            public void mouseExited(MouseEvent e)
            {
            	jouer.setIcon(new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/jouer1.png")));
            }
            
            
        });

	    
        // Bouton OPTIONS :
	    JLabel options = new JLabel(new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/option1.png")));
	    options.setBounds(950, 525, 252, 88);
	    layeredPane.add(options);
        layeredPane.setLayer(options, 1);		  
	    options.addMouseListener(new MouseAdapter()
        {
	    	public void mouseEntered(MouseEvent e)
            {
	    		options.setIcon(new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/option2.png")));
	    		options.setBounds(952, 524, 252 ,88);
            }
	    	
            public void mousePressed(MouseEvent e)
            {
            	
                System.out.println("OPTION");
                musique.arreterMusique();
                Option option = new Option();
                //choix.setVisible(true);
               // choix.setBounds(-7, 0, 1635, 919);
				frame.setVisible(false);
				
            }
            
            public void mouseExited(MouseEvent e)
            {
            	options.setIcon(new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/option1.png")));
            	options.setBounds(950, 525, 252, 88);
            }
            
        });
	    
	    
	    // Bouton QUITTER :
	    JLabel quitter = new JLabel(new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/quitter1.png")));
	    quitter.setBounds(450, 520, 252 ,88);
	    layeredPane.add(quitter);
        layeredPane.setLayer(quitter, 1);		  
	    quitter.addMouseListener(new MouseAdapter()
        {
	    	public void mouseEntered(MouseEvent e)
            {
	    		quitter.setIcon(new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/quitter2.png")));
	    		quitter.setBounds(449, 519, 252 ,88);
            }
                       
            public void mousePressed(MouseEvent e)
            {
            	
            	JOptionPane jop = new JOptionPane();		
				int option = jop.showConfirmDialog(null, "Etes-vous s�r de vouloir quitter ?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(option == JOptionPane.OK_OPTION)
				{
					System.exit(0);
				}
				
            	
            	System.out.println("QUITTER");
				//System.exit(0);
            }
            
            public void mouseExited(MouseEvent e)
            {
            	quitter.setIcon(new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/quitter1.png")));
            	quitter.setBounds(450, 520, 252 ,88);
            }
        });
	   
	}
	
}
