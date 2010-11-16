package Main;
import javax.media.j3d.*;
import com.sun.j3d.utils.geometry.ColorCube ;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

import Graphics3D.ShadedMaterial;
import Graphics3D.Shape3DView;
import Graphics3D.Shape3DViewInterface;
import Graphics3D.TextureMaterial;

import com.sun.j3d.utils.universe.ViewingPlatform ;
public class DemonstrationArea
	 {
	  BranchGroup 			branch ;
	  
	  TransformGroup		rotation ;
	  
	  BranchGroup			content ;
	  
	  Background			background ;
	  
	  Shape3DViewInterface  brain		;
	  
	  Transform3D 			rotateX		;
	  
	  Transform3D 			rotateY		;
	  
	  Transform3D 			rotateZ		;
	  
	  
	  Transform3D 			rotateMinusX		;
	  
	  Transform3D 			rotateMinusY		;
	  
	  Transform3D 			rotateMinusZ		;
	  
	  DemonstrationKeyNavigatorBehavior 		keyBehavior 	;
	  
	  DemonstrationBackgroundColorBehavior		colorBehavior	;
	  
	  BrainModelBehavior						brainModelBehavior ;
	  
	    Transform3D xPerspective  ;
	    
	    Transform3D yPerspective  ;
	  
	    Transform3D zPerspective  ;
	    
	  DemonstrationArea( Canvas3D canvas, ViewingPlatform viewingPlatform )
	  	{
	  	 System.out.println( "demo area const" ) ;
	  	
	  	 branch = new BranchGroup() ;
	  	 
	  	 rotation = new TransformGroup() ;
	  	 
	  	 content	= new BranchGroup() ;
	  	 
	  	 rotation.setCapability( TransformGroup.ALLOW_TRANSFORM_READ ) ;
	  	 rotation.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE ) ;
	  	 
	 
	  	 
	  	 branch.addChild( rotation ) ;
	  	 
	  	 rotation.addChild( content ) ;
	  	 
	  	 background  = new Background(new Color3f(.837f,.837f, .837f));
	  	BoundingSphere sphere = new BoundingSphere(new Point3d(0,0,0), 100000);
	  	background.setApplicationBounds(sphere);
	  	
	  	brain = new Shape3DView( content, null, "brainV8AlignedWithMegSensorLayout.obj", false ) ;
	  	
	 // 	brain.setVisible( 0.0 ) ;
	 // 	brain.setVisible( 1.0 ) ;
	  	
	//  	brain.setMaterialInterface( new TextureMaterial( brain ) ) ;
	
//	  	brain.initShape() ;
	
	  	System.out.println( "before set transparancy" ) ;
	  	
	  	brain.setTransparency( .6 ) ;
	  	
//	  	content.addChild( brain.getBranchGroup() ) ;
	  	
	  	brainModelBehavior = new BrainModelBehavior(  brain, true ) ;
	  	
	  	content.addChild( brainModelBehavior ) ;
	  	
	  	
	  	branch.addChild( background ) ;
	  	
	  	background.setCapability( Background.ALLOW_COLOR_WRITE ) ;
	  	
	  	keyBehavior = new DemonstrationKeyNavigatorBehavior( canvas, rotation ) ;

	  	branch.addChild(  keyBehavior ) ;

	  	colorBehavior = new DemonstrationBackgroundColorBehavior( canvas, background ) ;
	  	
	  	
	  	
	  	branch.addChild( colorBehavior ) ;
	  	
	  	 System.out.println( "end demo area const" ) ;

	  	}
	  
	  BranchGroup getBranchGroup() { return branch ; }
	  
	  
	  public void showBrainModel( boolean mustShow )
		   {
		    brainModelBehavior.setVisible( mustShow ) ;
		   }
	  
	  public boolean brainIsVisible() { return brain.getVisible() > .5 ; }
	  
	  void add( BranchGroup branch )
		   {
		    content.addChild( branch ) ;
		   }
	  
	  void initRotationSet()
		   {
		    rotateX = new Transform3D() ;
		    
		    rotateX.rotX( .01 ) ;
		    
		    rotateY = new Transform3D() ;
		    
		    rotateY.rotY( .01 ) ;
		    
		    rotateZ = new Transform3D() ;
		    
		    rotateZ.rotZ( .01 ) ;
		    
		    
		    
		    rotateMinusX = new Transform3D() ;
		    
		    rotateMinusX.rotX( -.01 ) ;
		    
		    rotateMinusY = new Transform3D() ;
		    
		    rotateMinusY.rotY( -.01 ) ;
		    
		    rotateMinusZ = new Transform3D() ;
		    
		    rotateMinusZ.rotZ( -.01 ) ;
		    
		    
		    xPerspective = new Transform3D() ;
		    
		    xPerspective.rotY( Math.PI / 2.0 ) ;
		    
		    
		    yPerspective = new Transform3D() ;
		    
		    yPerspective.rotX( Math.PI / 2.0 ) ;
		    
		    
		    zPerspective = new Transform3D() ;
		   }
	  
	  public void showBackgroundColorWindow()
		   {
		    colorBehavior.showWindow() ;
		   }
	  
	  void setBackgroundColor( Color3f color )
		   {
		    background.setColor( color ) ;
		   }
	  
	  void increaseYaw( int increase )
		   {
		    Transform3D transform = new Transform3D() ;
		    
		    rotation.getTransform( transform ) ;
		    
		    if( increase > 0 )
	    		    for( int i = 0 ; i < increase ; i++)
	    		    	 transform.mul( rotateX ) ;
			    
			    if( increase < 0 )
		    		    for( int i = 0 ; i < increase ; i++)
		    		    	 transform.mul( rotateMinusX ) ;
		    
		    rotation.setTransform( transform ) ;
		   }
	  
	  
	  void increasePitch( int increase )
		   {
		    Transform3D transform = new Transform3D() ;
		    
		    rotation.getTransform( transform ) ;
		    
		    if( increase > 0 )
    		    for( int i = 0 ; i < increase ; i++)
    		    	 transform.mul( rotateY ) ;
		    
		    if( increase < 0 )
	    		    for( int i = 0 ; i < increase ; i++)
	    		    	 transform.mul( rotateMinusY ) ;
		    
		    rotation.setTransform( transform ) ;
		   }
	  
	  void increaseRoll( int increase )
		   {
		    Transform3D transform = new Transform3D() ;
		    
		    rotation.getTransform( transform ) ;
		    
		    if( increase > 0 )
	    		    for( int i = 0 ; i < increase ; i++)
	    		    	 transform.mul( rotateZ ) ;
			    
			    if( increase < 0 )
		    		    for( int i = 0 ; i < increase ; i++)
		    		    	 transform.mul( rotateMinusZ ) ;
		    
		    rotation.setTransform( transform ) ;
		   }
	  
	  void xPerspective()
		   {
		    rotation.setTransform( xPerspective ) ;
		   }
	  
	  void yPerspective()
		   {
		    rotation.setTransform( yPerspective ) ;
		   }
	  
	  void zPerspective()
		   {
		    rotation.setTransform( zPerspective ) ;   
		   }
	  
	  void setBrainIsVisible( boolean isVisible )
		   {
		    brain.setVisible( isVisible ) ;
		   }
	 }
