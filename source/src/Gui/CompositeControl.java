package Gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;


public class CompositeControl
	 {
	  JPanel parent ;
		 
	  public JPanel viewContainer ;
	  
	  protected CompositeControl( JPanel parent )
	   	{
	  	 this.parent = parent ;
	  	 
	  	 viewContainer = new JPanel() ;
	  	  	 
	  //	 viewContainer.setBorder(BorderFactory.createLineBorder(Color.black));
	
	  //	 viewContainer.setLayout( null ) ;

	  	viewContainer.setLayout( new BorderLayout() );

	  	 
	  	 viewContainer.setVisible( true ) ;
	  	 
	  	 if( parent != null )
	  		  parent.add( "Center", viewContainer ) ;
	  	 
	  	 
	  
	   	}
	 }
