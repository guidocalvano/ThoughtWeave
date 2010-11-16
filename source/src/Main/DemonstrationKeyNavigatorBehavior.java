package Main;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.j3d.*;
import javax.vecmath.Point3d;

import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;

public class DemonstrationKeyNavigatorBehavior extends Behavior implements KeyListener
	 {
	 
	  double rotX ;
	  double rotY ;
	  double rotZ ;
	  
	  double angleX ;
	  double angleY ;
	  double angleZ ;
	  
	  
	  boolean alignRotationToAxis ;
	  
	  long previousTimeInMillis ;
	  
	  WakeupCriterion wakeup ;
	  
	  TransformGroup rotate ;
	  
	  static final double ANGLE_PER_MILLI = .002 ;
	 

	  public DemonstrationKeyNavigatorBehavior( Component c,
			   TransformGroup targetTG )
		  {
		  BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
			 setSchedulingBounds(bounds);
			 
		   c.addKeyListener( this ) ;
		   rotate = targetTG ;
		   
		   alignRotationToAxis = false ;
		  }

	  public void initialize()
		   {
		   
		    wakeup = new WakeupOnElapsedFrames( 1 ) ;

		    elapsedTime() ;
		    
		    wakeOnNextFrame() ;
		    

		   }
	  public void	processStimulus(java.util.Enumeration criteria)
		   {
		    doRotation( elapsedTime() ) ;

		    wakeOnNextFrame() ;
		    

		   }
	  
	  double elapsedTime()
		   {
		    long nextTimeInMillis = System.currentTimeMillis() ;
		    long dT = nextTimeInMillis - previousTimeInMillis ;
		   
		    previousTimeInMillis = nextTimeInMillis ;
		    
		    
		    return (double) dT ;
		   }
	  
	  void doRotation( double dT )
		  {
		   
		   Transform3D nextRotation = new Transform3D() ;
		   
		   if( !alignRotationToAxis )
				{
        		   rotate.getTransform( nextRotation ) ;
        		   angleX = rotX * dT * ANGLE_PER_MILLI ;
        		   angleY = rotY * dT * ANGLE_PER_MILLI ;
        		   angleZ = rotZ * dT * ANGLE_PER_MILLI ;
        
        		   
        		   Transform3D xRotation = new Transform3D() ;
        		   
        		   xRotation.rotX( angleX ) ;
        		   
        		   
        		   Transform3D yRotation = new Transform3D() ;
        		   
        		   yRotation.rotY( angleY ) ;
        		   
        		   Transform3D zRotation = new Transform3D() ;
        		   
        		   zRotation.rotZ( angleZ ) ;
        		   
        		   nextRotation.mul(  xRotation  ) ;
        		   nextRotation.mul(  yRotation  ) ;
        		   nextRotation.mul(  zRotation  ) ;
        
        		   
        		//   nextRotation.rotY( rotY * dT * ANGLE_PER_MILLI   ) ;
        		//   nextRotation.rotZ( rotZ * dT * ANGLE_PER_MILLI   ) ;
        		   
				}
		   else
				{
				 if( rotX != 0 )
					  {
					   nextRotation.rotX( Math.PI / 2.0 + Math.PI / 2.0 * rotX ) ;
					  }
				 else if( rotY != 0 )
					  {
					   nextRotation.rotY( Math.PI / 2.0 * rotY ) ;
					  }
				 else if( rotZ != 0 )
					  {
					   nextRotation.rotZ( Math.PI / 2.0 * rotZ ) ;
					  }
				 else return ;
				}
		   rotate.setTransform( nextRotation ) ;
		   
		  }
	  
	  
	  void wakeOnNextFrame()
		  {
		   
		   wakeupOn( wakeup ) ;
		  }
	  
	  public void keyPressed( java.awt.event.KeyEvent evt) 
		  {
		  
		   if( evt.getKeyCode() == java.awt.event.KeyEvent.VK_ALT )
				{
				 alignRotationToAxis = true ;
				}
		  
		   switch( evt.getKeyCode() )
		   		{
			 	
			 	 case java.awt.event.KeyEvent.VK_W :
			 		rotX = 1 ;				 		
			 	 break;
			 	
			 	 case java.awt.event.KeyEvent.VK_S :
			 		rotX = -1 ;				 		
		 		 break;
			 	
			 	 case java.awt.event.KeyEvent.VK_D :
			 		rotY = 1 ;				 		
			 	 break;
		 	
			 	 case java.awt.event.KeyEvent.VK_A :
			 		rotY = -1 ;				 		
	 			 break;
	 			 
	 			 
			 	 case java.awt.event.KeyEvent.VK_Q :
			 		rotZ = -1 ;				 		
			 	 break;
		 	
			 	 case java.awt.event.KeyEvent.VK_E :
			 		rotZ = 1 ;				 		
	 			 break;
		   		}
		  }
		  
	  public void keyReleased( java.awt.event.KeyEvent evt) 
		  {
		   if( evt.getKeyCode() == java.awt.event.KeyEvent.VK_ALT )
				{
				 alignRotationToAxis = false ;
				}
		   switch( evt.getKeyCode() )
	   		{
		 	
		 	 case java.awt.event.KeyEvent.VK_W :
		 		rotX = 0 ;				 		
		 	 break;
		 	
		 	 case java.awt.event.KeyEvent.VK_S :
		 		rotX = 0 ;				 		
	 		 break;
		 	
		 	 case java.awt.event.KeyEvent.VK_D :
		 		rotY = 0 ;				 		
		 	 break;
	 	
		 	 case java.awt.event.KeyEvent.VK_A :
		 		rotY = 0 ;				 		
			 break;
			 
			 
		 	 case java.awt.event.KeyEvent.VK_Q :
		 		rotZ = 0 ;				 		
		 	 break;
	 	
		 	 case java.awt.event.KeyEvent.VK_E :
		 		rotZ = 0 ;				 		
			 break;
	   		}
		  
		  
		  }

	 public void keyTyped( KeyEvent e)
		  {
		  
		  }
	 }
