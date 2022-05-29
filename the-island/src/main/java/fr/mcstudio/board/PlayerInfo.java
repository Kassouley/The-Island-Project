package fr.mcstudio.board;

import java.awt.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import fr.mcstudio.game.Game;
import fr.mcstudio.game.Player;

@SuppressWarnings("serial")
public class PlayerInfo extends JLayeredPane {

	private JLabel playerInfoLabel = new JLabel();
	private JPanel playerInfoPanel = new JPanel();

	private JLabel avatarPlayer = new JLabel();
	private List<JLabel> infosPlayer = new ArrayList<JLabel>();

	public PlayerInfo(int resolution) {
		super();
		this.setLayer(playerInfoLabel, 0);
		this.setPanelBoundsFromResolution(resolution);
		this.setLabel();
		this.add(playerInfoLabel);

		this.playerInfoPanel.setLayout(null);
		this.playerInfoPanel.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.playerInfoPanel.setOpaque(false);
		this.setLayer(playerInfoPanel, 1);
		this.add(playerInfoPanel);

		Font sizedFont = null;
		try {
			InputStream is = ActionInfo.class.getResourceAsStream("/Font/Treasuremap.ttf");
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			sizedFont = font.deriveFont(18f);
		} catch (Exception ex) {
			System.err.println("Not loaded");
		}

		for (int i = 0; i < 7; i++) {
			JLabel text = new JLabel();
			text.setFont(sizedFont);
			this.playerInfoPanel.add(text);
			this.infosPlayer.add(text);
		}

		this.avatarPlayer.setBounds(15 * resolution / 90, 120 * resolution / 90,
				256 * resolution / 90, 256 * resolution / 90);
		this.playerInfoPanel.add(avatarPlayer);

	}

	public void displayPlayerInfo(Game game, int resolution) {

		Player currentPlayer = game.getCurrentPlayer();
		Player[] players = game.getPlayers();
		int textWidth = 0;
		int textHeight = 0;
		int i = 0;

		int[][] pos = {
				{ 137, 55 },
				{ 139, 488 },
				{ 139, 573 },
				{ 139, 658 },
				{ 139, 800 },
				{ 139, 850 },
				{ 139, 900 },
		};

		String[] textInfo = {
				currentPlayer.getPseudo(),
				Integer.toString(currentPlayer.getNumberExplorerAlive()),
				Integer.toString(currentPlayer.getNumberExplorerSaved()),
				Integer.toString(currentPlayer.getNumberExplorerDead()),
				players[(game.getCurrentPlayerIndex() + 1) % players.length].getPseudo(),
				players[(game.getCurrentPlayerIndex() + 2) % players.length].getPseudo(),
				players[(game.getCurrentPlayerIndex() + 3) % players.length].getPseudo()
		};

		for (String text : textInfo) {

			JLabel playerInfo = this.infosPlayer.get(i);
			playerInfo.setText(text);
			textWidth = playerInfo.getFontMetrics(playerInfo.getFont()).stringWidth(playerInfo.getText());
			textHeight = playerInfo.getFontMetrics(playerInfo.getFont()).getHeight();
			playerInfo.setBounds((pos[i][0] - textWidth / 2) * resolution / 90, pos[i][1] * resolution / 90, textWidth,
					textHeight);
			i++;
		}

		this.avatarPlayer.setIcon(currentPlayer.getAvatar());
	}

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

	private void setLabel() {
		ImageIcon icone = new ImageIcon(PlayerInfo.class.getResource("/PlayerInfo.png"));
		Image scaleImage = icone.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
		;
		icone.setImage(scaleImage);
		playerInfoLabel.setIcon(icone);
		playerInfoLabel.setBounds(0, 0, getWidth(), getHeight());
	}
}
