package fr.mcstudio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

<<<<<<< Updated upstream
import fr.mcstudio.enums.Color;
=======
import fr.mcstudio.board.SelectPawn;
>>>>>>> Stashed changes
import fr.mcstudio.game.Game;
import fr.mcstudio.game.Player;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private Player[] players;
	private JPanel contentPane;

	int resolution = 70;

	public Main() {
		super("The Island");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setFrameSizeFromResolution(resolution);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		int nbJoueur = 4;
		players = new Player[nbJoueur];
		
		Player P1 = new Player("Akunes", Color.BLUE, false);
		Player P2 = new Player("lo", Color.RED, false);	
		Player P3 = new Player("Lucasse", Color.YELLOW, false);
		Player P4 = new Player("kev1", Color.GREEN, false);
		players[0] = P1;
		players[1] = P2;
		players[2] = P3;
		players[3] = P4;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main main = new Main();
					Game game = new Game(main.resolution, main.contentPane, main.players);
<<<<<<< Updated upstream
					game.initializeBoard();
=======
					//SelectPawn sp = new SelectPawn(game.getBoard());
>>>>>>> Stashed changes
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void setFrameSizeFromResolution(int resolution) {
		switch (resolution) {
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
