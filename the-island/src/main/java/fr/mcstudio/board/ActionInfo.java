package fr.mcstudio.board;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class ActionInfo extends JLabel {

	public ActionInfo(int resolution, JLayeredPane actionInfoPane) {
		super();
		actionInfoPane.setLayer(this, 0);
		setBoundsFromResolution(resolution);
		this.setIcon(new ImageIcon(PlayerInfo.class.getResource("/ActionInfo.png")));
		actionInfoPane.add(this);
	}
	
	private void setBoundsFromResolution(int resolution) {
		ImageIcon icone = new ImageIcon(Board.class.getResource("/ActionInfo.png"));
		Image scaleImage;
		switch(resolution) {
		case 70:
			this.setBounds(0, 0, 260, 770);
			scaleImage = icone.getImage().getScaledInstance(260, 770,Image.SCALE_SMOOTH);
			icone.setImage(scaleImage);
			break;
		case 80:
			this.setBounds(0, 0, 297, 880);
			scaleImage = icone.getImage().getScaledInstance(297, 880,Image.SCALE_SMOOTH);
			icone.setImage(scaleImage);
			break;
		case 90:
			this.setBounds(0, 0, 338, 990);
			scaleImage = icone.getImage().getScaledInstance(338, 990,Image.SCALE_SMOOTH);
			icone.setImage(scaleImage);
			break;
		default: break;
		}

	    this.setIcon(icone);
	}
}
