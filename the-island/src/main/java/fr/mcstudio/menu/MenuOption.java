package fr.mcstudio.menu;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class MenuOption {
	
	private JFrame frame ;
	
	public MenuOption() {
		
	frame = new JFrame();
	frame.setBounds(0,0,1635,919);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	JLayeredPane layeredPane = new JLayeredPane();
	layeredPane.setBounds(10, 11, 1599, 858);
	frame.getContentPane().add(layeredPane);
	
	JLabel lblNewLabel = new JLabel("OPTIONS");
	layeredPane.setLayer(lblNewLabel, 1);
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 45));
    //--------------------------------------------------------------------------- A changer aussi la font
	lblNewLabel.setForeground(Color.RED);
	lblNewLabel.setBounds(477, 34, 553, 110);
	layeredPane.add(lblNewLabel);
	
	JButton btnNewButton = new JButton("Aide");
	layeredPane.setLayer(btnNewButton, 1);
	btnNewButton.setFont(new Font("Monospaced", Font.BOLD, 35));
    //--------------------------------------------------------------------------- A changer aussi la font
	btnNewButton.setBounds(369, 458, 188, 77);
	layeredPane.add(btnNewButton);
	btnNewButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	
    		System.out.println("AIDE");
    		
    		//JOptionPane.showMessageDialog( null, "Vous allez �tre redirig� vers une page WEB ! ", "R�gles du Jeu", JOptionPane.PLAIN_MESSAGE);
    		JOptionPane jop = new JOptionPane();		
			int option = jop.showConfirmDialog(null, "Vous allez �tre redirig� vers une page WEB ! ", "R�gles du Jeu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(option == JOptionPane.OK_OPTION)
			{
				try {
					Desktop.getDesktop().browse(new URI("http://carnetdesgeekeries.com/j2s-the-island-asmodee/"));
                    //--------------------------------------------------------------------------- Ça je sais pas si ça marche mtn
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
    		
    	
            
            }
    });
	
	JButton btnDimensions = new JButton("Dimensions");
	layeredPane.setLayer(btnDimensions, 1);
	btnDimensions.setFont(new Font("Monospaced", Font.BOLD, 35));
    //--------------------------------------------------------------------------- A changer aussi la font
	btnDimensions.setBounds(628, 516, 268, 77);
	layeredPane.add(btnDimensions);
	
	JButton btnSon = new JButton("Son");
	layeredPane.setLayer(btnSon, 1);
	btnSon.setFont(new Font("Monospaced", Font.BOLD, 35));
    //--------------------------------------------------------------------------- A changer aussi la font
	btnSon.setBounds(972, 458, 188, 77);
	layeredPane.add(btnSon);
	
	
}

}
