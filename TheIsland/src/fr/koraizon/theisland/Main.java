package fr.koraizon.theisland;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Main extends JFrame{

	private static final long serialVersionUID = -6428921995278503091L;
	private Board board;
	private JPanel contentPane;
	
	public Main(){
	        super("The Island");
	        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        this.setSize(1850, 1040);
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
