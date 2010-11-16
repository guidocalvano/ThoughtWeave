package Graphics3D;

import java.util.Enumeration;

import javax.media.j3d.*;

import javax.vecmath.* ;


public class OverlayBehavior extends Behavior
	 {

	  TransformGroup trackedObject ;
	  
	  TransformGroup theOverlay ;
	  
	  Shape3DViewInterface overlayObject ;
	  
	  WakeupCondition relativeMovementCameraTrackedObject ;
	  
	  BranchGroup destroyGroup ;
	  
	  Locale	  locale ;
	  
	  public OverlayBehavior( TransformGroup trackedObject,
			   Shape3DViewInterface overlayObject, CameraControl camera )
		  {
		   this.overlayObject = overlayObject ;
		   
		   this.trackedObject 	= trackedObject ;
		   this.theOverlay 	= camera.getTransformGroup() ;
		   
		   this.locale = camera.getSimpleUniverse().getLocale() ;    
		   
		   //relativeMovementCameraTrackedObject = new WakeupOnTransformChange( trackedObject 		) ;
		   relativeMovementCameraTrackedObject = new WakeupOr( new WakeupCriterion[] {
		   			  
		   			  new WakeupOnTransformChange( trackedObject 	),
		   			  new WakeupOnTransformChange( theOverlay 		) } ) ;  
		   
		   setCapability( Node.ALLOW_LOCAL_TO_VWORLD_READ ) ;
 		   
		 	 destroyGroup = new BranchGroup() ;
	 	 	 
	 	 	 destroyGroup.setCapability( BranchGroup.ALLOW_DETACH ) ;
	  	 	 destroyGroup.setCapability( BranchGroup.ALLOW_CHILDREN_WRITE ) ;
	 	 	
	  	 	destroyGroup.setCapability(Group.ALLOW_CHILDREN_READ);
	  	 	destroyGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
	  	 	 
	  	 	 
	 	 	 
	 	 	 destroyGroup.addChild(  this  ) ;

	 	 	 overlayObject.getIsVisibleGroup().addChild( destroyGroup ) ;

	 	 	 
	 	 	 

		 	 initBounds() ;

	 	 	}
	 	 
	 	 void initBounds()
	 		  {
	 		   BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1000000.0);
			   setSchedulingBounds(bounds);
	 		  }
	 void projectObjectToOverlay()
		  {
		   // THE GOAL TRANSFORM, as yet undefined
		   Transform3D nextOverlayObjectTransform = new Transform3D() ;

		   
		   //  POSITION 
		   
		   Transform3D trackedTransform = new Transform3D() ;		   
		   trackedObject.getTransform( trackedTransform) ;
		   
		   Vector3f position = new Vector3f() ; 
		   
		   trackedTransform.get( position ) ;
		   

		   // ORIENTATION
			
		   // camera orientation
		   Transform3D cameraTransform = new Transform3D() ; theOverlay.getTransform( cameraTransform ) ;
	
		   Matrix3f		cameraOrientation = new Matrix3f() ; cameraTransform.get( cameraOrientation ) ;
		   
		   Transform3D cameraOrientationTransform = new Transform3D() ; cameraOrientationTransform.set( cameraOrientation ) ;
		   
		   // model rotation
		   
		   Transform3D demonstrationTransform = new Transform3D() ;  getLocalToVworld( demonstrationTransform ) ;
			
		   
		   Matrix3f		demonstrationOrientation = new Matrix3f() ; demonstrationTransform.get( demonstrationOrientation ) ;
		   
		   Transform3D preventDemonstrationTransform = new Transform3D() ; preventDemonstrationTransform.set( demonstrationOrientation ) ;
		   
		   preventDemonstrationTransform.invert( demonstrationTransform ) ;
		   
		   
		 

		   // SCALE

	
		   
		   Vector3f transformTrackedPositionVector = new Vector3f() ;
		   
		   trackedTransform.get( transformTrackedPositionVector ) ;

		   Point3f transformTrackedPosition = new Point3f( transformTrackedPositionVector ) ;

		   

		   
		   demonstrationTransform.transform( transformTrackedPosition ) ;


		   theOverlay.getTransform( cameraTransform ) ;
		   Transform3D invertedCameraTransform = new Transform3D() ; invertedCameraTransform.invert( cameraTransform ) ;
		   
		   invertedCameraTransform.transform( transformTrackedPosition ) ;
		   

		   
		//   Transform3D scaleTransform = new Transform3D() ; scaleTransform.setScale( transformTrackedPosition.z ) ;
		   
		 //  if( transformTrackedPosition.z < -.3 )
		   //if( transformTrackedPosition.z < -1f )
		//   else
		//		nextOverlayObjectTransform.setScale( .0001 ) ;

		  
				  // assignment
				   // FINAL ASSIGN
			  
		   if( transformTrackedPosition.z > -1f ) 
				nextOverlayObjectTransform = new Transform3D() ; 
		   else
				{
				 nextOverlayObjectTransform.mul( preventDemonstrationTransform ) ;
				   
				 nextOverlayObjectTransform.mul( cameraOrientationTransform ) ;
		   
				 if( transformTrackedPosition.z < -1f ) 

					  nextOverlayObjectTransform.setScale( -transformTrackedPosition.z / 80.0 ) ;
				}

		   

		   
		   
		   nextOverlayObjectTransform.setTranslation( position ) ;
		   
		   
		   overlayObject.getTransformGroup().setTransform( nextOverlayObjectTransform ) ;
	   

		   
		  }

	 void backup()
		   {
		    Transform3D toTracked 			= new Transform3D() ;
		    Transform3D invertedToTracked 	= new Transform3D() ;

		    
		    Transform3D toOverlay 			= new Transform3D() ;
		    Transform3D invertedToOverlay 	= new Transform3D() ;

		    
		    		    
		    trackedObject.getTransform( toTracked ) ;
		    invertedToOverlay.invert( toOverlay ) ;

		    
		    theOverlay.getTransform( toOverlay ) ;
		    invertedToTracked.invert( toOverlay ) ;
		    	   	    
		    
		    Transform3D overlayTransformer = new Transform3D() ;
		    Transform3D invertedOverlayTransformer = new Transform3D() ;

		    
		    overlayTransformer.mulInverse(  toOverlay, toTracked ) ;
		    
		
		    
		    Matrix3d overlayRotation = new Matrix3d() ;
		    
		    toOverlay.get( overlayRotation ) ;
		    
		    
		    
		    
		    Transform3D correct = new Transform3D() ;
		  
		    
		    

		    
		    Matrix3d toOverlayOrientation = new Matrix3d() ; toOverlay.get( toOverlayOrientation ) ;
	//	    Matrix3d toTrackedOrientation = new Matrix3d() ; toTrackedOrientation( toTrackedOrientation ) ;

		    Vector3d overlayPosition = new Vector3d() ; toOverlay.get( overlayPosition ) ;
		    Vector3d trackedPosition = new Vector3d() ; toTracked.get( trackedPosition ) ;
		    
		    Vector3d overlayDepthNormal = new Vector3d() ; toOverlayOrientation.getColumn( 2, overlayDepthNormal ) ;
		  //  overlayDepthNormal.normalize() ;
		    

		    double distanceOverlay = overlayDepthNormal.dot( overlayPosition ) ;
		    double distanceTracked = overlayDepthNormal.dot( trackedPosition ) ;
		    
		    double distanceInFront = toTracked.getScale() ;
		    
		    double totalDistance = ( distanceTracked - distanceOverlay ) - distanceInFront ;
		    
		  //  overlayObject.getTransformGroup().getTransform( correct ) ;

		    //overlayRotation.mul( totalDistance ) ;
		    
	
		    
		    correct.setRotation( overlayRotation ) ;
		    
		    correct.setTranslation( trackedPosition ) ;

		    Transform3D forward = new Transform3D() ;
  
		    forward.setTranslation( new Vector3d( 0.0, 0.0, distanceInFront ) ) ;
		    
		    correct.mul( forward ) ;
		    
		    
		    
		    Transform3D sideways = new Transform3D() ;
		    
		    sideways.setTranslation( new Vector3d( distanceInFront, 0.0, 0.0) ) ;
		    
		    correct.mul( sideways ) ;
		    
		    correct.setScale(  new Vector3d( -totalDistance / 30.0, -totalDistance/ 30.0, 1.0) ) ;
		    
		    
		    
		    
		    
		    overlayObject.getTransformGroup().setTransform( correct ) ;
		/*    
		    Vector3f trackedPosition = new Vector3f() ;
		    
		    toTracked.get( trackedPosition ) ;
	
		    
		    
		    Point3f  trackedProjectedInOverlay = new Point3f( trackedPosition ) ; 
		    
		    toOverlay.transform( trackedProjectedInOverlay ) ;
		    
		    trackedProjectedInOverlay.scale( 1.0f / trackedProjectedInOverlay.z ) ;
		    		    
		    
		    
		    Transform3D trackedProjectedInOverlayBackToTracked = new Transform3D() ;
		    
		    trackedProjectedInOverlayBackToTracked.setTranslation( new Vector3f( trackedProjectedInOverlay ) ) ;
	
	
		    trackedProjectedInOverlayBackToTracked.mulInverse( toOverlay ) ;
		
		    trackedProjectedInOverlayBackToTracked.mul( toTracked ) ;
		    
		    overlayObject.getTransformGroup().setTransform( trackedProjectedInOverlayBackToTracked ) ;
		    */
		 /*   
		    Transform3D t = new Transform3D() ;
		    
		    t.setTranslation( new Vector3d( 3.0, 3.0, 1.0 ) ) ;
		    overlayObject.getTransformGroup().setTransform( t ) ;*/
		   }
	 
	 @Override
	 public void initialize()
		  {
		   
		  wakeupOn( relativeMovementCameraTrackedObject ) ;

		  }

	 @Override
	 public void processStimulus( Enumeration arg0)
		  {
		  
		  
		   projectObjectToOverlay() ;
		  
		   wakeupOn( relativeMovementCameraTrackedObject ) ;
		   

		  }

	 }
