package Graphics3D.Animation;
import Graphics3D.* ;


import javax.media.j3d.Alpha;


public class ToSizeBehavior extends ViewPropertyToValueBehavior implements
		  InterruptableBehavior
	 {

	 public ToSizeBehavior( Shape3DViewInterface shape, double value, Alpha time )
		  {
		  super( shape, value, time );
		  // TODO Auto-generated constructor stub
		  }


	 @Override
	 double getProperty()
		  {
		  // TODO Auto-generated method stub
		  return shape.getScale() ;
		  }

	 @Override
	 void setProperty( double value)
		  {
		   shape.setScale( value ) ;
		  }

	 }
