package fr.mcstudio.board;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class PlayerInfo extends JLabel {

	public PlayerInfo(JLayeredPane playerInfoPane) {
		super();
		playerInfoPane.setLayer(this, 0);
		this.setBounds(0, 0, 282, 1000);
		this.setIcon(new ImageIcon(PlayerInfo.class.getResource("/PlayerInfo.png")));
		playerInfoPane.add(this);
	}

}
