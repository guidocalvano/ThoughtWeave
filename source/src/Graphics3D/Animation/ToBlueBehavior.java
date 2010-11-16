package Graphics3D.Animation;

import Graphics3D.* ;

import javax.media.j3d.Alpha;


public class ToBlueBehavior extends ViewPropertyToValueBehavior implements
		  InterruptableBehavior
	 {

	 public ToBlueBehavior( Shape3DViewInterface shape, double value, Alpha time )
		  {
		  super( shape, value, time );
		  // TODO Auto-generated constructor stub
		  }

	 @Override
	 double getProperty()
		  {
		  // TODO Auto-generated method stub
		  return shape.getBlue() ;
		  }

	 @Override
	 void setProperty( double value)
		  {
		   shape.setBlue( value ) ;
		  }

	 }