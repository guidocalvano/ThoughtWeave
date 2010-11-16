package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Gui.FloatField;

import javax.vecmath.Color3f;


public class ColorPanel implements ActionListener
	 {
	  JPanel panel ;
	 
	  FloatField red ;
	  FloatField green ;
	  FloatField blue ;
	 
	  JLabel	 redLabel ;
	  JLabel	 greenLabel ;
	  JLabel	 blueLabel ;
	  
	  Color3f color ;
	  
	  ColorPanel( Color3f color )
	  	{
	  	 this.color = color ;
	  	
	  	 panel = new JPanel() ;
	  	 
	  	 redLabel = new JLabel( "red"  ) ;
	  	 greenLabel = new JLabel( "green"  ) ;
	  	 blueLabel = new JLabel( "blue"  ) ;
	  	 
	  	 red 	= new FloatField( 0f, 1f ) ;
	  	 green	= new FloatField( 0f, 1f ) ;
	  	 blue	= new FloatField( 0f, 1f ) ;
	  	 
	  	 
	  	 panel.add(  redLabel   ) ; panel.add(  red.getPanel() ) ;
	  	 panel.add(  greenLabel ) ; panel.add(  green.getPanel() ) ;
	  	 panel.add(  blueLabel  ) ; panel.add(  blue.getPanel() ) ;
	  	 
	  	 red.addActionListener( 	this ) ;
	  	 green.addActionListener( 	this ) ;
	  	 blue.addActionListener(	this ) ;
	  	}


	 public void actionPerformed( ActionEvent e)
		  {
		   if( red.peekValue() != null && green.peekValue() != null && blue.peekValue() != null )
				updateColor() ;
		  }


	 private void updateColor()
		  {
		   color.x = red.acceptValue() ;
		   color.y = green.acceptValue() ;
		   color.z = blue.acceptValue() ;
		  }


	 public JPanel getPanel()
		  {
		  // TODO Auto-generated method stub
		  return panel ;
		  }
	 }
