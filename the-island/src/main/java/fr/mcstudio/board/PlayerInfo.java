package fr.mcstudio.board;

import java.awt.*;

import javax.swing.*;

import fr.mcstudio.game.Game;
import fr.mcstudio.game.Player;

@SuppressWarnings("serial")
public class PlayerInfo extends JLayeredPane {

	private JLabel playerInfoLabel = new JLabel();
	private JPanel playerInfoPanel = new JPanel();

	private JLabel pseudoPlayer = new JLabel();
	private JLabel avatarPlayer = new JLabel();
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

		this.avatarPlayer.setVerticalAlignment(SwingConstants.TOP);
		this.playerInfoPanel.add(avatarPlayer);

		this.explorersPlayer.setFont(new Font("/Font/Treasuremap.ttf", Font.PLAIN, 18));
		this.explorersPlayer.setVerticalAlignment(SwingConstants.TOP);
		this.playerInfoPanel.add(explorersPlayer);

		this.explorersSaved.setFont(new Font("/Font/Treasuremap.ttf", Font.PLAIN, 18));
		this.explorersSaved.setVerticalAlignment(SwingConstants.TOP);
		this.playerInfoPanel.add(explorersSaved);

		this.explorersDead.setFont(new Font("/Font/Treasuremap.ttf", Font.PLAIN, 18));
		this.explorersDead.setVerticalAlignment(SwingConstants.TOP);
		this.playerInfoPanel.add(explorersDead);

	}

	public void displayPlayerInfo(Game game, int resolution) {
		Player currentPlayer = game.getCurrentPlayer();
		this.pseudoPlayer.setText(currentPlayer.getPseudo());
		this.avatarPlayer.setIcon(currentPlayer.getAvatar());
		this.explorersPlayer.setText(Integer.toString(currentPlayer.getNumberExplorerAlive()));
		this.explorersSaved.setText(Integer.toString(currentPlayer.getNumberExplorerSaved()));
		this.explorersDead.setText(Integer.toString(currentPlayer.getNumberExplorerDead()));
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
