package fr.mcstudio.board;

import java.awt.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import fr.mcstudio.game.Game;
import fr.mcstudio.game.Player;

@SuppressWarnings("serial")


/**
 * It's a JLayeredPane that displays information about the current player.
 */
public class PlayerInfo extends JLayeredPane {

	
	// It's creating a new PlayerInfo object.
	public PlayerInfo(int resolution) {
		super();
		this.setLayer(this.playerInfoLabel, 0);
		this.setLayout(null);
		this.setPanelBoundsFromResolution(resolution);
		this.setLabel();
		this.add(this.playerInfoLabel);

		this.playerInfoPanel.setLayout(null);
		this.playerInfoPanel.setBounds(0, 0, 
				this.getWidth(), this.getHeight());
		this.playerInfoPanel.setOpaque(false);
		this.setLayer(playerInfoPanel, 1);
		this.add(playerInfoPanel);

		// It's creating a new Font object and assigning it to the variable sizedFont.
		Font sizedFont = null;
		try {
			InputStream is = ActionInfo.class
					.getResourceAsStream("/Font/Treasuremap.ttf");
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			sizedFont = font.deriveFont(18f);
		} catch (Exception ex) {
			System.err.println("Not loaded");
		}

		// It's creating 7 JLabel objects and adding them to the ArrayList infosPlayer.
		for (int i = 0; i < 7; i++) {
			JLabel text = new JLabel("null", SwingConstants.CENTER);
			text.setFont(sizedFont);
			text.setBounds(
				0,
				this.pos[i][0] * resolution / 90,
				this.getWidth(), 
				this.pos[i][0] * resolution / 90
			);
			this.playerInfoPanel.add(text);
			this.infosPlayer.add(text);
		}

		this.avatarPlayer.setBounds(15 * resolution / 90, 
				120 * resolution / 90,
				256 * resolution / 90, 
				256 * resolution / 90);
		this.playerInfoPanel.add(avatarPlayer);
	}


	// It's creating a new JLabel object and assigning it to the variable playerInfoLabel.
	private JLabel playerInfoLabel = new JLabel();
	
	// It's creating a new JPanel object and assigning it to the variable playerInfoPanel.
	private JPanel playerInfoPanel = new JPanel();

	// It's creating a new JLabel object and assigning it to the variable avatarPlayer.
	private JLabel avatarPlayer = new JLabel();

	// It's creating a new ArrayList object and assigning it to the variable infosPlayer.
	private List<JLabel> infosPlayer = new ArrayList<JLabel>();

	// It's creating a new array of ints and assigning it to the variable pos.
	private int[][] pos = {
		{ 43, 50 },
		{ 330, 50 },
		{ 390, 50 },
		{ 445, 50 },
		{ 540, 50 },
		{ 570, 50 },
		{ 600, 50 },
	};

	/**
	 * It displays the information of the current player in the GUI
	 * 
	 * @param game the game object
	 * @param resolution the resolution of the screen
	 */
	public void displayPlayerInfo(Game game, int resolution) {

		Player currentPlayer = game.getCurrentPlayer();
		ArrayList<Player> players = game.getPlayers();
		int i = 0;

		String[] textInfo = {
				currentPlayer.getPseudo(),
				Integer.toString(currentPlayer.getNumberExplorerAlive()),
				Integer.toString(currentPlayer.getNumberExplorerSaved()),
				Integer.toString(currentPlayer.getNumberExplorerDead()),
				players.get((game.getCurrentPlayerIndex() + 1) % 
						players.size()).getPseudo(),
				players.get((game.getCurrentPlayerIndex() + 2) % 
						players.size()).getPseudo(),
				players.get((game.getCurrentPlayerIndex() + 3) % 
						players.size()).getPseudo()
		};

		for (String text : textInfo) {
			this.infosPlayer.get(i).setText(text);
			i++;
		}

		this.avatarPlayer.setIcon(currentPlayer.getAvatar());
	}

	/**
	 * It sets the bounds of the panel based on the resolution
	 * 
	 * @param resolution The resolution
	 */
	private void setPanelBoundsFromResolution(int resolution) {
		switch (resolution) {
			case 70:
				setBounds(0, 0, 217, 770);
				break;
			case 80:
				setBounds(0, 0, 248, 880);
				break;
			case 90:
				setBounds(0, 0, 282, 990);
				break;
			default:
				break;
		}
	}

	/**
	 * It takes the image from the resource folder, scales it to the size of the JPanel, and then sets the
	 * background to the scaled image
	 */
	private void setLabel() {
		ImageIcon icone = new ImageIcon(PlayerInfo.class
				.getResource("/PlayerInfo.png"));
		Image scaleImage = icone.getImage()
				.getScaledInstance(getWidth(), 
						getHeight(), 
						Image.SCALE_SMOOTH);
		;
		icone.setImage(scaleImage);
		playerInfoLabel.setIcon(icone);
		playerInfoLabel.setBounds(0, 0, getWidth(), getHeight());
	}
}
