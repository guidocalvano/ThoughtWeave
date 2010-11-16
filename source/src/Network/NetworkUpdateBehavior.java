package Network;

import java.util.Enumeration;

import javax.media.j3d.*; 
import javax.vecmath.Point3d;

public class NetworkUpdateBehavior extends Behavior
	 {
	  Alpha progress ;
	  
	  float previousValue ;
	  
	  WakeupCondition wakeupCondition ;
	  
	  NetworkControlInterface network ;
	  
	  BranchGroup destroyGroup ;

	  boolean		isActive ;
	  
	  boolean 		mustAnimate ;
	  
	  
	  NetworkUpdateBehavior( NetworkControlInterface network )
	  	{
	  	 this.network = network ;
	  	 
	  	  progress = new Alpha( 1, Alpha.INCREASING_ENABLE
			        , 0, 0, 2000, 1000, 200, 2000, 1000,
			        200);
	  	  
	  	  previousValue = 0f ;
	  	  
	  	  mustAnimate = false ;
	  	  
	  	  init() ;
	  	}

	  public void setMustAnimate( boolean must ) { mustAnimate = must ; }
	  
	  public boolean isActive()
		   {
		    return isActive ;
		   }
	  
	  public void resetTimer()
	  	{
		   progress.setStartTime( System.currentTimeMillis());

	  	}
	 
	  void init()
		   {
		    
	 	 	
	 	 	 destroyGroup = new BranchGroup() ;
	 	 	 
	 	 	 destroyGroup.setCapability( BranchGroup.ALLOW_DETACH ) ;
	  	 	 destroyGroup.setCapability( BranchGroup.ALLOW_CHILDREN_WRITE ) ;
	 	 	
	  	 	destroyGroup.setCapability(Group.ALLOW_CHILDREN_READ);
	  	 	destroyGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
	  	 	 
	  	 	 
	 	 	 
	 	 	 destroyGroup.addChild(  this  ) ;


	 	     BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
			 setSchedulingBounds(bounds);
			 
			 isActive = false ;
			 
		   }
	  
	 @Override
	 public void initialize()
		  {
		   resetTimer() ;
		  wakeupCondition = new WakeupOnElapsedFrames( 1 ) ;
		   previousValue = 0f ;
		   
		   isActive = true ;

		   System.out.println( "RESETING ANIMATION" ) ;
		   this.wakeupOn( wakeupCondition ) ;

		  }

	 @Override
	 public void processStimulus( Enumeration arg0)
		  {
		   float value = progress.value() ;
		  
		   float increase = value - previousValue ;
		   
		   float totalDistance = 1f - previousValue ;
		   
		   float percentageIncrease = increase / totalDistance ;
		   
		   if( mustAnimate && percentageIncrease != 0 && previousValue != 1f )
				network.adaptViewToModel( percentageIncrease ) ;
		   else
				{
				 network.adaptViewToModel( 1.0f  ) ;
				 endBehavior() ;
				 return ;
				}
		   
		   previousValue = value ;
		   
		   if( !progress.finished() )
				   this.wakeupOn( wakeupCondition ) ;
		   else
				{
				 endBehavior() ;
				}
		  }

	  void endBehavior()
		   {
			destroyGroup.detach() ;
			
			isActive = false ;		
			
			   System.out.println( "ANIMATION COMPLETE" ) ;

		   }
	 
	  BranchGroup branchGroup() { return destroyGroup ; }
	 }
