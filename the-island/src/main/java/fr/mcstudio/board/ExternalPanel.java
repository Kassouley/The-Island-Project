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
import fr.mcstudio.enums.HelpType;
import fr.mcstudio.enums.PawnType;
import fr.mcstudio.pawns.Explorer;
import fr.mcstudio.util.Pair;
import fr.mcstudio.util.PairList;

@SuppressWarnings("serial")

/**
 * It creates an ExternalPanel which is a JLayeredPane for the game.
 */
public class ExternalPanel extends JLayeredPane {

	// Creating a new ExternalPanel object with the parameters board and resolution.
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
		this.helpPanel = createDisplayPanel();
		this.helpPanel.setLayout(new BorderLayout(0, 0));
		
		this.setVisible(false);

		board.setLayer(this, 4);
		board.add(this);

		this.initMonsterLists();
		this.initAnimationList();
		this.initHelpList();
	}

	// Creating a variable called board that is of type Board.
	private Board board;

	// Creating a new JLabel object called backgroundPanel.
	private JLabel backgroundPanel = new JLabel("");
	
	// Creating a private variable called boatOrSeaPanel of type JPanel.
	private JPanel boatOrSeaPanel;

	// Declaring a variable called pawnPanel of type JPanel.
	private JPanel pawnPanel;

	// Creating a private variable called dicePanel of type JPanel.
	private JPanel dicePanel;

	// Declaring a private variable called tilesEffectsRedPanel of type JPanel.
	private JPanel tilesEffectsRedPanel;

	// Creating a private JPanel called tilesEffectsDefensePanel.
	private JPanel tilesEffectsDefensePanel;

	// Creating a JPanel object called animationPanel.
	private JPanel animationPanel;

	// Declaring a variable of type JPanel.
	private JPanel boardingPanel;

	// Declaring a variable called helpPanel of type JPanel.
	private JPanel helpPanel;

	// Creating a new PairList object that contains a JButton and a JLayeredPane.
	private PairList<JButton, JLayeredPane> bPairList = new PairList<JButton, JLayeredPane>();

	// Creating a new PairList object that is a list of pairs of AnimationType and JLabel objects.
	private PairList<AnimationType, JLabel> aPairList = new PairList<AnimationType, JLabel>();

	// Creating a new PairList object with the type parameters HelpType and JLabel.
	private PairList<HelpType, JLabel> hPairList = new PairList<HelpType, JLabel>();
	
	// Creating a list of Explorer objects.
	private List<Explorer> boardingpawns = new ArrayList<Explorer>();

	// Creating a list of JLabels called seaSnakeList.
	private List<JLabel> seaSnakeList = new ArrayList<JLabel>();

	// Creating a new ArrayList of JLabels.
	private List<JLabel> sharkList = new ArrayList<JLabel>();

	// Creating a new ArrayList of JLabels.
	private List<JLabel> whaleList = new ArrayList<JLabel>();

	// Creating a variable called selection that is of type JLayeredPane.
	private JLayeredPane selection = null;

	// Declaring a variable called pawnType of type PawnType.
	private PawnType pawnType;

	// Declaring a variable of type Boolean and initializing it to null.
	private Boolean choice = null;

	// Creating a private variable called clickedHex of type Hexagon.
	private Hexagon clickedHex;

	// Declaring a variable called animationType of type AnimationType.
	private AnimationType animationType;

	// Declaring a variable of type HelpType.
	private HelpType helpType;

	// Declaring a variable of type ExternalPanelState and initializing it to the value VOID.
	private ExternalPanelState externalPanelState = ExternalPanelState.VOID;
	
	// Declaring a variable called resolution.
	private int resolution;

	/**
	 * > This function returns the current state of the external panel
	 * 
	 * @return The externalPanelState object.
	 */
	public ExternalPanelState getExternalPanelState() {
		return externalPanelState;
	}

	/**
	 * > This function sets the external panel state and displays the external panel
	 * 
	 * @param externalPanelState The state of the external panel.
	 */
	public void setExternalPanelState(ExternalPanelState externalPanelState) {
		this.externalPanelState = externalPanelState;
		displayExternalPanel(externalPanelState);
	}

	/**
	 * It creates a JPanel, sets its bounds to the size of the JFrame, sets its layer to 1, adds it to the
	 * JFrame, sets it to be transparent, and sets it to be invisible
	 * 
	 * @return A JPanel object.
	 */
	private JPanel createDisplayPanel() {
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.setLayer(panel, 1);
		this.add(panel);
		panel.setOpaque(false);
		panel.setVisible(false);
		return panel;
	}

	/**
	 * Display the external panel on the screen.
	 * 
	 * @param state the state of the external panel
	 */
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
			case HELPPANEL:
				displayHelpPanel();
				break;
		}
	}

	/**
	 * It removes all components from the helpPanel, then adds the component that corresponds to the
	 * helpType variable
	 */
	private void displayHelpPanel() {
		helpPanel.removeAll();
		this.helpPanel.setVisible(true);
		
		if (hPairList.containsInPair(helpType)) {
			int index = hPairList.getLeftList().indexOf(helpType);
			helpPanel.add(hPairList.get(index).getRight(), BorderLayout.CENTER);
		}

		helpType = null;
		helpPanel.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				board.setDisplayExternalPanel(false);
				setExternalPanelState(ExternalPanelState.VOID);
                board.getGame().getActionInfo().displayActionInfo(board.getGame());
                board.getGame().getPlayerInfo().displayPlayerInfo(board.getGame(), resolution);
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

	
	/**
	 * It creates a panel with buttons that represent the explorers on the hex. When you click on a
	 * button, it adds the explorer to a list and removes the button from the panel. When the list reaches
	 * a certain size, it performs an action
	 */
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

	/**
	 * It displays the animation panel
	 */
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
                board.getGame().getActionInfo().displayActionInfo(board.getGame());
                board.getGame().getPlayerInfo().displayPlayerInfo(board.getGame(), resolution);
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

	/**
	 * It displays the tiles of the current player that have a red effect when you click on the button "Voir mes tuiles"
	 */
	private void displayTileEffectRedPanel() {
		tilesEffectsRedPanel.removeAll();
		bPairList.clear();
		this.tilesEffectsRedPanel.setVisible(true);
        for (Tile tile : board.getGame().getCurrentPlayer().getTileList()) {
        	if(tile.getEffect().getType() == "Rouge") {
        		bPairList.add(new Pair<JButton,JLayeredPane>(
					new JButton(tile.getEffectLabel().getIcon()), tile
					)
				);
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
	
	/**
	 * It displays a panel with two buttons (yes or no), and when one of the buttons is clicked, it sets a boolean to
	 * true or false, and then calls a function in the game class
	 */
	private void displayTileEffectDefensePanel() {
		tilesEffectsDefensePanel.removeAll();
		this.tilesEffectsDefensePanel.setVisible(true);
		choice = false;
		
		JButton yes = new JButton(new ImageIcon(ExternalPanel.class.getResource("/yes_button.png")));
		JButton no = new JButton(new ImageIcon(ExternalPanel.class.getResource("/no_button.png")));
		yes.setFocusPainted(false);
		yes.setContentAreaFilled(false);
		no.setFocusPainted(false);
		no.setContentAreaFilled(false);
		tilesEffectsDefensePanel.add(yes);
		tilesEffectsDefensePanel.add(no);
		
		yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				choice = true;
				board.setDisplayExternalPanel(false);
                setExternalPanelState(ExternalPanelState.VOID);
                board.getGame().defWithTile(clickedHex);
			}
			
		});
		no.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				choice = false;
				board.setDisplayExternalPanel(false);
                setExternalPanelState(ExternalPanelState.VOID);
                board.getGame().defWithTile(clickedHex);
			}
			
		});
	}

	/**
	 * It displays a dice panel with a random animation of a sea snake, shark or whale
	 */
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
		dicePanel.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				board.setDisplayExternalPanel(false);
				setExternalPanelState(ExternalPanelState.VOID);
                board.getGame().getActionInfo().displayActionInfo(board.getGame());
                board.getGame().getPlayerInfo().displayPlayerInfo(board.getGame(), resolution);
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

	/**
	 * It displays a button with a boat image and a button with a sea image to choose where a player want to go
	 */
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

	/**
	 * It displays a panel of buttons that represent the pawns that are on the clicked hex
	 */
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

	/**
	 * It adds two gifs to each of the three lists
	 */
	private void initMonsterLists() {
		this.seaSnakeList.add(new JLabel(new ImageIcon(ExternalPanel.class.getResource("/Animation/seaSnake/seaSnake1.gif"))));
		this.seaSnakeList.add(new JLabel(new ImageIcon(ExternalPanel.class.getResource("/Animation/seaSnake/seaSnake2.gif"))));
		this.sharkList.add(new JLabel(new ImageIcon(ExternalPanel.class.getResource("/Animation/shark/shark1.gif"))));
		this.sharkList.add(new JLabel(new ImageIcon(ExternalPanel.class.getResource("/Animation/shark/shark1.gif"))));
		this.whaleList.add(new JLabel(new ImageIcon(ExternalPanel.class.getResource("/Animation/whale/whale1.gif"))));
		this.whaleList.add(new JLabel(new ImageIcon(ExternalPanel.class.getResource("/Animation/whale/whale2.gif"))));
	}

	/**
	 * It adds a bunch of animation to a list
	 */
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
	
	private void initHelpList() {
		this.hPairList.add(
				new Pair<HelpType,JLabel>(
					HelpType.INITIALISATION,
					new JLabel(
						new ImageIcon(ExternalPanel.class.getResource("/Help/helpInit.png"))
					)
				)
			);
		this.hPairList.add(
				new Pair<HelpType,JLabel>(
					HelpType.TILEEFFECT,
					new JLabel(
						new ImageIcon(ExternalPanel.class.getResource("/Help/helpPlayTile.png"))
					)
				)
			);
		this.hPairList.add(
				new Pair<HelpType,JLabel>(
					HelpType.MOVEPAWN,
					new JLabel(
						new ImageIcon(ExternalPanel.class.getResource("/Help/helpMovePawn.png"))
					)
				)
			);
		this.hPairList.add(
				new Pair<HelpType,JLabel>(
					HelpType.DISCOVER,
					new JLabel(
						new ImageIcon(ExternalPanel.class.getResource("/Help/helpDiscoverTile.png"))
					)
				)
			);
		this.hPairList.add(
				new Pair<HelpType,JLabel>(
					HelpType.MOVEMONSTER,
					new JLabel(
						new ImageIcon(ExternalPanel.class.getResource("/Help/helpRollMonster.png"))
					)
				)
			);
	}

	/**
	 * This function returns the hexagon that was clicked
	 * 
	 * @return The clickedHex variable is being returned.
	 */
	public Hexagon getClickedHex() {
		return clickedHex;
	}

	/**
	 * This function sets the clickedHex variable to the hexagon that was clicked
	 * 
	 * @param clickedHex The hexagon that was clicked
	 */
	public void setClickedHex(Hexagon clickedHex) {
		this.clickedHex = clickedHex;
	}

	/**
	 * This function returns the pawn type of the pawn
	 * 
	 * @return The pawnType variable is being returned.
	 */
	public PawnType getPawnType() {
		return pawnType;
	}

	/**
	 * This function sets the pawn type of the pawn
	 * 
	 * @param pawnType The type of pawn that the player is using.
	 */
	public void setPawnType(PawnType pawnType) {
		this.pawnType = pawnType;
	}

	/**
	 * This function sets the animation type of the current object
	 * 
	 * @param animationType The type of animation to use.
	 */
	public void setAnimationType(AnimationType animationType) {
		this.animationType = animationType;
	} 

	/**
	 * It hides all the panels of the external panel
	 */
	private void hideAllPanels() {
		this.pawnPanel.setVisible(false);
		this.boatOrSeaPanel.setVisible(false);
		this.dicePanel.setVisible(false);
		this.tilesEffectsRedPanel.setVisible(false);
		this.tilesEffectsDefensePanel.setVisible(false);
		this.boardingPanel.setVisible(false);
		this.animationPanel.setVisible(false);
		this.helpPanel.setVisible(false);
		this.setVisible(false);
	}

	/**
	 * It sets the bounds of the panel based on the resolution
	 * 
	 * @param resolution The resolution of the game.
	 */
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

	/**
	 * It sets the background of the panel to an image
	 */
	private void setLabel() {
		ImageIcon icone = new ImageIcon(Board.class.getResource("/Menu/ExternalPanel.png"));
		Image scaleImage = icone.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
		icone.setImage(scaleImage);
		backgroundPanel.setIcon(icone);
		backgroundPanel.setBounds(0, 0, getWidth(), getHeight());
		this.add(backgroundPanel);
	}
	
	/**
	 * This function sets the help type of the help request
	 * 
	 * @param helpType The type of help you want to display.
	 */
	public void setHelpType(HelpType helpType) {
		this.helpType = helpType;
	}

	/**
	 * This function returns the board
	 * 
	 * @return The board object.
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * This function returns the JLayeredPane object that is used to display the selection
	 * 
	 * @return The selection variable is being returned.
	 */
	public JLayeredPane getSelection() {
		return selection;
	}

	/**
	 * This function sets the selection to the JLayeredPane that is passed in
	 * 
	 * @param selection The JLayeredPane that is being selected.
	 */
	public void setSelection(JLayeredPane selection) {
		this.selection = selection;
	}

	/**
	 * This function returns the value of the choice variable
	 * 
	 * @return The choice variable is being returned.
	 */
	public Boolean getChoice() {
		return choice;
	}

	/**
	 * This function sets the value of the choice variable to the value of the choice parameter
	 * 
	 * @param choice This is the boolean value
	 */
	public void setChoice(Boolean choice) {
		this.choice = choice;
	}

}
