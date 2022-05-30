package fr.mcstudio.board;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import fr.mcstudio.game.Game;
import fr.mcstudio.pawns.Shark;
import fr.mcstudio.tiles.Tile;
import fr.mcstudio.enums.ExternalPanelState;
import fr.mcstudio.enums.PawnType;
import fr.mcstudio.enums.SideBarButton;

@SuppressWarnings("serial")
public class ActionInfo extends JLayeredPane {

	private JLabel actionInfoLabel = new JLabel();
	private JPanel actionInfoPanel = new JPanel();

	private JLabel actionTitle = new JLabel("null", SwingConstants.CENTER);
	private JLabel actionDesc = new JLabel("null", SwingConstants.CENTER);

	private JPanel actionLabel = new JPanel();

	private List<JButton> buttons = new ArrayList<JButton>();
	private List<JLabel> diceImage =  new ArrayList<JLabel>();

	public ActionInfo(Game game, int resolution) {
		super();
		this.setLayer(this.actionInfoLabel, 0);
		this.setLayout(null);
		this.setPanelBoundsFromResolution(resolution);
		this.setLabel();
		this.add(this.actionInfoLabel);

		this.actionInfoPanel.setLayout(null);
		this.actionInfoPanel.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.actionInfoPanel.setOpaque(false);
		this.setLayer(actionInfoPanel, 1);
		this.add(actionInfoPanel);

		Font sizedFont = null;
		try {
			InputStream is = ActionInfo.class.getResourceAsStream("/Font/Treasuremap.ttf");
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			sizedFont = font.deriveFont(15f);
		} catch (Exception ex) {
			System.err.println("Not loaded");
		}

		this.actionTitle.setFont(sizedFont);
		this.actionTitle.setBounds(
			0, 
			32 * resolution / 90, 
			this.getWidth(), 
			70 * resolution / 90
		);
		this.actionInfoPanel.add(actionTitle);

		this.actionDesc.setFont(sizedFont);
		this.actionDesc.setBounds(
			34 * resolution / 90, 
			150 * resolution / 90, 
			260 * resolution / 90, 
			130 * resolution / 90
		);
		this.actionInfoPanel.add(actionDesc);

		this.actionLabel.setOpaque(false);
		this.actionLabel.setBounds(
			34 * resolution / 90, 
			290 * resolution / 90, 
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

		for (String image : imagePath) {
			ImageIcon imageButton = new ImageIcon(ActionInfo.class.getResource(image));
			Image scaleImage = imageButton.getImage().getScaledInstance(imageButton.getIconWidth() * resolution / 90,
					imageButton.getIconHeight() * resolution / 90, Image.SCALE_SMOOTH);
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
						}
					};
						
					break;
				case "/SideBar/quitButton.png":
					actionListener = new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
						}
					};
							
					break;
				case "/SideBar/seeTilesButton.png":
					actionListener = new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							onClickTilesButton(game);
						}
					};
								
					break;
				case "/SideBar/rollButton.png":
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

		for (String path : dicePath) {
			ImageIcon image = new ImageIcon(ActionInfo.class.getResource(path));
			Image scaleImage = image.getImage().getScaledInstance(image.getIconWidth() * resolution / 90,
			image.getIconHeight() * resolution / 90, Image.SCALE_SMOOTH);
			image.setImage(scaleImage);
			this.diceImage.add(new JLabel(image));

		}

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

	public void displayInitialActionInfo(Game game) {
		this.actionTitle.setText("<html>Posez vos pions !</html>");
		this.actionDesc.setText("<html><center>Posez vos 10 pions et 2 bateaux!</center></html>");
		this.actionLabel.removeAll();
		this.actionLabel.revalidate();
		this.actionLabel.repaint();
		JLabel pawnNumber = new JLabel(
			"Il vous reste " 
			+ game.getCurrentPlayer().getExplorerList().size() 
			+ " exploreurs et " 
			+ game.getCurrentPlayer().getBoatToSet().size() 
			+ " à poser",
			SwingConstants.CENTER
		);
		JLabel pawnValue = new JLabel(
			"L'exploreur que vous allez poser possède " 
			+ game.getCurrentPlayer().getExplorerList().get(0).getTreasureValue() 
			+ " trésors ",
			SwingConstants.CENTER
		);
		actionLabel.add(pawnNumber);
	}

	public void displayActionInfo(Game game) {

		this.actionTitle.setText(game.getActionTurn().getTitle());
		this.actionDesc.setText(game.getActionTurn().getDesc());

		this.actionLabel.removeAll();
		this.actionLabel.revalidate();
		this.actionLabel.repaint();

		this.buttons.get(SideBarButton.SKIP).setVisible(true);

		switch (game.getActionTurn()) {
			case PLAY_TILE:
				
				if(!game.getCurrentPlayer().getTileList().isEmpty()) {
					actionLabel.add(this.buttons.get(SideBarButton.SEETILES));
				} else {
					actionLabel.add(new JLabel("Vous n'avez pas de tuile, appuyez sur < Passez votre tour >"));
				}
				break;

			case MOVE_PAWNS:
				JLabel moveLeft = new JLabel(
						"Il vous reste " + game.getCurrentPlayer().getMoveLeft() + " déplacements");
				moveLeft.setVerticalAlignment(SwingConstants.CENTER);
				moveLeft.setHorizontalAlignment(SwingConstants.CENTER);
				actionLabel.add(moveLeft);
				break;

			case DISCOVER_TILE:
				this.buttons.get(SideBarButton.SKIP).setVisible(false);
				break;

			case MOVE_MONSTER:
				if (game.getBoard().getExternalPanel().getPawnType() == null) {
					actionLabel.add(this.buttons.get(SideBarButton.ROLL));
					this.buttons.get(SideBarButton.ROLL).setVisible(true);
					this.buttons.get(SideBarButton.SKIP).setVisible(false);
				} else {
					if (!game.getBoard().isDisplayExternalPanel()) {	
						this.buttons.get(SideBarButton.ROLL).setVisible(false);
						switch (game.getBoard().getExternalPanel().getPawnType()) {
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
	}

	public void onClickTilesButton(Game game){
		if(!game.getCurrentPlayer().getTileList().isEmpty()) {
			if(game.getUsedTile() == null) {
				if (game.getBoard().getExternalPanel().getSelection() != null) {

					game.setUsedTile((Tile)game.getBoard().getExternalPanel().getSelection());
					game.getBoard().getExternalPanel().setSelection(null);
					//Effectuer l'effet de la tuile
					
					//
					game.nextActionTurn();
				} else {
					game.getBoard().setDisplayExternalPanel(true);
					game.getBoard().getExternalPanel().setExternalPanelState(ExternalPanelState.TILEEFFECTPANEL);
				}
			}
			displayActionInfo(game);
		}
	}

	public void onClickRollButton(Game game, JButton button){
		if (game.getBoard().getExternalPanel().getPawnType() == null) {
			button.setVisible(false);
			displayActionInfo(game);
			game.inGameMoveMonster(null);
		}
	}

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

	private void setLabel() {
		ImageIcon icone = new ImageIcon(Board.class.getResource("/ActionInfo.png"));
		Image scaleImage = icone.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);

		icone.setImage(scaleImage);
		actionInfoLabel.setIcon(icone);
		actionInfoLabel.setBounds(0, 0, getWidth(), getHeight());
	}
}
