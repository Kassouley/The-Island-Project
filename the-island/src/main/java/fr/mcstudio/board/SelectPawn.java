package fr.mcstudio.board;

import java.awt.event.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import fr.mcstudio.pawns.Explorer;
import fr.mcstudio.pawns.Pawn;
import fr.mcstudio.util.Pair;
import fr.mcstudio.util.PairList;

public class SelectPawn {

    private PairList<JButton, Pawn> bPairList;
    private Pawn pawn;
    private boolean clicked;

    public SelectPawn(Board board) {
        JLayeredPane selectPane = new JLayeredPane();
        JPanel backgroundPanel = new JPanel();
        JPanel pawnPanel = new JPanel();
        bPairList = new PairList<JButton, Pawn>();
        List<Explorer> explorers = new ArrayList<Explorer>();
        Explorer exp = new Explorer(fr.mcstudio.enums.Color.BLUE, 5);
        for (int i = 0; i < 11; i++) {
            explorers.add(exp);
        }

        selectPane.setBounds(0, 0, 955, 770);

        selectPane.setLayer(backgroundPanel, 0);
        backgroundPanel.setBounds(227, 235, 500, 300);
        backgroundPanel.setBackground(Color.GRAY);
        selectPane.add(backgroundPanel);

        selectPane.setLayer(pawnPanel, 1);
        pawnPanel.setOpaque(false);
        pawnPanel.setBounds(227, 235, 500, 300);
        pawnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        pawnPanel.setVisible(true);

        int explorersLength = explorers.size();
        for (int i = 0; i < explorersLength; i++) {
            bPairList.add(new Pair<JButton,Pawn>(new JButton(new ImageIcon(SelectPawn.class.getResource("/pion_rouge.png"))), explorers.get(i)));
            bPairList.get(i).getLeft().setBackground(Color.GRAY);
            pawnPanel.add(bPairList.get(i).getLeft());
        }

        selectPane.add(pawnPanel);
        board.setLayer(selectPane, 4);
        board.add(selectPane);

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
