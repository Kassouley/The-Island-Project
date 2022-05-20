package fr.mcstudio.board;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import fr.mcstudio.game.Game;

@SuppressWarnings("serial")
public class ActionInfo extends JLabel {

	public ActionInfo(int resolution, JLayeredPane actionInfoPane) {
		super();
		actionInfoPane.setLayer(this, 0);
		setBoundsFromResolution(resolution);
		actionInfoPane.add(this);
	}

	private void setBoundsFromResolution(int resolution) {
		ImageIcon icone = new ImageIcon(Board.class.getResource("/ActionInfo.png"));
		Image scaleImage;
		switch (resolution) {
			case 70:
				this.setBounds(0, 0, 260, 770);
				scaleImage = icone.getImage().getScaledInstance(260, 770, Image.SCALE_SMOOTH);
				icone.setImage(scaleImage);
				break;
			case 80:
				this.setBounds(0, 0, 297, 880);
				scaleImage = icone.getImage().getScaledInstance(297, 880, Image.SCALE_SMOOTH);
				icone.setImage(scaleImage);
				break;
			case 90:
				this.setBounds(0, 0, 338, 990);
				scaleImage = icone.getImage().getScaledInstance(338, 990, Image.SCALE_SMOOTH);
				icone.setImage(scaleImage);
				break;
			default:
				break;
		}

		this.setIcon(icone);
	}

	public void displayActionInfo(Game game, JLayeredPane actionInfoPane) {

		JLabel actionTitle = new JLabel(game.getActionTurn().getTitle());
		actionInfoPane.setLayer(actionTitle, 1);
		actionInfoPane.add(actionTitle);

		JLabel actionDesc = new JLabel(game.getActionTurn().getDesc());
		actionInfoPane.setLayer(actionDesc, 1);
		actionInfoPane.add(actionDesc);

		switch (game.getActionTurn()) {
			case PLAY_TILE:
				JButton seeTilesButton = new JButton(
						new ImageIcon(JButton.class.getResource("/SideBar/seeTilesButton")));
				seeTilesButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {

					}
				});
				actionInfoPane.setLayer(seeTilesButton, 1);
				actionInfoPane.add(seeTilesButton);
				break;

			case MOVE_PAWNS:
				JLabel moveLeft = new JLabel("Il vous reste" + game.getCurrentPlayer().getMoveLeft() + "déplacements");
				actionInfoPane.setLayer(moveLeft, 1);
				actionInfoPane.add(moveLeft);
				break;

			case DISCOVER_TILE:
				JButton discoverButton = new JButton(
						new ImageIcon(JButton.class.getResource("/SideBar/discoverTileButton")));
				discoverButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {

					}
				});
				actionInfoPane.setLayer(discoverButton, 1);
				actionInfoPane.add(discoverButton);
				break;

			case MOVE_MONSTER:
				JButton rollButton = new JButton(
						new ImageIcon(JButton.class.getResource("/SideBar/rollButton")));
				rollButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {

					}
				});
				actionInfoPane.setLayer(rollButton, 1);
				actionInfoPane.add(rollButton);
				break;

			default:
				break;
		}

		JButton skipButton = new JButton(
				new ImageIcon(JButton.class.getResource("/SideBar/skipButton")));
		skipButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		actionInfoPane.setLayer(skipButton, 1);
		actionInfoPane.add(skipButton);

		JButton rulesButton = new JButton(
				new ImageIcon(JButton.class.getResource("/SideBar/rulesButton")));
		rulesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		actionInfoPane.setLayer(rulesButton, 1);
		actionInfoPane.add(rulesButton);

		JButton quitButton = new JButton(
				new ImageIcon(JButton.class.getResource("/SideBar/quitButton")));
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		actionInfoPane.setLayer(quitButton, 1);
		actionInfoPane.add(quitButton);

	}
}
