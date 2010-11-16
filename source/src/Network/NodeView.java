package Network ;
import Graphics3D.* ;
import Graphics3D.Animation.* ;


import java.awt.*;
import java.util.Enumeration;

import javax.swing.* ;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import javax.media.j3d.* ;



public class NodeView extends Shape3DView implements NodeViewInterface, ModelChangeListener
	 {
	  String name ;
	  
	  ModelInterface model ;
	  
	  Text3DViewInterface textOverlay ;
	  
	  
	  
	  OverlayBehavior overlayBehavior ;
	  
	  double x		;
	  double y		;
	  double z		;
	  
	  double red 	;
	  double green 	;
	  double blue  	;
	  
	  double transparency 	;
	  double size		 	;
	  double shape			;

	 private double isVisible;
	 
	  public NodeView( ) 
	  	{
	  	 super( new String[]{ "internal4.obj", "external4.obj" }, true ) ;
	  	 
	  	 
//	  	 super( new String[]{ "/Users/guidocalvano/Tree/Data/Projecten/EEGVisualisation/internal2.obj", "/Users/guidocalvano/Tree/Data/Projecten/EEGVisualisation/external2.obj" } ) ;
	  	} 
	  
	  
	  public NodeView( ModelInterface model )
		  {
		   this() ;
		   
		   this.model = model ;
		   
		   model.addModelChangeListener( this ) ;
		   
		   x = ( (Constant) model.getViewProperties().propertyObject(  "x" ) ).getValue() ;
		   y = ( (Constant) model.getViewProperties().propertyObject(  "y" ) ).getValue() ;
		   z = ( (Constant) model.getViewProperties().propertyObject(  "z" ) ).getValue() ;
		  
		   red = ( (Constant) model.getViewProperties().propertyObject(  "red" ) ).getValue() ;
		    green = ( (Constant) model.getViewProperties().propertyObject(  "green" ) ).getValue() ;
		    blue = ( (Constant) model.getViewProperties().propertyObject(  "blue" ) ).getValue() ;

		    shape 	= ( (Constant) model.getViewProperties().propertyObject(  "shape" ) ).getValue() ;
		    size = ( (Constant) model.getViewProperties().propertyObject(  "size" ) ).getValue() ;
		    transparency 	= ( (Constant) model.getViewProperties().propertyObject(  "transparency" ) ).getValue() ;

		    isVisible 	=( (Constant) model.getViewProperties().propertyObject(  "visible" ) ).getValue() ;

		//   getTextOverlay().getText3D().setString( model.getDescription() ) ; 		    
		  }
	  
	  public NodeView( BranchGroup parent, Shape3DViewManagerInterface manager, ModelInterface model )
		  {
		   super( parent, manager, new String[]{ "internal4.obj", "external4.obj" }, true ) ;
		   
		   this.model = model ;
		   
		   model.addModelChangeListener( this ) ;
		   
		   x = ( (Constant) model.getViewProperties().propertyObject(  "x" ) ).getValue() ;
		   y = ( (Constant) model.getViewProperties().propertyObject(  "y" ) ).getValue() ;
		   z = ( (Constant) model.getViewProperties().propertyObject(  "z" ) ).getValue() ;
		  
		   red = ( (Constant) model.getViewProperties().propertyObject(  "red" ) ).getValue() ;
		    green = ( (Constant) model.getViewProperties().propertyObject(  "green" ) ).getValue() ;
		    blue = ( (Constant) model.getViewProperties().propertyObject(  "blue" ) ).getValue() ;

		    shape 	= ( (Constant) model.getViewProperties().propertyObject(  "shape" ) ).getValue() ;
		    size = ( (Constant) model.getViewProperties().propertyObject(  "size" ) ).getValue() ;
		    transparency 	= ( (Constant) model.getViewProperties().propertyObject(  "transparency" ) ).getValue() ;

		    isVisible 	= ( (Constant) model.getViewProperties().propertyObject(  "visible" ) ).getValue() ;

			setVisible(		0.0 ) ;
			setVisible(		1.0 ) ;

		    
	//	    initShape() ;
		  }


	 public void initNodeView()
		   {
		    // callibrateViewOnModel() ; ***
		   
		   }
	  
	  
	 void callibrateViewOnModel()
		  {
		  
		  Alpha time = new Alpha( 1, Alpha.INCREASING_ENABLE
			        , 0, 0, 2000, 1000, 200, 2000, 1000,
			        200);
	        time.setStartTime(System.currentTimeMillis());
	        
	  	  	new ToTextBehavior( this, model.getDescription(),  time ) ; 

	   //      textOverlay.text( model.getDescription() ) ;
	         
	         System.out.println( model.getDescription() ) ;
	 
	         new ToXBehavior( this,( model.property( "x" ) - .5 ) * 20.0,  time ) ;
	         new ToYBehavior( this,( model.property( "y" ) - .5 ) * 20.0,  time ) ;
	         new ToZBehavior( this,( model.property( "z" ) - .5 ) * 20.0,  time ) ;
	         
	         new ToRedBehavior( this, model.property( "red" ),  time ) ;
	         new ToGreenBehavior( this, model.property( "green" ),  time ) ;
	         new ToBlueBehavior( this, model.property( "blue" ),  time ) ;
	         
	         new ToShapeBehavior( this, model.property( "shape" ),  time ) ;
	         new ToSizeBehavior( this, 1.0 + model.property( "size" ),  time ) ;
	         new ToTransparencyBehavior( this, model.property( "transparency" ),  time ) ;
	
		  }


	 void set( Vector3f place, float red, float green, float blue, float radius, float shape )
		   {
		    Transform3D nextTransform = new Transform3D() ;
		    
			nextTransform.setScale( radius ) ;
			nextTransform.setTranslation( place ) ;
			
			
			getTransformGroup().setTransform( nextTransform ) ;
			
			getMorph().setWeights( new double[] { shape, 1.0 - shape  } ) ;
			
			getAppearance().getMaterial().setDiffuseColor( new Color3f( red, green, blue ) ) ;
			
			
	 // 		 set( new Vector3f( (float) Math.random() * 30.0f - 15.0f, (float) Math.random() * 30.0f - 15.0f, (float)Math.random() * 30.0f - 15.0f ), (float) Math.random(), (float) Math.random(), (float) Math.random() ,(float) ( Math.random() * 1.0 + 1.0 ), (float) Math.random()  ) ;
  
		   
		   }
	  
/*	  
	  public void randomChange( Alpha time )
		{
		 
		
		
		 Morph morph = getMorph() ;
		 
		 float weightBalance = (float) Math.random() ;
		 
		 new ChangeMorphWeightBehavior( morph, new double[] { 1.0 - weightBalance, weightBalance }, getBranchGroup(), time ) ;
		 Material material = getAppearance().getMaterial() ;
		 
		 new ChangeColorBehavior( material, new Color3f( (float) Math.random(), (float) Math.random(), (float) Math.random() ), getBranchGroup(), time ) ;

		 Transform3D testTransform = new Transform3D() ;
		 
		 getTransformGroup().getTransform( testTransform ) ;

		 
		 testTransform.setScale(  Math.random() * 1.0 + 1.0 ) ;
		 
		 testTransform.setTranslation( new Vector3f( (float) Math.random() * 30.0f - 15.0f, (float) Math.random() * 30.0f - 15.0f, (float)Math.random() * 30.0f - 15.0f ) ) ;
		 
		 		 
		 new ChangeTransformBehavior( getTransformGroup(), testTransform, getBranchGroup(), time ) ;
  
		   
		}

*/
	 public void handleModelChange( ModelInterface model )
		  {
		   callibrateViewOnModel() ;
		  }

	 
	 public void setCameraControl( CameraControl cameraControl)
		  {

		  
		   if( model.getDescriptionSource() != null )
				{
				 createTextOverlay( cameraControl ) ;
				}
		  }


	 public void createTextOverlay( CameraControl camera)
		  {
		  
		   textOverlay = new Text3DView( model.getDescription() ) ;
		   
		   getIsVisibleGroup().addChild( textOverlay.getBranchGroup() ) ;
		   
		   overlayBehavior = new OverlayBehavior( getTransformGroup(), textOverlay, camera ) ;
		  }
	 
	  public Text3DViewInterface getTextOverlay() { return textOverlay ; }


		 public void toModel( float percentageIncrease)
			  {
			   
			   double endX = -10.0 + 20.0 *  model.property(  "x" )  ;
			   double endY = -10.0 + 20.0 * model.property("y" )  ;
			   double endZ = -10.0 + 20.0 * model.property(  "z" )  ;
			  
			   double endRed = model.property( "red" )  ;
			   double endGreen = model.property(  "green" ) ;
			   double endBlue = model.property( "blue" )  ;

			   double endShape 	= model.property( "shape" ) ;
			   double endSize = model.property( "size" )  ;
			   double endTransparency 	= model.property(  "transparency" ) ;

			    isVisible 	=  model.property(  "visible" ) ;

			    x 	= endX  * percentageIncrease + x * ( 1.0 - percentageIncrease ) ;
			    y  = endY  * percentageIncrease + y * ( 1.0 - percentageIncrease ) ;
			    z 	= endZ  * percentageIncrease + z * ( 1.0 - percentageIncrease ) ;
			   
			    red 	= endRed  * percentageIncrease + red * ( 1.0 - percentageIncrease ) ;
			    green  = endGreen  * percentageIncrease + green * ( 1.0 - percentageIncrease ) ;
			    blue 	= endBlue  * percentageIncrease + blue * ( 1.0 - percentageIncrease ) ;

			    shape 				= endShape  * percentageIncrease + shape * ( 1.0 - percentageIncrease ) ;
			    size 				= endSize  * percentageIncrease + size * ( 1.0 - percentageIncrease ) ;
			    transparency 		= endTransparency  * percentageIncrease + transparency * ( 1.0 - percentageIncrease ) ;

				   setX( 		x 		) ;
				   setY( 		y 		) ;
				   setZ(		z		) ; 
			    
			    
			   setRed( 		red 		) ;
			   setGreen( 	green 		) ;
			   setBlue(		blue		) ;

			   setShapeValue( shape ) ;
			   setScale( size ) ;
			   setTransparency(		transparency ) ;
			   
			   setVisible(		isVisible ) ;

			   
			   getTextOverlay().getText3D().setString( model.getDescription() ) ;
			   
			  }
	  
	 }
