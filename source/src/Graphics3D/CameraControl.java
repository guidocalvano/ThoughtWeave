package Graphics3D;

import Gui.* ;

import java.awt.* ;

import java.awt.BorderLayout;
import java.awt.event.* ;
import java.io.File;
import java.util.Enumeration;
import java.util.Vector;


import javax.media.j3d.*;

import javax.media.j3d.Canvas3D;
import javax.swing.*;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior ;

public class CameraControl extends CompositeControl implements ActionListener, FocusListener, KeyListener, MouseListener, MouseMotionListener
	 {
	 
	  SimpleUniverse universe ;
	  

	  SceneManager sceneManager ;
	  
	  Canvas3DControl canvas3DControl ;
	  
	  CameraKeyNavigator keyNavigator ;
	
	  BranchGroup	 keyNavigatorBranch ;

	  
	  TransformGroup transformGroup ;
	  
	  

	  JButton testButton ;
	
	  boolean canvasHasFocus ;
	  
	  
	  
	  
	 
	 
	  public CameraControl( JPanel parent, Shape3DViewManager shape3DViewManager )
	  	{
	  	 super( parent ) ;
	  	 viewContainer.setSize( 800, 800 ) ; 
	  	 
	  	 sceneManager = shape3DViewManager.getSceneManager() ;
	  	 
	  	// testButton = new JButton( "camera" ) ;
	  	 
	  	// viewContainer.add( testButton ) ;
	  	 
	  	// testButton.addActionListener( this ) ;
	  	 
	//  	 viewContainer.setLayout( new FlowLayout() ) ;

	  	 
	  	 canvas3DControl = new Canvas3DControl( viewContainer, shape3DViewManager ) ;
	  	 
	  	 
	  	 
	  	 canvas3DControl.getCanvas3D().addFocusListener( this ) ;
	  	 canvas3DControl.getCanvas3D().addKeyListener( this ) ;
	  	 
	  	 canvas3DControl.getCanvas3D().addMouseListener( this ) ;
	  	 canvas3DControl.getCanvas3D().addMouseMotionListener( this ) ;
	  	 
	  	 
	  	 universe = canvas3DControl.getSimpleUniverse() ;

	  	 universe.getViewingPlatform().setNominalViewingTransform() ;
	  	 
	  	 universe.getViewingPlatform().getViewers()[0].getView().setBackClipDistance( 30.0 ) ;
	  	 
	  	universe.getViewingPlatform().getViewers()[0].getView().setTransparencySortingPolicy( View.TRANSPARENCY_SORT_GEOMETRY ) ;
	  	 
	  	universe.getViewingPlatform().getViewers()[0].getView().setMinimumFrameCycleTime( 1000 / 30 ) ;
	  	
	  	 transformGroup = universe.getViewingPlatform().getMultiTransformGroup().getTransformGroup(0) ;
	  	 
	  	 keyNavigator = new CameraKeyNavigator( canvas3DControl.getCanvas3D(), transformGroup ) ;
	  	 
	  	 
	  	 keyNavigatorBranch = new BranchGroup() ;
	  	 
	  	 
	  	 keyNavigator.setSchedulingBounds( new BoundingSphere(new Point3d(0.0, 0.0,
	  		        0.0), 100.0 ) ) ;
	  	 
	  	 keyNavigatorBranch.addChild( keyNavigator ) ;
	  	 
	  	 transformGroup.addChild( keyNavigatorBranch ) ;

	  	 
	  	 Transform3D moveBackTransform = new Transform3D() ;
	  	 
	  	 transformGroup.getTransform( moveBackTransform ) ;
	  	 
	  	Transform3D stepBackward = new Transform3D() ;
		   
	  	stepBackward.setTranslation( new Vector3f( 0.0f, 0.0f, 30.0f ) ) ;
		   
	  	 
	  	 moveBackTransform.mul( stepBackward ) ;
	  	 
	  	 transformGroup.setTransform( moveBackTransform ) ;
 		 
	  	
	  	 
	  	 sceneManager.addCameraControl( this ) ;
	  	 


	  	// GuiLayerManager.getSingleton().getSceneManager().addCameraControl( this ) ;
	  	 
	  	 System.out.println( "CameraControl constructed") ;

	  	}
	  
	  public SimpleUniverse getSimpleUniverse() { return universe ; }

	 public void actionPerformed( ActionEvent e)
		  {
		   System.out.println( "Camera button pressed" ) ;
		   
		   Transform3D currentTransform = new Transform3D() ;
		   transformGroup.getTransform( currentTransform ) ;
		   
		   Transform3D stepForward = new Transform3D() ;
		   
		   stepForward.setTranslation( new Vector3f( 0.0f, 0.0f, 1.1f ) ) ;
		   
		   
		   currentTransform.mul( stepForward ) ;
		   
		   transformGroup.setTransform( currentTransform ) ;
		   
		  
		  }

	 public void focusGained( FocusEvent e)
		  {
		   System.out.println( "Canvas gained focus" ) ;
		  
		   canvasHasFocus = true ;
		  }

	 public void focusLost( FocusEvent e)
		  {
		   System.out.println( "Canvas lost focus" ) ;
		  
		   canvasHasFocus = false ;
		  }

	 public void keyPressed( KeyEvent e)
		  {
		  
		   if( canvasHasFocus )
				{
				 System.out.println( "you pressed " + e.getKeyChar() ) ;
				
				 switch( e.getKeyChar() )
				 	{
				 	/*
				 	 case 'w' :
				 		move( new Vector3f( 0.0f, 0.0f, -1.0f ) ) ; 				 		
				 	 break;
				 	
				 	 case 's' :
			 			move( new Vector3f( 0.0f, 0.0f, 1.0f ) ) ; 				 		
			 		 break;
				 	
				 	 case 'a' :
				 		yaw( .3 ) ; 				 		
				 	 break;
			 	
				 	 case 'd' :
				 		yaw( -.3 ) ; 				 		
		 			 break;
		 			 
		 			 
				 	 case 'q' :
			 			move( new Vector3f( -1.0f, 0.0f, 0.0f ) ) ; 				 		
				 	 break;
			 	
				 	 case 'e' :
			 			move( new Vector3f( 1.0f, 0.0f, 0.0f ) ) ; 				 		
		 			 break;
		 			 */
	
				 	}
				 
				}
		  }

	 void move( Vector3f direction )
		  {
		   Transform3D currentTransform = new Transform3D() ;
		   transformGroup.getTransform( currentTransform ) ;
		   
		   Transform3D step = new Transform3D() ;
		   
		   step.setTranslation( direction ) ;
		   
		   
		   currentTransform.mul( step ) ;
		   
		   transformGroup.setTransform( currentTransform ) ;	  
		  }
	 
	 
	 void yaw( double angle )
		  {
		   Transform3D currentTransform = new Transform3D() ;
		   transformGroup.getTransform( currentTransform ) ;
		   
		   Transform3D step = new Transform3D() ;
		   
		   step.rotY( angle ) ;
		   
		   
		   currentTransform.mul( step ) ;
		   
		   transformGroup.setTransform( currentTransform ) ;	  
		  }
	 
	 
	 public void keyReleased( KeyEvent e)
		  {
		  // TODO Auto-generated method stub
		  
		  }

	 public void keyTyped( KeyEvent e)
		  {
		  // TODO Auto-generated method stub
		  
		  }

	 public void mouseClicked( MouseEvent e)
		  {
		   
		  }

	 public void mouseEntered( MouseEvent e)
		  {
		  // TODO Auto-generated method stub
		  
		  }

	 public void mouseExited( MouseEvent e)
		  {
		  // TODO Auto-generated method stub
		  
		  }

	 public void mousePressed( MouseEvent e)
		  {
		   System.out.println( "Mouse at" + e.getX() + " " + e.getY() + " button " + e.getButton() ) ;	   
		  }

	 public void mouseReleased( MouseEvent e)
		  {
		  // TODO Auto-generated method stub
		  
		  }

	 public void mouseDragged( MouseEvent e)
		  {
		  // TODO Auto-generated method stub
		  
		  }

	 public void mouseMoved( MouseEvent e)
		  {
		   // System.out.println( "Mouse at" + e.getX() + " " + e.getY() ) ;
		  }
	 
	  public TransformGroup getTransformGroup()
		   {
		    return transformGroup ;
		   }

	 public Canvas3D getCanvas3D()
		  {
		  // TODO Auto-generated method stub
		  return canvas3DControl.getCanvas3D() ;
		  }
	 
	  public void saveFoto( String filename, int x, int y, int width, int height )
		   {
		    canvas3DControl.save( filename, canvas3DControl.makeImage( x, y, width, height ) ) ;
		   }
	  
	  RecordBehavior record ;
	  
	  public void record(  int x, int y, int width, int height, int frameRate, String filename )
		   {
		    record = new RecordBehavior(   x,  y,  width,  height,  frameRate,  filename ) ;		    
		   }
	  
	  public void stopRecord()
		   {
		    record.completeAnimation = true ;
		   }
	  

	  class RecordBehavior extends Behavior
	  	{
	  	 Vector vector ;
	  	
	  	 int x ;
	  	 int y ;
	  	 
	  	 int width ;
	  	 int height ;
	  	 
	  	 boolean completeAnimation ;

	 private int frameRate;

	 private String filename;

	 
	 WakeupCriterion wakeup ;
	 
	 long maxDurationInMillis ;
	 
	 
	  BranchGroup destroyGroup ;
	  
	  
	  int frameCount ;


	 RecordBehavior( int x, int y, int width, int height, int frameRate, String filename )
	 	{
	 	 this.x = x ;
	 	 this.y = y ;
	 	 
	 	 this.width  = width 	;
	 	 this.height = height 	;
	 	 
	 	 this.frameRate = frameRate ;
	 	 
	 	 this.filename = filename ;
	 
	 	 

	 	 wakeup = new WakeupOnElapsedTime( (long) ( 1000.0 / ( (double) frameRate ) ) ) ;
	 	 
	 	 BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1000000.0);
		 setSchedulingBounds(bounds);
	 	 
		 maxDurationInMillis = 1000 * 20 ;
		 
	 	 destroyGroup = new BranchGroup() ;
 	 	 
 	 	 destroyGroup.setCapability( BranchGroup.ALLOW_DETACH ) ;
  	 	 destroyGroup.setCapability( BranchGroup.ALLOW_CHILDREN_WRITE ) ;
 	 	
  	 	 destroyGroup.setCapability(Group.ALLOW_CHILDREN_READ);
  	 	 destroyGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
  	 	 
  	 	 
 	 	 
 	 	 destroyGroup.addChild(  this  ) ;
 	 	 
 	 	 vector = new Vector() ;
 	 	 
	 	 sceneManager.getRoot().addChild( destroyGroup ) ;
 
	 	 completeAnimation = false ;
	 	}
	 
	 
	 	 @Override
	 	 public void initialize()
	 		  {
	 		   wakeupOn( wakeup ) ;
	 		  }

	 	 @Override
	 	 public void processStimulus( Enumeration arg0)
	 		  {
	 		   // saveFoto( System.getProperty( "user.dir" ) + File.separator + "movieTemp" + File.separator + "frame" + frameCount , x, y, width, height ) ;
	 		  
	 		   frameCount++ ;
	 		   vector.add(  canvas3DControl.makeFrame( x, y, width, height ) ) ;
	 		   
	 		   // maxDurationInMillis -= (long) ( 1000.0 / ( (double) frameRate ) ) ;
	 		   
	 		   // if( maxDurationInMillis < 0 ) completeAnimation = true ;
	 		   
	 		   if( completeAnimation )
	 				{
	 				 JpegImagesToMovie.writeMovie( width, height, frameRate, vector, filename ) ;
	 				 record = null ;
	 				 
	 				 destroyGroup.detach() ;
	 				}
	 		   else wakeupOn( wakeup ) ;
	 		  }
	  
	  	}
	  

	  

	 }


