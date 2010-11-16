package Main;
import java.util.Enumeration;

import javax.media.j3d.*;
import javax.vecmath.Point3d;

import Graphics3D.Shape3DViewInterface;


public class BrainModelBehavior extends Behavior
	 {
	  WakeupCriterion wakeup ;
	 
	  Shape3DViewInterface brainModel ;
	  
	  boolean isVisible ;
	  
	  boolean mustUpdate ;
	  
	  BrainModelBehavior( Shape3DViewInterface brainModel, boolean isVisible )
	  	{
	  	 this.brainModel = brainModel ;
	  	 
	  	 this.isVisible = isVisible ;
	  	 
	  	 mustUpdate = true ;
	  	 
	  	  BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
			 setSchedulingBounds(bounds);
			 
	  	}
	 
	 @Override
	 public void initialize()
		  {
		    wakeup = new WakeupOnElapsedFrames( 1 ) ;

		    
		    //update() ;
		    
		    wakeupOn( wakeup ) ;
		  }
	 
	 public void setVisible( boolean isVisible )
		  {
		   if( isVisible == this.isVisible ) return ;
		   
		   this.isVisible = isVisible ;
		   mustUpdate = true ;
		  }

	 private void update()
		  {
		   
		  
		   if( !mustUpdate ) return ;
		   
		   mustUpdate = false ;
		   
		   double isVisibleDouble = isVisible ? 1.0 : 0.0 ;
		   
		   System.out.println( "is vis doub" + isVisibleDouble ) ;
		   brainModel.setVisible( isVisibleDouble ) ;
		   
//		   System.exit( 0 ) ;

		  }

	 @Override
	 public void processStimulus( Enumeration arg0)
		  {
		   update() ;
		   
		   wakeupOn( wakeup ) ;
		  }

	 }
