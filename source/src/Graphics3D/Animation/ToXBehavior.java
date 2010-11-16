package Graphics3D.Animation;
import Graphics3D.* ;


import javax.media.j3d.Alpha;


public class ToXBehavior extends ViewPropertyToValueBehavior implements
		  InterruptableBehavior
	 {

	 public ToXBehavior( Shape3DViewInterface shape3dView, double value, Alpha time )
		  {
		   super( shape3dView, value, time ) ;
		  }

	 @Override
	 double getProperty()
		  {
		  // TODO Auto-generated method stub
		  return shape.getX() ;
		  }

	 @Override
	 void setProperty( double value)
		  {
		   shape.setX( value ) ;
		  }

	 }
