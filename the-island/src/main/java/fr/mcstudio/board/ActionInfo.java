package fr.mcstudio.board;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class ActionInfo extends JLayeredPane {
	
	JLabel actionInfoLabel = new JLabel();

	public ActionInfo(int resolution) {
		super();
		setLayer(actionInfoLabel, 0);
		setPanelBoundsFromResolution(resolution);
		setLabel();
		add(actionInfoLabel);
	}
	
	private void setPanelBoundsFromResolution(int resolution) {
		switch (resolution) {
		case 70:
			setBounds(1172, 0, 260, 770);
			break;
		case 80:
			setBounds(1338, 0, 297, 880);
			break;
		case 90:
			setBounds(1512, 0, 338, 990);
			break;
		default:
			break;
	}
	}
	
	private void setLabel() {
		ImageIcon icone = new ImageIcon(Board.class.getResource("/ActionInfo.png"));
		Image scaleImage = icone.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);;
		icone.setImage(scaleImage);
		actionInfoLabel.setIcon(icone);
		actionInfoLabel.setBounds(0, 0, getWidth(), getHeight());
	}
}
