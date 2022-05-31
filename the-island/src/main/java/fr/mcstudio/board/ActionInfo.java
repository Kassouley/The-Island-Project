package fr.mcstudio.board;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fr.mcstudio.enums.ExternalPanelState;
import fr.mcstudio.enums.GameState;
import fr.mcstudio.enums.HelpType;
import fr.mcstudio.enums.SideBarButton;
import fr.mcstudio.game.Game;

@SuppressWarnings("serial")

/**
 * It's a JLayeredPane that displays information about the current action turn.
 */
public class ActionInfo extends JLayeredPane {


	// Creating a new ActionInfo object.
	public ActionInfo(Game game, int resolution) {
		super();

		// Setting the bounds of the panel and adding the label to the panel.
		this.setLayer(this.actionInfoLabel, 0);
		this.setLayout(null);
		this.setPanelBoundsFromResolution(resolution);
		this.setLabel();
		this.add(this.actionInfoLabel);

		// Setting the layout of the actionInfoPanel to null, setting the bounds of the actionInfoPanel to
		// the width and height of the JPanel, setting the actionInfoPanel to be transparent, setting the
		// layer of the actionInfoPanel to 1, and adding the actionInfoPanel to the JPanel.
		this.actionInfoPanel.setLayout(null);
		this.actionInfoPanel.setBounds(0, 0, 
				this.getWidth(), this.getHeight());
		this.actionInfoPanel.setOpaque(false);
		this.setLayer(actionInfoPanel, 1);
		this.add(actionInfoPanel);

		// Loading a font from the jar file.
		try {
			InputStream is = ActionInfo.class
					.getResourceAsStream("/Font/Treasuremap.ttf");
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			sizedFont = font.deriveFont(15f);
		} catch (Exception ex) {
			System.err.println("Not loaded");
		}

		// Setting the font and bounds of the actionTitle.
		this.actionTitle.setFont(this.sizedFont);
		this.actionTitle.setBounds(
			0, 
			32 * resolution / 90, 
			this.getWidth(), 
			70 * resolution / 90
		);
		this.actionInfoPanel.add(actionTitle);

		// Setting the font and bounds of the actionDesc object.
		this.actionDesc.setFont(this.sizedFont);
		this.actionDesc.setBounds(
			34 * resolution / 90, 
			150 * resolution / 90, 
			260 * resolution / 90, 
			130 * resolution / 90
		);
		this.actionInfoPanel.add(actionDesc);

		// Setting the bounds of the actionLabel.
		this.actionLabel.setOpaque(false);
		this.actionLabel.setLayout(null);
		this.actionLabel.setBounds(
			34 * resolution / 90, 
			250 * resolution / 90, 
			260 * resolution / 90, 
			300 * resolution / 90
		);
		this.actionInfoPanel.add(this.actionLabel);

		String[] imagePath = {
				"/SideBar/skipButton.png",
				"/SideBar/rulesButton.png",
				"/SideBar/quitButton.png",
				"/SideBar/seeTilesButton.png",
				"/SideBar/rollButton.png"
		};

		String[] dicePath = {
			"/seaSnakeDice.png",
			"/sharkDice.png",
			"/whaleDice.png"
		};

		ActionListener actionListener = null; 

		// Creating a button from each image of imagePath and adding an action listener to it.
		for (String image : imagePath) {
			ImageIcon imageButton = new ImageIcon(ActionInfo.class.
					getResource(image));
			Image scaleImage = imageButton.getImage()
					.getScaledInstance(imageButton
							.getIconWidth() * resolution / 90,
					imageButton
					.getIconHeight() * resolution / 90, Image.SCALE_SMOOTH);
			imageButton.setImage(scaleImage);
			JButton button = new JButton(imageButton);

			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setFocusPainted(false);
			button.setBorderPainted(false);
			

			switch (image) {
			case "/SideBar/skipButton.png":
				actionListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						game.nextActionTurn();
						displayActionInfo(game);
						game.inGame(null);
					}
				};
				
				break;
			case "/SideBar/rulesButton.png":
				actionListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(game.getGameState() == 
								GameState.INITIALISATION) {
							game.getBoard()
									.getExternalPanel()
									.setHelpType(HelpType.INITIALISATION);
						} else {

							game.getBoard()
									.getExternalPanel()
									.setHelpType(HelpType.
											values()[game.getActionTurn()
											         .ordinal()+1]);
						}
						game.getBoard()
								.setDisplayExternalPanel(true);
						game.getBoard()
								.getExternalPanel()
								.setExternalPanelState(ExternalPanelState
										.HELPPANEL);
					}
				};
					
				break;
			case "/SideBar/quitButton.png":
				actionListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int option = JOptionPane
								.showConfirmDialog(null, 
										"Etes-vous s�r "
										+ "de vouloir quitter ?", 
										"Quitter", 
										JOptionPane.YES_NO_OPTION, 
										JOptionPane.QUESTION_MESSAGE);
						if(option == JOptionPane.OK_OPTION)
						{
							game.getAccueil().reinitialize();
							game.getAccueil().welcomeMenu();
						}
					}
				};
						
				break;
			case "/SideBar/seeTilesButton.png":
				button.setBounds(
					0, 
					0, 
					this.actionLabel.getWidth(), 
					this.actionLabel.getHeight()
				);
				actionListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onClickTilesButton(game);
					}
				};
							
				break;
			case "/SideBar/rollButton.png":
				button.setBounds(
					0, 
					0, 
					this.actionLabel.getWidth(), 
					this.actionLabel.getHeight()
				);
				actionListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onClickRollButton(game, (JButton) e.getSource());
					}
				};
								
				break;
			
		
			default:
				break;
			}
			button.addActionListener(actionListener);
			buttons.add(button);
		}

		// Creating a JLabel for each image in the dicePath array.
		for (String path : dicePath) {
			ImageIcon image = new ImageIcon(ActionInfo.class
					.getResource(path));
			Image scaleImage = image.getImage()
					.getScaledInstance(image
							.getIconWidth() * resolution / 90,
							image.getIconHeight() * resolution / 90, 
							Image.SCALE_SMOOTH);
			image.setImage(scaleImage);
			this.diceImage.add(new JLabel(image));
			this.diceImage.get(0).setBounds(
				0, 
				0, 
				this.actionLabel.getWidth(), 
				this.actionLabel.getHeight()
			);
		}

		// Setting the bounds of the buttons and adding them to the actionInfoPanel.
		this.buttons.get(SideBarButton.SKIP).setBounds(
			0, 635 * resolution / 90, this.getWidth(), 115 * resolution / 90
		);
		this.buttons.get(SideBarButton.RULES).setBounds(
			0, 750 * resolution / 90, this.getWidth(), 115 * resolution / 90
		);
		this.buttons.get(SideBarButton.QUIT).setBounds(
			0, 875 * resolution / 90, this.getWidth(), 115 * resolution / 90
		);
		this.actionInfoPanel.add(this.buttons.get(SideBarButton.SKIP));
		this.actionInfoPanel.add(this.buttons.get(SideBarButton.RULES));
		this.actionInfoPanel.add(this.buttons.get(SideBarButton.QUIT));
	}

	// Creating a new JLabel object.
	private JLabel actionInfoLabel = new JLabel();

	// Creating a new JPanel object and assigning it to the variable actionInfoPanel.
	private JPanel actionInfoPanel = new JPanel();

	// Creating a new JLabel object with the text "null" and the alignment CENTER.
	private JLabel actionTitle = new JLabel("null", SwingConstants.CENTER);

	// Creating a new JLabel object with the text "null" and the alignment CENTER.
	private JLabel actionDesc = new JLabel("null", SwingConstants.CENTER);

	// Creating a new JPanel object and assigning it to the variable actionLabel.
	private JPanel actionLabel = new JPanel();

	// Creating a new ArrayList of JButtons.
	private List<JButton> buttons = new ArrayList<JButton>();

	// Creating a list of JLabels.
	private List<JLabel> diceImage =  new ArrayList<JLabel>();

	// Creating a new font object with the size of the font being the value of the variable sizedFont.
	private Font sizedFont;

	/**
	 * It displays the information of the current action turn of the game
	 * 
	 * @param game the game object
	 */
	public void displayActionInfo(Game game) {

		this.actionTitle.setText(game.getActionTurn().getTitle());
		this.actionDesc.setText(game.getActionTurn().getDesc());

		this.actionLabel.removeAll();
		this.actionLabel.revalidate();
		this.actionLabel.repaint();

		this.buttons.get(SideBarButton.SKIP).setVisible(true);

		if (game.getGameState() == GameState.PLAYING) {
			
			switch (game.getActionTurn()) {
			case PLAY_TILE:
				
				if(!game.getCurrentPlayer()
						.getTileList()
						.isEmpty()) {
					
					actionLabel.add(this.buttons
							.get(SideBarButton.SEETILES));
				} else {
					JLabel text = new JLabel(
						"<html><center>Vous n'avez pas de tuile,"
						+ " appuyez sur "
						+ "\"Passez votre tour\"</center></html>", 
						SwingConstants.CENTER
					);
					text.setBounds(
						0, 
						0, 
						this.actionLabel.getWidth(), 
						this.actionLabel.getHeight()
					);
					text.setFont(this.sizedFont);
					actionLabel.add(text);
				}
				break;

			case MOVE_PAWNS:
				JLabel moveLeft = new JLabel(
						"<html><center>Il vous reste " 
						+ game.getCurrentPlayer().getMoveLeft()
						+ " déplacements</center></html>", 
						SwingConstants.CENTER);
				moveLeft.setBounds(
					0, 
					0, 
					this.actionLabel.getWidth(), 
					this.actionLabel.getHeight()
				);
				moveLeft.setFont(this.sizedFont);
				actionLabel.add(moveLeft);
				break;

			case DISCOVER_TILE:
				this.buttons.get(SideBarButton.SKIP).setVisible(false);
				break;

			case MOVE_MONSTER:
				if (game.getBoard()
						.getExternalPanel()
						.getPawnType() == null) {
					
					actionLabel.add(this.buttons
							.get(SideBarButton.ROLL));
					this.buttons.get(SideBarButton.ROLL)
							.setVisible(true);
					this.buttons.get(SideBarButton.SKIP)
							.setVisible(false);
					
				} else {
					if (!game.getBoard().isDisplayExternalPanel()) {	
						this.buttons.get(SideBarButton.ROLL)
								.setVisible(false);
						switch (game.getBoard().getExternalPanel()
								.getPawnType()) {
						case SEASNAKE:
							actionLabel.add(diceImage.get(0));
							break;
						case SHARK:
							actionLabel.add(diceImage.get(1));
							break;						
						case WHALE:
							actionLabel.add(diceImage.get(2));
							break;
						default:
                            break;
						}
					}	
				}
				break;

			default:
				break;
			}		
		} else if(game.getGameState() == GameState.INITIALISATION) {
			this.buttons.get(SideBarButton.SKIP).setVisible(false);
			this.actionTitle.setText("<html>Posez vos pions !</html>");
			this.actionDesc.setText("<html><center>Posez vos 10 pions"
									+ "et 2 bateaux!</center></html>");
			this.actionLabel.removeAll();
			this.actionLabel.revalidate();
			this.actionLabel.repaint();
			String initText = new String(
				"<html><center>Il vous reste " 
				+ game.getCurrentPlayer().getExplorerList().size() 
				+ " explorateur(s) et " 
				+ game.getCurrentPlayer().getBoatToSet().size() 
				+ " bateau(x) à poser"
			);
			
			if (game.getCurrentPlayer().getExplorerList().size() != 0) {
				String pawnValue = new String(
					"<br><br>L'explorateur que vous allez poser possède " 
					+ game.getCurrentPlayer()
							.getExplorerList()
							.get(0)
							.getTreasureValue() 
					+ " trésors</center></html>"
				);
				initText = initText + pawnValue;
			
			}
			JLabel initTextLabel = new JLabel(initText, 
					SwingConstants.CENTER);
			initTextLabel.setFont(this.sizedFont);
			initTextLabel.setBounds(
				0, 
				0, 
				this.actionLabel.getWidth(), 
				this.actionLabel.getHeight()
			);	
			actionLabel.add(initTextLabel);
		}
	}

	/**
	 * If the player has tiles, he can play it by clicking on the corresponding button.
	 * 
	 * @param game The game object
	 */
	public void onClickTilesButton(Game game){
		if(!game.getCurrentPlayer().getTileList().isEmpty()) {
			if(game.getUsedTile() == null) {
				if (game.getBoard()
						.getExternalPanel()
						.getSelection() != null) {
					
					if(((Tile)game.getBoard().
							getExternalPanel().
							getSelection()).
							checkPlayTile(game.getBoard(), 
									game.getCurrentPlayer())) {
						
						game.setUsedTile((Tile)game.getBoard()
								.getExternalPanel()
								.getSelection());
						
						game.getBoard()
								.getExternalPanel()
								.setSelection(null);
					}
				} else {
					game.getBoard()
							.setDisplayExternalPanel(true);
					game.getBoard()
							.getExternalPanel()
							.setExternalPanelState(ExternalPanelState
									.TILEEFFECTREDPANEL);
				}
			}
			displayActionInfo(game);			
		}
	}

	/**
	 * If the player click on the roll button, we roll the dice and move the monster.
	 * 
	 * @param game the game object
	 * @param button the button that was clicked
	 */
	public void onClickRollButton(Game game, JButton button){
		if (game.getBoard().getExternalPanel().getPawnType() == null) {
			button.setVisible(false);
			displayActionInfo(game);
			game.inGameMoveMonster(null);
		}
	}



	/**
	 * Set the bound from the resolution of the panel.
	 * 
	 * @param resolution The resolution of the screen.
	 */
	private void setPanelBoundsFromResolution(int resolution) {
		switch (resolution) {
			case 70:
				setBounds(1162, 0, 254, 770);
				break;
			case 80:
				setBounds(1338, 0, 281, 880);
				break;
			case 90:
				setBounds(1512, 0, 320, 990);
				break;
			default:
				break;
		}
	}

	/**
	 * Set the background image of the panel.
	 */
	private void setLabel() {
		ImageIcon icone = new ImageIcon(Board.class
				.getResource("/ActionInfo.png"));
		Image scaleImage = icone.getImage()
				.getScaledInstance(getWidth(), 
						getHeight(), 
						Image.SCALE_SMOOTH);

		icone.setImage(scaleImage);
		actionInfoLabel.setIcon(icone);
		actionInfoLabel.setBounds(0, 0, getWidth(), getHeight());
	}

}
