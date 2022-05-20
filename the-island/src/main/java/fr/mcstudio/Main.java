package fr.mcstudio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.mcstudio.game.Game;
import fr.mcstudio.game.Player;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private Player[] players;
	private JPanel contentPane;

	int resolution = 90;

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
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main main = new Main();
					Game game = new Game(main.resolution, main.contentPane, main.players);
					
					
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
