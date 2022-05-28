package fr.mcstudio.board;

import java.awt.*;

import javax.swing.*;

import fr.mcstudio.game.Game;

@SuppressWarnings("serial")
public class PlayerInfo extends JLayeredPane {

	private JLabel playerInfoLabel = new JLabel();
	private JPanel playerInfoPanel = new JPanel();

	private JLabel pseudoPlayer = new JLabel();
	private JLabel explorersPlayer = new JLabel();
	private JLabel explorersSaved = new JLabel();
	private JLabel explorersDead = new JLabel();
	private JLabel nextPlayer1 = new JLabel();
	private JLabel nextPlayer2 = new JLabel();
	private JLabel nextPlayer3 = new JLabel();

	public PlayerInfo(int resolution) {
		super();
		this.setLayer(playerInfoLabel, 0);
		this.setPanelBoundsFromResolution(resolution);
		this.setLabel();
		this.add(playerInfoLabel);

		this.playerInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.playerInfoPanel.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.playerInfoPanel.setOpaque(false);
		this.setLayer(playerInfoPanel, 1);
		this.add(playerInfoPanel);

		this.pseudoPlayer.setFont(new Font("/Font/Treasuremap.ttf", Font.PLAIN, 18));
		this.pseudoPlayer.setVerticalAlignment(SwingConstants.TOP);
		this.playerInfoPanel.add(pseudoPlayer);

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
