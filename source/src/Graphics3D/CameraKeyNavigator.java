package Graphics3D;

import java.awt.Component;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Enumeration;

import javax.media.j3d.*;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnElapsedFrames;
import javax.vecmath.*;

public class CameraKeyNavigator extends Behavior implements KeyListener
	 {
	 
	  double dX ;
	  double dY ;
	  double dZ ;
	  
	  double angleX ;
	  double angleY ;
	  double angleZ ;
	  
	  
	  boolean strafeMode ;


	  
	  long previousTimeInMillis ;
	  
	  WakeupCriterion wakeup ;
	  
	  TransformGroup cameraTransform ;
	  
	  static final double ANGLE_PER_MILLI = .002 ;

	  static final double UNITS_PER_MILLI = .015 ;


	  public CameraKeyNavigator( Component c,
			   TransformGroup targetTG )
		  {
		  BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
			 setSchedulingBounds(bounds);
			 
		   c.addKeyListener( this ) ;
		   cameraTransform = targetTG ;
		   
		   strafeMode = true ;
		  }

	  public void initialize()
		   {
		   
		    wakeup = new WakeupOnElapsedFrames( 1 ) ;

		    elapsedTime() ;
		    
		    wakeOnNextFrame() ;
		    

		   }
	  public void	processStimulus(java.util.Enumeration criteria)
		   {
		    doMovement( elapsedTime() ) ;

		    wakeOnNextFrame() ;
		    

		   }
	  
	  double elapsedTime()
		   {
		    long nextTimeInMillis = System.currentTimeMillis() ;
		    long dT = nextTimeInMillis - previousTimeInMillis ;
		   
		    previousTimeInMillis = nextTimeInMillis ;
		    
		    
		    return (double) dT ;
		   }
	  
	  void doMovement( double dT )
		  {
		   
		   Transform3D nextCameraTransform = new Transform3D() ;
   		   cameraTransform.getTransform( nextCameraTransform ) ;

		   if( !strafeMode )
				{
       		   angleX = dX * dT * ANGLE_PER_MILLI ;
       		   angleY = dY * dT * ANGLE_PER_MILLI ;
       		   angleZ = dZ * dT * ANGLE_PER_MILLI ;
       
       		   
       		   Transform3D xRotation = new Transform3D() ;
       		   
       		   xRotation.rotX( angleY ) ;
       		   
       		   
       		   Transform3D yRotation = new Transform3D() ;
       		   
       		   yRotation.rotY( -angleX ) ;
       		   
       		   Transform3D zRotation = new Transform3D() ;
       		   
       		   zRotation.rotZ( angleZ ) ;
       		   
       		   nextCameraTransform.mul(  xRotation  ) ;
       		   nextCameraTransform.mul(  yRotation  ) ;
       		   nextCameraTransform.mul(  zRotation  ) ;
       
       		   
       		//   nextRotation.rotY( rotY * dT * ANGLE_PER_MILLI   ) ;
       		//   nextRotation.rotZ( rotZ * dT * ANGLE_PER_MILLI   ) ;
       		   
				}
		   else
				{
				 Transform3D move = new Transform3D() ;
				
				 move.set( new Vector3f( (float)( dX * dT * UNITS_PER_MILLI ),  (float) ( dY * dT * UNITS_PER_MILLI ), (float)( dZ * dT * UNITS_PER_MILLI) ) ) ;
			
				 nextCameraTransform.mul(  move ) ;
				}
		   cameraTransform.setTransform( nextCameraTransform ) ;
		   
		  }
	  
	  
	  void wakeOnNextFrame()
		  {
		   
		   wakeupOn( wakeup ) ;
		  }
	  
	  public void keyPressed( java.awt.event.KeyEvent evt) 
		  {
		  
		   if( evt.getKeyCode() == java.awt.event.KeyEvent.VK_ALT )
				{
				 strafeMode = false ;
				}
		  
		   switch( evt.getKeyCode() )
		   		{
			 	
			 	 case java.awt.event.KeyEvent.VK_L :
			 		dX = 1 ;				 		
			 	 break;
			 	
			 	 case java.awt.event.KeyEvent.VK_J :
			 		dX = -1 ;				 		
		 		 break;
			 	
			 	 case java.awt.event.KeyEvent.VK_I :
			 		dY = 1 ;				 		
			 	 break;
		 	
			 	 case java.awt.event.KeyEvent.VK_K :
			 		dY = -1 ;				 		
	 			 break;
	 			 
	 			 
			 	 case java.awt.event.KeyEvent.VK_U :
			 		dZ = -1 ;				 		
			 	 break;
		 	
			 	 case java.awt.event.KeyEvent.VK_O :
			 		dZ = 1 ;				 		
	 			 break;
		   		}
		  }
		  
	  public void keyReleased( java.awt.event.KeyEvent evt) 
		  {
		   if( evt.getKeyCode() == java.awt.event.KeyEvent.VK_ALT )
				{
				 strafeMode = true ;
				}
		   switch( evt.getKeyCode() )
	   		{
		 	
		 	 case java.awt.event.KeyEvent.VK_L :
		 		dX = 0 ;				 		
		 	 break;
		 	
		 	 case java.awt.event.KeyEvent.VK_J :
		 		dX = 0 ;				 		
	 		 break;
		 	
		 	 case java.awt.event.KeyEvent.VK_I :
		 		dY = 0 ;				 		
		 	 break;
	 	
		 	 case java.awt.event.KeyEvent.VK_K :
		 		dY = 0 ;				 		
			 break;
			 
			 
		 	 case java.awt.event.KeyEvent.VK_U :
		 		dZ = 0 ;				 		
		 	 break;
	 	
		 	 case java.awt.event.KeyEvent.VK_O :
		 		dZ = 0 ;				 		
			 break;
	   		}
		  
		  
		  }

	 public void keyTyped( KeyEvent e )
		  {
		  
		  }

	 }
