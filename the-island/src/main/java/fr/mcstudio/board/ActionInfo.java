package fr.mcstudio.board;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class ActionInfo extends JLabel {

	public ActionInfo(int resolution, JLayeredPane actionInfoPane) {
		super();
		actionInfoPane.setLayer(this, 0);
		this.setBounds(0, 0, 338, 1000);
		this.setIcon(new ImageIcon(PlayerInfo.class.getResource("/ActionInfo.png")));
		actionInfoPane.add(this);
	}
}
