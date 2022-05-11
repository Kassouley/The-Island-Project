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
	@SuppressWarnings("unused")
	private Board board;
	@SuppressWarnings("unused")
	private PlayerInfo playerInfo;
	@SuppressWarnings("unused")
	private ActionInfo actionInfo;
	private JPanel contentPane;

	public Main() {
		super("The Island");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSizeFromResolution(resolution);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
	}
	int resolution = 90;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main main = new Main();
					
					JLayeredPane boardPane = new JLayeredPane();
					switch(main.resolution) {
					case 70:
						boardPane.setBounds(282, 0, 1230, 1000);
						break;
					case 80:
						boardPane.setBounds(282, 0, 1230, 1000);
						break;
					case 90:
						boardPane.setBounds(282, 0, 1230, 1000);
						break;
					default:
						break;
					}
					main.contentPane.add(boardPane);
					main.board = new Board(main.resolution, boardPane);

					JLayeredPane playerInfoPane = new JLayeredPane();
					switch(main.resolution) {
					case 70:
						playerInfoPane.setBounds(0, 0, 282, 1000);
						break;
					case 80:
						playerInfoPane.setBounds(0, 0, 282, 1000);
						break;
					case 90:
						playerInfoPane.setBounds(0, 0, 282, 1000);
						break;
					default:
						break;
					}
					main.contentPane.add(playerInfoPane);
					main.playerInfo = new PlayerInfo(main.resolution, playerInfoPane);

					JLayeredPane actionInfoPane = new JLayeredPane();
					switch(main.resolution) {
					case 70:
						actionInfoPane.setBounds(1512, 0, 338, 1000);
						break;
					case 80:
						actionInfoPane.setBounds(1512, 0, 338, 1000);
						break;
					case 90:
						actionInfoPane.setBounds(1512, 0, 338, 1000);
						break;
					default:
						break;
					}
					main.contentPane.add(actionInfoPane);
					main.actionInfo = new ActionInfo(main.resolution, actionInfoPane);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void setSizeFromResolution(int resolution) {
		switch(resolution) {
		case 70:
			this.setSize(1432, 809);
			break;
		case 80:
			this.setSize(1635, 919);
			break;
		case 90:
			this.setSize(1850, 1029);
			break;
		default:
			break;
		}
	}

}