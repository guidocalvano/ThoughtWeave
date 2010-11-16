package Graphics3D.Animation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class AnimationManager implements AnimationManagerInterface
	 {
	  Map viewPropertyToAnimation ;
	 
	 
	  public AnimationManager()
	  	{
	  	 viewPropertyToAnimation = new HashMap() ; 
	  	}
	  
	  
	  public void animateProperty( String viewProperty, InterruptableBehavior animation )
		  {
		   if( viewPropertyToAnimation.containsKey( viewProperty ) )
				{
				 InterruptableBehavior stopMe = (InterruptableBehavior) viewPropertyToAnimation.get( viewProperty ) ;
				 
				 stopMe.stop() ;
				}
		  
		   viewPropertyToAnimation.put( viewProperty, animation ) ;
		  }
	  
	  
	  public void animateProperty( List viewPropertySet, InterruptableBehavior animation )
		  {
		   Iterator it = viewPropertySet.iterator() ;
		   
		   while( it.hasNext() )
				{
				 String key = (String) it.next() ;
				
				 InterruptableBehavior stopMe = (InterruptableBehavior) viewPropertyToAnimation.get( key ) ;
				 
				 stopMe.stop() ;
				 viewPropertyToAnimation.put( key, animation ) ;

				}
		   
		  }


	 public void stop( String property )
		  {
			 InterruptableBehavior stopMe = (InterruptableBehavior) viewPropertyToAnimation.get( property ) ;
			 stopMe.stop();
		  }

	 }
