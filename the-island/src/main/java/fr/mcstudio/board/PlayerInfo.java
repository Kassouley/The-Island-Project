package fr.mcstudio.board;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class PlayerInfo extends JLabel {

	public PlayerInfo(int resolution, JLayeredPane playerInfoPane) {
		super();
		playerInfoPane.setLayer(this, 0);
		setBoundsFromResolution(resolution);
		playerInfoPane.add(this);
	}
	
	private void setBoundsFromResolution(int resolution) {
		ImageIcon icone = new ImageIcon(Board.class.getResource("/PlayerInfo.png"));
		Image scaleImage;
		switch(resolution) {
		case 70:
			this.setBounds(0, 0, 217, 770);
			scaleImage = icone.getImage().getScaledInstance(217, 770,Image.SCALE_SMOOTH);
			icone.setImage(scaleImage);
			break;
		case 80:
			this.setBounds(0, 0, 248, 880);
			scaleImage = icone.getImage().getScaledInstance(248, 880,Image.SCALE_SMOOTH);
			icone.setImage(scaleImage);
			break;
		case 90:
			this.setBounds(0, 0, 282, 990);
			scaleImage = icone.getImage().getScaledInstance(282, 990,Image.SCALE_SMOOTH);
			icone.setImage(scaleImage);
			break;
		default: break;
		}

	    this.setIcon(icone);
	}

}
