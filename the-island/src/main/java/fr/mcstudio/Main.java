package fr.mcstudio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import fr.mcstudio.board.ActionInfo;
import fr.mcstudio.board.Board;
import fr.mcstudio.board.PlayerInfo;

public class Main extends JFrame {

	private static final long serialVersionUID = -6428921995278503091L;
	private Board board;
	private PlayerInfo playerInfo;
	private ActionInfo actionInfo;
	private JPanel contentPane;

	public Main() {
		super("The Island");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1850, 1039);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLayeredPane boardPane = new JLayeredPane();
		boardPane.setBounds(282, 0, 1230, 1000);
		contentPane.add(boardPane);
		board = new Board(boardPane);

		JLayeredPane playerInfoPane = new JLayeredPane();
		playerInfoPane.setBounds(0, 0, 282, 1000);
		contentPane.add(playerInfoPane);
		playerInfo = new PlayerInfo(playerInfoPane);

		JLayeredPane actionInfoPane = new JLayeredPane();
		actionInfoPane.setBounds(1512, 0, 338, 1000);
		contentPane.add(actionInfoPane);
		actionInfo = new ActionInfo(actionInfoPane);
	
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main main = new Main();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
