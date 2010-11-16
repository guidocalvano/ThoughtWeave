package Main;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

import Gui.FrameManager;

public class DemonstrationBackgroundColorBehavior extends Behavior implements KeyListener
	 {
	 
	  double dRed 	;
	  double dGreen ;
	  double dBlue 	;
	  
	  Color3f color  ;
	  
	  long previousTimeInMillis ;
	  
	  WakeupCriterion wakeup ;
	  
	  Background background ;

	  
	  ColorPanel colorPanel ;
	  
	  FrameManager frameManager ;
	  
	  static final double COLOR_PER_MILLI = .002 ;
	 

	  public DemonstrationBackgroundColorBehavior( Component c,
			   Background background  )
		  {
		  BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
			 setSchedulingBounds(bounds);
			 
		   c.addKeyListener( this ) ;
		   this.background = background ;
		   
		   color = new Color3f() ;
		   background.getColor( color ) ;
		   
		   colorPanel = new ColorPanel( color ) ;
		   
		   frameManager = new FrameManager() ;
		   
		   frameManager.init( "Background Color", 100, 100, colorPanel.getPanel() ) ;
		  }

	  public void showWindow()
		   {
		    frameManager.showFrame() ;
		   }
	  
	  public void initialize()
		   {
		   
		    wakeup = new WakeupOnElapsedFrames( 1 ) ;

		    elapsedTime() ;
		    
		    wakeOnNextFrame() ;
		    

		   }
	  public void	processStimulus(java.util.Enumeration criteria)
		   {
		    resetBackgroundColor( elapsedTime() ) ;

		    wakeOnNextFrame() ;
		    

		   }
	  
	  double elapsedTime()
		   {
		    long nextTimeInMillis = System.currentTimeMillis() ;
		    long dT = nextTimeInMillis - previousTimeInMillis ;
		   
		    previousTimeInMillis = nextTimeInMillis ;
		    
		    
		    return (double) dT ;
		   }
	  
	  void resetBackgroundColor( double dT )
		  {
		   
		  
		   color.x += dRed   * COLOR_PER_MILLI ;
		   color.y += dGreen * COLOR_PER_MILLI ;
		   color.z += dBlue  * COLOR_PER_MILLI ;
		   
		   if( color.x > 1 ) color.x = 1 ;
		   if( color.y > 1 ) color.y = 1 ;
		   if( color.z > 1 ) color.z = 1 ;
		   
		   if( color.x < 0 ) color.x = 0 ;
		   if( color.y < 0 ) color.y = 0 ;
		   if( color.z < 0 ) color.z = 0 ;
		   
		   
		   background.setColor( color ) ;
		   
		  }
	  
	  
	  void wakeOnNextFrame()
		  {
		   
		   wakeupOn( wakeup ) ;
		  }
	  
	  public void keyPressed( java.awt.event.KeyEvent evt) 
		  {
		  
		
		  
		   switch( evt.getKeyCode() )
		   		{
			 	
			 	 case java.awt.event.KeyEvent.VK_R :
			 		dRed = 1 ;				 		
			 	 break;
			 	
			 	 case java.awt.event.KeyEvent.VK_F :
			 		dRed = -1 ;				 		
		 		 break;
			 	
			 	 case java.awt.event.KeyEvent.VK_T :
			 		dGreen = 1 ;				 		
			 	 break;
		 	
			 	 case java.awt.event.KeyEvent.VK_G :
			 		dGreen = -1 ;				 		
	 			 break;
	 			 
	 			 
			 	 case java.awt.event.KeyEvent.VK_Y :
			 		dBlue = 1 ;				 		
			 	 break;
		 	
			 	 case java.awt.event.KeyEvent.VK_H :
			 		dBlue = -1 ;				 		
	 			 break;
		   		}
		  }
		  
	  public void keyReleased( java.awt.event.KeyEvent evt) 
		  {
		  
		   switch( evt.getKeyCode() )
	   		{
		 	
		 	 case java.awt.event.KeyEvent.VK_R :
		 		dRed = 0 ;				 		
		 	 break;
		 	
		 	 case java.awt.event.KeyEvent.VK_F :
		 		dRed = 0 ;				 		
	 		 break;
		 	
		 	 case java.awt.event.KeyEvent.VK_T :
		 		dGreen = 0 ;				 		
		 	 break;
	 	
		 	 case java.awt.event.KeyEvent.VK_G :
		 		dGreen = 0 ;				 		
			 break;
			 
			 
		 	 case java.awt.event.KeyEvent.VK_Y :
		 		dBlue = 0 ;				 		
		 	 break;
	 	
		 	 case java.awt.event.KeyEvent.VK_H :
		 		dBlue = 0 ;				 		
			 break;
	   		}
		  
		  
		  }

	 public void keyTyped( KeyEvent e)
		  {
		  
		  }
	 

	 }
