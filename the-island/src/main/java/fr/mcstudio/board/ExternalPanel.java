package fr.mcstudio.board;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import fr.mcstudio.enums.AnimationType;
import fr.mcstudio.enums.ExternalPanelState;
import fr.mcstudio.enums.PawnType;
import fr.mcstudio.pawns.Explorer;
import fr.mcstudio.tiles.Tile;
import fr.mcstudio.util.Pair;
import fr.mcstudio.util.PairList;

@SuppressWarnings("serial")
public class ExternalPanel extends JLayeredPane {

	private Board board;
	private JLabel backgroundPanel = new JLabel("");
	private JPanel boatOrSeaPanel;
	private JPanel pawnPanel;
	private JPanel dicePanel;
	private JPanel tilesEffectsPanel;
	private JPanel animationPanel;

	private PairList<JButton, JLayeredPane> bPairList = new PairList<JButton, JLayeredPane>();
	private PairList<AnimationType, JLabel> aPairList = new PairList<AnimationType, JLabel>();
	private List<JLabel> seaSnakeList = new ArrayList<JLabel>();
	private List<JLabel> sharkList = new ArrayList<JLabel>();
	private List<JLabel> whaleList = new ArrayList<JLabel>();

	private JLayeredPane selection = null;
	private PawnType pawnType;

	private Hexagon clickedHex;
	private AnimationType animationType;

	private ExternalPanelState externalPanelState = ExternalPanelState.VOID;
	private int resolution;

	public ExternalPanel(Board board, int resolution) {
		this.board = board;
		this.resolution = resolution;
		this.setLayout(null);
		this.setLayer(backgroundPanel, 0);
		this.setPanelBoundsFromResolution(resolution);
		this.setLabel();

		this.pawnPanel = createDisplayPanel();
		this.pawnPanel.setLayout(new GridLayout(4, 0, 0, 0));
		this.boatOrSeaPanel = createDisplayPanel();
		this.boatOrSeaPanel.setLayout(new GridLayout(4, 0, 0, 0));
		this.dicePanel = createDisplayPanel();
		this.dicePanel.setLayout(new BorderLayout(0, 0));
		this.tilesEffectsPanel = createDisplayPanel();
		this.tilesEffectsPanel.setLayout(new GridLayout(4, 0, 0, 0));
		this.animationPanel = createDisplayPanel();
		this.setVisible(false);

		board.setLayer(this, 4);
		board.add(this);

		this.initMonsterLists();
		this.initAnimationList();
	}

	public ExternalPanelState getExternalPanelState() {
		return externalPanelState;
	}

	public void setExternalPanelState(ExternalPanelState externalPanelState) {
		this.externalPanelState = externalPanelState;
		displayExternalPanel(externalPanelState);
	}

	private JPanel createDisplayPanel() {
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.setLayer(panel, 1);
		this.add(panel);
		panel.setOpaque(false);
		panel.setVisible(false);
		panel.setLayout(null);
		return panel;
	}

	private void displayExternalPanel(ExternalPanelState state) {
		this.setVisible(true);
		switch (state) {
			case VOID:
				hideAllPanels();
				break;
			case PAWNPANEL:
				displayPawnPanel();
				break;
			case BOATORSEA:
				displayBoatOrSea();
				break;
			case DICEPANEL:
				displayDicePanel();
				break;
			case TILEEFFECTPANEL:
				displayTileEffectPanel();
				break;
			case ANIMATIONPANEL:
				displayAnimationPanel();
				break;
		}
	}

	private void displayAnimationPanel() {
		this.animationPanel.setVisible(true);
		for (int i = 0; i < bPairList.size(); i++) {
			animationPanel.remove(bPairList.get(i).getLeft());
		}
		bPairList.clear();

		if (aPairList.containsInPair(animationType)) {
			int index = aPairList.getLeftList().indexOf(animationType);
			animationPanel.add(aPairList.get(index).getRight(), BorderLayout.CENTER);
		}

		animationPanel.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				board.setDisplayExternalPanel(false);
				setExternalPanelState(ExternalPanelState.VOID);
				animationType = null;
				board.getGame().inGame(clickedHex);
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});
	}

	private void displayTileEffectPanel() {
		this.tilesEffectsPanel.setVisible(true);

		int explorersHand = board.getGame().getCurrentPlayer().getTileList().size();
        for (int i = 0; i < explorersHand; i++) {
        	Tile tile = board.getGame().getCurrentPlayer().getTileList().get(i);
        	
    		bPairList.add(new Pair<JButton,JLayeredPane>(new JButton(tile.getEffectLabel().getIcon()), tile));
            //bPairList.get(i).getLeft().setBackground(java.awt.Color.WHITE);
            //bPairList.get(i).getLeft().setBorderPainted(false);
            //bPairList.get(i).getLeft().setBorder(BorderFactory.createLineBorder(java.awt.Color.WHITE));
            bPairList.get(i).getLeft().setFocusPainted(false);
            bPairList.get(i).getLeft().setContentAreaFilled(false);
            tilesEffectsPanel.add(bPairList.get(i).getLeft());
                
            bPairList.get(i).getLeft().addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int index = bPairList.getLeftList().indexOf(e.getSource());
                    setSelection(bPairList.get(index).getRight());
                    tilesEffectsPanel.removeAll();
            		bPairList.clear();
                    board.setDisplayExternalPanel(false);
                    setExternalPanelState(ExternalPanelState.VOID);
                    board.getGame().inGame(clickedHex);
                }
            });
        }
	}

	}

	private void displayDicePanel() {
		this.dicePanel.setVisible(true);
		dicePanel.removeAll();
		int animationNumber;
		switch ((int) (Math.random() * 3)) {
			case 0:
				animationNumber = (int) (Math.random() * this.seaSnakeList.size());
				this.dicePanel.add(this.seaSnakeList.get(animationNumber), BorderLayout.CENTER);
				this.pawnType = PawnType.SEASNAKE;
				break;
			case 1:
				animationNumber = (int) (Math.random() * this.sharkList.size());
				this.dicePanel.add(this.sharkList.get(animationNumber), BorderLayout.CENTER);
				this.pawnType = PawnType.SHARK;
				break;
			case 2:
				animationNumber = (int) (Math.random() * this.whaleList.size());
				this.dicePanel.add(this.whaleList.get(animationNumber), BorderLayout.CENTER);
				this.pawnType = PawnType.WHALE;
				break;
			default:
				break;
		}
		// this.dicePanel.add(new JLabel(new
		// ImageIcon(ExternalPanel.class.getResource("/Bateau.png"))),
		// BorderLayout.NORTH);
		dicePanel.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				board.setDisplayExternalPanel(false);
				setExternalPanelState(ExternalPanelState.VOID);
				board.getGame().inGame(clickedHex);
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});
	}

	private void displayBoatOrSea() {
		this.boatOrSeaPanel.setVisible(true);
		ImageIcon icon = new ImageIcon(ExternalPanel.class.getResource("/Mer.png"));
		Image scaleImage;
		scaleImage = icon.getImage().getScaledInstance(resolution, resolution, Image.SCALE_SMOOTH);
		icon.setImage(scaleImage);
		bPairList.add(new Pair<JButton, JLayeredPane>(new JButton(icon), clickedHex));
		bPairList.add(new Pair<JButton, JLayeredPane>(new JButton(clickedHex.getBoat().getImage().getIcon()),
				clickedHex.getBoat()));
		for (int i = 0; i < 2; i++) {
			bPairList.get(i).getLeft().setFocusPainted(false);
			bPairList.get(i).getLeft().setContentAreaFilled(false);
			boatOrSeaPanel.add(bPairList.get(i).getLeft());
			bPairList.get(i).getLeft().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int index = bPairList.getLeftList().indexOf(e.getSource());
					setSelection(bPairList.get(index).getRight());

					boatOrSeaPanel.removeAll();
					bPairList.clear();
					board.setDisplayExternalPanel(false);
					setExternalPanelState(ExternalPanelState.VOID);
					board.getGame().inGame(clickedHex);
				}
			});
		}

	}

	private void displayPawnPanel() {
		this.pawnPanel.setVisible(true);

		int explorersLength = clickedHex.getExplorerList().size();
		for (int i = 0; i < explorersLength; i++) {
			Explorer explorer = clickedHex.getExplorerList().get(i);
			if (board.getGame().getCurrentPlayer().getColor() == explorer.getColor()) {
				bPairList.add(new Pair<JButton, JLayeredPane>(new JButton(explorer.getImage().getIcon()), explorer));
			}
		}
		for (int i = 0; i < bPairList.size(); i++) {
			bPairList.get(i).getLeft().setFocusPainted(false);
			bPairList.get(i).getLeft().setContentAreaFilled(false);
			pawnPanel.add(bPairList.get(i).getLeft());
			bPairList.get(i).getLeft().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int index = bPairList.getLeftList().indexOf(e.getSource());
					setSelection(bPairList.get(index).getRight());
					pawnPanel.removeAll();
					bPairList.clear();
					board.setDisplayExternalPanel(false);
					setExternalPanelState(ExternalPanelState.VOID);
					board.getGame().inGame(clickedHex);
				}
			});
		}
	}

	private void initMonsterLists() {
		this.seaSnakeList.add(new JLabel(new ImageIcon(ExternalPanel.class.getResource("/Animation/seaSnake/seaSnake1.gif"))));
		this.seaSnakeList.add(new JLabel(new ImageIcon(ExternalPanel.class.getResource("/Animation/seaSnake/seaSnake2.gif"))));
		this.sharkList.add(new JLabel(new ImageIcon(ExternalPanel.class.getResource("/Animation/shark/shark1.gif"))));
		this.sharkList.add(new JLabel(new ImageIcon(ExternalPanel.class.getResource("/Animation/shark/shark1.gif"))));
		this.whaleList.add(new JLabel(new ImageIcon(ExternalPanel.class.getResource("/Animation/whale/whale1.gif"))));
		this.whaleList.add(new JLabel(new ImageIcon(ExternalPanel.class.getResource("/Animation/whale/whale2.gif"))));
	}

	private void initAnimationList() {
		this.aPairList.add(
			new Pair<AnimationType,JLabel>(
				AnimationType.BOAT_SUMMON,
				new JLabel(
					new ImageIcon(ExternalPanel.class.getResource("/Animation/event/boatSummon.gif"))
				)
			)
		);
		this.aPairList.add(
			new Pair<AnimationType,JLabel>(
				AnimationType.DOLPHIN_SUMMON,
				new JLabel(
					new ImageIcon(ExternalPanel.class.getResource("/Animation/event/dolphinSummon.gif"))
				)
			)
		);
		this.aPairList.add(
			new Pair<AnimationType,JLabel>(
				AnimationType.SEASNAKE_ATTACK,
				new JLabel(
					new ImageIcon(ExternalPanel.class.getResource("/Animation/event/seaSnakeAttack.gif"))
				)
			)
		);
		this.aPairList.add(
			new Pair<AnimationType,JLabel>(
				AnimationType.SHARK_ATTACK,
				new JLabel(
					new ImageIcon(ExternalPanel.class.getResource("/Animation/event/sharkAttack.gif"))
				)
			)
		);
		this.aPairList.add(
			new Pair<AnimationType,JLabel>(
				AnimationType.SHARK_COUNTER,
				new JLabel(
					new ImageIcon(ExternalPanel.class.getResource("/Animation/event/sharkCounter.gif"))
				)
			)
		);
		this.aPairList.add(
			new Pair<AnimationType,JLabel>(
				AnimationType.SHARK_SUMMON,
				new JLabel(
					new ImageIcon(ExternalPanel.class.getResource("/Animation/event/sharkSummon.gif"))
				)
			)
		);
		this.aPairList.add(
			new Pair<AnimationType,JLabel>(
				AnimationType.VOLCANO,
				new JLabel(
					new ImageIcon(ExternalPanel.class.getResource("/Animation/event/volcano.gif"))
				)
			)
		);
		this.aPairList.add(
			new Pair<AnimationType,JLabel>(
				AnimationType.WHALE_COUNTER,
				new JLabel(
					new ImageIcon(ExternalPanel.class.getResource("/Animation/event/whaleCounter.gif"))
				)
			)
		);
		this.aPairList.add(
			new Pair<AnimationType,JLabel>(
				AnimationType.WHALE_SUMMON,
				new JLabel(
					new ImageIcon(ExternalPanel.class.getResource("/Animation/event/whaleSummon.gif"))
				)
			)
		);
		this.aPairList.add(
			new Pair<AnimationType,JLabel>(
				AnimationType.WHIRLPOOL,
				new JLabel(
					new ImageIcon(ExternalPanel.class.getResource("/Animation/event/whirlpool.gif"))
				)
			)
		);
		this.aPairList.add(
			new Pair<AnimationType,JLabel>(
				AnimationType.WIND,
				new JLabel(
					new ImageIcon(ExternalPanel.class.getResource("/Animation/event/wind.gif"))
				)
			)
		);
	}

	public Hexagon getClickedHex() {
		return clickedHex;
	}

	public void setClickedHex(Hexagon clickedHex) {
		this.clickedHex = clickedHex;
	}

	public PawnType getPawnType() {
		return pawnType;
	}

	public void setPawnType(PawnType pawnType) {
		this.pawnType = pawnType;
	}

	private void hideAllPanels() {

		this.pawnPanel.setVisible(false);
		this.boatOrSeaPanel.setVisible(false);
		this.dicePanel.setVisible(false);
		this.tilesEffectsPanel.setVisible(false);
		this.animationPanel.setVisible(false);
		this.setVisible(false);

	}

	private void setPanelBoundsFromResolution(int resolution) {

		switch (resolution) {
			case 70:
				setBounds(206, 180, 545, 409);
				break;
			case 80:
				setBounds(235, 206, 623, 467);
				break;
			case 90:
				setBounds(265, 232, 700, 525);
				break;
			default:
				break;
		}
	}

	private void setLabel() {
		ImageIcon icone = new ImageIcon(Board.class.getResource("/Menu/ExternalPanel.png"));
		Image scaleImage = icone.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
		;
		icone.setImage(scaleImage);
		backgroundPanel.setIcon(icone);
		backgroundPanel.setBounds(0, 0, getWidth(), getHeight());
		this.add(backgroundPanel);
	}

	/*
	 * public void getReturnedPawn() {
	 * int bPairListLength = bPairList.size();
	 * 
	 * for (int i = 0; i < bPairListLength; i++) {
	 * bPairList.get(i).getLeft().addActionListener( new ActionListener() {
	 * 
	 * @Override
	 * public void actionPerformed(ActionEvent e) {
	 * int index = bPairList.getLeftList().indexOf(e.getSource());
	 * pawn = bPairList.get(index).getRight();
	 * }
	 * });
	 * }
	 * }
	 */

	public Board getBoard() {
		return board;
	}

	public JLayeredPane getSelection() {
		return selection;
	}

	public void setSelection(JLayeredPane selection) {
		this.selection = selection;
	}
}
