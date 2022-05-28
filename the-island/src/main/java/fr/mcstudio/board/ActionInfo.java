package fr.mcstudio.board;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import fr.mcstudio.game.Game;
import fr.mcstudio.enums.SideBarButton;

@SuppressWarnings("serial")
public class ActionInfo extends JLayeredPane {

	private JLabel actionInfoLabel = new JLabel();
	private JPanel actionInfoPanel = new JPanel();

	private JLabel actionTitle = new JLabel();
	private JLabel actionDesc = new JLabel();

	private JPanel actionLabel = new JPanel();

	private List<JButton> buttons = new ArrayList<JButton>();

	public ActionInfo(int resolution) {
		super();
		setLayer(this.actionInfoLabel, 0);
		this.setLayout(null);
		setPanelBoundsFromResolution(resolution);
		setLabel();
		add(this.actionInfoLabel);

		String[] imagePath = {
				"/SideBar/skipButton.png",
				"/SideBar/rulesButton.png",
				"/SideBar/quitButton.png",
				"/SideBar/seeTilesButton.png",
				"/SideBar/discoverTileButton.png",
				"/SideBar/rollButton.png"
		};

		for (String image : imagePath) {
			ImageIcon imageButton = new ImageIcon(ActionInfo.class.getResource(image));
			Image scaleImage = imageButton.getImage().getScaledInstance(imageButton.getIconWidth() * resolution / 90,
					imageButton.getIconHeight() * resolution / 90, Image.SCALE_SMOOTH);
			imageButton.setImage(scaleImage);
			JButton button = new JButton(imageButton);

			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setFocusPainted(false);
			button.setBorderPainted(false);
			buttons.add(button);
		}

		this.actionInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.actionInfoPanel.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.actionInfoPanel.setOpaque(false);
		this.setLayer(actionInfoPanel, 1);
		this.add(actionInfoPanel);

		this.actionTitle.setFont(new Font("/Font/Treasuremap.ttf", Font.PLAIN, 18));
		this.actionTitle.setVerticalAlignment(SwingConstants.TOP);
		this.actionInfoPanel.add(actionTitle);

		this.actionDesc.setFont(new Font("/Font/Treasuremap.ttf", Font.PLAIN, 18));
		this.actionDesc.setVerticalAlignment(SwingConstants.TOP);
		// this.actionDesc.setBounds(this.actionDesc.getX(), this.actionDesc.getY(),
		// this.actionInfoPanel.getWidth(),
		// this.actionInfoPanel.getHeight());
		this.actionInfoPanel.add(actionDesc);

		this.actionLabel.setOpaque(false);
		this.actionInfoPanel.add(this.actionLabel);

		this.buttons.get(SideBarButton.SKIP.ordinal()).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		this.actionInfoPanel.add(this.buttons.get(SideBarButton.SKIP.ordinal()));

		this.buttons.get(SideBarButton.RULES.ordinal()).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		this.actionInfoPanel.add(this.buttons.get(SideBarButton.RULES.ordinal()));

		this.buttons.get(SideBarButton.QUIT.ordinal()).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		this.actionInfoPanel.add(this.buttons.get(SideBarButton.QUIT.ordinal()));
	}

	public void displayActionInfo(Game game, int resolution) {

		this.actionTitle.setText(game.getActionTurn().getTitle());
		this.actionDesc.setText(game.getActionTurn().getDesc());
		this.actionLabel.removeAll();
		this.actionLabel.revalidate();
		this.actionLabel.repaint();

		switch (game.getActionTurn()) {
			case PLAY_TILE:
				actionLabel.add(this.buttons.get(SideBarButton.SEETILES.ordinal()));
				this.buttons.get(SideBarButton.SEETILES.ordinal()).addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {

					}
				});
				break;

			case MOVE_PAWNS:
				JLabel moveLeft = new JLabel(
						"Il vous reste " + game.getCurrentPlayer().getMoveLeft() + " déplacements");
				moveLeft.setVerticalAlignment(SwingConstants.TOP);
				actionLabel.add(moveLeft);
				break;

			case DISCOVER_TILE:
				actionLabel.add(this.buttons.get(SideBarButton.DISCOVER.ordinal()));
				this.buttons.get(SideBarButton.DISCOVER.ordinal()).addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {

					}
				});
				break;

			case MOVE_MONSTER:
				actionLabel.add(this.buttons.get(SideBarButton.ROLL.ordinal()));
				this.buttons.get(SideBarButton.ROLL.ordinal()).addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {

					}
				});
				break;

			default:
				break;
		}
	}

	private void setPanelBoundsFromResolution(int resolution) {
		switch (resolution) {
			case 70:
				setBounds(1162, 0, 260, 770);
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
		Image scaleImage = icone.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);

		icone.setImage(scaleImage);
		actionInfoLabel.setIcon(icone);
		actionInfoLabel.setBounds(0, 0, getWidth(), getHeight());
	}
}
