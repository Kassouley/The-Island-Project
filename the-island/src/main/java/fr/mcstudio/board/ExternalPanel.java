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
import fr.mcstudio.enums.ExplorerStatus;
import fr.mcstudio.enums.ExternalPanelState;
import fr.mcstudio.enums.PawnType;
import fr.mcstudio.pawns.Explorer;
import fr.mcstudio.pawns.Pawn;
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
	private JPanel tilesEffectsRedPanel;
	private JPanel tilesEffectsDefensePanel;
	private JPanel animationPanel;
	private JPanel boardingPanel;

	private PairList<JButton, JLayeredPane> bPairList = new PairList<JButton, JLayeredPane>();
	private PairList<AnimationType, JLabel> aPairList = new PairList<AnimationType, JLabel>();
	private List<Explorer> boardingpawns = new ArrayList<Explorer>();
	private List<JLabel> seaSnakeList = new ArrayList<JLabel>();
	private List<JLabel> sharkList = new ArrayList<JLabel>();
	private List<JLabel> whaleList = new ArrayList<JLabel>();

	private JLayeredPane selection = null;
	private PawnType pawnType;
	private boolean choice = false;

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
		this.tilesEffectsRedPanel = createDisplayPanel();
		this.tilesEffectsRedPanel.setLayout(new GridLayout(4, 0, 0, 0));
		this.tilesEffectsDefensePanel = createDisplayPanel();
		this.tilesEffectsDefensePanel.setLayout(new GridLayout(1, 0, 0, 0));
		this.animationPanel = createDisplayPanel();
		this.animationPanel.setLayout(new BorderLayout(0, 0));
		this.boardingPanel = createDisplayPanel();
		this.boardingPanel.setLayout(new GridLayout(4, 0, 0, 0));
		
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
			case TILEEFFECTREDPANEL:
				displayTileEffectRedPanel();
				break;
			case TILEEFFECTDEFENSEPANEL:
				displayTileEffectDefensePanel();
				break;
			case ANIMATIONPANEL:
				displayAnimationPanel();
				break;
			case BOARDINGPANEL:
				displayBoardingPanel();
				break;
		}
	}

	private void displayBoardingPanel() {
		boardingPanel.removeAll();
		bPairList.clear();
		boardingpawns.clear();
		this.boardingPanel.setVisible(true);
		
		int explorerLength = clickedHex.getExplorerList().size();
		for (int i = 0; i < explorerLength; i++) {
			Explorer e = clickedHex.getExplorerList().get(i);
			bPairList.add(new Pair<JButton,JLayeredPane>(new JButton(e.getImage().getIcon()), e));bPairList.get(i).getLeft().setFocusPainted(false);
            bPairList.get(i).getLeft().setContentAreaFilled(false);
            boardingPanel.add(bPairList.get(i).getLeft());
                
            bPairList.get(i).getLeft().addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int index = bPairList.getLeftList().indexOf(e.getSource());
                    if(!boardingpawns.contains((Explorer) bPairList.get(index).getRight())) {
                    	boardingpawns.add((Explorer) bPairList.get(index).getRight());

                    	bPairList.remove(index);
                    	boardingPanel.remove(index);
                    	boardingPanel.revalidate();
                    	boardingPanel.repaint();
                    }
                    if(boardingpawns.size() >= 3) {
            	        board.getExternalPanel().setExternalPanelState(ExternalPanelState.VOID);
                    	board.getExternalPanel().setAnimationType(AnimationType.BOAT_SUMMON);
            	        board.getExternalPanel().setExternalPanelState(ExternalPanelState.ANIMATIONPANEL);
            	        for(Explorer boatE : boardingpawns) {
            	        	boatE.move(clickedHex, clickedHex.getBoat(), clickedHex);
            			}
                        board.getGame().inGame(clickedHex);
            	        clickedHex = null;
                    }
                }
            });
		}
	}

	private void displayAnimationPanel() {
		animationPanel.removeAll();
		this.animationPanel.setVisible(true);
		
		if (aPairList.containsInPair(animationType)) {
			int index = aPairList.getLeftList().indexOf(animationType);
			animationPanel.add(aPairList.get(index).getRight(), BorderLayout.CENTER);
		}

		animationType = null;
		animationPanel.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				board.setDisplayExternalPanel(false);
				setExternalPanelState(ExternalPanelState.VOID);
				//board.getGame().inGame(clickedHex);
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});
	}

	private void displayTileEffectRedPanel() {
		tilesEffectsRedPanel.removeAll();
		bPairList.clear();
		this.tilesEffectsRedPanel.setVisible(true);
        for (Tile tile : board.getGame().getCurrentPlayer().getTileList()) {
        	if(tile.getEffect().getType() == "Rouge") {
        		bPairList.add(new Pair<JButton,JLayeredPane>(new JButton(tile.getEffectLabel().getIcon()), tile));
        	}
        }
        
        for (int i = 0; i < bPairList.size(); i++) {
        	bPairList.get(i).getLeft().setFocusPainted(false);
            bPairList.get(i).getLeft().setContentAreaFilled(false);
            tilesEffectsRedPanel.add(bPairList.get(i).getLeft());
                
            bPairList.get(i).getLeft().addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int index = bPairList.getLeftList().indexOf(e.getSource());
                    setSelection(bPairList.get(index).getRight());
                    board.setDisplayExternalPanel(false);
                    setExternalPanelState(ExternalPanelState.VOID);
                    board.getGame().getActionInfo().onClickTilesButton(board.getGame());
                }
            });
		}
	}
	
	private void displayTileEffectDefensePanel() {
		tilesEffectsDefensePanel.removeAll();
		this.tilesEffectsDefensePanel.setVisible(true);
		choice = false;
		
		JButton yes = new JButton(new ImageIcon(ExternalPanel.class.getResource("/yes_button.png")));
		JButton no = new JButton(new ImageIcon(ExternalPanel.class.getResource("/no_button.png")));
		tilesEffectsDefensePanel.add(yes);
		tilesEffectsDefensePanel.add(no);
		
		yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				choice = true;
				board.setDisplayExternalPanel(false);
                setExternalPanelState(ExternalPanelState.VOID);
			}
			
		});
		no.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				choice = false;
				board.setDisplayExternalPanel(false);
                setExternalPanelState(ExternalPanelState.VOID);
			}
			
		});
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
				board.getGame().inGame(null);
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
		boatOrSeaPanel.removeAll();
		bPairList.clear();
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

					
					board.setDisplayExternalPanel(false);
					setExternalPanelState(ExternalPanelState.VOID);
					board.getGame().inGame(clickedHex);
				}
			});
		}

	}

	private void displayPawnPanel() {
		pawnPanel.removeAll();
		bPairList.clear();
		this.pawnPanel.setVisible(true);

		for (Explorer e : clickedHex.getExplorerList()) {
			if (board.getGame().getCurrentPlayer().getColor() == e.getColor()) {
				bPairList.add(new Pair<JButton, JLayeredPane>(new JButton(e.getImage().getIcon()), e));
			}
			
		}
		if((clickedHex.getBoat() != null 
            	&& clickedHex.getBoat().
    			isOwnedBy(board.
    					getGame().
    					getCurrentPlayer()))) {
			
			bPairList.add(new Pair<JButton, JLayeredPane>(new JButton(clickedHex.getBoat().getImage().getIcon()), clickedHex.getBoat()));
			
		}
		if((clickedHex.getBoat() != null)){
			for (Explorer e : clickedHex.getBoat().getExplorerList()) {
				if (board.getGame().getCurrentPlayer().getColor() == e.getColor()) {
					bPairList.add(new Pair<JButton, JLayeredPane>(new JButton(e.getImage().getIcon()), e));
				}
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
				AnimationType.WHALE_ATTACK,
				new JLabel(
					new ImageIcon(ExternalPanel.class.getResource("/Animation/event/whaleAttack.gif"))
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

	public void setAnimationType(AnimationType animationType) {
		this.animationType = animationType;
	} 

	private void hideAllPanels() {

		this.pawnPanel.setVisible(false);
		this.boatOrSeaPanel.setVisible(false);
		this.dicePanel.setVisible(false);
		this.tilesEffectsRedPanel.setVisible(false);
		this.tilesEffectsDefensePanel.setVisible(false);
		this.boardingPanel.setVisible(false);
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

	public boolean isChoice() {
		return choice;
	}

}
