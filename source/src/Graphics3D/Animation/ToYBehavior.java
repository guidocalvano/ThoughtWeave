package Graphics3D.Animation;
import Graphics3D.* ;


import javax.media.j3d.Alpha;


public class ToYBehavior extends ViewPropertyToValueBehavior implements
		  InterruptableBehavior
	 {

	 public ToYBehavior( Shape3DViewInterface shape, double value, Alpha time )
		  {
		  super( shape, value, time );
		  // TODO Auto-generated constructor stub
		  }
		 

	 @Override
	 double getProperty()
		  {
		  // TODO Auto-generated method stub
		  return shape.getY() ;
		  }

	 @Override
	 void setProperty( double value)
		  {
		   shape.setY( value ) ;
		  }

	 }
