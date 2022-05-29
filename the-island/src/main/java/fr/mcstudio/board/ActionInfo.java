package fr.mcstudio.board;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
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

		Font sizedFont = null;
		try {
			InputStream is = ActionInfo.class.getResourceAsStream("/Font/Treasuremap.ttf");
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			sizedFont = font.deriveFont(18f);
		} catch (Exception ex) {
			System.err.println("Not loaded");
		}

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

		this.actionInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
		this.actionInfoPanel.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.actionInfoPanel.setOpaque(false);
		this.setLayer(actionInfoPanel, 1);
		this.add(actionInfoPanel);

		this.actionTitle.setFont(sizedFont);
		this.actionTitle.setVerticalAlignment(SwingConstants.CENTER);
		this.actionTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.actionInfoPanel.add(actionTitle);

		this.actionDesc.setFont(sizedFont);
		this.actionDesc.setVerticalAlignment(SwingConstants.CENTER);
		this.actionDesc.setHorizontalAlignment(SwingConstants.CENTER);
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
				moveLeft.setVerticalAlignment(SwingConstants.CENTER);
				moveLeft.setHorizontalAlignment(SwingConstants.CENTER);
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
