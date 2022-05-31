package fr.mcstudio.menu;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Image;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;

/**
 * It's a class named Accueil for the main menu.
 */
public class Accueil {

	// Constructor : Calling the initialize() method and the welcomeMenu() method.
	public Accueil() {
		initialize();
		welcomeMenu();
	}

	/**
	 * The main function is the entry point of the program
	 */
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

	// Creating a new JFrame object.
	private JFrame frame;

	// Creating a JLayeredPane object.
	private JLayeredPane layeredPane;

	// Creating a new JPanel object and assigning it to the variable panel.
	private JPanel panel = new JPanel();

	// Declaring a variable called resolution and assigning it a value of 70.
	private int resolution = 70;

	// Creating a new object of the class Musique.
	private Musique music;

	// Declaring a boolean variable called isMute and setting it to false.
	private boolean isMute = false;

	/**
	 * It creates a JFrame, sets its size, adds a JLayeredPane to it, adds a background image to the
	 * JLayeredPane, adds a mute button to the JLayeredPane, adds a volume up button to the JLayeredPane,
	 * adds a volume down button to the JLayeredPane, and plays a music file
	 */
	private void initialize() {
		this.frame = new JFrame();
		this.setFrameSizeFromResolution(frame);
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    this.frame.setTitle("The Island");
	    this.frame.setLocationRelativeTo(null);
	    this.frame.setVisible(true);
		this.frame.setResizable(false);
	    
	    // Layered Pane Settings :    
		this.layeredPane = new JLayeredPane();
		this.frame.setContentPane(layeredPane);
		this.frame.getContentPane().setLayout(null);
		this.panel.setLayout(null);
		this.panel.setBounds(0, 0, this.frame.getWidth(), this.frame.getHeight());
		this.panel.setOpaque(false);
	    
	    // Background : 
		ImageIcon background = new ImageIcon(
			Accueil.class.getResource("/Menu/Menu/background.jpg"));
		Image scaleImage = background.getImage().getScaledInstance(
			this.frame.getWidth(), this.frame.getHeight(), Image.SCALE_SMOOTH);;
		background.setImage(scaleImage);

		JLabel backgroundLabel = new JLabel(background);
		backgroundLabel.setBounds(
			0, 0, background.getIconWidth(), background.getIconHeight());

        this.layeredPane.setLayer(backgroundLabel, 0);
		this.layeredPane.add(backgroundLabel);

		this.music = new Musique(
			Accueil.class.getClassLoader().getResource("Son/sample1.wav").getPath());

		// Bouton MUTE :
		ImageIcon muteRed = new ImageIcon(
			Accueil.class.getResource("/Menu/Menu/images_80/mute_red.png"));
		Image scaleMuteRed = muteRed.getImage().getScaledInstance(
			muteRed.getIconWidth() * resolution / 80,
			muteRed.getIconHeight() * resolution / 80, 
			Image.SCALE_SMOOTH
		);
		muteRed.setImage(scaleMuteRed);
		ImageIcon muteBrown = new ImageIcon(
			Accueil.class.getResource("/Menu/Menu/images_80/mute_marron.png"));
		Image scaleMuteBrown = muteBrown.getImage().getScaledInstance(
			muteBrown.getIconWidth() * resolution / 80,
			muteBrown.getIconHeight() * resolution / 80,
			Image.SCALE_SMOOTH);
		muteBrown.setImage(scaleMuteBrown);

		JLabel muteLabel = new JLabel(muteBrown);
	    muteLabel.setBounds(
			50 * resolution / 80, 
			35 * resolution / 80, 
			muteBrown.getIconWidth(), 
			muteBrown.getIconHeight()
		);
        this.layeredPane.setLayer(muteLabel, 1);
	    this.layeredPane.add(muteLabel);
	    muteLabel.addMouseListener(new MouseAdapter() {
	    	public void mousePressed(MouseEvent e) {
            	if(!isMute) {
            		music.arreterMusique();
            		muteLabel.setIcon(muteRed);
            		isMute = true ; 
            	} else {
            		music.jouerMusique();
            		muteLabel.setIcon(muteBrown);
                	isMute = false ; 
            	}
            }
	    	
            public void mouseEntered(MouseEvent e) {
            	if(!isMute) {
            		muteLabel.setIcon(muteRed);
            	} else {
            		muteLabel.setIcon(muteBrown);
            	}
            }
            
            public void mouseExited(MouseEvent e)  {
            	if(!isMute) {
            		muteLabel.setIcon(muteBrown);
            	} else {
            		muteLabel.setIcon(muteRed);
            	}
            }
        });
	    
	    // Bouton UP :
		ImageIcon levelUp = new ImageIcon(
			Accueil.class.getResource("/Menu/Menu/images_80/volume_up.png"));
		Image scaleLevelUp = levelUp.getImage().getScaledInstance(
			levelUp.getIconWidth() * resolution / 80,
			levelUp.getIconHeight() * resolution / 80, 
			Image.SCALE_SMOOTH);
		levelUp.setImage(scaleLevelUp);

		JLabel levelUpLabel = new JLabel(levelUp);
	    levelUpLabel.setBounds(
			65 * resolution / 80, 
			170 * resolution / 80, 
			levelUp.getIconWidth(), 
			levelUp.getIconHeight());
        layeredPane.setLayer(levelUpLabel, 1);
	    layeredPane.add(levelUpLabel);
        levelUpLabel.addMouseListener(new MouseAdapter() {
	    	public void mousePressed(MouseEvent e) {
            	if(!isMute) {
            		music.augVolMusique();
            	}
            }
        });
        
    	// Bouton down :
	 	ImageIcon levelDown = new ImageIcon(
			 Accueil.class.getResource("/Menu/Menu/images_80/volume_down.png"));
		Image scaleLevelDown = levelDown.getImage().getScaledInstance(
			levelDown.getIconWidth() * resolution / 80,
			levelDown.getIconHeight() * resolution / 80, 
			Image.SCALE_SMOOTH);
		levelDown.setImage(scaleLevelDown);

		JLabel levelDownLabel = new JLabel(levelDown);
	    levelDownLabel.setBounds(65 * resolution / 80, 120 * resolution / 80, levelDown.getIconWidth(), levelDown.getIconHeight());
        layeredPane.setLayer(levelDownLabel, 1);
	    layeredPane.add(levelDownLabel);
        levelDownLabel.addMouseListener(new MouseAdapter() {
	    	public void mousePressed(MouseEvent e) {
            	if(!isMute) {
            		music.dimVolMusique();
            	}
            }
        });
	}

	/**
	 * It resets the frame and the panel, then adds a background image, a mute button, and two volume
	 * buttons
	 */
	public void reinitialize() {
		this.setFrameSizeFromResolution(frame);
		this.panel.setBounds(0, 0, this.frame.getWidth(), this.frame.getHeight());

		this.layeredPane.removeAll();

		ImageIcon background = new ImageIcon(Accueil.class.getResource("/Menu/Menu/background.jpg"));
		Image scaleImage = background.getImage().getScaledInstance(this.frame.getWidth(), this.frame.getHeight(), Image.SCALE_SMOOTH);;
		background.setImage(scaleImage);

		JLabel backgroundLabel = new JLabel(background);
		backgroundLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

        this.layeredPane.setLayer(backgroundLabel, 0);
		this.layeredPane.add(backgroundLabel);

		// Bouton MUTE :
		ImageIcon muteRed = new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/mute_red.png"));
		Image scaleMuteRed = muteRed.getImage().getScaledInstance(muteRed.getIconWidth() * resolution / 80,
			muteRed.getIconHeight() * resolution / 80, Image.SCALE_SMOOTH);
		muteRed.setImage(scaleMuteRed);
		ImageIcon muteBrown = new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/mute_marron.png"));
		Image scaleMuteBrown = muteBrown.getImage().getScaledInstance(muteBrown.getIconWidth() * resolution / 80,
			muteBrown.getIconHeight() * resolution / 80, Image.SCALE_SMOOTH);
		muteBrown.setImage(scaleMuteBrown);

		JLabel muteLabel = new JLabel();
		if (isMute) {
			muteLabel.setIcon(muteRed);
		} else {
			muteLabel.setIcon(muteBrown);
		}
	    muteLabel.setBounds(50 * resolution / 80, 35 * resolution / 80, muteBrown.getIconWidth(), muteBrown.getIconHeight());
        this.layeredPane.setLayer(muteLabel, 1);
	    this.layeredPane.add(muteLabel);
	    muteLabel.addMouseListener(new MouseAdapter() {
	    	public void mousePressed(MouseEvent e) {
            	if(!isMute) {
            		music.arreterMusique();
            		muteLabel.setIcon(muteRed);
            		isMute = true ; 
            	} else {
            		music.jouerMusique();
            		muteLabel.setIcon(muteBrown);
                	isMute = false ; 
            	}
            }
	    	
            public void mouseEntered(MouseEvent e) {
            	if(!isMute) {
            		muteLabel.setIcon(muteRed);
            	} else {
            		muteLabel.setIcon(muteBrown);
            	}
            }
            
            public void mouseExited(MouseEvent e) {
            	if(!isMute) {
            		muteLabel.setIcon(muteBrown);
            	} else {
            		muteLabel.setIcon(muteRed);
            	}
            }
        });
	    
	    // Bouton UP :
		ImageIcon levelUp = new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/volume_up.png"));
		Image scaleLevelUp = levelUp.getImage().getScaledInstance(levelUp.getIconWidth() * resolution / 80,
			levelUp.getIconHeight() * resolution / 80, Image.SCALE_SMOOTH);
		levelUp.setImage(scaleLevelUp);

		JLabel levelUpLabel = new JLabel(levelUp);
	    levelUpLabel.setBounds(65 * resolution / 80, 170 * resolution / 80, levelUp.getIconWidth(), levelUp.getIconHeight());
        layeredPane.setLayer(levelUpLabel, 1);
	    layeredPane.add(levelUpLabel);
        levelUpLabel.addMouseListener(new MouseAdapter() {
	    	public void mousePressed(MouseEvent e) {
            	if(!isMute) {
            		music.augVolMusique();
            	}
            }
        });
        
    	// Bouton down :
	 	ImageIcon levelDown = new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/volume_down.png"));
		Image scaleLevelDown = levelDown.getImage().getScaledInstance(levelDown.getIconWidth() * resolution / 80,
			levelDown.getIconHeight() * resolution / 80, Image.SCALE_SMOOTH);
		levelDown.setImage(scaleLevelDown);

		JLabel levelDownLabel = new JLabel(levelDown);
	    levelDownLabel.setBounds(65 * resolution / 80, 120 * resolution / 80, levelDown.getIconWidth(), levelDown.getIconHeight());
        layeredPane.setLayer(levelDownLabel, 1);
	    layeredPane.add(levelDownLabel);
        levelDownLabel.addMouseListener(new MouseAdapter() {
	    	public void mousePressed(MouseEvent e) {
            	if(!isMute) {
            		music.dimVolMusique();
            	}
            }
        });
	}

	/**
	 * It removes all the components from the panel, removes the panel from the layered pane, and then
	 * repaints the layered pane
	 */
	private void clearWindow() {
		this.panel.removeAll();
		this.layeredPane.remove(this.panel);
		this.layeredPane.revalidate();
		this.layeredPane.repaint();
	}

	/**
	 * It sets the size of the frame based on the resolution
	 * 
	 * @param frame The JFrame that you want to set the size of.
	 */
	private void setFrameSizeFromResolution(JFrame frame) {
		switch (resolution) {
			case 70:
				frame.setSize(1432, 809);
				break;
			case 80:
				frame.setSize(1635, 919);
				break;
			case 90:
				frame.setSize(1850, 1029);
				break;
			default:
				break;
		}
	}

	/**
	 * It's the display for the main menu.
	 * It creates a JPanel, adds some JLabels to it, and then adds the JPanel to a JLayeredPane
	 */
	public void welcomeMenu() {
		clearWindow();
	    
	    // Titre :
		ImageIcon title = new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/titre.png"));
		Image scaleTitle = title.getImage().getScaledInstance(title.getIconWidth() * resolution / 80,
			title.getIconHeight() * resolution / 80, Image.SCALE_SMOOTH);
		title.setImage(scaleTitle);

		JLabel titleLabel = new JLabel(title);
	    titleLabel.setBounds(592 * resolution / 80, 150 * resolution / 80, title.getIconWidth(), title.getIconHeight());
		this.panel.add(titleLabel);
	    
        // Bouton JOUER :
		ImageIcon play = new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/jouer1.png"));
		Image scalePlay = play.getImage().getScaledInstance(play.getIconWidth() * resolution / 80,
			play.getIconHeight() * resolution / 80, Image.SCALE_SMOOTH);
		play.setImage(scalePlay);
		ImageIcon play2 = new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/jouer2.png"));
		Image scalePlay2 = play2.getImage().getScaledInstance(play2.getIconWidth() * resolution / 80,
			play2.getIconHeight() * resolution / 80, Image.SCALE_SMOOTH);
		play2.setImage(scalePlay2);

		JLabel playLabel = new JLabel(play);
	    playLabel.setBounds(692 * resolution / 80, 400 * resolution / 80, play.getIconWidth(), play.getIconHeight());
		this.panel.add(playLabel);  
	    playLabel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
            	playLabel.setIcon(play2);
            }
            
            public void mousePressed(MouseEvent e) {
                launchMenu();
            }
            
            public void mouseExited(MouseEvent e) {
            	playLabel.setIcon(play);
            }
        });

	    
        // Bouton OPTIONS :
		ImageIcon settings = new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/option1.png"));
		Image scaleSettings = settings.getImage().getScaledInstance(settings.getIconWidth() * resolution / 80,
			settings.getIconHeight() * resolution / 80, Image.SCALE_SMOOTH);
		settings.setImage(scaleSettings);
		ImageIcon settings2 = new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/option2.png"));
		Image scaleSettings2 = settings2.getImage().getScaledInstance(settings2.getIconWidth() * resolution / 80,
			settings2.getIconHeight() * resolution / 80, Image.SCALE_SMOOTH);
		settings2.setImage(scaleSettings2);

		JLabel settingsLabel = new JLabel(settings);
	    settingsLabel.setBounds(950 * resolution / 80, 525 * resolution / 80, settings.getIconWidth(), settings.getIconHeight());
		this.panel.add(settingsLabel);
	    settingsLabel.addMouseListener(new MouseAdapter() {
	    	public void mouseEntered(MouseEvent e) {
	    		settingsLabel.setIcon(settings2);
            }
	    	
            public void mousePressed(MouseEvent e) {
               optionMenu();
            }
            
            public void mouseExited(MouseEvent e) {
            	settingsLabel.setIcon(settings);
            }
        });
	    
	    
	    // Bouton QUITTER :
		ImageIcon quit = new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/quittez1.png"));
		Image scaleQuit = quit.getImage().getScaledInstance(quit.getIconWidth() * resolution / 80,
			quit.getIconHeight() * resolution / 80, Image.SCALE_SMOOTH);
		quit.setImage(scaleQuit);
		ImageIcon quit2 = new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/quittez2.png"));
		Image scaleQuit2 = quit2.getImage().getScaledInstance(quit2.getIconWidth() * resolution / 80,
			quit2.getIconHeight() * resolution / 80, Image.SCALE_SMOOTH);
		quit2.setImage(scaleQuit2);

		JLabel quitLabel = new JLabel(quit);
	    quitLabel.setBounds(450 * resolution / 80, 520 * resolution / 80, quit.getIconWidth(), quit.getIconHeight());
		this.panel.add(quitLabel);	  
	    quitLabel.addMouseListener(new MouseAdapter() {
	    	public void mouseEntered(MouseEvent e) {
	    		quitLabel.setIcon(quit2);
            }
                       
            public void mousePressed(MouseEvent e) {		
				int option = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir quitter ?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(option == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
            }
            
            public void mouseExited(MouseEvent e) {
            	quitLabel.setIcon(quit);
            }
        });

		this.layeredPane.setLayer(this.panel, 1);
		this.layeredPane.add(this.panel);
	}

	/**
	 * It's the display for the launch menu.
	 * It creates a new JPanel, adds a JLabel and a JButton to it, and then adds the JPanel to a
	 * JLayeredPane.
	 */
	private void launchMenu() {
		clearWindow();
		// Titre :
		ImageIcon title = new ImageIcon(Accueil.class.getResource("/Menu/Menu/titre_jouer.png"));
		Image scaleTitle = title.getImage().getScaledInstance(title.getIconWidth() * resolution / 80,
			title.getIconHeight() * resolution / 80, Image.SCALE_SMOOTH);
		title.setImage(scaleTitle);

		JLabel titleLabel = new JLabel(title);
	    titleLabel.setBounds(517 * resolution / 80, 100 * resolution / 80, title.getIconWidth(), title.getIconHeight());
		this.panel.add(titleLabel);
        
        // Menu Choix Nombre Joueurs :
        Choix choice = new Choix(this);
		choice.displayChoice(this.panel, this.frame, this.resolution);

		JButton retour = new JButton("Retour");
		retour.setFont(new Font("Showcard Gothic", Font.BOLD, 30 * resolution / 80));
		retour.setBounds(710 * resolution / 80, this.panel.getHeight() - 50 * resolution / 80 - 50, 250 * resolution / 80, 50 * resolution / 80);
		this.panel.add(retour);
		retour.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
        		welcomeMenu();
            }
        });
  
		this.layeredPane.add(this.panel);
		this.layeredPane.setLayer(this.panel, 1);
	}

	/**
	 * It returns the layered pane.
	 * 
	 * @return The layeredPane variable is being returned.
	 */
	public JLayeredPane getLayeredPane() {
		return layeredPane;
	}

	/**
	 * It creates a menu with 3 buttons, one of which is supposed to open a JOptionPane with a JList in it
	 */
	private void optionMenu() {
		clearWindow();
		// Titre :
		ImageIcon title = new ImageIcon(Accueil.class.getResource("/Menu/Menu/titre_jouer.png"));
		Image scaleTitle = title.getImage().getScaledInstance(title.getIconWidth() * resolution / 80,
			title.getIconHeight() * resolution / 80, Image.SCALE_SMOOTH);
		title.setImage(scaleTitle);

		JLabel titleLabel = new JLabel(title);
	    titleLabel.setBounds(517 * resolution / 80, 100 * resolution / 80, title.getIconWidth(), title.getIconHeight());
		this.panel.add(titleLabel);
		    
		// OPTION :
		JLabel lblNewLabel = new JLabel("OPTIONS");
		lblNewLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 60 * resolution / 80));
	    lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(550 * resolution / 80, 300 * resolution / 80, 553 * resolution / 80, 110 * resolution / 80);
		this.panel.add(lblNewLabel);
		
		// Bouton AIDE :
		ImageIcon help = new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/aide_1.png"));
		Image scaleHelp = help.getImage().getScaledInstance(help.getIconWidth() * resolution / 80,
			help.getIconHeight() * resolution / 80, Image.SCALE_SMOOTH);
		help.setImage(scaleHelp);

		JLabel helpLabel = new JLabel(help);
	    helpLabel.setBounds(550 * resolution / 80, 430 * resolution / 80, help.getIconWidth(), help.getIconHeight());
		this.panel.add(helpLabel);
        helpLabel.addMouseListener(new MouseAdapter() {
	    	public void mousePressed(MouseEvent e) {		
				int option = JOptionPane.showConfirmDialog(
					null,
					"Vous allez être redirigé vers une page WEB ! ",
					"Règles du Jeu",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE
				);
				if(option == JOptionPane.OK_OPTION) {
					try {
						Desktop.getDesktop().browse(new URI("http://carnetdesgeekeries.com/j2s-the-island-asmodee/"));
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
            }
	    	
        });
		
				
		// Bouton DIMENSIONS :	
		ImageIcon dimensions = new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/dim_1.png"));
		Image scaleDimensions = dimensions.getImage().getScaledInstance(dimensions.getIconWidth() * resolution / 80,
			dimensions.getIconHeight() * resolution / 80, Image.SCALE_SMOOTH);
		dimensions.setImage(scaleDimensions);

		JLabel dimensionsLabel = new JLabel(dimensions);
	    dimensionsLabel.setBounds(950 * resolution / 80, 430 * resolution / 80, dimensions.getIconWidth(), dimensions.getIconHeight());
		this.panel.add(dimensionsLabel);
        dimensionsLabel.addMouseListener(new MouseAdapter() {
	    	public void mousePressed(MouseEvent e) {
        		JList<String> list = new JList<String>(new String[] {"Petit : 1432 x 809", "Moyen : 1635 x 919" , "Grand : 1850 x 1029" });
        		JOptionPane.showMessageDialog(null, list, "Choix du dimensionnement", JOptionPane.PLAIN_MESSAGE);
        		
        		if(list.getSelectedValue() == "Petit : 1432 x 809") {
        			resolution = 70 ;        			
        		} else if (list.getSelectedValue() == "Moyen : 1635 x 919") {
        			resolution = 80 ;
        		} else if (list.getSelectedValue() == "Grand : 1850 x 1029") {
        			resolution = 90 ;
        		}

				if(resolution != 0) {
					reinitialize();
					optionMenu();
				}
            }
        });
        
        
        // Bouton RETOUR :	
		ImageIcon back = new ImageIcon(Accueil.class.getResource("/Menu/Menu/images_80/retour_1.png"));
		Image scaleBack = back.getImage().getScaledInstance(back.getIconWidth() * resolution / 80,
			back.getIconHeight() * resolution / 80, Image.SCALE_SMOOTH);
		back.setImage(scaleBack);

		JLabel backLabel = new JLabel(back);
	    backLabel.setBounds(750 * resolution / 80, 600 * resolution / 80, back.getIconWidth(), back.getIconHeight());
		this.panel.add(backLabel);
        backLabel.addMouseListener(new MouseAdapter() {
	    	public void mousePressed(MouseEvent e) {
	    		welcomeMenu();
            }
        });

		this.layeredPane.add(this.panel);
		this.layeredPane.setLayer(this.panel, 1);
	}
}
