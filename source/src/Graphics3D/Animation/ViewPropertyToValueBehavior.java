package Graphics3D.Animation;

import Graphics3D.* ;
import java.util.Enumeration;

import javax.media.j3d.*	;
import javax.vecmath.*		;


public abstract class ViewPropertyToValueBehavior extends Behavior implements InterruptableBehavior
	 {
	  Shape3DViewInterface 	shape 	;
	  
	  double 				value 	;

	  Alpha 				time 	;
	  
	  
	  double				startValue 	;
	  
	  double				endValue	;
	  
	  
	  WakeupOnElapsedFrames nextFrame ; 

	  BranchGroup destroyGroup ;

	  
	  ViewPropertyToValueBehavior( Shape3DViewInterface shape, double value, Alpha time )
	  	{
	  	 this.shape = shape ;
	  	 endValue   = value ;
	  	 this.time  = time 	;
	  	 
	  	 
	  	 startValue = getProperty() ;
	  	 
	  	 initAnimation( shape.getBranchGroup() ) ;
	  	}
	  
	  void initAnimation( BranchGroup parentBranch )
		   {
		    
		   
		 	 nextFrame = new WakeupOnElapsedFrames( 0 ) ; 

		 	 	
	 	 	 destroyGroup = new BranchGroup() ;
	 	 	 
	 	 	 destroyGroup.setCapability( BranchGroup.ALLOW_DETACH ) ;
	  	 	 destroyGroup.setCapability( BranchGroup.ALLOW_CHILDREN_WRITE ) ;
	 	 	
	  	 	destroyGroup.setCapability(Group.ALLOW_CHILDREN_READ);
	  	 	destroyGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
	  	 	 
	  	 	 
	 	 	 
	 	 	 destroyGroup.addChild(  this  ) ;

	 	 	 parentBranch.addChild( destroyGroup ) ;

	 	     BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
			 setSchedulingBounds(bounds);
			 
			 shape.getAnimationManager().animateProperty( this.getClass().getName(), this ) ;
		   }
	 
	  abstract double getProperty() ;
	  
	  abstract void setProperty( double value ) ;
	  
	  
		 
		 @Override
		 public void initialize()
			  {
			  // TODO Auto-generated method stub
			   wakeupOn( nextFrame ) ;
			  }

		 @Override
		 public void processStimulus( Enumeration arg0)
			  {
			  // TODO Auto-generated method stub
			  
			   nextProperty() ;
			  
			  
			   if( time.finished() )
					 destroyGroup.detach() ;
			   else
					 wakeupOn( nextFrame ) ;
			  }
	 	
	  
	  void nextProperty()
		   {
		    double alpha = time.value() ;
		   
		    setProperty( endValue * alpha + startValue * ( 1.0 - alpha ) ) ;
		   
		   }

	 
	  public void stop()
		  {
		   destroyGroup.detach() ;
		  }

	 }
