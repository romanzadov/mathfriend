package java_specific;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import parse.path;
import representTerms.GUIMath;
import representTerms.PlaceAndFont;
import representTerms.Image;
import representTerms.stringrect;
import user.UserPrefferences;

public class RunAsJava {
	
	int width = 500;
	int height = 300;
	

	public static void main(String[] args) {
		String st = "2x-5=7";
		
		RunAsJava raj = new RunAsJava();
		raj.startFrame(st);
	}
	
	public void startFrame(String st){
		
		Image im = new Image(st, UserPrefferences.PREFFERED_FONT, width, height);
		System.out.println(im);
		
		JFrame f = new JFrame();
		JPanel p = new JPanel();
		
		p.setBackground(Color.blue);
		
		f.setSize(width,height);
		f.add(p);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}
}
