package Network;

import javax.media.j3d.*;
import javax.media.j3d.Material;
import javax.media.j3d.Morph;
import javax.media.j3d.Transform3D;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Matrix3f;

import Graphics3D.FastFlatColor;
import Graphics3D.ShadedMaterial;
import Graphics3D.Shape3DView;
import Graphics3D.Shape3DViewManagerInterface;
import Graphics3D.Animation.ToBlueBehavior;
import Graphics3D.Animation.ToCylinderRadiusBehavior;
import Graphics3D.Animation.ToGreenBehavior;
import Graphics3D.Animation.ToRedBehavior;
import Graphics3D.Animation.ToShapeBehavior;
import Graphics3D.Animation.ToSizeBehavior;
import Graphics3D.Animation.ToTransparencyBehavior;
import Graphics3D.Animation.ToXBehavior;
import Graphics3D.Animation.ToYBehavior;
import Graphics3D.Animation.ToZBehavior;


public class LinkView extends Shape3DView implements LinkViewInterface
	 {
	  ModelInterface model ;

	  NodeViewInterface a ;
	  NodeViewInterface b ;
	 
	  LinkToNodeBehavior stickToA ; 
//	  LinkToNodeBehavior stickToB ; 
	  
	  float radius ;
	  
	  
	  double red 	;
	  double green 	;
	  double blue  	;
	  
	  double transparency 	;
	  double cylinderRadius 	;

	 private double isVisible;
	  
	  public LinkView( NodeViewInterface nodeViewInterface, NodeViewInterface nodeViewInterface2 ) 
	  	{
	  	  super(  "connection6.obj", false ) ;
	  	// super(  "line", true ) ;
	  	 

	  	 this.a = nodeViewInterface ;
	  	 this.b = nodeViewInterface2 ;
	  	 
	  	 Transform3D transform = new Transform3D() ;
	  	 
	  	 getTransformGroup().getTransform( transform ) ;
	
	  	 
	  	 
		    Transform3D linkTransform = new Transform3D() ; getTransformGroup().getTransform( linkTransform ) ;		    
		    Vector3d	linkDimensions = new Vector3d()   ; linkTransform.getScale( linkDimensions ) ; 
		    
		    
		    linkTransform.setScale( .2 ) ;
		    
		    
	  	 
	//  	 radius = .2f ;
	  //	 transform.setScale( .2 ) ;
	  	 
	  	 
	  	 
//	  	 super( new String[]{ "/Users/guidocalvano/Tree/Data/Projecten/EEGVisualisation/internal2.obj", "/Users/guidocalvano/Tree/Data/Projecten/EEGVisualisation/external2.obj" } ) ;

	  	 reposition() ;
	  	 
	//	 stickToA =  new LinkToNodeBehavior( this, nodeViewInterface,  nodeViewInterface2 ) ;
	//	 stickToB =  new LinkToNodeBehavior( this, nodeViewInterface2 ) ;
		 
	  	} 
	

	  
	  public LinkView( BranchGroup parent, Shape3DViewManagerInterface manager, NodeViewInterface nodeViewInterface, NodeViewInterface nodeViewInterface2 ) 
	  	{
	  	  super( parent, manager, "connection6.obj", false ) ;
	  	// super(  "line", true ) ;
	  	 

	  	 this.a = nodeViewInterface ;
	  	 this.b = nodeViewInterface2 ;
	  	 

	  	} 
	
	  
	  
	  public LinkView( ModelInterface model, NodeViewInterface view,
			   NodeViewInterface view2 )
		  {
		   this( view, view2 ) ;
		   
		   this.model = model ;
		   
		   model.addModelChangeListener( this ) ;
		   
		   // handleModelChange( model ) ;
		   
		   red = ( (Constant) model.getViewProperties().propertyObject(  "red" ) ).getValue() ;
		    green = ( (Constant) model.getViewProperties().propertyObject(  "green" ) ).getValue() ;
		    blue = ( (Constant) model.getViewProperties().propertyObject(  "blue" ) ).getValue() ;


		    cylinderRadius = ( (Constant) model.getViewProperties().propertyObject(  "thickness" ) ).getValue() ;
		    transparency 	= ( (Constant) model.getViewProperties().propertyObject(  "transparency" ) ).getValue() ;
 		  }


	  public LinkView( BranchGroup parent, Shape3DViewManagerInterface manager, ModelInterface model, NodeViewInterface view,
				   NodeViewInterface view2 )
			  {
			   this( parent, manager, view, view2 ) ;
			   
			   this.model = model ;
			   
			   model.addModelChangeListener( this ) ;
			   
			   // handleModelChange( model ) ;
			   
			   red = ( (Constant) model.getViewProperties().propertyObject(  "red" ) ).getValue() ;
			    green = ( (Constant) model.getViewProperties().propertyObject(  "green" ) ).getValue() ;
			    blue = ( (Constant) model.getViewProperties().propertyObject(  "blue" ) ).getValue() ;


			    cylinderRadius = ( (Constant) model.getViewProperties().propertyObject(  "thickness" ) ).getValue() ;
			    transparency 	= ( (Constant) model.getViewProperties().propertyObject(  "transparency" ) ).getValue() ;
	 		  }


	  public void initShape()
	  	{
	  	 a.initShape() ;
	  	 b.initShape() ;
	  
	  	 super.initShape() ;
	  	 
	  	 Transform3D transform = new Transform3D() ;
	  	 
	  	 getTransformGroup().getTransform( transform ) ;
	
	  	 
	  	 
		    Transform3D linkTransform = new Transform3D() ; getTransformGroup().getTransform( linkTransform ) ;		    
		    Vector3d	linkDimensions = new Vector3d()   ; linkTransform.getScale( linkDimensions ) ; 
		    
		    
		    linkTransform.setScale( .2 ) ;
		    
		    
	  	 
	//  	 radius = .2f ;
	  //	 transform.setScale( .2 ) ;
	  	 
	  	 
	  	 
//	  	 super( new String[]{ "/Users/guidocalvano/Tree/Data/Projecten/EEGVisualisation/internal2.obj", "/Users/guidocalvano/Tree/Data/Projecten/EEGVisualisation/external2.obj" } ) ;

	  	 reposition() ;
	  	 
	//	 stickToA =  new LinkToNodeBehavior( this, nodeViewInterface,  nodeViewInterface2 ) ;
	//	 stickToB =  new LinkToNodeBehavior( this, nodeViewInterface2 ) ;

	  	 
	  	}



	 public void reposition() 
		   {
		    Transform3D aTransform = new Transform3D() ; a.getTransformGroup().getTransform( aTransform ) ;
		    Transform3D bTransform = new Transform3D() ; b.getTransformGroup().getTransform( bTransform ) ;
		   
		    
		    Transform3D linkTransform = new Transform3D() ; getTransformGroup().getTransform( linkTransform ) ;		    
		    Vector3d	linkDimensions = new Vector3d()   ; linkTransform.getScale( linkDimensions ) ; 
		    
		    Vector3f	aLocation  = new Vector3f()	; aTransform.get( aLocation ) ;
		    Vector3f	bLocation  = new Vector3f()	; bTransform.get( bLocation ) ;
		   
		    
		    if( aLocation.equals( bLocation ) ) return ;
		    
		    Vector3f	aToB = new Vector3f() ; aToB.sub( bLocation, aLocation ) ;
		    double		lengthAToB = aToB.length() ;
		    
		    Vector3f	normalizedAToB = new Vector3f( aToB ) ; 
		    
		    
		    normalizedAToB.scale( (float) ( 1.0 / lengthAToB ) ) ;
		    
		   
		    double		radiusA = aTransform.getScale() ;
		    double 		radiusB = bTransform.getScale() ;
		    
		    double		radiusLink = linkDimensions.x ;
		    
		    
		    double		intersectionAngleLinkAtA = Math.asin( radiusLink / radiusA ) ;
		    double		intersectionAngleLinkAtB = Math.asin( radiusLink / radiusB ) ;

		    
		    double		distanceLinkAFromA = radiusA * Math.cos( intersectionAngleLinkAtA ) ;
		    double		distanceLinkBFromB = radiusB * Math.cos( intersectionAngleLinkAtB ) ;
		    
		    
		    
		    double		linkAOnAToB 		= distanceLinkAFromA ;
		    double		linkBOnAToB 		= lengthAToB - distanceLinkBFromB ;
		    
	
		    Vector3f fromAToLinkA = new Vector3f( normalizedAToB ) ;
		    
   		 			fromAToLinkA.scale( (float) linkAOnAToB ) ; 		    
		    
		    Vector3f	linkNearALocation = new Vector3f() ; 	
		    					 
		    			linkNearALocation.add( aLocation, fromAToLinkA	) ; 
		    
		    			
		    Vector3f fromAToLinkB = new Vector3f( normalizedAToB ) ;
		    
		    		 fromAToLinkB.scale( (float) linkBOnAToB ) ; 
		    
		    Vector3f	linkNearBLocation = new Vector3f() ;
		    
		    			linkNearBLocation.add( aLocation, fromAToLinkB ) ; 
		    
		    aTransform.get( linkNearALocation ) ;
		    bTransform.get( linkNearBLocation ) ;
		    
		    Vector3f	centerLink						= new Vector3f() ;
		    			centerLink.sub( linkNearBLocation, linkNearALocation ) ;
		    			centerLink.scale( .5f ) ;
		    			
		    			centerLink.add( linkNearALocation ) ;
		    			
		    
		    
		    double linkLength = linkBOnAToB - linkAOnAToB ;
		 //   double linkLength = linkBOnAToB - linkAOnAToB ;
	//	    System.out.println( "old link length" + linkDimensions.y ) ;
		    
		//    System.out.println( "new link length" + linkLength ) ;
		    
		  //  System.exit( 0 ) ;
		    linkDimensions.x = radiusLink ;
		    linkDimensions.y = lengthAToB ;
		    linkDimensions.z = radiusLink ;

		    
		    Transform3D repositioned = new Transform3D() ;
		    

		 

		    Matrix3f orientation = pointAt( normalizedAToB, linkDimensions ) ;// normalizedAToB ) ;// new Vector3f( 0.0f, 0.0f, 1.0f ) ) ; //
		    
		    
		
		    
		    
		    repositioned.set(  orientation ) ;

		    repositioned.setTranslation( centerLink ) ;

		//    repositioned.setScale( 5.0 ) ;
		    
	    // repositioned.setScale( linkDimensions ) ;
    
		    

		    
		    

		    getTransformGroup().setTransform( repositioned ) ;

		   }
	  
	  /*
	  Matrix3f pointAt( Vector3f qAxis ) 
		   {

		    Vector3f pAxis = new Vector3f() ;

		    pAxis.x = (float) 0.0 ;
		    
		    pAxis.y = (float) Math.sqrt(   ( ( qAxis.z * qAxis.z ) / ( qAxis.z * qAxis.z + qAxis.y * qAxis.y ) ) ) ;
  
		    pAxis.z = - ( ( qAxis.y * pAxis.y ) / qAxis.z ) ;
		   

		    
		    
		    Vector3f rAxis = new Vector3f() ;	
		    
		    System.out.println( "pAxis " + pAxis ) ;
		    
		    double scalePxToQx = qAxis.x / pAxis.x ;
		    
		    System.out.println( "scalePxToQx ; " + scalePxToQx ) ;

		    
		    double scaleRyToRz = ( scalePxToQx * pAxis.y - qAxis.y ) / 
		    					 ( scalePxToQx * pAxis.z - qAxis.z ) ;

		    System.out.println( "scaleRyToRz ; " + scaleRyToRz ) ;
    

		    
		    double scalePzToQz = qAxis.z / pAxis.z ;		
		    
		    System.out.println( "scalePzToQz ; " + scalePzToQz ) ;
		    
		    double scaleRyToRx = ( scalePzToQz * pAxis.y - qAxis.y ) /
		    					 ( scalePzToQz * pAxis.x - qAxis.x ) ;
		    
		    System.out.println( "scaleRyToRx ; " + scaleRyToRx ) ;

		    
		    rAxis.y = (float) Math.sqrt(  1.0 / ( scaleRyToRx * scaleRyToRx + scaleRyToRz * scaleRyToRz + 1.0 ) ) ;
		    
		    rAxis.x = (float) ( rAxis.y * scaleRyToRx ) ;
		    
		    rAxis.z = (float) ( rAxis.y * scaleRyToRz ) ;
		    
		    
		   
		  
		    
		    
		    
		    Matrix3f rotation = new Matrix3f() ;
   
		    rotation.setColumn( 0, pAxis ) ;
		    rotation.setColumn( 1, qAxis ) ;
		    rotation.setColumn( 2, rAxis ) ;
		    
		    System.out.println( "" + pAxis ) ;
		    System.out.println( "" + qAxis ) ;
		    System.out.println( "" + rAxis ) ;
		    System.exit(  0);
		    
		    return rotation ;
		   }
	  */
	  
	  Matrix3f pointAt( Vector3f qAxisVector, Vector3d linkDimensions ) 
		   {
		    int third ;
		    
		    float[] qAxis = new float[ 3 ] ;
		    float[] pAxis = new float[ 3 ] ;
		    float[] rAxis = new float[ 3 ] ;
		    
		    qAxisVector.get( qAxis ) ;
		    
		    
		    float guessPAxisSecond = 1.0f ;
		    float guessPAxisThird  = 2.0f ;
		    
		    float guessRAxisThird  = 3.0f ;
		    
		    float scaleQFirstToPFirst ;
		    
		    float scaleRSecondToRThird ;
		    
		    boolean  allAxesFound = false ;
		    
		    while( true )
		    {
		    for( int first = 0 ; first < qAxis.length ; first++ )
			for( int second = 0 ; second < qAxis.length ; second++ )
		    	 {
		    	  if( first == second ) continue ;
		    	  
		    	  if( qAxis[ first ] == 0.0 ) continue ;
		    	  

		    	  third = ( 3 - first ) - second ;

		    	  pAxis[ second ] = guessPAxisSecond ;
		    	  pAxis[ third ] = guessPAxisThird ;
		    	  
		    	  
		    	  pAxis[ first ] = ( - pAxis[ second ] * qAxis[ second ] - pAxis[ third ] * qAxis[ third ] ) / qAxis[ first ] ;
		    	  
		    	  
		    	  scaleQFirstToPFirst = pAxis[ first ] / qAxis[ first ] ;
		    	  
		    	  scaleRSecondToRThird = -( scaleQFirstToPFirst * qAxis[ second ] - pAxis[ second ] ) / ( scaleQFirstToPFirst * qAxis[ third ] - pAxis[ third ]  ) ;
		    	  
		    	  if( scaleRSecondToRThird == Float.NaN ) continue ;
		    	  if( scaleRSecondToRThird == Float.POSITIVE_INFINITY ) continue ;
		    	  if( scaleRSecondToRThird == Float.NEGATIVE_INFINITY ) continue ;
		    	  
		    	  rAxis[ second ] =  guessRAxisThird ;

		    	  rAxis[ third ] = scaleRSecondToRThird * rAxis[ second ] ;
		    	  
		    	  rAxis[ first ] = ( - rAxis[ second ] * qAxis[ second ] - rAxis[ third ] * qAxis[ third ] ) / qAxis[ first ] ;
		    	  
		    	  allAxesFound = true ;
		    	 }
		   
		     if( allAxesFound ) break ;
		     
			     guessPAxisSecond = (float) Math.random() ;
			     guessPAxisThird  = (float) Math.random() ;
			    
			     guessRAxisThird  = (float) Math.random() ;
		    
		    }  
		    Vector3f pAxisVector = new Vector3f( pAxis ) ;
		    qAxisVector.set( qAxis ) ;
		    Vector3f rAxisVector = new Vector3f( rAxis ) ;

		    pAxisVector.normalize() ;
		    qAxisVector.normalize() ;
		    rAxisVector.normalize() ;
		    
		    pAxisVector.scale(  (float) linkDimensions.x ) ;
		    qAxisVector.scale(  (float) linkDimensions.y ) ;
		    rAxisVector.scale(  (float) linkDimensions.z ) ;
		    
		    
		    Matrix3f rotation = new Matrix3f() ;

		    rotation.setColumn( 0, pAxisVector ) ;
		    rotation.setColumn( 1, qAxisVector ) ;
		    rotation.setColumn( 2, rAxisVector ) ;
		    
		    return rotation ;

		   }
	 
	  void randomThicknessChange( Alpha progress )
		   {
		    float newThickness = .2f + (float) Math.random() * .8f ;
		    
		    new ToCylinderRadiusBehavior( this, newThickness, progress ) ;
		   
		   }

	 void callibrateViewOnModel()
		  {
		  
		  Alpha time = new Alpha( 1, Alpha.INCREASING_ENABLE
			        , 0, 0, 2000, 1000, 200, 2000, 1000,
			        200);
	        time.setStartTime(System.currentTimeMillis());
	        

	 
	         
	         new ToRedBehavior( this, model.property( "red" ),  time ) ;
	         new ToGreenBehavior( this, model.property( "green" ),  time ) ;
	         new ToBlueBehavior( this, model.property( "blue" ),  time ) ;
	         
	         new ToCylinderRadiusBehavior( this, model.property( "thickness" ),  time ) ;
	         new ToTransparencyBehavior( this, model.property( "transparency" ),  time ) ;

		  }

	 public void handleModelChange( ModelInterface model )
		  {
		   callibrateViewOnModel() ;
		  }





	 public void toModel( float percentageIncrease)
		  {
		   if( initShapeComplete ) 
		  
				reposition() ;
		   
		   double endRed = model.property( "red" )  ;
		   double endGreen = model.property(  "green" ) ;
		   double endBlue = model.property( "blue" )  ;
		   
		   double endCylinderRadius =  model.property(  "thickness" ) ;
		   double endTransparency 	=  model.property(  "transparency" ) ;

		    isVisible = model.property(  "visible" );

		   
		    red 	= endRed  * percentageIncrease + red * ( 1.0 - percentageIncrease ) ;
		    green = endGreen  * percentageIncrease + green * ( 1.0 - percentageIncrease ) ;
		    blue 	= endBlue  * percentageIncrease + blue * ( 1.0 - percentageIncrease ) ;

		    cylinderRadius 	= endCylinderRadius  * percentageIncrease + cylinderRadius * ( 1.0 - percentageIncrease ) ;
		    transparency 		= endTransparency  * percentageIncrease + transparency * ( 1.0 - percentageIncrease ) ;


		    
		   setRed( 		red 		) ;
		   setGreen( 	green 		) ;
		   setBlue(		blue		) ;
		   
		   setCylinderRadius( cylinderRadius ) ;
		   setTransparency(		transparency ) ;

		   setVisible(		isVisible ) ;

		  }
	  
	 }


