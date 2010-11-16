package Graphics3D;
import Gui.* ;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.PickRay;
import javax.media.j3d.TransformGroup;
import javax.swing.JPanel;

import com.sun.j3d.utils.universe.*;

import javax.media.j3d.*;
import javax.vecmath.* ;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.* ;


interface MouseRayListener
	{

	 public void mouseClicked( PickRay ray, MouseEvent e ) 	;

//	 public void mouseEntered( PickRay ray, MouseEvent e) 	;
		
//	 public void mouseExited( PickRay ray, MouseEvent e) 	;

	 public void mousePressed( PickRay ray, MouseEvent e) 	;
	
	 public void mouseReleased( PickRay ray, MouseEvent e) 	;

	 
	}


public class Canvas3DControl extends CompositeControl implements MouseListener
	{
	
	 Shape3DViewManager shape3DViewManager ;
	
	 
	 Canvas3D canvas3D 	;
	 
	 Canvas3D offscreen	;
	 
	 Vector mouseListenerSet ;
	 Vector mouseMotionListenerSet ;
	 
	 Vector mouseRayListenerSet ;

	 private ImageComponent2D saveImage;
	 
	 
	 Canvas3DControl( JPanel parent, Shape3DViewManager shape3DViewManager )
	 	{
	 	 super( parent ) ;
	 	 
	 	 this.shape3DViewManager = shape3DViewManager ;

	 	 
	 	 mouseListenerSet 		= new Vector() ;
	 	 mouseMotionListenerSet = new Vector() ;
	 	 mouseRayListenerSet = new Vector() ;

	 	 mouseRayListenerSet.add( shape3DViewManager ) ;
 
	 	 
		 GraphicsConfiguration config =     	            SimpleUniverse.getPreferredConfiguration(); 
	      canvas3D = new Canvas3D( config) ;
	     

	     canvas3D.setFocusable(true);
			canvas3D.requestFocus();
	     
			canvas3D.setCursor( new Cursor( Cursor.CROSSHAIR_CURSOR ) ) ;
	     
	     if( canvas3D != null ) System.out.println( "we have a canvas" ) ;
	   	 	 
	 	 
	 	 this.canvas3D = canvas3D ;
	
	 	 canvas3D.setSize( 800, 800 ) ;

	 	 
	 	/* 
	      offscreen = new Canvas3D( config, true ) ;
	 	   	 	 
	 	 
	 	 this.offscreen = offscreen ;
	
	 	 offscreen.setSize( 800, 800 ) ;
		  */
//	 	getSimpleUniverse().getViewingPlatform().getViewers()
	 	 
	      //	canvas3D.setOffScreenBuffer( saveImage ) ;

	 	 viewContainer.setSize(  800, 800 ) ;
	
	  //	 viewContainer.setLayout( new FlowLayout() ) ;

	  	 
	 	 
	 	 viewContainer.add( canvas3D ) ;
	 	 
	 	 canvas3D.addMouseListener( this ) ;
	 	 
	 
	 	}
	 
	 
	 Canvas3D getCanvas3D() { return canvas3D ; } 
	 
	 PickRay getMouseRay( int x, int y )
		  {
		   Point3d eye = new Point3d() ; 	canvas3D.getCenterEyeInImagePlate( eye ) ;
		   
		   Point3d mouse  = new Point3d() ; canvas3D.getPixelLocationInImagePlate( new Point2d( x, y ), mouse ) ;
		   
		   
		   Transform3D motion = new Transform3D()	;
		   canvas3D.getImagePlateToVworld( motion ) ;

		   motion.transform(  eye   ) ;
		   motion.transform(  mouse ) ;
		   
		   
		   Vector3d eyeToMouse = new Vector3d( mouse  ) ;
		   
		   eyeToMouse.sub( eye ) ;
		   
		   
		   return new PickRay( eye, eyeToMouse ) ;
		  }
	 
	 
	 public SimpleUniverse getSimpleUniverse()
		  {
		   SimpleUniverse universe = new SimpleUniverse( canvas3D ) ;
		  
		   ViewingPlatform viewingPlatform = universe.getViewingPlatform() ;
		   ViewPlatform vp = viewingPlatform.getViewPlatform() ;
		   viewingPlatform.detach() ;
		   vp.setCapability(ViewPlatform.ALLOW_POLICY_READ) ;
		   vp.setCapability(Node.ALLOW_LOCAL_TO_VWORLD_READ) ;
		   TransformGroup transformGroup = universe.getViewingPlatform().getMultiTransformGroup().getTransformGroup(0) ;
		   transformGroup.setCapability(TransformGroup.ALLOW_LOCAL_TO_VWORLD_READ);

		   universe.addBranchGraph(viewingPlatform) ;

		   return universe ;
		  }
	 
	 public void addMouseListener( MouseListener listener ) 
		  {
		   mouseListenerSet.add( listener ) ;
		  
		  }
	 
	 public void addMouseMotionListener( MouseMotionListener listener ) 
		  {
		   mouseMotionListenerSet.add( listener ) ;

		  
		  }


	 public void mouseClicked( MouseEvent e)
		  {
		   System.out.println( " picking object") ;
		  
		   PickRay ray = getMouseRay( e.getX(), e.getY() ) ;
		   
		   for( int i = 0 ; i < mouseRayListenerSet.size() ; i++ )
				{
				 ( (MouseRayListener) mouseRayListenerSet.get( i ) ).mouseClicked( ray, e ) ;
				 
				
				}
		  
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
		   System.out.println( " picking object") ;
			  
		   PickRay ray = getMouseRay( e.getX(), e.getY() ) ;
		   
		   for( int i = 0 ; i < mouseRayListenerSet.size() ; i++ )
				{
				 ( (MouseRayListener) mouseRayListenerSet.get( i ) ).mousePressed( ray, e ) ;
				 
				
				}
		  		  
		  }


	 public void mouseReleased( MouseEvent e)
		  {
		  // TODO Auto-generated method stub
		  
		  } 
	 
	 public BufferedImage makeImage( int x, int y, int width, int height) 
		  {		 
		 /*  
		   Graphics2D g2 = saveImage.getImage().createGraphics() ;
		   
		   canvas3D.paint( g2 )	;
		   g2.dispose()			;
		   */
		  
		  
		  // return saveImage.getImage()	;
		  
		   GraphicsContext3D graphicsContext = canvas3D.getGraphicsContext3D() ;
		
	//	  GraphicsContext3D graphicsContext = offscreen.getGraphicsContext3D() ;
		  
		   Raster raster = new Raster( new Point3f( 0f,0f, 0f),
		              Raster.RASTER_COLOR,
		              x,
		              y,
		              width,
		              height,
		              new ImageComponent2D( ImageComponent2D.FORMAT_RGB, new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB ) ) ,
		              null ) ;

		   
		   graphicsContext.readRaster( raster ) ;
		   
		   BufferedImage image = raster.getImage().getImage() ;
		   
		   
		   return  image ;  
		  }
 
	 
	 
	 public BufferedImage makeFrame( int x, int y, int width, int height) 
		  {		 
		 /*  
		   Graphics2D g2 = saveImage.getImage().createGraphics() ;
		   
		   canvas3D.paint( g2 )	;
		   g2.dispose()			;
		   */
		  
		  
		  // return saveImage.getImage()	;
		  
		   GraphicsContext3D graphicsContext = canvas3D.getGraphicsContext3D() ;
		
	//	  GraphicsContext3D graphicsContext = offscreen.getGraphicsContext3D() ;
		  
		   Raster raster = new Raster( new Point3f( 0f,0f, 0f),
		              Raster.RASTER_COLOR,
		              x,
		              y,
		              width,
		              height,
		              new ImageComponent2D( ImageComponent2D.FORMAT_RGB, new BufferedImage( width, height, BufferedImage.TYPE_3BYTE_BGR ) ) ,
		              null ) ;

		   
		   graphicsContext.readRaster( raster ) ;
		   
		   BufferedImage image = raster.getImage().getImage() ;
		   
		   
		   return  image ;  
		  }
	 
	 
	 
    public void save( String fileName, BufferedImage image ) 
    	 {
    	  String ext = "jpg";  // png, bmp (j2se 1.5+), gif (j2se 1.6+)
    	  File file = new File( fileName + "." + ext ) ;
    	  try 
    		   {
    		    ImageIO.write(image, ext, file);
    		   }
    	  catch(IOException e) 
    		   {
    		    System.out.println("write error: " + e.getMessage());
    		   }
    	 }
 
    
 
	}

