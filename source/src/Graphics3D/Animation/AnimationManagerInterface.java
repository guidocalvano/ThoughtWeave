package Graphics3D.Animation;


import java.util.List;


public interface AnimationManagerInterface
	 {
	  void animateProperty( String viewProperty, InterruptableBehavior animation )  ;
	  void animateProperty( List viewPropertySet, InterruptableBehavior animation ) ;
	 void stop( String property);

	 }
