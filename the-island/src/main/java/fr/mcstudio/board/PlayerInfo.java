package fr.mcstudio.board;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import fr.mcstudio.game.Game;

@SuppressWarnings("serial")
public class PlayerInfo extends JLayeredPane {

	JLabel playerInfoLabel = new JLabel();

	public PlayerInfo(int resolution) {
		super();
		setLayer(playerInfoLabel, 0);
		setPanelBoundsFromResolution(resolution);
		setLabel();
		add(playerInfoLabel);
	}

	public void displayPlayerInfo(Game game, int resolution) {

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
		ImageIcon icone = new ImageIcon(Board.class.getResource("/PlayerInfo.png"));
		Image scaleImage = icone.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
		;
		icone.setImage(scaleImage);
		playerInfoLabel.setIcon(icone);
		playerInfoLabel.setBounds(0, 0, getWidth(), getHeight());
	}
}
