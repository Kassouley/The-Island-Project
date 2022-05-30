package fr.mcstudio.menu;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Background extends JLabel {
	
	ImageIcon img ; 
	JFrame frame ;
	int resolution ; 
	
	public Background(ImageIcon img  , int resolution , JFrame frame)
	{
		this.img = img ; 
		this.frame = frame ;
		this.resolution = resolution ; 
		
		int a=0 , b=0 ; 
		
		switch (resolution) {
        case 70:
        	System.out.println("Petite Interface");
        	a = 1432 ;
        	b = 809 ;
        	//  1432x809
            break;
        case 80:
        	a = 1635 ;
        	b = 919 ;
        	
            break;      	
        case 90:
        	System.out.println("Grande Interface");
        	a = 1850 ;
        	b = 1029 ;
        	//1850x1029 
            break;
        default:
            break;
        }
		
		Image image = img.getImage().getScaledInstance(a,b , java.awt.Image.SCALE_SMOOTH);
	    JLabel background=new JLabel(new ImageIcon(image));
	    background.setBounds(0,0,a,b);
	    //background.setFont(new Font("Kristen ITC", Font.PLAIN, 30));
	    background.setLayout(null);
	    frame.add(background);

		
	}
}