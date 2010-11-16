package Gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.* ;

import Network.Gui.PropertySetOverrideEditor;

public class FrameManager  extends WindowAdapter
	 {
	  JPanel panel  ;
	  
	  JFrame frame  ;
	  
	  
	  String title  ;
	  
	  int 	 width  ;
	  
	  int 	 height ;
	  
	  public FrameManager()
	  	{
	  	}

	  
	  public void init( String title, int width, int height, JPanel panel ) 
		   {
		    this.title	= title 	;
		   
		    this.width  = width 	;
		    this.height = height 	;	
		    
		    this.panel  = panel 	;	    
		    
		    frame 		= null 		;
		   }
	  
	  public void showFrame()
		   {
		    if( panel == null )
		    	 {
		    	  System.out.println( "Cannot show panel in frame when panel is not set. Please contact developer." ) ;
		    	 
		    	  System.exit(  0  ) ;
		    	 
		    	 }
		   
		    if( frame == null )
		    	 {
		    	  initFrame() ;
		    	 }
		    else
		    	 {
		    	  frame.setVisible( true ) ;
		    	  frame.pack() ;
		    	 
		    	 }
		   }


	 private void initFrame()
		  {
		    frame = new JFrame() ;
		   
	 		 
		  	frame.setVisible( true ) ;
		  	 
		  	frame.setSize( width, height ) ;
		  	frame.setResizable(true);		
	 		frame.setContentPane(  panel  ) ;

	 		frame.setTitle( title ) ;

	 		frame.pack();		  
		  }
	  
	
      public void windowClosing( WindowEvent event ) 
    	 {
    	 }


	 public void destroy()
		  {
		   frame.dispose() ;
		   
		   frame = null ;
		   panel = null ;
		  }
    	 
	 }
