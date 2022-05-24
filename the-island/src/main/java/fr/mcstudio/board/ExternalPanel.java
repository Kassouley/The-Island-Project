package fr.mcstudio.board;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import fr.mcstudio.enums.Color;
import fr.mcstudio.enums.ExternalPanelState;
import fr.mcstudio.game.Player;
import fr.mcstudio.pawns.Explorer;
import fr.mcstudio.pawns.Pawn;
import fr.mcstudio.util.Pair;
import fr.mcstudio.util.PairList;

@SuppressWarnings("serial")
public class ExternalPanel extends JLayeredPane{
	
	private Board board;
	private JLabel backgroundPanel = new JLabel("");;
	private JPanel pawnPanel;
	private JPanel dicePanel;
	private JPanel tilesEffectsPanel;
	private JPanel animationPanel;
	
	private PairList<JButton, Pawn> bPairList = new PairList<JButton, Pawn>();
	
	private Pawn pawn = null;
	private Hexagon clickedHex;
	
	private ExternalPanelState externalPanelState = ExternalPanelState.VOID;
	
	public ExternalPanel(Board board, int resolution) {
		this.board = board;
		this.setLayout(null);
		this.setLayer(backgroundPanel, 0);
		this.setPanelBoundsFromResolution(resolution);
		this.setLabel();
		
		this.pawnPanel = createDisplayPanel();
		this.pawnPanel.setLayout(new GridLayout(4, 0, 0, 0));
		this.dicePanel = createDisplayPanel();
		this.tilesEffectsPanel = createDisplayPanel();
		this.animationPanel = createDisplayPanel();
		this.setVisible(false);
		
		board.setLayer(this, 4);
		board.add(this);
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
		switch(state) {
		case VOID : 
			hideAllPanels();
			break;
		case PAWNPANEL : 
			displayPawnPanel();
			break;
		case DICEPANEL : 
			displayDicePanel();
			break;
		case TILEEFFECTPANEL : 
			displayTileEffectPanel();
			break;
		case ANIMATIONPANEL : 
			displayAnimationPanel();
			break;
	}
	}
	
	private void displayAnimationPanel() {
		// TODO Auto-generated method stub
		
	}

	private void displayTileEffectPanel() {
		// TODO Auto-generated method stub
		
	}

	private void displayDicePanel() {
		// TODO Auto-generated method stub
		
	}

	private void displayPawnPanel() {

		this.pawnPanel.setVisible(true);
		for (int i = 0; i < bPairList.size(); i++) {
			pawnPanel.remove(bPairList.get(i).getLeft());
		}
		bPairList.clear();
		bPairList = new PairList<JButton, Pawn>();
		
        int explorersLength = clickedHex.getExplorerList().size();
        for (int i = 0; i < explorersLength; i++) {
        	Explorer explorer = clickedHex.getExplorerList().get(i);
        	if(board.game.getCurrentPlayer().getColor() == explorer.getColor()) {
        		bPairList.add(new Pair<JButton,Pawn>(new JButton(explorer.getImage().getIcon()), explorer));
                bPairList.get(i).getLeft().setBackground(java.awt.Color.WHITE);
                //bPairList.get(i).getLeft().setBorderPainted(false);
                //bPairList.get(i).getLeft().setBorder(BorderFactory.createLineBorder(java.awt.Color.WHITE));
                bPairList.get(i).getLeft().setFocusPainted(false);
                bPairList.get(i).getLeft().setContentAreaFilled(false);
                pawnPanel.add(bPairList.get(i).getLeft());
        	}
            bPairList.get(i).getLeft().addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int index = bPairList.getLeftList().indexOf(e.getSource());
                    pawn = bPairList.get(index).getRight();
                    board.setDisplayExternalPanel(false);
                    setExternalPanelState(ExternalPanelState.VOID);
                    board.game.inGame(clickedHex);
                }
            });
        }
		
	}

	public Pawn getPawn() {
		return pawn;
	}

	public void setPawn(Pawn pawn) {
		this.pawn = pawn;
	}

	public Hexagon getClickedHex() {
		return clickedHex;
	}

	public void setClickedHex(Hexagon clickedHex) {
		this.clickedHex = clickedHex;
	}

	private void hideAllPanels() {

		this.pawnPanel.setVisible(false);
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
		Image scaleImage = icone.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);;
		icone.setImage(scaleImage);
		backgroundPanel.setIcon(icone);
		backgroundPanel.setBounds(0, 0, getWidth(), getHeight());
		this.add(backgroundPanel);
	}
	
	/*public void getReturnedPawn() {
		int bPairListLength = bPairList.size();

        for (int i = 0; i < bPairListLength; i++) {
            bPairList.get(i).getLeft().addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int index = bPairList.getLeftList().indexOf(e.getSource());
                    pawn = bPairList.get(index).getRight();
                }
            });
        }
	}*/

	public Board getBoard() {
		return board;
	}
}
