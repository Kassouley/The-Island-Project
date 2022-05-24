package fr.mcstudio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.mcstudio.enums.Color;
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
		
		int nbJoueur = 1;
		players = new Player[nbJoueur];
		
		Player P1 = new Player("Akunes", Color.BLUE, false, resolution);
		players[0] = P1;
		/*Player P2 = new Player("lo", Color.RED, false, resolution);
		players[1] = P2;
		Player P3 = new Player("Lucasse", Color.YELLOW, false);
		players[2] = P3;
		Player P4 = new Player("kev1", Color.GREEN, false);
		players[3] = P4;*/
			
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main main = new Main();
					Game game = new Game(main.resolution, main.contentPane, main.players);
					game.initializeBoard();
					
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
