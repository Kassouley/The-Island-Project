package fr.mcstudio.board;

import java.awt.event.*;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import fr.mcstudio.enums.Color;
import fr.mcstudio.pawns.Explorer;
import fr.mcstudio.pawns.Pawn;
import fr.mcstudio.util.Pair;
import fr.mcstudio.util.PairList;

@SuppressWarnings("serial")
public class SelectPawn extends JLayeredPane{

    private PairList<JButton, Pawn> bPairList;
    private Pawn pawn;
    private boolean clicked;

    public SelectPawn(Board board) {
        JPanel backgroundPanel = new JPanel();
        JPanel pawnPanel = new JPanel();
        bPairList = new PairList<JButton, Pawn>();
        List<Explorer> explorers = new ArrayList<Explorer>();
        Explorer exp = new Explorer(Color.YELLOW, 5);
        for (int i = 0; i < 11; i++) {
            explorers.add(exp);
        }

        this.setBounds(0, 0, 955, 770);

        this.setLayer(backgroundPanel, 0);
        backgroundPanel.setBounds(227, 235, 500, 300);
        backgroundPanel.setBackground(java.awt.Color.GRAY);
        this.add(backgroundPanel);

        this.setLayer(pawnPanel, 1);
        pawnPanel.setOpaque(false);
        pawnPanel.setBounds(227, 235, 500, 300);
        pawnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        pawnPanel.setVisible(true);

        int explorersLength = explorers.size();
        for (int i = 0; i < explorersLength; i++) {
            bPairList.add(new Pair<JButton,Pawn>(new JButton(new ImageIcon(SelectPawn.class.getResource("/pion_jaune.png"))), explorers.get(i)));
            bPairList.get(i).getLeft().setBackground(java.awt.Color.GRAY);
            bPairList.get(i).getLeft().setBorderPainted(false);
            bPairList.get(i).getLeft().setFocusPainted(false);
            bPairList.get(i).getLeft().setContentAreaFilled(false);
            pawnPanel.add(bPairList.get(i).getLeft());
        }

        this.add(pawnPanel);
        board.setLayer(this, 4);
        board.add(this);

        this.getReturnedPawn();
    }

    public void getReturnedPawn() {
        this.clicked = false;
        int bPairListLength = bPairList.size();

        for (int i = 0; i < bPairListLength; i++) {
            bPairList.get(i).getLeft().addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int index = bPairList.getLeftList().indexOf(e.getSource());
                    pawn = bPairList.get(index).getRight();
                    clicked = true;
                }
            });
        }
    }
}
