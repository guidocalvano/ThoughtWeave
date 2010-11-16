package Graphics3D.Animation;
import Graphics3D.* ;


import javax.media.j3d.Alpha;


public class ToGreenBehavior extends ViewPropertyToValueBehavior
	 {

	 public ToGreenBehavior( Shape3DViewInterface shape, double value, Alpha time )
		  {
		  super( shape, value, time );
		  // TODO Auto-generated constructor stub
		  }


	 @Override
	 double getProperty()
		  {
		  // TODO Auto-generated method stub
		  return shape.getGreen() ;
		  }

	 @Override
	 void setProperty( double value)
		  {
		   shape.setGreen( value ) ;
		  }

	 }
